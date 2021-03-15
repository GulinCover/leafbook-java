package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class TypeResp {
    private Integer code;
    private List<String> typeList;
}
