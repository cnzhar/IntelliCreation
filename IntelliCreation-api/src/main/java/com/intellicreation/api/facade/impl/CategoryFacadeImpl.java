package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.api.facade.CategoryFacade;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.intellicreation.article.domain.vo.CategoryVO;
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
    public List<CategoryVO> getCategoryList() {
        // 查询文章表  状态为已发布的文章
        LambdaQueryWrapper<AmsArticleDO> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        List<AmsArticleDO> articleList = amsArticleService.list(articleWrapper);
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(AmsArticleDO::getCategory1Id)
                .collect(Collectors.toSet());
        // 查询分类表，只留下正常状态的分类（未被禁用）
        List<AmsCategoryDO> categories = amsCategoryService.listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装VO
        List<CategoryVO> categoryVOList = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);
        return categoryVOList;
    }
}
