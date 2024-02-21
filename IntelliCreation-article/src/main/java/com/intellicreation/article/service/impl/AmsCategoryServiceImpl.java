package com.intellicreation.article.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.article.domain.dto.AddCategoryDTO;
import com.intellicreation.article.domain.dto.CategoryQueryParamDTO;
import com.intellicreation.article.domain.dto.UpdateCategoryDTO;
import com.intellicreation.article.domain.vo.CategoryDetailVO;
import com.intellicreation.article.service.AmsCategoryService;
import com.intellicreation.article.mapper.AmsCategoryMapper;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-12
 */
@Service
public class AmsCategoryServiceImpl extends ServiceImpl<AmsCategoryMapper, AmsCategoryDO> implements AmsCategoryService {

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        AmsCategoryDO amsCategoryDO = BeanCopyUtils.copyBean(addCategoryDTO, AmsCategoryDO.class);
        save(amsCategoryDO);
    }

    @Override
    public void updateCategoryInfo(UpdateCategoryDTO updateCategoryDTO) {
        AmsCategoryDO amsCategoryDO = BeanCopyUtils.copyBean(updateCategoryDTO, AmsCategoryDO.class);
        updateById(amsCategoryDO);
    }

    @Override
    public PageVO queryCategoryList(Integer pageNum, Integer pageSize, CategoryQueryParamDTO categoryQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<AmsCategoryDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsCategoryDO::getId, AmsCategoryDO::getName, AmsCategoryDO::getDescription)
                .like(!ObjectUtils.isEmpty(categoryQueryParamDTO.getId()), AmsCategoryDO::getId, categoryQueryParamDTO.getId())
                .like(StringUtils.hasText(categoryQueryParamDTO.getName()), AmsCategoryDO::getName, categoryQueryParamDTO.getName())
                .like(StringUtils.hasText(categoryQueryParamDTO.getDescription()), AmsCategoryDO::getDescription, categoryQueryParamDTO.getDescription());
        Page<AmsCategoryDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public CategoryDetailVO getCategoryDetail(Long id) {
        AmsCategoryDO amsCategoryDO = getById(id);
        return BeanCopyUtils.copyBean(amsCategoryDO, CategoryDetailVO.class);
    }
}
