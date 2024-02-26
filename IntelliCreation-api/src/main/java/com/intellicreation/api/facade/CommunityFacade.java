package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.common.vo.PageVO;

/**
 * @author za
 */
public interface CommunityFacade {

    /**
     * 发表帖子
     *
     * @param createPostDTO
     */
    void createPost(CreatePostDTO createPostDTO);

    /**
     * 获取帖子
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO postList(Integer pageNum, Integer pageSize);
}
