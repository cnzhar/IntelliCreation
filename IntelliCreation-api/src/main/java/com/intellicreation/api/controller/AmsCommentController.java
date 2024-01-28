package com.intellicreation.api.controller;


import com.intellicreation.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @Autowired
//    private AmsCommentService amsCommentService;
//
//    @GetMapping("/commentList")
//    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
//        return amsCommentService.commentList(articleId, pageNum, pageSize);
//    }
//
//    @PostMapping("/addComment")
//    public ResponseResult addComment(@RequestBody AddCommentDTO addCommentDTO){
//        // todo 直接这样copybean合适吗
//        AmsCommentDO comment = BeanCopyUtils.copyBean(addCommentDTO, AmsCommentDO.class);
//        return amsCommentService.addComment(comment);
//    }

}
