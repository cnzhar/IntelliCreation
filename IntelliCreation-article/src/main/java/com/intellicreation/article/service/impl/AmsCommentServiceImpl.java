package com.intellicreation.article.service.impl;

import com.intellicreation.article.ai.ClassifyUtil;
import com.intellicreation.article.domain.dto.AddCommentDTO;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.article.mapper.AmsCommentMapper;
import com.intellicreation.article.service.AmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.SensitiveUtil;
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
    public void addComment(AddCommentDTO addCommentDTO) throws Exception {
        if (SensitiveUtil.isContainsIllegalWord(addCommentDTO.getContent())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        if (ClassifyUtil.isSensitive(addCommentDTO.getContent())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        AmsCommentDO comment = BeanCopyUtils.copyBean(addCommentDTO, AmsCommentDO.class);
        save(comment);
    }
}
