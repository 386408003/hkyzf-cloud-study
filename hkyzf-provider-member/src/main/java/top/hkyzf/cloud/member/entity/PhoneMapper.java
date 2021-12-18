package top.hkyzf.cloud.member.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import top.hkyzf.cloud.common.dto.PhoneDTO;

import java.util.List;

/**
 * @author 朱峰
 * @date 2021-12-17 0:42
 */
@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    PhoneDTO phoneToPhoneDTO(Phone phone);

    List<PhoneDTO> phoneToPhoneDTOList(List<Phone> phoneList);

    Phone phoneDtoToPhone(PhoneDTO phoneDTO);
}
