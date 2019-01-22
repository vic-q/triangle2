package com.example.contract.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangqing 
 */

@Getter
@Setter
public class Item implements Serializable {

    @NotNull(message = "item id 不能为空")
    private Integer id;

    @NotNull(message = "item name 不能为空")
    private String name;
}
