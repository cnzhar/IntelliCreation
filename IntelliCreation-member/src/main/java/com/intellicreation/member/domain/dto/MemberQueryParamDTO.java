package com.intellicreation.member.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author za
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberQueryParamDTO {

    private Long id;

    private String uid;

    private String nickname;
}
