package com.intellicreation.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.UpdateArticleInfoDTO;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.vo.UpdateArticleInfoVO;
import com.intellicreation.common.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
public interface AmsArticleService extends IService<AmsArticleDO> {

    /**
     * 查询热门文章
     *
     * @return
     */
    List<HotArticleVO> hotArticleList();

    /**
     * 更新浏览量
     *
     * @param id
     * @return
     */
    void updateViewCount(Long id);

    /**
     * 更新文章点赞量
     * @param articleId
     */
    void updateLikeCount(Long articleId);

    /**
     * 减少文章点赞量
     * @param articleId
     */
    void decreaseLikeCount(Long articleId);

    /**
     * 用户新增文章
     *
     * @param addArticleDTO
     * @return 返回新增成功的文章id
     */
    Long addArticle(AddArticleDTO addArticleDTO) throws Exception;

    /**
     * 用户编辑文章
     *
     * @param updateArticleInfoDTO
     */
    void updateArticle(UpdateArticleInfoDTO updateArticleInfoDTO);

    /**
     * 后台查询文章列表
     *
     * @param pageNum
     * @param pageSize
     * @param articleQueryParamDTO
     * @return
     */
    PageVO queryArticleList(Integer pageNum, Integer pageSize, ArticleQueryParamDTO articleQueryParamDTO);

    /**
     * 后台获取文章详情
     *
     * @param id
     * @return
     */
    ArticleDetailVO getArticleDetail(Long id);

    /**
     * 判断传入用户是否是文章作者
     *
     * @param articleId
     * @return
     */
    boolean isAuthor(Long memberId, Long articleId);
}
