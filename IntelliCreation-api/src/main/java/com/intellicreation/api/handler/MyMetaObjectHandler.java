package com.intellicreation.api.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.intellicreation.member.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author za
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long memberId = null;
        try {
            memberId = SecurityUtils.getMemberId();
        } catch (Exception e) {
            e.printStackTrace();
            memberId = 0L;
        }
        this.setFieldValByName("createBy", memberId, metaObject);
        this.setFieldValByName("modifiedBy", memberId, metaObject);
        this.setFieldValByName("gmtCreate", getLocalDateTime(), metaObject);
        this.setFieldValByName("gmtModified", getLocalDateTime(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("gmtModified", getLocalDateTime(), metaObject);
        this.setFieldValByName("modifiedBy", SecurityUtils.getMemberId(), metaObject);
    }

    /**
     * 获取当前时间并转化为LocalDateTime
     *
     * @return 当前时间，LocalDateTime格式
     */
    private LocalDateTime getLocalDateTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
