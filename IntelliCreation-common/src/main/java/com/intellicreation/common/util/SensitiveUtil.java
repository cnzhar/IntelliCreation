package com.intellicreation.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author za
 */
public class SensitiveUtil {

    private static List<String> illegalWords = Arrays.asList("违规词1", "小猪佩奇", "会员");

    public static boolean isContainsIllegalWord(String content) {
        for (String word : illegalWords) {
            if (content.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
