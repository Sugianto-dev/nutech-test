package com.sugianto.nutech.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RegistrationRequest {

    @Email(message = "Parameter email tidak sesuai format")
    @NotNull
    private String email;

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @Size(min = 8, message = "Password minimal 8 karakter")
    @NotNull
    private String password;

}
