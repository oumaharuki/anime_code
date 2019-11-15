package com.anime.framework.exception;

import com.anime.framework.model.response.ResultCode;

/**异常
 * @author
 * @date 2019-10-22
 */
public class CustomException extends RuntimeException {

    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super("错误代码："+resultCode.code()+"错误信息："+resultCode.message());
        this.resultCode=resultCode;
    }

    public ResultCode getResultCode(){
        return this.resultCode;
    }
}
