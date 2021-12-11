package top.hkyzf.cloud.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一封装返回数据
 *
 * @author 朱峰
 * @date 2021-3-8 17:41
 */
@Data
@NoArgsConstructor
public class ResultMsg<T> implements Serializable {
    /**
     * 提示码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 携带数据
     */
    private T data;

    private ResultMsg(ResultStatus resultStatus) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMessage();
    }

    private ResultMsg(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMessage();
        this.data = data;
    }

    /**
     * 直接返回成功状态，不可带数据
     *
     * @return 成功状态
     */
    public static <T> ResultMsg<T> ok() {
        return new ResultMsg<>(ResultStatus.SUCCESS);
    }

    /**
     * 直接返回成功状态，并带有返回数据
     *
     * @param data 要返回的数据
     * @param <T>  数据类型
     * @return 返回构造好的ResultMsg对象
     */
    public static <T> ResultMsg<T> ok(T data) {
        return new ResultMsg<>(ResultStatus.SUCCESS, data);
    }

    /**
     * 根据传入的状态，构造ResultMsg对象
     *
     * @param resultStatus 状态
     * @param <T>          数据类型
     * @return 返回构造好的ResultMsg对象
     */
    public static <T> ResultMsg<T> build(ResultStatus resultStatus) {
        return new ResultMsg<>(resultStatus);
    }

    /**
     * 根据传入的状态和数据，构造ResultMsg对象
     *
     * @param resultStatus 状态
     * @param data         要返回的数据
     * @param <T>          数据类型
     * @return 返回构造好的ResultMsg对象
     */
    public static <T> ResultMsg<T> build(ResultStatus resultStatus, T data) {
        return new ResultMsg<>(resultStatus, data);
    }

}

