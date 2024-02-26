package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.CommunityFacade;
import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.article.service.AmsPostService;
import com.intellicreation.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author za
 */
@Component
public class CommunityFacadeImpl implements CommunityFacade {

    @Autowired
    private AmsPostService amsPostService;

    @Override
    public void createPost(CreatePostDTO createPostDTO) {
        amsPostService.createPost(createPostDTO);
    }

    @Override
    public PageVO postList(Integer pageNum, Integer pageSize) {
        return amsPostService.postList(pageNum, pageSize);
    }
}
