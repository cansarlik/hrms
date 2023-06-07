package jforce.hrms.entities.dtos.projectAnnounce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAnnounceFilterDto {
    private String search;
    private List<Integer> titleIds;
    private List<Integer> officeIds;
    private List<Integer> workingTimeIds;
    private int minSalary;
    private int maxSalary;
}
