package org.leafbook.api.modelApi.policyInfo;

import lombok.Data;
import org.leafbook.api.modelApi.common.Model;

@Data
public class PolicyModel extends Model {
    private Long policyModelId;
    private String content;
}
