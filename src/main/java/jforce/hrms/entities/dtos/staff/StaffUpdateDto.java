package jforce.hrms.entities.dtos.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUpdateDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
}
