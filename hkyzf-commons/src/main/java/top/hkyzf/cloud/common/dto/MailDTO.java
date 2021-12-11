package top.hkyzf.cloud.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author 朱峰
 * @date 2021-12-9 0:07
 */
@Data
public class MailDTO {
    /**
     * 收件邮箱
     */
    @NotNull(message = "收件邮箱不能为空")
    @Email
    private String to;
    /**
     * 收件人名称
     */
    private String toName;
    /**
     * 邮件主题
     */
    @NotNull(message = "邮件主题不能为空")
    private String subject;
    /**
     * 邮件正文
     */
    private String text;
    /**
     * 附件文件路径（附件）
     */
    private String[] filePath;
    /**
     * 内联图片路径（邮件内图片）
     */
    private String[] imagePath;

}
