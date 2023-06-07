package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties
    private User user;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private Information information;

    @ManyToOne()
    @JoinColumn(name = "title_id")
    @JsonIgnoreProperties
    private Title title;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="identity_no", nullable = false, unique = true)
    private String identityNo;

    @Column(name="birth_year", nullable = false)
    private int birthYear;

    public Employee(int userId, String firstName, String lastName, String identityNo, int birthYear, Title title) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNo = identityNo;
        this.birthYear = birthYear;
        this.title = title;
    }
}
