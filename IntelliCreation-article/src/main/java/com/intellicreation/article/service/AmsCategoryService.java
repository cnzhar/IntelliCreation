package com.intellicreation.article.service;

import com.intellicreation.article.domain.dto.AddCategoryDTO;
import com.intellicreation.article.domain.dto.CategoryQueryParamDTO;
import com.intellicreation.article.domain.dto.UpdateCategoryDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.domain.vo.CategoryVO;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.common.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-12
 */
public interface AmsCategoryService extends IService<AmsCategoryDO> {

    /**
     * 新增分类
     *
     * @param addCategoryDTO
     */
    void addCategory(AddCategoryDTO addCategoryDTO);

    /**
     * 编辑分类信息
     *
     * @param updateCategoryDTO
     */
    void updateCategoryInfo(UpdateCategoryDTO updateCategoryDTO);

    /**
     * 根据条件查询分类列表
     *
     * @param pageNum
     * @param pageSize
     * @param categoryQueryParamDTO
     * @return
     */
    PageVO queryCategoryList(Integer pageNum, Integer pageSize, CategoryQueryParamDTO categoryQueryParamDTO);

    /**
     * 根据id获取分类详情
     *
     * @param id
     * @return
     */
    CategoryDetailVO getCategoryDetail(Long id);
}
