package com.intellicreation.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.*;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-01-01
 */
public interface UmsMemberService extends IService<UmsMemberDO> {

    /**
     * 注册
     *
     * @param registerMemberDTO
     */
    void register(RegisterMemberDTO registerMemberDTO);

    /**
     * 管理员新增用户
     *
     * @param addMemberDTO
     */
    void addMember(AddMemberDTO addMemberDTO);

    /**
     * 更新用户信息
     *
     * @param updateMemberInfoDTO
     */
    void updateMemberInfo(UpdateMemberInfoDTO updateMemberInfoDTO);

    /**
     * 根据查询条件，查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param memberQueryParamDTO
     * @return
     */
    PageVO queryMemberList(Integer pageNum, Integer pageSize, MemberQueryParamDTO memberQueryParamDTO);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    MemberInfoVO getMemberInfo(Long id);

    /**
     * 根据id批量获取用户
     *
     * @param pageNum
     * @param pageSize
     * @param idList
     * @return
     */
    PageVO getMemberListByIds(Integer pageNum, Integer pageSize, List<Long> idList);

    /**
     * 个人信息页顶部信息
     *
     * @param memberId
     * @return
     */
    HeaderInfoVO headerInfo(Long memberId);

    /**
     * 获取阅读页面“我的”卡片信息
     *
     * @param memberId
     * @return
     */
    ReadMineCardInfoVO readMineCardInfo(Long memberId);

    /**
     * 获取社区页面“我的”卡片信息
     *
     * @param memberId
     * @return
     */
    CommunityMineCardInfoVO communityMineCardInfo(Long memberId);

    /**
     * 个人基础信息
     *
     * @param memberId
     * @return
     */
    MineBasicInfoVO mineBasicInfo(Long memberId);

    /**
     * 修改个人信息页面，需要先获取信息
     *
     * @param memberId
     * @return
     */
    MineEditInfoVO mineEditInfo(Long memberId);

    // todo 要不要把所有更新接口整合，去掉service层，facade层copybean，然后update

    /**
     * 更新个人信息
     * @param updateMineInfoDTO
     */
    void updateMineInfo(UpdateMineInfoDTO updateMineInfoDTO);

    /**
     * 根据用户id获取用户昵称
     *
     * @param id
     * @return
     */
    String getNicknameById(Long id);

    /**
     * 根据id获取用户头像
     *
     * @param id
     * @return
     */
    String getAvatarById(Long id);
}
