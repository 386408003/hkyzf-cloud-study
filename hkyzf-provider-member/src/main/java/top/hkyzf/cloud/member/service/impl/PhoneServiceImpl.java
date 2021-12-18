package top.hkyzf.cloud.member.service.impl;

import top.hkyzf.cloud.member.entity.Phone;
import top.hkyzf.cloud.member.mapper.PhoneMapper;
import top.hkyzf.cloud.member.service.IPhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  所有用户手机型号信息服务实现类
 * </p>
 *
 * @author 朱峰
 * @since 2021-12-16
 */
@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements IPhoneService {

}
