package top.hkyzf.cloud.common.utils;

import cn.hutool.core.codec.Base64;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串简易工具类
 *
 * @author 朱峰
 * @date 2021-4-1 14:33
 */
public class StringUtils {

    /**
     * 通过正则表达式获取内容
     *
     * @param regex 正则表达式
     * @param from  原字符串
     * @return 返回匹配结果
     */
    public static String[] regex(String regex, String from) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(from);
        List<String> results = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                results.add(matcher.group(i + 1));
            }
        }
        return results.toArray(new String[]{});
    }

    /**
     * 获取一个随机的UUID，调用金三 rUUID 参数使用
     *
     * @return 返回一个不带 - 的32位 UUID
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取一个随机值，调用金三或决策一包Url中的 r 或者 temp 参数使用
     *
     * @return 返回一个 0-1 之间的随机数
     */
    public static double randomTempOrR() {
        return Math.random();
    }

    /**
     * 数字加上逗号，三位隔开
     *
     * @param number 要转换的数字
     * @return 逗号隔开的字符串
     */
    public static String numberPlusComma(Integer number) {
        return numberPlusComma(number, "");
    }

    /**
     * 数字加上逗号，三位隔开
     *
     * @param number 要转换的数字
     * @param suffix 要添加到末尾的字符 如：户、万
     * @return 逗号隔开的字符串
     */
    public static String numberPlusComma(Integer number, String suffix) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number) + suffix;
    }

    /**
     * 将对象转为字符串
     *
     * @param object 要转换的对象
     * @return 转换后的字符串，如果对象为空，则返回空字符串
     */
    public static String objectToString(Object object) {
        String str = String.valueOf(object);
        if (str.isEmpty() || "null".equals(str)) {
            return "";
        }
        return str;
    }

    /**
     * 将一个String类型合法的URL的Host提取出来
     * Authority	IP:PORT
     * Host			IP
     *
     * @param urlStr 合法的URL
     * @return Authority信息
     */
    public static String getAuthority(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url == null ? "" : url.getAuthority();
    }

    /**
     * 获取 base64 图片字符串的文件格式
     *
     * @param base64Img Base64格式的图片字符串
     * @return 文件扩展名
     */
    public static String getBase64ImgExtension(String base64Img) {
        byte[] b = Base64.decode(base64Img);
        String type = "jpg";
        if (0x424D == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "bmp";
        } else if (0x8950 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "png";
        } else if (0xFFD8 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "jpg";
        }
        return type;
    }

}
