package top.hkyzf.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.hkyzf.cloud.common.dto.MailDTO;
import top.hkyzf.cloud.common.utils.ResultMsg;

/**
 * @author 朱峰
 * @date 2021-12-7 23:57
 */
@FeignClient("provider-message")
public interface MailService {
    /**
     * 简单邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/message/mail/sendSimple")
    ResultMsg<String> sendSimpleMail(@RequestBody MailDTO mail);

    /**
     * 复杂邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/message/mail/sendAttachment")
    ResultMsg<String> sendAttachmentMail(@RequestBody MailDTO mail);

    /**
     * 测试接口
     *
     * @param msg 信息
     * @return 你好：信息
     */
    @GetMapping("/message/mail/hello/{msg}")
    ResultMsg<String> hello(@PathVariable("msg") String msg);
}
