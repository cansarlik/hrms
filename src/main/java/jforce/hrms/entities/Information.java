package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name="informations")
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @OneToOne()
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties
    private Employee employee;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "description", length = 1000)
    private String description;



    @OneToMany(mappedBy = "information")
    @JsonIgnoreProperties
    private List<Ability> abilities;

    @OneToMany(mappedBy = "information")
    @JsonIgnoreProperties
    private List<Image> images;

    @OneToMany(mappedBy = "information")
    @JsonIgnoreProperties
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "information")
    @JsonIgnoreProperties
    private List<ProjectExperience> projectExperiences;

    @OneToMany(mappedBy = "information")
    @JsonIgnoreProperties
    private List<Language> languages;


    public Information(Employee employee, String githubUrl, String linkedinUrl, String description) {
        this.employee = employee;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
        this.description = description;
    }
}
