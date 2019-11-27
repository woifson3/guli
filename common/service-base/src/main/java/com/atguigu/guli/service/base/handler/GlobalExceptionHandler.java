package com.atguigu.guli.service.base.handler;

import com.atguigu.guili.common.base.result.R;
import com.atguigu.guili.common.base.result.ResultCodeEnum;
import com.atguigu.guili.common.base.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice   //统一处理异常的
public class GlobalExceptionHandler {
//模板异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        // e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return R.error();
    }
//特定异常处理 ww
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        /*e.printStackTrace();*/
        log.error(ResultCodeEnum.JSON_PARSE_ERROR.toString());
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }
}