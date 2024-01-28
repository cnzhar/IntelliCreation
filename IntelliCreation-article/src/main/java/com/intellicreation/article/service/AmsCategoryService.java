package com.intellicreation.article.service;

import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 分类列表
     * @return
     */
    ResponseResult getCategoryList();
}
