package com.intellicreation.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.AddMemberDTO;
import com.intellicreation.member.domain.dto.MemberQueryParamDTO;
import com.intellicreation.member.domain.dto.RegisterMemberDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.MemberInfoVO;

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
     * @param member
     * @return
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
     * @param member
     * @return
     */
    void updateMemberInfo(UmsMemberDO member);

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
}
