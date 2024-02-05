package com.intellicreation.api.controller;


import com.intellicreation.api.facade.CommentFacade;
import com.intellicreation.article.domain.dto.AddCommentDTO;
import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.article.service.AmsCommentService;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
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
public class CommentController {

    @Autowired
    private CommentFacade commentFacade;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        PageVO pageVO = commentFacade.commentList(articleId, pageNum, pageSize);
        return ResponseResult.okResult(pageVO);
    }

    @PostMapping("/addComment")
    public ResponseResult addComment(@RequestBody AddCommentDTO addCommentDTO){
        // todo 直接这样copybean合适吗
        AmsCommentDO comment = BeanCopyUtils.copyBean(addCommentDTO, AmsCommentDO.class);
        commentFacade.addComment(comment);
        return ResponseResult.okResult();
    }

}
