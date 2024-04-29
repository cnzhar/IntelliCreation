package com.intellicreation.api.controller;


import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.api.facade.ArticleFacade;
import com.intellicreation.article.domain.dto.AddArticleDTO;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.dto.UpdateArticleInfoDTO;
import com.intellicreation.article.domain.vo.ArticleViewVO;
import com.intellicreation.article.domain.vo.HotArticleVO;
import com.intellicreation.article.domain.vo.RatingViewVO;
import com.intellicreation.article.domain.vo.UpdateArticleInfoVO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AmsArticleService amsArticleService;

    @ApiOperation(value = "获取热门文章")
    @SystemLog(businessName = "获取热门文章", operationType = SystemConstants.USER_TYPE)
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        List<HotArticleVO> hotArticleVOList = articleFacade.hotArticleList();
        return ResponseResult.okResult(hotArticleVOList);
    }

    @ApiOperation(value = "查询文章列表", notes = "获取文章列表")
    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      Long category1Id, Long category2Id) {
        PageVO pageVO = articleFacade.articleList(pageNum, pageSize, category1Id, category2Id);
        return ResponseResult.okResult(pageVO);
    }

    // todo 考虑要不要加上防止刷浏览量, 接口防刷，注解
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        ArticleViewVO articleViewVO = articleFacade.getArticleDetail(id);
        return ResponseResult.okResult(articleViewVO);
    }

    @GetMapping("/availableCategory1")
    public ResponseResult availableCategory1(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             String name) {
        PageVO pageVO = articleFacade.availableCategory1(pageNum, pageSize, name);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/availableCategory2")
    public ResponseResult availableCategory2(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             String name,
                                             Long parent) {
        PageVO pageVO = articleFacade.availableCategory2(pageNum, pageSize, name, parent);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/availableTag")
    public ResponseResult availableTag(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       String name) {
        PageVO pageVO = articleFacade.availableTag(pageNum, pageSize, name);
        return ResponseResult.okResult(pageVO);
    }

    @PostMapping("/addArticle")
    public ResponseResult addArticle(@RequestBody AddArticleDTO addArticleDTO) throws Exception {
        articleFacade.addArticle(addArticleDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/updateArticleInfo/{id}")
    public ResponseResult updateArticleInfo(@PathVariable("id") Long articleId) {
        // 判断是否为文章的作者
        Long memberId = SecurityUtils.getMemberId();
        if (!amsArticleService.isAuthor(memberId, articleId)) {
            throw new SystemException(AppHttpCodeEnums.NO_OPERATOR_AUTH);
        }
        UpdateArticleInfoVO updateArticleInfoVO = articleFacade.updateArticleInfo(articleId);
        return ResponseResult.okResult(updateArticleInfoVO);
    }

    @PostMapping("/updateArticle")
    public ResponseResult updateArticle(@RequestBody UpdateArticleInfoDTO updateArticleInfoDTO) {
        // 判断是否为文章的作者
        Long memberId = SecurityUtils.getMemberId();
        Long articleId = updateArticleInfoDTO.getId();
        if (!amsArticleService.isAuthor(memberId, articleId)) {
            throw new SystemException(AppHttpCodeEnums.NO_OPERATOR_AUTH);
        }
        articleFacade.updateArticle(updateArticleInfoDTO);
        return ResponseResult.okResult();
    }

    @PostMapping("/likeArticle/{id}")
    public ResponseResult likeArticle(@PathVariable("id") Long articleId) {
        articleFacade.likeArticle(articleId);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/unlikeArticle/{id}")
    public ResponseResult unlikeArticle(@PathVariable("id") Long articleId) {
        articleFacade.unlikeArticle(articleId);
        return ResponseResult.okResult();
    }

    @PostMapping("postRating")
    public ResponseResult postRating(@Valid @RequestBody PostRatingDTO postRatingDTO) throws Exception {
        articleFacade.postRating(postRatingDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/ratingList")
    public ResponseResult ratingList(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                     Long articleId) {
        PageVO pageVO = articleFacade.ratingList(pageNum, pageSize, articleId);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/ratingDetail/{id}")
    public ResponseResult ratingDetail(@PathVariable("id") Long id) {
        RatingViewVO ratingViewVO = articleFacade.ratingDetail(id);
        return ResponseResult.okResult(ratingViewVO);
    }

}
