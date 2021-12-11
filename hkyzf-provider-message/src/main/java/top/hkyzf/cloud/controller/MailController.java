package top.hkyzf.cloud.controller;

import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import top.hkyzf.cloud.common.dto.MailDTO;
import top.hkyzf.cloud.common.utils.ResultMsg;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 邮件相关接口
 *
 * @author 朱峰
 * @date 2021-12-7 0:36
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/message/mail")
public class MailController {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 通过邮箱用户名获取邮箱昵称地址
     *
     * @return 邮箱昵称
     */
    private String getNickName(String nickName) {
        try {
            // 使用邮箱别名
            nickName = MimeUtility.encodeText(nickName, CharsetUtil.UTF_8, null);
            nickName = nickName + "<" + username + ">";
            log.info("邮箱昵称为：" + nickName);
        } catch (UnsupportedEncodingException e) {
            log.error("邮箱昵称转换错误", e);
        }
        return nickName;
    }

    /**
     * 通过前台传入的邮件对象封装邮件消息并发送邮件
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    private Boolean buildMimeMessageAndSendMail(MailDTO mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // true 表示需要创建一个 Multipart MimeMessage
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CharsetUtil.UTF_8);
            // 设置发件人信息
            String fromName = getNickName("臭粑粑大朱");
            if (!"".equals(fromName)) {
                messageHelper.setFrom(new InternetAddress(fromName));
            } else {
                messageHelper.setFrom(username);
            }
            // 设置收件人信息
            if (mail.getToName() == null) {
                messageHelper.setTo(mail.getTo());
            } else {
                String toName = getNickName(mail.getToName());
                if (!"".equals(toName)) {
                    messageHelper.setTo(new InternetAddress(toName));
                } else {
                    messageHelper.setTo(mail.getTo());
                }
            }
            // 设置主题，即邮件标题
            messageHelper.setSubject(mail.getSubject());
            // 如果 fileName、imagePath 都没有则只是模板邮件，mail.getText() 是处理好的模板
            messageHelper.setText(mail.getText(), true);
            // 如果有 filePath 证明有附件
            String[] filePaths = mail.getFilePath();
            if (filePaths != null) {
                for (String filePath : filePaths) {
                    File file = new File(filePath);
                    String fileName = file.getName();
                    messageHelper.addAttachment(fileName, file);
                }
            }
            // 如果有 imagePath 证明有内联图片
            String[] imagePaths = mail.getImagePath();
            if (imagePaths != null) {
                for (String imagePath : imagePaths) {
                    File image = new File(imagePath);
                    String imageName = image.getName();
                    messageHelper.addInline(imageName, image);
                }
            }
            mailSender.send(mimeMessage);
            log.info("邮件消息封装成功！");
            log.info("复杂邮件发送给 {} 成功！", mail.getTo());
            return true;
        } catch (MessagingException e) {
            log.error("邮件消息封装失败！", e);
            log.error("复杂邮件发送给 {} 失败", mail.getTo());
            return false;
        }
    }

    /**
     * 发送简单邮件
     *
     * @param mail 邮件对象
     * @return 发送邮件结果
     */
    @PostMapping("/sendSimple")
    public ResultMsg<Boolean> sendSimpleMail(@RequestBody MailDTO mail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(username);
            simpleMailMessage.setTo(mail.getTo());
            simpleMailMessage.setSubject(mail.getSubject());
            simpleMailMessage.setText(mail.getText());
            mailSender.send(simpleMailMessage);
            log.info("简单邮件发送给 {} 成功！", mail.getTo());
            return ResultMsg.ok(true);
        } catch (MailException e) {
            log.error("简单邮件发送给 " + mail.getTo() + " 失败", e);
            return ResultMsg.ok(false);
        }
    }

    /**
     * 发送模板邮件
     * 发送复杂邮件，带附件，必须单击它才能打开并查看内容
     * 发送内联邮件，带内联图片，通常在电子邮件正文消息中
     *
     * @param mail 邮件对象
     * @return 发送邮件结果
     */
    @PostMapping("/sendAttachment")
    public ResultMsg<Boolean> sendAttachmentMail(@RequestBody MailDTO mail) {
        return ResultMsg.ok(buildMimeMessageAndSendMail(mail));
    }

    @GetMapping("/hello/{msg}")
    public ResultMsg<String> hello(@PathVariable String msg) {
        String str = "你好：" + msg;
        return ResultMsg.ok(str);
    }
}
