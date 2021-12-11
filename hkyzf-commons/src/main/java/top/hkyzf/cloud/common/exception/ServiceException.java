package top.hkyzf.cloud.common.exception;


/**
 * 业务逻辑层异常
 *
 * @author 朱峰
 * @date 2021-7-15 12:05
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -1841384137083803263L;

    public ServiceException(Exception e) {
        super(e);
    }

    /**
     * @param msg 消息
     */
    public ServiceException(String msg) {
        super(msg);
    }

    /**
     * @param message 异常消息
     * @param e       异常
     */
    public ServiceException(String message, Exception e) {
        super(message, e);
    }

}
