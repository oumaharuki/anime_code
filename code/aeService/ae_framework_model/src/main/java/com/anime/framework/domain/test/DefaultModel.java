package com.anime.framework.domain.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author
 * @date 2019-11-14
 */
@Data
@ToString
public class DefaultModel {
    //id
    @ApiModelProperty("id")
    private String id;
}
