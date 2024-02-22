package com.intellicreation.member.service.impl;

import com.intellicreation.common.util.RequestUtil;
import com.intellicreation.member.domain.entity.UmsMemberLoginLogDO;
import com.intellicreation.member.mapper.UmsMemberLoginLogMapper;
import com.intellicreation.member.service.UmsMemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-02-22
 */
@Service
public class UmsMemberLoginLogServiceImpl extends ServiceImpl<UmsMemberLoginLogMapper, UmsMemberLoginLogDO> implements UmsMemberLoginLogService {

    @Override
    public void generateLoginLog(Long memberId) {
        UmsMemberLoginLogDO umsMemberLoginLogDO = new UmsMemberLoginLogDO();
        umsMemberLoginLogDO.setMemberId(SecurityUtils.getMemberId());
        // 获取ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        umsMemberLoginLogDO.setIp(RequestUtil.getRequestIp(request));
        umsMemberLoginLogDO.setUserAgent(request.getHeader("User-Agent"));
        save(umsMemberLoginLogDO);
    }
}
