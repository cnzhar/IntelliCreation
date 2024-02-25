package com.intellicreation.api.controller;


import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.api.facade.ArticleFacade;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.vo.ArticleTextVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleFacade articleFacade;

    @ApiOperation(value = "获取热门文章")
    @SystemLog(businessName = "获取热门文章", operationType = SystemConstants.USER_TYPE)
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        List<HotArticleVO> hotArticleVOList = articleFacade.hotArticleList();
        return ResponseResult.okResult(hotArticleVOList);
    }

    @ApiOperation(value = "查询文章列表", notes = "获取文章列表")
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        PageVO pageVO = articleFacade.articleList(pageNum, pageSize, categoryId);
        return ResponseResult.okResult(pageVO);
    }

    // todo 考虑要不要加上防止刷浏览量, 接口防刷，注解
    // todo 要不要整合到查文章详情的接口
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        articleFacade.updateViewCount(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        ArticleTextVO articleTextVO = articleFacade.getArticleDetail(id);
        return ResponseResult.okResult(articleTextVO);
    }

    @PostMapping("/addArticle")
    public ResponseResult addArticle(@RequestBody AddArticleDTO addArticleDTO) {
        articleFacade.addArticle(addArticleDTO);
        return ResponseResult.okResult();
    }

    @PostMapping("postRating")
    public ResponseResult postRating(@Valid @RequestBody PostRatingDTO postRatingDTO) {
        articleFacade.postRating(postRatingDTO);
        return ResponseResult.okResult();
    }
}
