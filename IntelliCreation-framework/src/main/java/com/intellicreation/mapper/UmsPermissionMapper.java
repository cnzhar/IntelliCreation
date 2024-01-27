package com.intellicreation.mapper;

import com.intellicreation.domain.model.UmsPermissionDO;
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
public interface UmsPermissionMapper extends BaseMapper<UmsPermissionDO> {

    /**
     * 根据用户id返回所具有的权限
     *
     * @param memberId
     * @return
     */
    List<String> selectPermissionByMemberId(Long memberId);
    // todo 这个的mapper中相关内容，建立一下索引
}
