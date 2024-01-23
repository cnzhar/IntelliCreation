package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.constant.SystemConstants;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.AmsCommentDO;
import com.intellicreation.domain.vo.CommentVO;
import com.intellicreation.domain.vo.PageVO;
import com.intellicreation.enumtype.AppHttpCodeEnums;
import com.intellicreation.exception.SystemException;
import com.intellicreation.mapper.AmsCommentMapper;
import com.intellicreation.service.AmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.service.UmsMemberService;
import com.intellicreation.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-19
 */
@Service
public class AmsCommentServiceImpl extends ServiceImpl<AmsCommentMapper, AmsCommentDO> implements AmsCommentService {

    @Autowired
    UmsMemberService umsMemberService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<AmsCommentDO> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(AmsCommentDO::getArticleId, articleId);
        // 根评论 rootId为0
        queryWrapper.eq(AmsCommentDO::getRootId, SystemConstants.COMMENT_ROOT);
        // 分页查询
        Page<AmsCommentDO> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装
        List<CommentVO> commentVOList = toCommentVOList(page.getRecords());
        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVO commentVO : commentVOList) {
            // 查询对应的子评论
            List<CommentVO> children = getChildren(commentVO.getId());
            // 赋值
            commentVO.setChildren(children);
        }

        return ResponseResult.okResult(new PageVO(commentVOList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(AmsCommentDO comment) {
        // 评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnums.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVO> toCommentVOList(List<AmsCommentDO> list) {
        List<CommentVO> commentVOs = BeanCopyUtils.copyBeanList(list, CommentVO.class);
        // 遍历vo集合
        for (CommentVO commentVO : commentVOs) {
            // 通过CreatBy查询用户的昵称并赋值
            String nickName = umsMemberService.getById(commentVO.getCreateBy()).getNickname();
            commentVO.setNickName(nickName);
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
        List<AmsCommentDO> comments = list(queryWrapper);

        List<CommentVO> commentVOs = toCommentVOList(comments);
        return commentVOs;
    }
}
