package org.leafbook.serviceapi.ControllerApi.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.fileRelated.UploadPictureResp;
import org.leafbook.serviceapi.serviceApi.common.FileServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin("*")
@Api("FileControllerApi")
@RestController
public class FileControllerApi {
    @Autowired
    private FileServiceApi fileServiceApi;

    @ApiOperation("/api/post/insert/picture")
    @PostMapping("/api/post/insert/picture")
    public UploadPictureResp postInsertPictureApi(@RequestHeader("user_id")Long userId, @RequestParam("file")MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        String url = fileServiceApi.postInsertPicture();
        UploadPictureResp resp = new UploadPictureResp();
        resp.setCode(HttpStatus.OK.toString());
        resp.setFileUrl("http://localhost/api/get/select/picture/"+url);
        return resp;
    }
}
