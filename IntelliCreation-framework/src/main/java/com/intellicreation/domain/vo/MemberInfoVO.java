package com.intellicreation.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author za
 */
@Data
@Accessors(chain = true)
public class MemberInfoVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}
