package top.hkyzf.cloud.common.exception;


/**
 * Json解析异常
 *
 * @author 朱峰
 * @date 2021-7-15 12:05
 */
public class JsonProcessException extends RuntimeException {
    private static final long serialVersionUID = 1091267451246895301L;

    public JsonProcessException(Exception e) {
        super(e);
    }

    /**
     * @param msg 消息
     */
    public JsonProcessException(String msg) {
        super(msg);
    }

    /**
     * @param message 异常消息
     * @param e       异常
     */
    public JsonProcessException(String message, Exception e) {
        super(message, e);
    }

}
