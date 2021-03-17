package org.leafbook.api.modelApi;

import lombok.Data;

import java.util.Date;

@Data
public class CodeModel {
    private Long codeId;
    private Long userId;
    private String code;
    private String phone;
    private String email;

    private Long timestamp;
}
