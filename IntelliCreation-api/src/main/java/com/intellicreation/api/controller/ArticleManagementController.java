package com.intellicreation.api.controller;


import com.intellicreation.api.facade.ArticleManagementFacade;
import com.intellicreation.article.domain.dto.*;
import com.intellicreation.article.domain.vo.ArticleDetailVO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.domain.vo.TagDetailVO;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@Api(tags = "文章管理接口")
@RestController
@RequestMapping("/articleManagement")
public class ArticleManagementController {

    @Autowired
    private ArticleManagementFacade articleManagementFacade;

    // *==================================================*
    // *--------------------- Article --------------------*
    // *==================================================*

    @DeleteMapping("/deleteArticle/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id) {
        articleManagementFacade.deleteArticle(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      ArticleQueryParamDTO articleQueryParamDTO) {
        PageVO pageVO = articleManagementFacade.queryArticleList(pageNum, pageSize, articleQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getArticleDetail/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        ArticleDetailVO articleDetailVO = articleManagementFacade.getArticleDetail(id);
        return ResponseResult.okResult(articleDetailVO);
    }

    // *==================================================*
    // *-------------------- Category --------------------*
    // *==================================================*

    @PostMapping("/addCategory")
    public ResponseResult addCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        articleManagementFacade.addCategory(addCategoryDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseResult deleteCategory(@PathVariable Long id) {
        articleManagementFacade.deleteCategory(id);
        return ResponseResult.okResult();
    }

    @PutMapping("updateCategoryInfo")
    public ResponseResult updateCategoryInfo(@RequestBody UpdateCategoryDTO updateCategoryDTO) {
        articleManagementFacade.updateCategoryInfo(updateCategoryDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/categoryList")
    public ResponseResult categoryList(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       CategoryQueryParamDTO categoryQueryParamDTO) {
        PageVO pageVO = articleManagementFacade.queryCategoryList(pageNum, pageSize, categoryQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getCategoryDetail/{id}")
    public ResponseResult getCategoryDetail(@PathVariable("id") Long id) {
        CategoryDetailVO categoryDetailVO = articleManagementFacade.getCategoryDetail(id);
        return ResponseResult.okResult(categoryDetailVO);
    }

    // *==================================================*
    // *----------------------- Tag ----------------------*
    // *==================================================*

    @PostMapping("/addTag")
    public ResponseResult addTag(@Valid @RequestBody AddTagDTO addTagDTO) {
        articleManagementFacade.addTag(addTagDTO);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/deleteTag/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        articleManagementFacade.deleteTag(id);
        return ResponseResult.okResult();
    }

    @PutMapping("updateTagInfo")
    public ResponseResult updateTagInfo(@RequestBody UpdateTagDTO updateTagDTO) {
        articleManagementFacade.updateTagInfo(updateTagDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/tagList")
    public ResponseResult tagList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  TagQueryParamDTO tagQueryParamDTO) {
        PageVO pageVO = articleManagementFacade.queryTagList(pageNum, pageSize, tagQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/getTagDetail/{id}")
    public ResponseResult getTagDetail(@PathVariable("id") Long id) {
        TagDetailVO tagDetailVO = articleManagementFacade.getTagDetail(id);
        return ResponseResult.okResult(tagDetailVO);
    }
}
