package org.leafbook.api.modelApi.common;

import lombok.Data;

import java.util.Date;

@Data
public class Model {
    private Date updateTime;
    private Date publicTime;
    private Integer version;
    private Integer isBlack;
}
