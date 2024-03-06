package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.api.facade.CategoryFacade;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.intellicreation.article.domain.vo.CategoryItemVO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.article.service.AmsCategoryService;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author za
 */
@Component
public class CategoryFacadeImpl implements CategoryFacade {

    @Autowired
    private AmsArticleService amsArticleService;

    @Autowired
    private AmsCategoryService amsCategoryService;

    @Override
    public List<CategoryItemVO> getCategoryList() {
        // 查询文章表  状态为已发布的文章
        LambdaQueryWrapper<AmsArticleDO> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper
                .select(AmsArticleDO::getCategory1Id, AmsArticleDO::getCategory2Id)
                .eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        List<AmsArticleDO> articleList = amsArticleService.list(articleWrapper);
        // 获取文章的分类1id，并且去重
        Set<Long> category1Ids = articleList.stream()
                .map(AmsArticleDO::getCategory1Id)
                .collect(Collectors.toSet());
        Set<Long> category2Ids = articleList.stream()
                .map(AmsArticleDO::getCategory2Id)
                .collect(Collectors.toSet());
        // 查询分类表，只留下正常状态的分类（未被禁用）
        // todo select太多？
        List<AmsCategoryDO> category1List = amsCategoryService.listByIds(category1Ids);
        category1List = category1List.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        List<CategoryItemVO> category1Items = BeanCopyUtils.copyBeanList(category1List, CategoryItemVO.class);
        List<AmsCategoryDO> category2List = amsCategoryService.listByIds(category2Ids);
        category2List = category2List.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        List<CategoryItemVO> category2Items = BeanCopyUtils.copyBeanList(category2List, CategoryItemVO.class);
        // 封装VO
        return buildCategoryTree(category1Items, category2Items);
    }

    /**
     * 构建tree
     *
     * @param category1Items
     * @param category2Items
     * @return
     */
    private List<CategoryItemVO> buildCategoryTree(List<CategoryItemVO> category1Items, List<CategoryItemVO> category2Items) {
        return category1Items.stream()
                .map(category -> category.setChildren(getChildren(category, category2Items)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的子category集合
     *
     * @param categoryItemVO
     * @param category2Items
     * @return
     */
    private List<CategoryItemVO> getChildren(CategoryItemVO categoryItemVO, List<CategoryItemVO> category2Items) {
        return category2Items.stream()
                .filter(category -> category.getParent().equals(categoryItemVO.getId()))
                .map(category -> category.setChildren(getChildren(category, category2Items)))
                .collect(Collectors.toList());
    }
}
