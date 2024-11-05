package com.sugianto.nutech.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProfileRequest {

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

}
