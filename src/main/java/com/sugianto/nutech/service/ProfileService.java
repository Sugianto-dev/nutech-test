package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.AppUser;
import com.sugianto.nutech.exception.AppUserNotFoundException;
import com.sugianto.nutech.exception.ImageFormatException;
import com.sugianto.nutech.repository.AppUserRepository;
import com.sugianto.nutech.request.profile.UpdateProfileRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.profile.ProfileResponse;
import com.sugianto.nutech.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class ProfileService {

    @Autowired
    AppUserRepository appUserRepository;

    public WebResponse<ProfileResponse> getProfile() {
        AppUser appUser = appUserRepository.findByEmail(SecurityUtil.getPayloadToken());

        ProfileResponse response = new ProfileResponse();
        response.setEmail(appUser.getEmail());
        response.setFirstName(appUser.getFirstName());
        response.setLastName(appUser.getLastName());
        response.setProfileImage(appUser.getProfileImage());

        return WebResponse.<ProfileResponse>builder()
                .status(0)
                .message("Sukses")
                .data(response)
                .build();
    }

    public WebResponse<ProfileResponse> updateProfile(UpdateProfileRequest request) {
        AppUser appUser = appUserRepository.findByEmail(SecurityUtil.getPayloadToken());

        if (appUser != null) {
            appUser.setFirstName(request.getFirstName());
            appUser.setLastName(request.getLastName());

            appUserRepository.save(appUser);
        } else {
            throw new AppUserNotFoundException("Data user tidak ditemukan");
        }

        ProfileResponse response = new ProfileResponse();
        response.setEmail(appUser.getEmail());
        response.setFirstName(appUser.getFirstName());
        response.setLastName(appUser.getLastName());
        response.setProfileImage(appUser.getProfileImage());

        return WebResponse.<ProfileResponse>builder()
                .status(0)
                .message("Update Profile berhasil")
                .data(response)
                .build();
    }

    public WebResponse<ProfileResponse> updateProfileImage(MultipartFile multipartFile) throws IOException {
        if (!isImage(multipartFile.getContentType())) {
            throw new ImageFormatException("Format Image tidak sesuai");
        }

        AppUser appUser = appUserRepository.findByEmail(SecurityUtil.getPayloadToken());

        if (appUser != null) {
            appUser.setProfileImage(multipartFile.getOriginalFilename());

            appUserRepository.save(appUser);
        } else {
            throw new AppUserNotFoundException("Data user tidak ditemukan");
        }

        ProfileResponse response = new ProfileResponse();
        response.setEmail(appUser.getEmail());
        response.setFirstName(appUser.getFirstName());
        response.setLastName(appUser.getLastName());
        response.setProfileImage(appUser.getProfileImage());

        Path path = Path.of("upload/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(path);

        return WebResponse.<ProfileResponse>builder()
                .status(0)
                .message("Update Profile Image berhasil")
                .data(response)
                .build();
    }

    private boolean isImage(String contentType) {
        return "image/jpeg".equals(contentType) || "image/png".equals(contentType);
    }

}
