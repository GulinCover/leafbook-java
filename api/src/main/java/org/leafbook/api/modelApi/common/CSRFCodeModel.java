package org.leafbook.api.modelApi.common;

import lombok.Data;

@Data
public class CSRFCodeModel extends Model {
    private Long csrfId;
    private String code;
}
