package org.leafbook.api.modelApi.common;

import lombok.Data;

@Data
public class CodeModel extends Model {
    private Long codeId;
    private String code;
    private String phone;
    private String email;
    private Integer codeType;//0:registry,1:email,2:password,

    private Long timestamp;
}
