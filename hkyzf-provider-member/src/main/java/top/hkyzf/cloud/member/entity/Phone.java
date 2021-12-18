package top.hkyzf.cloud.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 所有用户手机型号信息
 * </p>
 *
 * @author 朱峰
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member_phone")
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志
     */
    private Boolean deleted;


}
