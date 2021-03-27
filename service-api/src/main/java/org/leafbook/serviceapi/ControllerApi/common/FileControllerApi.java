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

    /**
     * 文件上传
     * @param userId
     * @param file
     * @return
     */
    @ApiOperation("/api/post/insert/picture")
    @PostMapping("/api/post/insert/picture")
    public UploadPictureResp postInsertPictureApi(@RequestHeader("userId")Long userId, @RequestParam("file")MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        String url = fileServiceApi.postInsertPicture();
        UploadPictureResp resp = new UploadPictureResp();
        resp.setCode(200);
        resp.setFileUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2809052415,518307309&fm=26&gp=0.jpg");
        return resp;
    }
}
