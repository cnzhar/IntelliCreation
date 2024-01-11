package com.intellicreation.mapper;

import com.intellicreation.domain.UmsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author za
 * @since 2024-01-02
 */
@Component
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    List<String> selectPermissionByMemberId(Long memberId);
}
