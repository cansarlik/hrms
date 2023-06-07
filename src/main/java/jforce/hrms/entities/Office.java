package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="offices")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "projectAnnounces"})
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @Column(name="office_name",nullable=false)
    private String officeName;

    @OneToMany(mappedBy="office",fetch = FetchType.LAZY)
    private List<ProjectAnnounce> projectAnnounces;

    public Office(String officeName){
        this.officeName = officeName;
    }
}