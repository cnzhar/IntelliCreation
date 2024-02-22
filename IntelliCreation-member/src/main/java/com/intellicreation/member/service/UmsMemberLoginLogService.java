package com.intellicreation.member.service;

import com.intellicreation.member.domain.entity.UmsMemberLoginLogDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author za
 * @since 2024-02-22
 */
public interface UmsMemberLoginLogService extends IService<UmsMemberLoginLogDO> {

    /**
     * 生成登录日志
     *
     * @param memberId
     */
    void generateLoginLog(Long memberId);
}
