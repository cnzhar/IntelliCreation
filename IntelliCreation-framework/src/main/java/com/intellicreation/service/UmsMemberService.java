package com.intellicreation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellicreation.domain.dto.ResponseResult;
import com.intellicreation.domain.model.UmsMemberDO;

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
     * 获取用户信息
     *
     * @return
     */
    ResponseResult memberInfo();

    /**
     * 更新用户信息
     *
     * @param member
     * @return
     */
    ResponseResult updateMemberInfo(UmsMemberDO member);

    /**
     * 注册
     *
     * @param member
     * @return
     */
    ResponseResult register(UmsMemberDO member);
}
