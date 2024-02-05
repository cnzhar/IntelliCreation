package com.intellicreation.article.service.impl;

import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.article.mapper.AmsCommentMapper;
import com.intellicreation.article.service.AmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
@Service
public class AmsCommentServiceImpl extends ServiceImpl<AmsCommentMapper, AmsCommentDO> implements AmsCommentService {

    @Override
    public void addComment(AmsCommentDO comment) {
        // 评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnums.CONTENT_NOT_NULL);
        }
        save(comment);
    }
}
