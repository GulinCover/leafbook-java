package org.leafbook.api.custom;

import lombok.Data;

@Data
public class JWT {
    private String userId;
    private String csrf;
}
