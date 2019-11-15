package com.anime.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.anime.framework.model.response.CommonCode;
import com.anime.framework.model.response.ResponseResult;
import com.anime.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author
 * @date 2019-10-22
 */
@ControllerAdvice //控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionCatch.class);

    //定义map
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPIONS;
    //定义map的builder
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder=ImmutableMap.builder();

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException c){
        LOGGER.error("catch exception:{}",c.getMessage(),c);
        ResponseResult responseResult = new ResponseResult(c.getResultCode());
        return responseResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e){
        LOGGER.error("catch exception:{}",e.getMessage(),e);
        if(EXCEPIONS==null){
            EXCEPIONS=builder.build();
        }
        ResultCode resultCode = EXCEPIONS.get(e.getClass());

        if(resultCode!=null){
            ResponseResult responseResult = new ResponseResult(resultCode);
            return responseResult;
        }else {

        }
        ResponseResult responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        return responseResult;
    }
    static {
        builder.put(HttpMessageNotReadableException.class,CommonCode.IINVALID_PARAM);
    }
}
