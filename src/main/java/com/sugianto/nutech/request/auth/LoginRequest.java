package com.sugianto.nutech.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Email(message = "Parameter email tidak sesuai format")
    @NotNull
    private String email;

    @Size(min = 8, message = "Password minimal 8 karakter")
    @NotNull
    private String password;

}
