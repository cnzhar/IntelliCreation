package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.dto.UpdateArticleInfoDTO;
import com.intellicreation.article.domain.vo.ArticleViewVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.domain.vo.UpdateArticleInfoVO;
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
     * 查询文章列表
     * 仅返回“已发布”状态的文章
     *
     * @param pageNum
     * @param pageSize
     * @param category1Id
     * @param category2Id
     * @return
     */
    PageVO articleList(Integer pageNum, Integer pageSize, Long category1Id, Long category2Id);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    ArticleViewVO getArticleDetail(Long id);

    /**
     * 查找可选的一级分类
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    PageVO availableCategory1(Integer pageNum, Integer pageSize, String name);

    /**
     * 查找可选的二级分类
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @param parent
     * @return
     */
    PageVO availableCategory2(Integer pageNum, Integer pageSize, String name, Long parent);

    /**
     * 查找可选标签
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    PageVO availableTag(Integer pageNum, Integer pageSize, String name);

    /**
     * 新增文章
     *
     * @param addArticleDTO
     */
    void addArticle(AddArticleDTO addArticleDTO);

    /**
     * 更新文章之前，先获取文章信息
     *
     * @param id
     * @return
     */
    UpdateArticleInfoVO updateArticleInfo(Long id);

    /**
     * 用户编辑文章
     *
     * @param updateArticleInfoDTO
     */
    void updateArticle(UpdateArticleInfoDTO updateArticleInfoDTO);

    /**
     * 用户发表评价
     *
     * @param postRatingDTO
     */
    void postRating(PostRatingDTO postRatingDTO);

    /**
     * 用户获取评价列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO ratingList(Integer pageNum, Integer pageSize, Long articleId);
}
