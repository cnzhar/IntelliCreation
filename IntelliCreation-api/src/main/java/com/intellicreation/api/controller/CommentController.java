package com.intellicreation.api.controller;


import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.api.facade.CommentFacade;
import com.intellicreation.article.domain.dto.AddCommentDTO;
import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @SystemLog(businessName = "获取帖子评论", isSaveRequestArg = false,
            isSaveResponseData = false, operationType = SystemConstants.USER_TYPE)
    @GetMapping("/commentList")
    public ResponseResult commentList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      Long articleId) {
        PageVO pageVO = commentFacade.commentList(pageNum, pageSize, articleId);
        return ResponseResult.okResult(pageVO);
    }

    @PostMapping("/addComment")
    public ResponseResult addComment(@RequestBody AddCommentDTO addCommentDTO) throws Exception {
        commentFacade.addComment(addCommentDTO);
        return ResponseResult.okResult();
    }

}
