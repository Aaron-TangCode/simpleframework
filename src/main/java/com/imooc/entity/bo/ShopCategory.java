package com.imooc.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author hailin.tang
 * @date 2020/9/6 1:56 上午
 * @function cvvfc
 */
@Data
public class ShopCategory {
    private Long shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private ShopCategory parent;

}
