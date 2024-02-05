package com.intellicreation.api.controller;


import com.intellicreation.api.facade.MemberFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.article.service.AmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-24
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private MemberFacade memberFacade;

    @GetMapping("/tagList")
    public ResponseResult tagList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  TagQueryParamDTO tagQueryParamDTO) {
        PageVO pageVO = memberFacade.queryTagList(pageNum, pageSize, tagQueryParamDTO);
        return ResponseResult.okResult(pageVO);
    }

}
