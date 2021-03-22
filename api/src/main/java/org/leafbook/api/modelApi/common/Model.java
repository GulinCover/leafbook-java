package org.leafbook.api.modelApi.common;

import lombok.Data;

import java.util.Date;

@Data
public class Model {
    private Long updateTime;
    private Long publicTime;
    private Integer version;
    private Integer isBlack;
}
