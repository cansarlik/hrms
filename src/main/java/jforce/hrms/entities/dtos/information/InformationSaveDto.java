package jforce.hrms.entities.dtos.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationSaveDto {
    private int employeeId;
    private String description;
    private String githubUrl;
    private String linkedinUrl;
}
