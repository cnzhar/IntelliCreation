package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.*;
import com.intellicreation.member.domain.vo.*;
import com.intellicreation.member.mapper.UmsMemberMapper;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.common.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author za
 * @since 2024-01-01
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMemberDO> implements UmsMemberService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterMemberDTO registerMemberDTO) {
        // 对数据进行是否存在的判断
        if (uidExist(registerMemberDTO.getUid())) {
            throw new SystemException(AppHttpCodeEnums.USERNAME_EXIST);
        }
        if (emailExist(registerMemberDTO.getEmail())) {
            throw new SystemException(AppHttpCodeEnums.EMAIL_EXIST);
        }
        if (nicknameExist(registerMemberDTO.getNickname())) {
            throw new SystemException(AppHttpCodeEnums.NICKNAME_EXIST);
        }
        if (phoneNumberExist(registerMemberDTO.getPhoneNumber())) {
            throw new SystemException(AppHttpCodeEnums.PHONE_NUMBER_EXIST);
        }
        // todo 看看还有没有其他需要校验的，包括需要非空的，或者不能已存在的
        // todo 用validation框架 正则等方式判断密码长度，是否包含字母数字等进行判断
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(registerMemberDTO.getPassword());
        registerMemberDTO.setPassword(encodePassword);
        // 存入数据库
        UmsMemberDO umsMemberDO = BeanCopyUtils.copyBean(registerMemberDTO, UmsMemberDO.class);
        save(umsMemberDO);
    }

    @Override
    public void addMember(AddMemberDTO addMemberDTO) {
        UmsMemberDO umsMemberDO = BeanCopyUtils.copyBean(addMemberDTO, UmsMemberDO.class);
        // todo 考虑要不要加上修改默认密码的功能
        // todo 数据校验
        if (uidExist(addMemberDTO.getUid())) {
            throw new SystemException(AppHttpCodeEnums.USERNAME_EXIST);
        }
        String encodePassword = passwordEncoder.encode(SystemConstants.defaultPassword);
        umsMemberDO.setPassword(encodePassword);
        save(umsMemberDO);
    }

    @Override
    public void updateMemberInfo(UpdateMemberInfoDTO updateMemberInfoDTO) {
        UmsMemberDO umsMemberDO = BeanCopyUtils.copyBean(updateMemberInfoDTO, UmsMemberDO.class);
        updateById(umsMemberDO);
    }

    @Override
    public PageVO queryMemberList(Integer pageNum, Integer pageSize, MemberQueryParamDTO memberQueryParamDTO) {
        // 分页查询
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getId, UmsMemberDO::getUid, UmsMemberDO::getNickname)
                .like(!ObjectUtils.isEmpty(memberQueryParamDTO.getId()), UmsMemberDO::getId, memberQueryParamDTO.getId())
                .like(StringUtils.hasText(memberQueryParamDTO.getUid()), UmsMemberDO::getUid, memberQueryParamDTO.getUid())
                .like(StringUtils.hasText(memberQueryParamDTO.getNickname()), UmsMemberDO::getNickname, memberQueryParamDTO.getNickname());
        Page<UmsMemberDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public MemberInfoVO getMemberInfo(Long id) {
        // 根据用户id查询用户信息
        UmsMemberDO member = getById(id);
        // 封装成MemberInfoVO
        MemberInfoVO memberInfoVO = BeanCopyUtils.copyBean(member, MemberInfoVO.class);
        return memberInfoVO;
    }

    @Override
    public PageVO getMemberListByIds(Integer pageNum, Integer pageSize, List<Long> idList) {
        // 如果传入的id列表为空，则返回空分页
        if (idList == null || idList.isEmpty()) {
            return new PageVO(Collections.emptyList(), 0L);
        }
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getId, UmsMemberDO::getUid, UmsMemberDO::getNickname)
                .in(UmsMemberDO::getId, idList);
        Page<UmsMemberDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }

    @Override
    public HeaderInfoVO headerInfo(Long memberId) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getNickname, UmsMemberDO::getAvatar, UmsMemberDO::getPersonalizedSignature)
                .eq(UmsMemberDO::getId, memberId);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return BeanCopyUtils.copyBean(umsMemberDO, HeaderInfoVO.class);
    }

    @Override
    public ReadMineCardInfoVO readMineCardInfo(Long memberId) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getNickname, UmsMemberDO::getAvatar, UmsMemberDO::getArticleCount,
                        UmsMemberDO::getRatingCount, UmsMemberDO::getExp)
                .eq(UmsMemberDO::getId, memberId);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return BeanCopyUtils.copyBean(umsMemberDO, ReadMineCardInfoVO.class);
    }

    @Override
    public CommunityMineCardInfoVO communityMineCardInfo(Long memberId) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getNickname, UmsMemberDO::getAvatar, UmsMemberDO::getPostCount,
                        UmsMemberDO::getPostCommentCount, UmsMemberDO::getExp)
                .eq(UmsMemberDO::getId, memberId);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return BeanCopyUtils.copyBean(umsMemberDO, CommunityMineCardInfoVO.class);
    }

    @Override
    public MineBasicInfoVO mineBasicInfo(Long memberId) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getUid, UmsMemberDO::getEmail, UmsMemberDO::getEmailStatus,
                        UmsMemberDO::getPhoneNumber, UmsMemberDO::getFullName, UmsMemberDO::getGender,
                        UmsMemberDO::getBirthday, UmsMemberDO::getExp, UmsMemberDO::getLocationId,
                        UmsMemberDO::getJob, UmsMemberDO::getCompany, UmsMemberDO::getQqNumber)
                .eq(UmsMemberDO::getId, memberId);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return BeanCopyUtils.copyBean(umsMemberDO, MineBasicInfoVO.class);
    }

    @Override
    public MineEditInfoVO mineEditInfo(Long memberId) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getAvatar)
                .eq(UmsMemberDO::getId, memberId);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return BeanCopyUtils.copyBean(umsMemberDO, MineEditInfoVO.class);
    }

    @Override
    public void updateMineInfo(UpdateMineInfoDTO updateMineInfoDTO) {
        UmsMemberDO umsMemberDO = BeanCopyUtils.copyBean(updateMineInfoDTO, UmsMemberDO.class);
        updateById(umsMemberDO);
    }

    @Override
    public String getNicknameById(Long id) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getNickname)
                .eq(UmsMemberDO::getId, id);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        return umsMemberDO.getNickname();
    }

    @Override
    public String getAvatarById(Long id) {
        LambdaQueryWrapper<UmsMemberDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberDO::getAvatar)
                .eq(UmsMemberDO::getId, id);
        UmsMemberDO umsMemberDO = getOne(lambdaQueryWrapper);
        if (umsMemberDO != null) {
            return umsMemberDO.getAvatar();
        } else {
            return "";
        }
    }

    @Override
    public void addArticleCount(Long memberId) {
        UmsMemberDO umsMemberDO = getById(memberId);
        Long currentArticle = umsMemberDO.getArticleCount();
        umsMemberDO.setArticleCount(currentArticle + 1);
        updateById(umsMemberDO);
    }

    @Override
    public void addRatingCount(Long id) {
        UmsMemberDO umsMemberDO = getById(id);
        Long currentRating = umsMemberDO.getRatingCount();
        umsMemberDO.setRatingCount(currentRating + 1);
        updateById(umsMemberDO);
    }

    @Override
    public void addPostCount(Long id) {
        UmsMemberDO umsMemberDO = getById(id);
        Long currentPost = umsMemberDO.getPostCount();
        umsMemberDO.setPostCount(currentPost + 1);
        updateById(umsMemberDO);
    }

    @Override
    public void addCommentCount(Long id) {
        UmsMemberDO umsMemberDO = getById(id);
        Long currentComment = umsMemberDO.getPostCommentCount();
        umsMemberDO.setPostCommentCount(currentComment + 1);
        updateById(umsMemberDO);
    }

    /**
     * 判断uid是否存在
     *
     * @param uid
     * @return
     */
    private boolean uidExist(String uid) {
        // todo 这些判断是否存在的函数是否select过多字段
        LambdaQueryWrapper<UmsMemberDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberDO::getUid, uid);
        return count(queryWrapper) > 0;
    }

    /**
     * 判断email是否存在
     *
     * @param email
     * @return
     */
    private boolean emailExist(String email) {
        LambdaQueryWrapper<UmsMemberDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberDO::getEmail, email);
        return count(queryWrapper) > 0;
    }

    /**
     * 判断昵称是否存在
     *
     * @param nickname
     * @return
     */
    private boolean nicknameExist(String nickname) {
        LambdaQueryWrapper<UmsMemberDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberDO::getNickname, nickname);
        return count(queryWrapper) > 0;
    }

    /**
     * 判断手机号是否存在
     *
     * @param phoneNumber
     * @return
     */
    private boolean phoneNumberExist(Integer phoneNumber) {
        LambdaQueryWrapper<UmsMemberDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberDO::getPhoneNumber, phoneNumber);
        return count(queryWrapper) > 0;
    }

}
