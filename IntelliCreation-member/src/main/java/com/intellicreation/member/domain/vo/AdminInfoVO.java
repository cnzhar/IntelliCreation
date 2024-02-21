package com.intellicreation.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfoVO {

    // todo 看看要不要改掉这个VO, 以及是不是应该改掉和MemberInfoVO的关联

    private List<String> permissions;

    private List<String> roles;

    private MemberInfoVO memberInfoVO;

}
