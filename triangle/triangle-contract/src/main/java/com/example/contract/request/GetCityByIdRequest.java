package com.example.contract.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wangqing 
 */
@Getter
@Setter
@ToString
public class GetCityByIdRequest implements Serializable {

    @NotNull(message = "id不能为空")
    private Integer id;
}
