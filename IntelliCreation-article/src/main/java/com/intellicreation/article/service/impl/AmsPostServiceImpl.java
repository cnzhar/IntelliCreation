package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.article.ai.ClassifyUtil;
import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.article.domain.dto.PostQueryParamDTO;
import com.intellicreation.article.domain.entity.AmsPostDO;
import com.intellicreation.article.domain.entity.AmsTagDO;
import com.intellicreation.article.mapper.AmsPostMapper;
import com.intellicreation.article.service.AmsPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.SensitiveUtil;
import com.intellicreation.common.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-02-25
 */
@Service
public class AmsPostServiceImpl extends ServiceImpl<AmsPostMapper, AmsPostDO> implements AmsPostService {

    @Override
    public void createPost(CreatePostDTO createPostDTO) throws Exception {
        if (SensitiveUtil.isContainsIllegalWord(createPostDTO.getContent())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        if (ClassifyUtil.isSensitive(createPostDTO.getContent())) {
            throw new SystemException(AppHttpCodeEnums.CONTAIN_ILLEGALWORD);
        }
        AmsPostDO amsPostDO = BeanCopyUtils.copyBean(createPostDTO, AmsPostDO.class);
        save(amsPostDO);
    }

    @Override
    public PageVO postList(Integer pageNum, Integer pageSize) {
        // 查询条件
        LambdaQueryWrapper<AmsPostDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsPostDO::getId, AmsPostDO::getTitle, AmsPostDO::getContent, AmsPostDO::getThumbnail, AmsPostDO::getCreateBy);
        // 分页查询
        Page<AmsPostDO> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public PageVO queryPostList(Integer pageNum, Integer pageSize, PostQueryParamDTO postQueryParamDTO) {
        // 查询条件
        LambdaQueryWrapper<AmsPostDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsPostDO::getId, AmsPostDO::getTitle, AmsPostDO::getCreateBy)
                .like(!ObjectUtils.isEmpty(postQueryParamDTO.getId()), AmsPostDO::getId, postQueryParamDTO.getId())
                .like(StringUtils.hasText(postQueryParamDTO.getTitle()), AmsPostDO::getTitle, postQueryParamDTO.getTitle())
                .like(!ObjectUtils.isEmpty(postQueryParamDTO.getCreateBy()), AmsPostDO::getCreateBy, postQueryParamDTO.getCreateBy());
        // 分页查询
        Page<AmsPostDO> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public void deletePost(Long id) {
        removeById(id);
    }
}
