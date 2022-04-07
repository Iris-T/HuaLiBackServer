package cn.baobao.server.config;

import cn.baobao.server.pojo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Iris 2022/4/7
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 默认全局异常处理
     * @param e 异常对象
     * @return RespBean包含错误信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespBean exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        RespBean respBean = new RespBean();
        respBean.setCode(504);
        respBean.setMessage("服务器错误");
        respBean.setObj(e.getMessage());
        return respBean;
    }
}
