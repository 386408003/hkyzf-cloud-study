package top.hkyzf.cloud.common.utils;

import cn.hutool.core.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

/**
 * 字符串与16进制互转
 *
 * @author 朱峰
 * @date 2021-3-24 11:31
 */
public class StringHexInverse {
    public static void main(String[] args) throws Exception {
        //十六进制编码转中文字符串
        String s1 = "C2ACD0E3cbd5343232373234313836323230323033313833";
        String r1 = stringToCustom(s1, CharsetUtil.GBK);
        System.out.println(r1);
        String s2 = "65B0767B8BB062376570";
        String r2 = stringToCustom(s2, CharsetUtil.UTF_8);
        System.out.println(r2);

        //中文字符串转十六进制编码字符串
        String a = "新登记户数";
        String hexStr1 = stringToHex(a, CharsetUtil.UTF_8);
        String hexStr2 = stringToHex(a, CharsetUtil.GBK);
        String hexStr3 = stringToHex(a, CharsetUtil.GBK);
        System.out.println(hexStr1);
        System.out.println(hexStr2);
        System.out.println(hexStr3);

    }

    /**
     * 将传过来的16进制编码转换成汉字
     *
     * @param content 被16进制编码过的密文字符串
     * @param charSet 字符编码
     * @return 转码后的明文结果
     * @throws UnsupportedEncodingException 编码不支持异常
     */
    private static String stringToCustom(String content, String charSet) throws UnsupportedEncodingException {
        byte[] bytes = new byte[content.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            byte high = Byte.parseByte(content.substring(i * 2, i * 2 + 1), 16);
            byte low = Byte.parseByte(content.substring(i * 2 + 1, i * 2 + 2), 16);
            bytes[i] = (byte) (high << 4 | low);
        }
        return new String(bytes, charSet);
    }

    //

    /**
     * 将明文字符串转成16进制字符串
     *
     * @param string  要进行16进制编码的明文字符串
     * @param charSet 字符编码
     * @return 转码后的密文
     * @throws UnsupportedEncodingException 编码不支持异常
     */
    private static String stringToHex(String string, String charSet) throws UnsupportedEncodingException {
        byte[] bytes = string.getBytes(charSet);
        return bytesToHex(bytes);
    }

    /**
     * 将byte数组转成16进制字符串
     *
     * @param bytes 要进行16进制编码的明文字符串
     * @return 转码后的密文
     */
    private static String bytesToHex(byte[] bytes) {
        char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for (byte b : bytes) { // 使用除与取余进行转换
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }
            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }
        return new String(buf);
    }
}
