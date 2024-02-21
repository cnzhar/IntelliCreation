package com.intellicreation.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.dto.AddTagDTO;
import com.intellicreation.article.domain.vo.TagDetailVO;
import com.intellicreation.article.domain.dto.UpdateTagDTO;
import com.intellicreation.article.domain.entity.AmsTagDO;
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
     * 新增标签
     *
     * @param addTagDTO
     */
    void addTag(AddTagDTO addTagDTO);

    /**
     * 编辑标签
     *
     * @param updateTagDTO
     */
    void updateTagInfo(UpdateTagDTO updateTagDTO);

    /**
     * 根据条件查询标签
     *
     * @param pageNum
     * @param pageSize
     * @param tagQueryParamDTO
     * @return
     */
    PageVO queryTagList(Integer pageNum, Integer pageSize, TagQueryParamDTO tagQueryParamDTO);

    /**
     * 根据id获取标签详情
     *
     * @param id
     * @return
     */
    TagDetailVO getTagDetail(Long id);
}
