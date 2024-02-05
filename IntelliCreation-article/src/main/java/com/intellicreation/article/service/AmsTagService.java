package com.intellicreation.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.entity.AmsTagDO;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.common.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-24
 */
public interface AmsTagService extends IService<AmsTagDO> {

    /**
     * 根据条件查询标签
     *
     * @param pageNum
     * @param pageSize
     * @param tagQueryParamDTO
     * @return
     */
    PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO);
}
