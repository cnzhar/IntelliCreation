package com.intellicreation.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.common.util.IpRegionUtils;
import com.intellicreation.common.util.RequestUtil;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.entity.UmsMemberLoginLogDO;
import com.intellicreation.member.mapper.UmsMemberLoginLogMapper;
import com.intellicreation.member.service.UmsMemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        // todo memberId和create by有冲突
        umsMemberLoginLogDO.setMemberId(memberId);
        // 获取ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String ip = RequestUtil.getRequestIp(request);
        umsMemberLoginLogDO.setIp(ip);
        // 获取ip属地
        String ipRegion = IpRegionUtils.getIpRegion(ip);
        umsMemberLoginLogDO.setRegion(ipRegion);
        umsMemberLoginLogDO.setUserAgent(request.getHeader("User-Agent"));
        save(umsMemberLoginLogDO);
    }

    @Override
    public PageVO getMemberLoginLog(Integer pageNum, Integer pageSize, Long memberId) {
        LambdaQueryWrapper<UmsMemberLoginLogDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(UmsMemberLoginLogDO::getIp, UmsMemberLoginLogDO::getRegion, UmsMemberLoginLogDO::getUserAgent, UmsMemberLoginLogDO::getGmtCreate)
                .eq(UmsMemberLoginLogDO::getMemberId, memberId);
        Page<UmsMemberLoginLogDO> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        // 封装数据返回
        return new PageVO(page.getRecords(), page.getTotal());
    }
}
