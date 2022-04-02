package top.fanzhengke.mipay.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fanzhengke.mipay.domain.MallPayInfo;

/**
 * @author coco
 * @description 针对表【mall_pay_info】的数据库操作Mapper
 * @createDate 2022-04-01 15:32:35
 * @Entity top.fanzhengke.mipay.domain.MallPayInfo
 */
@Mapper
public interface MallPayInfoMapper {
    Integer insertSelective(MallPayInfo mallPayInfo);

    MallPayInfo selectByOrderNo(String orderNo);

    Integer updateByPrimaryKeySelective(MallPayInfo record);
}




