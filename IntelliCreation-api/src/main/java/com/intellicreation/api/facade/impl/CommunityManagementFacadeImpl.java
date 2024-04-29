package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.CommunityManagementFacade;
import com.intellicreation.article.domain.dto.PostQueryParamDTO;
import com.intellicreation.article.service.AmsPostService;
import com.intellicreation.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author za
 */
@Component
public class CommunityManagementFacadeImpl implements CommunityManagementFacade {

    @Autowired
    private AmsPostService amsPostService;

    @Override
    public PageVO postList(Integer pageNum, Integer pageSize, PostQueryParamDTO postQueryParamDTO) {
        return amsPostService.queryPostList(pageNum, pageSize, postQueryParamDTO);
    }

    @Override
    public void deletePost(Long id) {
        amsPostService.deletePost(id);
    }
}
