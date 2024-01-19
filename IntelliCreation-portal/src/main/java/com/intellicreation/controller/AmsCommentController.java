package com.intellicreation.controller;


import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.service.AmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
@RestController
@RequestMapping("/comment")
public class AmsCommentController {

    @Autowired
    private AmsCommentService amsCommentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return amsCommentService.commentList(articleId, pageNum, pageSize);
    }

}
