package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.article.ai.ClassifyUtil;
import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.entity.AmsRatingDO;
import com.intellicreation.article.domain.vo.RatingViewVO;
import com.intellicreation.article.mapper.AmsRatingMapper;
import com.intellicreation.article.service.AmsRatingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.SensitiveUtil;
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
    public void postRating(PostRatingDTO postRatingDTO) throws Exception {
        if (SensitiveUtil.isContainsIllegalWord(postRatingDTO.getText())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        if (ClassifyUtil.isSensitive(postRatingDTO.getText())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        AmsRatingDO amsRatingDO = BeanCopyUtils.copyBean(postRatingDTO, AmsRatingDO.class);
        save(amsRatingDO);
    }

    @Override
    public RatingViewVO ratingDetail(Long id) {
        AmsRatingDO amsRatingDO = getById(id);
        return BeanCopyUtils.copyBean(amsRatingDO, RatingViewVO.class);
    }

    @Override
    public boolean isRated(Long id, Long memberId) {
        LambdaQueryWrapper<AmsRatingDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AmsRatingDO::getArticleId, id)
                .eq(AmsRatingDO::getCreateBy, memberId);
        AmsRatingDO amsRatingDO = getBaseMapper().selectOne(lambdaQueryWrapper);
        // 如果有符合条件的记录，则返回ture
        return amsRatingDO != null;
    }
}
