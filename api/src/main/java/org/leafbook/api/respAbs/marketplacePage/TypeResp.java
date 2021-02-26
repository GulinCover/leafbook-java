package org.leafbook.api.respAbs.marketplacePage;

import lombok.Data;

import java.util.List;

@Data
public class TypeResp {
    private String code;
    private List<String> typeList;
}
