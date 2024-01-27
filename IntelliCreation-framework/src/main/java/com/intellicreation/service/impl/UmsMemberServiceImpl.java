package com.intellicreation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.domain.vo.MemberInfoVO;
import com.intellicreation.mapper.UmsMemberMapper;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.enumtype.AppHttpCodeEnums;
import com.intellicreation.exception.SystemException;
import com.intellicreation.domain.model.UmsMemberDO;
import com.intellicreation.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.util.BeanCopyUtils;
import com.intellicreation.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public ResponseResult memberInfo() {
        // 获取当前用户id
        Long memberId = SecurityUtils.getMemberId();
        // 根据用户id查询用户信息
        UmsMemberDO member = getById(memberId);
        // 封装成MemberInfoVO
        MemberInfoVO memberInfoVO = BeanCopyUtils.copyBean(member, MemberInfoVO.class);
        return ResponseResult.okResult(memberInfoVO);
    }

    @Override
    public ResponseResult updateMemberInfo(UmsMemberDO member) {
        // todo 改成update(wrapper....),防止有人获取接口地址，恶意更新密码等字段
        updateById(member);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(UmsMemberDO member) {
        // todo 用validation框架等方式把以下if改成注解
        // 对数据进行非空判断
        if (!StringUtils.hasText(member.getUid())) {
            throw new SystemException(AppHttpCodeEnums.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(member.getPassword())) {
            throw new SystemException(AppHttpCodeEnums.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(member.getEmail())) {
            throw new SystemException(AppHttpCodeEnums.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(member.getNickname())) {
            throw new SystemException(AppHttpCodeEnums.NICKNAME_NOT_NULL);
        }
        // 对数据进行是否存在的判断
        if (uidExist(member.getUid())) {
            throw new SystemException(AppHttpCodeEnums.USERNAME_EXIST);
        }
        // todo 校验邮箱格式
        if (emailExist(member.getEmail())) {
            throw new SystemException(AppHttpCodeEnums.EMAIL_EXIST);
        }
        // todo 看看有没有必要让nickname不重复，或者要不要加个开关
        if (nicknameExist(member.getNickname())) {
            throw new SystemException(AppHttpCodeEnums.NICKNAME_EXIST);
        }
        if (phoneNumberExist(member.getPhoneNumber())) {
            throw new SystemException(AppHttpCodeEnums.PHONE_NUMBER_EXIST);
        }
        // todo 看看还有没有其他需要校验的，包括需要非空的，或者不能已存在的
        // todo 用validation框架 正则等方式判断密码长度，是否包含字母数字等进行判断
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePassword);
        // 存入数据库
        save(member);
        return ResponseResult.okResult();
    }

    /**
     * 判断uid是否存在
     *
     * @param uid
     * @return
     */
    private boolean uidExist(String uid) {
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
