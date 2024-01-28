package com.intellicreation.member.domain.vo;

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
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    private String gender;

    private String email;


}
