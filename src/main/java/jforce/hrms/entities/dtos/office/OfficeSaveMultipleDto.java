package jforce.hrms.entities.dtos.office;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeSaveMultipleDto {
    private List<String> officeNames;
}
