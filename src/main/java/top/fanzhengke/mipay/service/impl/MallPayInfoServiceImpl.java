package top.fanzhengke.mipay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.fanzhengke.mipay.domain.MallPayInfo;

import top.fanzhengke.mipay.enums.PayrStatusEnum;
import top.fanzhengke.mipay.mapper.MallPayInfoMapper;
import top.fanzhengke.mipay.service.MallPayInfoService;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

/**
 * @author coco
 * @description 针对表【mall_pay_info】的数据库操作Service实现
 * @createDate 2022-04-01 15:32:35
 */
@Service
public class MallPayInfoServiceImpl implements MallPayInfoService {

    @Autowired
    private MallPayInfoMapper mallPayInfoMapper;

    /**
     * 创建订单
     *
     * @param orderId
     * @param amount
     * @param payTypeEnum
     * @return
     */
    @Override
    public Integer create(String orderId, BigDecimal amount, Integer payTypeEnum) {
        MallPayInfo payInfo = new MallPayInfo(orderId, payTypeEnum, PayrStatusEnum.NOTPAY.toString(), amount);
        Integer integer = mallPayInfoMapper.insertSelective(payInfo);
        if (integer == null) {
            throw new RuntimeException("创建订单失败,请联系管理员");
        }
        return integer;
    }

    /**
     * 异步通知处理
     *
     * @param orderId
     * @return
     */
    @Override
    public String asyncNotify(String orderId) {
        // 1.签名校验
        // TODO 没有企业认证无法实现

        // 2.金额校验
        MallPayInfo payInfo = mallPayInfoMapper.selectByOrderNo(orderId);
        if (payInfo == null) {
            // TODO 给WeChat、Email 发送告警邮件
            throw new RuntimeException("错误: 查询到的订单信息为null");
        }

        if (!payInfo.getPlatformStatus().equals(PayrStatusEnum.SUCCESS.name())) {
            // TODO 比较数据库金额和异步通知金额

            // 3.修改订单支付状态
            payInfo.setPlatformStatus(PayrStatusEnum.SUCCESS.name());
            // TODO 修改订单流水号
            mallPayInfoMapper.updateByPrimaryKeySelective(payInfo);
        }
        // TODO Pay发送MQ消息, Mall接受MQ消息

        return "系统默认支付成功";
    }

    /**
     * 查询支付记录
     *
     * @param orderId
     * @return
     */
    @Override
    public MallPayInfo queryByOrderId(String orderId) {
        return mallPayInfoMapper.selectByOrderNo(orderId);
    }
}




