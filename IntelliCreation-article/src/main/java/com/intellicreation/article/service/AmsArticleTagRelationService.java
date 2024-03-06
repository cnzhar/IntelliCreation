package com.intellicreation.article.service;

import com.intellicreation.article.domain.entity.AmsArticleTagRelationDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-03-05
 */
public interface AmsArticleTagRelationService extends IService<AmsArticleTagRelationDO> {

    /**
     * 保存文章标签关联关系
     *
     * @param articleId
     * @param tag
     */
    void saveArticleTag(Long articleId, List<Long> tag);

    /**
     * 根据文章id获取标签
     *
     * @param id
     * @return
     */
    List<Long> getTagByArticleId(Long id);
}
