package cn.sevenlion.logistics.common.handler;

import cn.sevenlion.logistics.common.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:41 下午
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResult baseException(HttpServletRequest request, Exception exception) {
        return CommonResult.failed(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        log.error("参数传入错误! msg:{}", exception.getMessage());
        return CommonResult.failed("参数传入错误!,信息" + exception.getMessage());
    }
}
