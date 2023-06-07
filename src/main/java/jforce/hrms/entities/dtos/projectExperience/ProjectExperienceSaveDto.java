package jforce.hrms.entities.dtos.projectExperience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExperienceSaveDto {
    private int informationId;

    @NotEmpty
    @NotBlank
    private String companyName;

    @NotEmpty
    @NotBlank
    private String titleName;

    @NotEmpty
    @NotBlank
    private int startYear;

    private int endYear;
}
