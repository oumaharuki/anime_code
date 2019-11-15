package com.anime.framework.exception;

import com.anime.framework.model.response.ResultCode;

/**
 * @author
 * @date 2019-10-22
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
