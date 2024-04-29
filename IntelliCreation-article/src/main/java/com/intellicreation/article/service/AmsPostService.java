package com.intellicreation.article.service;

import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.article.domain.dto.PostQueryParamDTO;
import com.intellicreation.article.domain.entity.AmsPostDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.article.domain.vo.PostViewVO;
import com.intellicreation.common.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-02-25
 */
public interface AmsPostService extends IService<AmsPostDO> {

    /**
     * 发表帖子
     *
     * @param createPostDTO
     */
    void createPost(CreatePostDTO createPostDTO) throws Exception;

    /**
     * 获取帖子
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO postList(Integer pageNum, Integer pageSize);

    PageVO queryPostList(Integer pageNum, Integer pageSize, PostQueryParamDTO postQueryParamDTO);

    void deletePost(Long id);
}
