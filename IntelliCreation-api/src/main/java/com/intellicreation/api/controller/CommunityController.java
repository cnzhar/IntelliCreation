package com.intellicreation.api.controller;


import com.intellicreation.api.facade.CommunityFacade;
import com.intellicreation.article.domain.dto.CreatePostDTO;
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
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityFacade communityFacade;

    @PostMapping("/createPost")
    public ResponseResult createPost(@Valid @RequestBody CreatePostDTO createPostDTO) throws Exception {
        communityFacade.createPost(createPostDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/postList")
    public ResponseResult postList(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize) {
        PageVO pageVO = communityFacade.postList(pageNum, pageSize);
        return ResponseResult.okResult(pageVO);
    }

    @GetMapping("/postDetail/{id}")
    public ResponseResult postDetail(@PathVariable Long id) {
        PostViewVO postViewVO = communityFacade.postDetail(id);
        return ResponseResult.okResult(postViewVO);
    }
}
