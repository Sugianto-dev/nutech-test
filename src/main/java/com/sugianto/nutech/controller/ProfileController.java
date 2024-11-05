package com.sugianto.nutech.controller;

import com.sugianto.nutech.request.profile.UpdateProfileRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.profile.ProfileResponse;
import com.sugianto.nutech.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public WebResponse<ProfileResponse> getProfile() {
        return profileService.getProfile();
    }

    @PutMapping("/update")
    public WebResponse<ProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        return profileService.updateProfile(request);
    }

    @PutMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WebResponse<ProfileResponse> updateProfileImage(@RequestPart(name = "file") MultipartFile multipartFile) throws IOException {
        return profileService.updateProfileImage(multipartFile);
    }

}
