package com.intellicreation.service.impl;

import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.AmsCommentDO;
import com.intellicreation.mapper.AmsCommentMapper;
import com.intellicreation.service.AmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
@Service
public class AmsCommentServiceImpl extends ServiceImpl<AmsCommentMapper, AmsCommentDO> implements AmsCommentService {

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return null;
    }
}
