package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="titles")
@AllArgsConstructor
@NoArgsConstructor
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @Column(name="title_name", nullable = false, unique = true)
    private String titleName;

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProjectAnnounce> projectAnnounces;

    public Title(String titleName){
        this.titleName = titleName;
    }
}
