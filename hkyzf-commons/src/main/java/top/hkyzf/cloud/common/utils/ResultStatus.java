package top.hkyzf.cloud.common.utils;

/**
 * @author 朱峰
 * @date 2021-3-8 13:08
 */
public enum ResultStatus {

    // 公共
    SUCCESS(2000, "成功"),

    // 3xxx 用户登陆、权限异常
    LOGIN_EXPIRE(3001, "未登录或者登录失效"),
    LOGIN_CODE_ERROR(3002, "登录验证码错误"),
    LOGIN_ERROR(3003, "用户名不存在或密码错误"),
    LOGIN_USER_STATUS_ERROR(3004, "用户状态不正确"),
    LOGIN_USER_NOT_EXIST(3005, "该用户不存在"),
    LOGIN_USER_EXIST(3006, "该用户已存在"),
    LOGOUT_ERROR(3007, "退出失败，token不存在"),


    // 4xxx 请求参数异常
    REQUEST_PARAMETER_ERROR(4001, "请求参数错误"),


    // 5xxx 服务器错误，自定义错误
    SERVER_INTERNAL_ERROR(5001, "服务器内部异常"),
    SERVER_JSON_PARSE_ERROR(5002, "JSON解析异常"),
    SERVER_SERVICE_ERROR(5003, "业务逻辑层异常"),
    SERVER_NULLPOINTER_ERROR(5004, "服务器内部空指针异常"),

    // 51xx 文件上传类错误
    SERVER_FILE_UPLOAD_NOTEXISTS(5101, "上传文件不存在"),


    // 9xxx 其他异常
    UNKNOWN_ERROR(9998, "未知异常"),
    SYSTEM_ERROR(9999, "系统异常");

    private final Integer code;
    private final String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
