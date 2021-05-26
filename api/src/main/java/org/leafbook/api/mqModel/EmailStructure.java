package org.leafbook.api.mqModel;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailStructure implements Serializable {
    private String email;
    private String code;
}
