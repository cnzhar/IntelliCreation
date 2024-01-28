package com.intellicreation.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO {

    private String token;

    private MemberInfoVO userInfo;
}
