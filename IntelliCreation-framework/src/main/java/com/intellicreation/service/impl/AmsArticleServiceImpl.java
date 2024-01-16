package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.model.AmsArticleDO;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.AmsCategoryDO;
import com.intellicreation.domain.vo.AmsHotArticleVO;
import com.intellicreation.domain.vo.ArticleDetailVO;
import com.intellicreation.domain.vo.ArticleListVO;
import com.intellicreation.domain.vo.PageVO;
import com.intellicreation.mapper.AmsArticleMapper;
import com.intellicreation.service.AmsArticleService;
import com.intellicreation.service.AmsCategoryService;
import com.intellicreation.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@Service
public class AmsArticleServiceImpl extends ServiceImpl<AmsArticleMapper, AmsArticleDO> implements AmsArticleService {

    @Autowired
    private AmsCategoryService amsCategoryService;

    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章，并封装成ResponseResult
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章（不是草稿）
        lambdaQueryWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 按照浏览量进行排序AmsArticle
        lambdaQueryWrapper.orderByDesc(AmsArticleDO::getViewCount);
        // 最多只查询十条
        Page<AmsArticleDO> page = new Page(1, 10);
        page(page, lambdaQueryWrapper);
        List<AmsArticleDO> articleList = page.getRecords();
        // bean拷贝
        List<AmsHotArticleVO> amsHotArticleVOList = BeanCopyUtils.copyBeanList(articleList, AmsHotArticleVO.class);
        return ResponseResult.okResult(amsHotArticleVOList);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有categoryId，只留下该分类下的
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, AmsArticleDO::getCategory1Id, categoryId);
        // 状态是已发布的
        lambdaQueryWrapper.eq(AmsArticleDO::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 分页查询
        Page<AmsArticleDO> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        // 封装查询结果
        List<ArticleListVO> articleListVOList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVO.class);
        // 根据categoryId，查询的对应categoryName
        articleListVOList.stream()
                .map(articleListVO -> articleListVO.setCategory1Name(amsCategoryService.getById(articleListVO.getCategory1Id()).getName()))
                .collect(Collectors.toList());
        PageVO pageVO = new PageVO(articleListVOList, page.getTotal());
        return ResponseResult.okResult(pageVO);
    }

    // Todo 只有已发布的能被所有人看到，草稿等只能自己看
    @Override
    public ResponseResult getArticleDetail(Long id) {
        // 根据id查询文章
        AmsArticleDO amsArticleDO = getById(id);
        // 从redis中获取viewCount
//        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
//        amsArticleDO.setViewCount(viewCount.longValue());
        // 转换成VO
        ArticleDetailVO articleDetailVO = BeanCopyUtils.copyBean(amsArticleDO, ArticleDetailVO.class);
        // 根据分类id查询分类名
        Long category1Id = articleDetailVO.getCategory1Id();
        AmsCategoryDO amsCategoryDO = amsCategoryService.getById(category1Id);
        if (amsCategoryDO != null) {
            articleDetailVO.setCategory1Name(amsCategoryDO.getName());
        }
        // 封装响应返回
        return ResponseResult.okResult(articleDetailVO);
    }
}
