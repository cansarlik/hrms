package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="favorite_projects")
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties
    private User user;

    @ManyToOne(targetEntity = ProjectAnnounce.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_announce_id")
    @JsonIgnoreProperties
    private ProjectAnnounce projectAnnounce;

    public FavoriteProject(User user, ProjectAnnounce projectAnnounce) {
        this.user = user;
        this.projectAnnounce = projectAnnounce;
    }
}
