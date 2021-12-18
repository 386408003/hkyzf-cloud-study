package top.hkyzf.cloud.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户手机设备传输对象
 *
 * @author 朱峰
 * @since 2021-12-16
 */
@Data
public class PhoneDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 修订版本号
     */
    private String buildId;

    /**
     * 设备厂商品牌
     */
    private String brand;

    /**
     * 产品名称
     */
    private String product;

    /**
     * CPU硬件型号
     */
    private String hardware;

    /**
     * 设备序列号
     */
    private String serial;

    /**
     * 系统详细版本号
     */
    private String incremental;

    /**
     * Android ID
     */
    private String androidId;

    /**
     * MAC地址
     */
    private String macAddress;

}
