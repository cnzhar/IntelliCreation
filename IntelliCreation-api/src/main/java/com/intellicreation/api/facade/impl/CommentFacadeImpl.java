package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.api.facade.CommentFacade;
import com.intellicreation.article.domain.dto.AddCommentDTO;
import com.intellicreation.article.domain.entity.AmsCommentDO;
import com.intellicreation.article.domain.vo.CommentVO;
import com.intellicreation.article.service.AmsCommentService;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.util.SensitiveUtil;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.service.UmsMemberService;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author za
 */
@Component
public class CommentFacadeImpl implements CommentFacade {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private AmsCommentService amsCommentService;

    @Override
    public PageVO commentList(Integer pageNum, Integer pageSize, Long articleId) {
        // todo 部分业务逻辑有没有必要下放到service
        // 查询对应文章的根评论
        LambdaQueryWrapper<AmsCommentDO> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(AmsCommentDO::getArticleId, articleId);
        // 根评论 rootId为0
        queryWrapper.eq(AmsCommentDO::getRootId, SystemConstants.COMMENT_ROOT);
        // 分页查询
        Page<AmsCommentDO> page = new Page(pageNum, pageSize);
        amsCommentService.page(page, queryWrapper);
        // 封装
        List<CommentVO> commentVOList = toCommentVOList(page.getRecords());
        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVO commentVO : commentVOList) {
            // 查询对应的子评论
            List<CommentVO> children = getChildren(commentVO.getId());
            // 赋值
            commentVO.setChildren(children);
        }
        return new PageVO(commentVOList, page.getTotal());
    }

    @Override
    public void addComment(AddCommentDTO addCommentDTO) throws Exception {
        amsCommentService.addComment(addCommentDTO);
        Long id = SecurityUtils.getMemberId();
        umsMemberService.addCommentCount(id);
    }

    private List<CommentVO> toCommentVOList(List<AmsCommentDO> list) {
        List<CommentVO> commentVOs = BeanCopyUtils.copyBeanList(list, CommentVO.class);
        // 遍历vo集合
        for (CommentVO commentVO : commentVOs) {
            UmsMemberDO umsMemberDO = umsMemberService.getById(commentVO.getCreateBy());
            // 通过CreatBy查询用户的昵称并赋值
            String nickName = umsMemberDO.getNickname();
            commentVO.setNickname(nickName);
            // 用户头像
            String avatar = umsMemberDO.getAvatar();
            commentVO.setAvatar(avatar);
            // 通过toCommentMemberId查询用户的昵称并赋值
            // 如果是根评论，toCommentMemberId不为“不对任何用户进行回复”才进行查询
            if (commentVO.getToCommentMemberId() != SystemConstants.COMMENT_TO_NULL) {
                String toCommentMemberName = umsMemberService.getById(commentVO.getToCommentMemberId()).getNickname();
                commentVO.setToCommentMemberName(toCommentMemberName);
            }
        }
        return commentVOs;
    }

    /**
     * 根据根评论的id查询所对应的子评论的集合
     *
     * @param id 根评论的id
     * @return
     */
    private List<CommentVO> getChildren(Long id) {
        // todo 考虑子评论过多需要分页的情况
        LambdaQueryWrapper<AmsCommentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AmsCommentDO::getRootId, id);
        queryWrapper.orderByAsc(AmsCommentDO::getGmtCreate);
        List<AmsCommentDO> comments = amsCommentService.list(queryWrapper);

        List<CommentVO> commentVOs = toCommentVOList(comments);
        return commentVOs;
    }
}
