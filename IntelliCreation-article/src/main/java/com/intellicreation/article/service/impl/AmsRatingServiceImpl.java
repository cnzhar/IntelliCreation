package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.entity.AmsRatingDO;
import com.intellicreation.article.mapper.AmsRatingMapper;
import com.intellicreation.article.service.AmsRatingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-02-25
 */
@Service
public class AmsRatingServiceImpl extends ServiceImpl<AmsRatingMapper, AmsRatingDO> implements AmsRatingService {

    @Override
    public void postRating(PostRatingDTO postRatingDTO) {
        AmsRatingDO amsRatingDO = BeanCopyUtils.copyBean(postRatingDTO, AmsRatingDO.class);
        save(amsRatingDO);
    }
}
