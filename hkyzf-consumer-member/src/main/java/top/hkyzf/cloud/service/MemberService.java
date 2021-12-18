package top.hkyzf.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.hkyzf.cloud.common.dto.MailDTO;
import top.hkyzf.cloud.common.dto.PhoneDTO;
import top.hkyzf.cloud.common.utils.ResultMsg;

import java.util.List;

/**
 * @author 朱峰
 * @date 2021-12-7 23:57
 */
@FeignClient("provider-member")
@RequestMapping("/provider/member")
public interface MemberService {
    /**
     * 简单邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/simpleMail/send")
    ResultMsg<Boolean> sendSimpleMail(@RequestBody MailDTO mail);

    /**
     * 复杂邮件发送
     *
     * @param mail 邮件对象
     * @return 邮件发送结果
     */
    @PostMapping("/complexMail/send")
    ResultMsg<Boolean> sendComplexMail(@RequestBody MailDTO mail);

    /**
     * 查询所有的手机设备列表
     * @return 手机设备列表
     */
    @GetMapping("/phone")
    ResultMsg<List<PhoneDTO>> getPhone();

    /**
     * 保存使用脚本的手机设备信息
     * @param phone 手机设备信息
     * @return 保存结果
     */
    @PostMapping("/phone")
    ResultMsg<Boolean> savePhone(@RequestBody PhoneDTO phone);

    /**
     * 测试接口
     *
     * @param msg 信息
     * @return 你好：信息
     */
    @GetMapping("/hello/{msg}")
    ResultMsg<String> hello(@PathVariable("msg") String msg);

}
