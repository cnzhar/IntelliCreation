package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.ArticleManagementFacade;
import com.intellicreation.article.domain.dto.*;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.domain.vo.TagDetailVO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.article.service.AmsCategoryService;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author za
 */
@Component
public class ArticleManagementFacadeImpl implements ArticleManagementFacade {

    @Autowired
    private AmsArticleService amsArticleService;

    @Autowired
    private AmsCategoryService amsCategoryService;

    @Autowired
    private AmsTagService amsTagService;

    @Override
    public void deleteArticle(Long id) {
        amsArticleService.removeById(id);
    }

    @Override
    public PageVO queryArticleList(Integer pageNum, Integer pageSize, ArticleQueryParamDTO articleQueryParamDTO) {
        return amsArticleService.queryArticleList(pageNum, pageSize, articleQueryParamDTO);
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        return amsArticleService.getArticleDetail(id);
    }

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        amsCategoryService.addCategory(addCategoryDTO);
    }

    @Override
    public void deleteCategory(Long id) {
        amsCategoryService.removeById(id);
    }

    @Override
    public void updateCategoryInfo(UpdateCategoryDTO updateCategoryDTO) {
        amsCategoryService.updateCategoryInfo(updateCategoryDTO);
    }

    @Override
    public PageVO queryCategoryList(Integer pageNum, Integer pageSize, CategoryQueryParamDTO categoryQueryParamDTO) {
        return amsCategoryService.queryCategoryList(pageNum, pageSize, categoryQueryParamDTO);
    }

    @Override
    public CategoryDetailVO getCategoryDetail(Long id) {
        return amsCategoryService.getCategoryDetail(id);
    }

    @Override
    public void addTag(AddTagDTO addTagDTO) {
        amsTagService.addTag(addTagDTO);
    }

    @Override
    public void deleteTag(Long id) {
        amsTagService.removeById(id);
    }

    @Override
    public void updateTagInfo(UpdateTagDTO updateTagDTO) {
        amsTagService.updateTagInfo(updateTagDTO);
    }

    @Override
    public PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        return amsTagService.queryTagList(pageNum, pageSize, tagQueryParamDTO);
    }

    @Override
    public TagDetailVO getTagDetail(Long id) {
        return amsTagService.getTagDetail(id);
    }
}
