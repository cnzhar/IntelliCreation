package com.intellicreation.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.common.ResponseResult;
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
     * 新增文章
     *
     * @param addArticleDTO
     * @param memberId
     */
    void addArticle(AddArticleDTO addArticleDTO, Long memberId);
}
