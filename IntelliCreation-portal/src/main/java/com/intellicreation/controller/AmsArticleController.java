package com.intellicreation.controller;


import com.intellicreation.domain.ResponseResult;
import com.intellicreation.service.AmsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/article")
public class AmsArticleController {

    @Autowired
    private AmsArticleService amsArticleService;

    /**
     * 查询热门文章，并封装成ResponseResult
     * @return ResponseResult
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        return amsArticleService.hotArticleList();
    }

    /**
     * 文章列表，并封装成ResponseResult
     * @return ResponseResult
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return amsArticleService.articleList(pageNum,pageSize,categoryId);
    }
//
//    @PutMapping("/updateViewCount/{id}")
//    public ResponseResult updateViewCount(@PathVariable("id") Long id){
//        return amsArticleService.updateViewCount(id);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
//        return amsArticleService.getArticleDetail(id);
//    }

}
