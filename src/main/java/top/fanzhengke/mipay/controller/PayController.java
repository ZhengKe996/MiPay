package top.fanzhengke.mipay.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fanzhengke.mipay.common.utils.Result;
import top.fanzhengke.mipay.controller.form.CreateForm;
import top.fanzhengke.mipay.controller.form.QueryByOrderIdForm;
import top.fanzhengke.mipay.domain.MallPayInfo;

import top.fanzhengke.mipay.service.MallPayInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
public class PayController {

    @Autowired
    private MallPayInfoService mallPayInfoService;

    @PostMapping("/create")
    public Result create(@Valid @RequestBody CreateForm createForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("创建订单...");
        mallPayInfoService.create(createForm.getOrderId(), createForm.getAmount(), createForm.getPayTypeEnum());
        request.setAttribute("orderId", createForm.getOrderId());
        request.getRequestDispatcher("/notify").forward(request, response);
        return Result.ok("订单创建成功");

    }

    @PostMapping("/notify")
    public Result asyncNotify(HttpServletRequest request) {
        String orderId = (String) request.getAttribute("orderId");

        String asyncNotify = mallPayInfoService.asyncNotify(orderId);
        return Result.ok(asyncNotify);
    }

    @PostMapping("/queryByOrderId")
    public Result queryByOrderId(@Valid @RequestBody QueryByOrderIdForm form) {
        log.info("查询支付记录...");
        MallPayInfo payInfo = mallPayInfoService.queryByOrderId(form.getOrderId());
        return Result.ok("查询成功").put("patInfo", payInfo);
    }
}
