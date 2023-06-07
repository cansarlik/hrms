package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="project_experiences")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    @JsonIgnore
    private Information information;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "title_name", nullable = false)
    private String titleName;

    @Column(name = "start_year", nullable = false)
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    public ProjectExperience(Information information, String companyName, String titleName, int startYear, int endYear){
        this.information = information;
        this.companyName = companyName;
        this.titleName = titleName;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
