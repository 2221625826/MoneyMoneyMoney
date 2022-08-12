package org.money.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyiheng03
 * @since 2022/7/8 17:21
 */

public class VersionUtils {
    /**
     * 大于等于 return true, 小于return false
     * @param appVer 当前版本app version
     * @param minVer 比较的 app version
     * @return boolean
     */
    public static boolean compareAppVersion(String appVer, String minVer) {
        if (StringUtils.isBlank(appVer)) {
            return false;
        }
        String appFullVer = getFullFormatAppVer(appVer);
        if (appFullVer.equalsIgnoreCase(minVer)) { // 版本号一致
            return true;
        }
        String[] reqParts = appVer.split("\\.");
        String[] minParts = minVer.split("\\.");
        // 开始比较
        for (int i = 0; i < 3; i++) {
            int reqPart = Integer.parseInt(reqParts[i]);
            int minPart = Integer.parseInt(minParts[i]);
            if (reqPart < minPart) {
                return false;
            } else if (reqPart > minPart) {
                return true; // 请求版本号大
            }
        }
        return true;
    }

    private static String getFullFormatAppVer(String appVer) {
        String[] parts = appVer.split("\\.");
        if (parts.length != 3) {
            return "0.0.0";
        }
        StringBuilder sb = new StringBuilder();
        for (String part: parts) {
            try {
                int num = Integer.parseInt(part);
                sb.append(".").append(num);
            } catch (Exception e) {
                return "0.0.0";
            }
        }
        return sb.substring(1);
    }
}