package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.article.mapper.AmsTagMapper;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.article.domain.entity.AmsTagDO;
import com.intellicreation.common.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-24
 */
@Service
public class AmsTagServiceImpl extends ServiceImpl<AmsTagMapper, AmsTagDO> implements AmsTagService {

    @Override
    public PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        // todo 这样查会select所有字段
        // 分页查询
        LambdaQueryWrapper<AmsTagDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagQueryParamDTO.getName()), AmsTagDO::getName, tagQueryParamDTO.getName())
                .like(StringUtils.hasText(tagQueryParamDTO.getRemark()), AmsTagDO::getRemark, tagQueryParamDTO.getRemark());
        Page<AmsTagDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // todo 换成tagvo返回，不要返回所有信息
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }
}
