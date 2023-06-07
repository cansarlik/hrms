package jforce.hrms.entities.dtos.projectAnnounce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAnnounceUpdateDto {
    private int id;
    private boolean active;
    private String description;
    private int officeId;
    private int workingTimeId;
    private int titleId;
    private Date deadline;
    private int minSalary;
    private int maxSalary;
    private int openTitlesAmount;
}
