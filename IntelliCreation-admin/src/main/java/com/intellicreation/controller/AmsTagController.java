package com.intellicreation.controller;


import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.dto.TagQueryParamDTO;
import com.intellicreation.domain.vo.PageVO;
import com.intellicreation.service.AmsTagService;
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
 * @since 2024-01-24
 */
@RestController
@RequestMapping("/tag")
public class AmsTagController {

    @Autowired
    private AmsTagService amsTagService;

    @GetMapping("/list")
    public ResponseResult<PageVO> list(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        return amsTagService.pageTagList(pageNum, pageSize, tagQueryParamDTO);
    }

}
