package top.fanzhengke.mipay.service;

import top.fanzhengke.mipay.domain.MallPayInfo;

import java.math.BigDecimal;

/**
 * @author coco
 * @description 针对表【mall_pay_info】的数据库操作Service
 * @createDate 2022-04-01 15:32:35
 */
public interface MallPayInfoService {

    /**
     * 创建/发起支付
     */
    Integer create(String orderId, BigDecimal amount, Integer payTypeEnum);

    /**
     * 异步通知处理
     *
     * @param orderId
     */
    String asyncNotify(String orderId);

    /**
     * 查询支付记录(通过订单号)
     *
     * @param orderId
     * @return
     */
    MallPayInfo queryByOrderId(String orderId);
}
