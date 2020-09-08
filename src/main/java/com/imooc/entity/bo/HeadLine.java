package com.imooc.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author hailin.tang
 * @date 2020/9/6 2:01 上午
 * @function
 */
@Data
public class HeadLine {
    private Long lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

}
