package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.common.vo.PageVO;

import java.util.List;

/**
 * @author za
 */
public interface ArticleFacade {

    /**
     * 查询热门文章
     *
     * @return
     */
    List<HotArticleVO> hotArticleList();

    /**
     * 直接查询文章列表，或根据分类查询文章列表
     * 注意：
     * 1.仅返回有“已发布”状态的文章
     * 2.必须是未被删除的文章
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    PageVO articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 更新浏览量
     *
     * @param id
     * @return
     */
    void updateViewCount(Long id);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    ArticleDetailVO getArticleDetail(Long id);

    /**
     * 新增文章
     *
     * @param addArticleDTO
     * @param memberId
     */
    void addArticle(AddArticleDTO addArticleDTO, Long memberId);
}
