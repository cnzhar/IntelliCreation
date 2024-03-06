package com.intellicreation.api.controller;


import com.intellicreation.api.facade.CategoryFacade;
import com.intellicreation.article.domain.vo.CategoryItemVO;
import com.intellicreation.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author za
 * @since 2024-01-12
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        List<CategoryItemVO> categoryItemVOList = categoryFacade.getCategoryList();
        return ResponseResult.okResult(categoryItemVOList);
    }

}
