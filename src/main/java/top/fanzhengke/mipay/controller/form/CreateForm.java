package top.fanzhengke.mipay.controller.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class CreateForm {
    @NotBlank(message = "单号不能为空")
    private String orderId;

    @NotNull
    private BigDecimal amount;

    @Min(1)
    @Max(2)
    @NotNull
    private Integer payTypeEnum;

}
