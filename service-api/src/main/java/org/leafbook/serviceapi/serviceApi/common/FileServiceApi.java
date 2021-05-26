package org.leafbook.serviceapi.serviceApi.common;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@GlobalTransactional
@Service
public class FileServiceApi {
    public String postInsertPicture() {
        return "dsadasa";
    }
}
