package com.intellicreation.api.controller;


import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.service.AmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class AmsCategoryController {

    @Autowired
    private AmsCategoryService amsCategoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return amsCategoryService.getCategoryList();
    }

}
