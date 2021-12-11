package top.hkyzf.cloud.common.utils;

/**
 * 字符串与Unicode互转
 *
 * @author 朱峰
 * @date 2021-4-1 14:33
 */
public class StringUnicodeInverse {
    public static void main(String[] args) {
        // String aa = "\\u65B0\\u767B\\u8BB0\\u6237\\u6570";
        // 16进制
        String aa = "&#x65B0;&#x767B;&#x8BB0;&#x6237;&#x6570;";
        // 10进制
        // String aa = "&#26032;&#30331;&#35760;&#25143;&#25968;";
        System.out.println(unicodeToString(aa));

        String bb = "新登记户数";
        Integer radix = 16;
        System.out.println(stringToUnicode(bb, radix));

        // 决策一包转码测试
        String cc = "%u56FD%u5BB6%u7A0E%u52A1%u603B%u5C40%u5B89%u9633%u5E02%u7A0E%u52A1%u5C40";
        System.out.println(unicodeToStringJ1(cc));

        String dd = "国家税务总局安阳市税务局";
        System.out.println(stringToUnicodeJ1(dd));

        System.out.println();

    }

    /**
     * Unicode转字符串
     *
     * @param unicode Unicode字符
     * @return 转换后的字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuilder string = new StringBuilder();
        if (unicode.startsWith("&#x")) {
            String[] hex = unicode.replace("&#x", "").split(";");
            for (String str : hex) {
                int data = Integer.parseInt(str, 16);
                string.append((char) data);
            }
        } else if (unicode.startsWith("&#")) {
            String[] hex = unicode.replace("&#", "").split(";");
            for (String str : hex) {
                int data = Integer.parseInt(str, 10);
                string.append((char) data);
            }
        } else {
            String[] hex = unicode.split("\\\\u");
            for (int i = 1; i < hex.length; i++) {
                int data = Integer.parseInt(hex[i], 16);
                string.append((char) data);
            }
        }
        return string.toString();
    }

    /**
     * 字符串转Unicode
     *
     * @param string 待转换的字符串
     * @param radix  进制，10进制，16进制和其他三种情况
     * @return 转换后的Unicode字符
     */
    public static String stringToUnicode(String string, Integer radix) {
        StringBuilder unicode = new StringBuilder();
        if (radix == 16) {
            for (int i = 0; i < string.length(); i++) {
                // 取出每一个字符
                char c = string.charAt(i);
                // 转换为16进制unicode
                unicode.append("&#x").append(Integer.toHexString(c).toUpperCase()).append(";");
            }
        } else if (radix == 10) {
            for (int i = 0; i < string.length(); i++) {
                // 取出每一个字符
                char c = string.charAt(i);
                // 转换为10进制unicode
                unicode.append("&#").append((int) c).append(";");
            }
        } else {
            for (int i = 0; i < string.length(); i++) {
                // 取出每一个字符
                char c = string.charAt(i);
                // 转换为unicode
                unicode.append("\\u").append(Integer.toHexString(c).toUpperCase());
            }
        }
        return unicode.toString();
    }

    /**
     * 决策一包Unicode转字符串
     *
     * @param unicode 决策一包的Unicode字符
     * @return 转换后的字符串
     */
    public static String unicodeToStringJ1(String unicode) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("%u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 决策一包字符串转Unicode
     *
     * @param string 待转换的字符串
     * @return 转换后的Unicode字符
     */
    public static String stringToUnicodeJ1(String string) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("%u").append(Integer.toHexString(c).toUpperCase());
        }
        return unicode.toString();
    }
}
