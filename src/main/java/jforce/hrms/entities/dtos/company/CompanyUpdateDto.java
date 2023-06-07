package jforce.hrms.entities.dtos.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {
    private int userId;
    private String companyName;
    private String email;
    private String website;
    private String phone;
}
