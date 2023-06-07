package jforce.hrms.entities.dtos.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanySaveDto {
    @NotEmpty
    @NotBlank
    private String companyName;

    @NotEmpty
    @NotBlank
    private String website;

    @NotEmpty
    @NotBlank
    private String phone;

    @NotEmpty
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String password;

    @NotEmpty
    @NotBlank
    private String passwordRetry;
}
