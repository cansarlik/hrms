package jforce.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="project_announce")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAnnounce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @Column(name="description",nullable=false, length = 1000)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne()
    @JoinColumn(name = "working_time_id")
    private WorkingTime workingTime;

    @ManyToOne()
    @JoinColumn(name = "title_id")
    private Title title;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name="release_date",nullable=false)
    private Date releaseDate;

    @Column(name="deadline",nullable=false)
    private Date deadline;

    @Column(name="min_salary",nullable=true)
    private int minSalary;

    @Column(name="max_salary",nullable=true)
    private int maxSalary;

    @Column(name="open_titles_amount",nullable=false)
    private int openTitlesAmount;

    @Column(name="active",nullable=false)
    private boolean active;

    @Column(name="confirmed",nullable=false)
    private boolean confirmed;

    public ProjectAnnounce(String description, Date releaseDate, Date deadline, int minSalary, int maxSalary, int openTitlesAmount, boolean active, boolean confirmed) {
        this.description = description;
        this.releaseDate = releaseDate;
        this.deadline = deadline;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.openTitlesAmount = openTitlesAmount;
        this.active = active;
        this.confirmed = confirmed;
    }
}
