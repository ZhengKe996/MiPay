package top.fanzhengke.mipay.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QueryByOrderIdForm {
    @NotBlank(message = "订单号不能为空")
    private String orderId;
}
