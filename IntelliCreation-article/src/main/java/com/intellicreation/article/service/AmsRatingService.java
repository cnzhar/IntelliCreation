package com.intellicreation.article.service;

import com.intellicreation.article.domain.dto.PostRatingDTO;
import com.intellicreation.article.domain.entity.AmsRatingDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.common.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-02-25
 */
public interface AmsRatingService extends IService<AmsRatingDO> {

    /**
     * 用户发表评价
     *
     * @param postRatingDTO
     */
    void postRating(PostRatingDTO postRatingDTO);
}
