package com.intellicreation.api.facade.impl;

import com.intellicreation.api.facade.CommunityFacade;
import com.intellicreation.article.domain.dto.CreatePostDTO;
import com.intellicreation.article.domain.entity.AmsPostDO;
import com.intellicreation.article.domain.vo.PostViewVO;
import com.intellicreation.article.service.AmsPostService;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author za
 */
@Component
public class CommunityFacadeImpl implements CommunityFacade {

    @Autowired
    private AmsPostService amsPostService;

    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public void createPost(CreatePostDTO createPostDTO) {
        amsPostService.createPost(createPostDTO);
    }

    @Override
    public PageVO postList(Integer pageNum, Integer pageSize) {
        return amsPostService.postList(pageNum, pageSize);
    }

    @Override
    public PostViewVO postDetail(Long id) {
        AmsPostDO amsPostDO = amsPostService.getById(id);
        PostViewVO postViewVO = BeanCopyUtils.copyBean(amsPostDO, PostViewVO.class);
        // 设定头像
        String avatar = umsMemberService.getAvatarById(postViewVO.getCreateBy());
        postViewVO.setAvatar(avatar);
        // 设定昵称
        String nickname = umsMemberService.getNicknameById(postViewVO.getCreateBy());
        postViewVO.setNickname(nickname);
        return postViewVO;
    }
}
