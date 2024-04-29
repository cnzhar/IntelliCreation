package com.intellicreation.api.controller;


import com.intellicreation.api.facade.CommunityFacade;
import com.intellicreation.api.facade.CommunityManagementFacade;
import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.article.domain.dto.PostQueryParamDTO;
import com.intellicreation.article.domain.vo.ArticleQueryParamDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.domain.vo.PostViewVO;
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
@Api(tags = "社区接口")
@RestController
@RequestMapping("/communityManagement")
public class CommunityManagementController {

    @Autowired
    private CommunityManagementFacade communityManagementFacade;

    @GetMapping("/queryPostList")
    public ResponseResult queryPostList(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        PostQueryParamDTO postQueryParamDTO) {
        PageVO pageVO = communityManagementFacade.postList(pageNum, pageSize, postQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseResult deletePost(@PathVariable Long id) {
        communityManagementFacade.deletePost(id);
        return ResponseResult.okResult();
    }
}
