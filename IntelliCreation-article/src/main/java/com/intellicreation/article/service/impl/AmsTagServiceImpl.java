package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.article.domain.dto.AddTagDTO;
import com.intellicreation.article.domain.vo.TagDetailVO;
import com.intellicreation.article.domain.dto.UpdateTagDTO;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.article.mapper.AmsTagMapper;
import com.intellicreation.article.domain.dto.TagQueryParamDTO;
import com.intellicreation.article.domain.entity.AmsTagDO;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
    public void addTag(AddTagDTO addTagDTO) {
        AmsTagDO amsTagDO = BeanCopyUtils.copyBean(addTagDTO, AmsTagDO.class);
        save(amsTagDO);
    }

    @Override
    public void updateTagInfo(UpdateTagDTO updateTagDTO) {
        AmsTagDO amsTagDO = BeanCopyUtils.copyBean(updateTagDTO, AmsTagDO.class);
        updateById(amsTagDO);
    }

    @Override
    public PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<AmsTagDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(AmsTagDO::getId, AmsTagDO::getName, AmsTagDO::getDescription)
                .like(!ObjectUtils.isEmpty(tagQueryParamDTO.getId()), AmsTagDO::getId, tagQueryParamDTO.getId())
                .like(StringUtils.hasText(tagQueryParamDTO.getName()), AmsTagDO::getName, tagQueryParamDTO.getName())
                .like(StringUtils.hasText(tagQueryParamDTO.getRemark()), AmsTagDO::getDescription, tagQueryParamDTO.getRemark());
        Page<AmsTagDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public TagDetailVO getTagDetail(Long id) {
        AmsTagDO amsTagDO = getById(id);
        return BeanCopyUtils.copyBean(amsTagDO, TagDetailVO.class);
    }
}
