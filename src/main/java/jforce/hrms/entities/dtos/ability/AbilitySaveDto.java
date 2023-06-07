package jforce.hrms.entities.dtos.ability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilitySaveDto {
    private int informationId;

    @NotEmpty
    @NotBlank
    private String abilityName;
}
