package com.intellicreation.controller;


import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.service.AmsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class AmsArticleController {

    @Autowired
    private AmsArticleService amsArticleService;

    @ApiOperation(value = "查询热门文章", notes = "获取热门文章列表")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        return amsArticleService.hotArticleList();
    }

    @ApiOperation(value = "查询文章列表", notes = "获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "categoryId", value = "分类id"),
    })
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return amsArticleService.articleList(pageNum, pageSize, categoryId);
    }

    // todo 考虑要不要加上防止刷浏览量, 接口防刷，注解
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return amsArticleService.updateViewCount(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return amsArticleService.getArticleDetail(id);
    }

}
