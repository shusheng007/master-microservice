package top.shusheng007.ordersevice.api.base;


import entity.ApiException;
import entity.BaseResponse;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public BaseResponse handleException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("************************************未知错误******************************************");
        log.error(httpServletRequest.getRequestURL().toString(), e);
        return ResultUtil.error(StatusCode.FAILED.getCode(), StatusCode.FAILED.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    public BaseResponse handleBusinessException(HttpServletRequest httpServletRequest, ApiException e) {
        log.error("************************************业务异常******************************************");
        log.error("异常:{} 请求URL:{}", e.toString(),httpServletRequest.getRequestURL());
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
