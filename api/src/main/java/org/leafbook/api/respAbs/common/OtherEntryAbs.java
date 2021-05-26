package org.leafbook.api.respAbs.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class OtherEntryAbs implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long entryId;
    private String entryName;
}
