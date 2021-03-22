package org.leafbook.api.modelApi.common;

import lombok.Data;

import java.util.Date;

@Data
public class CodeModel {
    private Long codeId;
    private String code;
    private String phone;
    private String email;

    private Long timestamp;
}
