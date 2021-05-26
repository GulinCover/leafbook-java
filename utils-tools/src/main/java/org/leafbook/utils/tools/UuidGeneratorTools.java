package org.leafbook.utils.tools;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class UuidGeneratorTools {
    public static String createGenerate() {
        return UUID.randomUUID().toString();
    }
}
