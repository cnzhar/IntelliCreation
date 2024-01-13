package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.ResponseResult;
import com.intellicreation.domain.model.AmsArticleDO;
import com.intellicreation.domain.model.AmsCategoryDO;
import com.intellicreation.domain.vo.AmsCategoryVO;
import com.intellicreation.mapper.AmsCategoryMapper;
import com.intellicreation.service.AmsArticleService;
import com.intellicreation.service.AmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-12
 */
@Service
public class AmsCategoryServiceImpl extends ServiceImpl<AmsCategoryMapper, AmsCategoryDO> implements AmsCategoryService {

    @Autowired
    private AmsArticleService amsArticleService;

    /**
     * 获取所有分类
     * 注意：
     * 1.仅返回有“已发布”状态的文章的分类
     * 2.必须是正常状态的分类（未被禁用）
     * @return
     */
    @Override
    public ResponseResult getCategoryList() {
        // 查询文章表  状态为已发布的文章
        LambdaQueryWrapper<AmsArticleDO> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        List<AmsArticleDO> articleList = amsArticleService.list(articleWrapper);
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(AmsArticleDO::getCategory1Id)
                .collect(Collectors.toSet());

        // 查询分类表，只留下正常状态的分类（未被禁用）
        List<AmsCategoryDO> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装VO
        List<AmsCategoryVO> amsCategoryVOList = BeanCopyUtils.copyBeanList(categories, AmsCategoryVO.class);

        return ResponseResult.okResult(amsCategoryVOList);
    }
}
