package com.intellicreation.api.facade;

import com.intellicreation.article.domain.vo.CategoryItemVO;

import java.util.List;

/**
 * @author za
 */
public interface CategoryFacade {

    /**
     * 获取所有分类
     * 注意：
     * 1.仅返回有“已发布”状态的文章的分类
     * 2.必须是正常状态的分类（未被禁用）
     *
     * @return
     */
    List<CategoryItemVO> getCategoryList();
}
