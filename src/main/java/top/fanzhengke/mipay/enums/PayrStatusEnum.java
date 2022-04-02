package top.fanzhengke.mipay.enums;


public enum PayrStatusEnum {
    SUCCESS("支付成功"), REFUND("转入退款"), NOTPAY("未支付"), CLOSED("已关闭"), REVOKED("已撤销（刷卡支付）"), USERPAYING("用户支付中"), PAYERROR("支付失败"), UNKNOW("未知状态"),
    ;
    private String desc;

    private PayrStatusEnum(String desc) {
        this.desc = desc;
    }
}
