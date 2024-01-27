package com.intellicreation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.dto.TagQueryParamDTO;
import com.intellicreation.domain.model.AmsTagDO;
import com.intellicreation.domain.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-24
 */
public interface AmsTagService extends IService<AmsTagDO> {

    ResponseResult<PageVO> pageTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO);
}
