package jforce.hrms.entities.dtos.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageSaveDto {
    private int informationId;

    @NotEmpty
    @NotBlank
    private String languageName;

    @NotEmpty
    @NotBlank
    private int languageLevel;
}
