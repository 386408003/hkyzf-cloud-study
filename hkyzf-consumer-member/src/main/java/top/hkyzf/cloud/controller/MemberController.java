package top.hkyzf.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.hkyzf.cloud.service.MailService;
import top.hkyzf.cloud.common.dto.MailDTO;
import top.hkyzf.cloud.common.utils.ResultMsg;

/**
 * @author 朱峰
 * @date 2021-12-7 23:57
 */
@RestController
@RequestMapping("/consumer/member")
public class MemberController {
    @Autowired
    MailService mailService;

    /**
     * 简单邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/sendMail")
    public ResultMsg<String> sendSimpleMail(@RequestBody @Validated MailDTO mail) {
        return mailService.sendSimpleMail(mail);
    }

    /**
     * 复杂邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/sendAttachment")
    public ResultMsg<String> sendAttachmentMail(@RequestBody @Validated MailDTO mail) {
        return mailService.sendAttachmentMail(mail);
    }

    /**
     * 测试接口
     *
     * @param msg 信息
     * @return 你好：信息
     */
    @GetMapping("/hello/{msg}")
    public ResultMsg<String> hello(@PathVariable String msg) {
        return mailService.hello(msg);
    }
}
