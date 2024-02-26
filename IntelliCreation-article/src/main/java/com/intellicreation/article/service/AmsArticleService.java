package com.intellicreation.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.domain.entity.AmsArticleDO;
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
     * 用户新增文章
     *
     * @param addArticleDTO
     */
    void addArticle(AddArticleDTO addArticleDTO);

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
}
