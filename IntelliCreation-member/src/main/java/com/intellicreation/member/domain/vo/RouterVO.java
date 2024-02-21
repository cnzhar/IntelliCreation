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
public class RouterVO {

    private List<MenuItemVO> menus;
}
