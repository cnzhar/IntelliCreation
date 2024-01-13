package com.intellicreation.service;

import com.intellicreation.domain.ResponseResult;
import com.intellicreation.domain.model.AmsCategoryDO;
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

    ResponseResult getCategoryList();
}
