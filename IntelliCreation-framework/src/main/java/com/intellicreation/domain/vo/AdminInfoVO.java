package com.intellicreation.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author za
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfoVO {

    private List<String> permissions;

    private List<String> roles;

    private MemberInfoVO memberInfoVO;

}
