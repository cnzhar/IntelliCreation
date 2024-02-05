package com.intellicreation.article.service.impl;


import com.intellicreation.article.service.AmsCategoryService;
import com.intellicreation.article.mapper.AmsCategoryMapper;
import com.intellicreation.article.domain.entity.AmsCategoryDO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
