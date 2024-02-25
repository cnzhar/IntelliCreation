package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.*;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.domain.vo.TagDetailVO;
import com.intellicreation.common.vo.PageVO;

/**
 * @author za
 */
public interface ArticleManagementFacade {

    /**
     * 删除文章
     *
     * @param id
     */
    void deleteArticle(Long id);

    /**
     * 查询文章列表
     *
     * @param pageNum
     * @param pageSize
     * @param articleQueryParamDTO
     * @return
     */
    PageVO queryArticleList(Integer pageNum, Integer pageSize, ArticleQueryParamDTO articleQueryParamDTO);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    ArticleDetailVO getArticleDetail(Long id);

    /**
     * 新增分类
     *
     * @param addCategoryDTO
     */
    void addCategory(AddCategoryDTO addCategoryDTO);

    /**
     * 删除分类
     *
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * 编辑分类信息
     *
     * @param updateCategoryDTO
     */
    void updateCategoryInfo(UpdateCategoryDTO updateCategoryDTO);

    /**
     * 根据条件查询分类列表
     *
     * @param pageNum
     * @param pageSize
     * @param categoryQueryParamDTO
     * @return
     */
    PageVO queryCategoryList(Integer pageNum, Integer pageSize, CategoryQueryParamDTO categoryQueryParamDTO);

    /**
     * 根据id获取分类详情
     *
     * @param id
     * @return
     */
    CategoryDetailVO getCategoryDetail(Long id);

    /**
     * 新增标签
     *
     * @param addTagDTO
     */
    void addTag(AddTagDTO addTagDTO);

    /**
     * 删除标签
     *
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 编辑标签
     *
     * @param updateTagDTO
     */
    void updateTagInfo(UpdateTagDTO updateTagDTO);

    /**
     * 根据条件查询文章标签
     *
     * @param pageNum
     * @param pageSize
     * @param tagQueryParamDTO
     * @return
     */
    PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO);

    /**
     * 根据id获取标签详情
     *
     * @param id
     * @return
     */
    TagDetailVO getTagDetail(Long id);
}
