package top.fanzhengke.mipay.config;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fanzhengke.mipay.common.utils.Result;
import top.fanzhengke.mipay.exception.PayException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("执行异常", e);
        JSONObject json = new JSONObject();
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            json.set("error", exception.getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof PayException) {
            PayException exception = (PayException) e;
            json.set("error", exception.getMsg());
        } else {
            json.set("error", "服务端异常");
        }
        return Result.error(json.toString());
    }
}
