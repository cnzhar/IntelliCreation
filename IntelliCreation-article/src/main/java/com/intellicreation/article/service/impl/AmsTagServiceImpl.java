package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.article.mapper.AmsTagMapper;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.article.domain.entity.AmsTagDO;
import com.intellicreation.article.domain.vo.PageVO;
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
    public ResponseResult<PageVO> queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        //分页查询
        LambdaQueryWrapper<AmsTagDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagQueryParamDTO.getName()), AmsTagDO::getName, tagQueryParamDTO.getName());
        queryWrapper.eq(StringUtils.hasText(tagQueryParamDTO.getRemark()), AmsTagDO::getRemark, tagQueryParamDTO.getRemark());
        // todo 似乎只能查名字一模一样的，不能模糊查找
        Page<AmsTagDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // todo 换成tagvo返回，不要返回所有信息
        //封装数据返回
        PageVO pageVO = new PageVO(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVO);
    }
}
