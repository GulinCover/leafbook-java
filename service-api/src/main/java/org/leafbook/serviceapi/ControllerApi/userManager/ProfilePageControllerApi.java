package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import org.leafbook.serviceapi.serviceApi.userManager.ProfilePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("ProfilePageControllerApi")
@RestController
public class ProfilePageControllerApi {
    @Autowired
    private ProfilePageServiceApi profilePageServiceApi;



}











