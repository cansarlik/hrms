package jforce.hrms.entities.dtos.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventorySaveDto {
    private int informationId;

    @NotEmpty
    @NotBlank
    private String itemName;

    @NotEmpty
    @NotBlank
    private String delivererStaff;

    @NotEmpty
    @NotBlank
    private int startYear;


    private int endYear;
}
