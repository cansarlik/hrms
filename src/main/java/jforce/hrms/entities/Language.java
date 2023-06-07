package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="languages")
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    @JsonIgnore
    private Information information;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @Column(name = "language_level", nullable = false)
    private int languageLevel;

    public Language(Information information, String languageName, int languageLevel){
        this.information = information;
        this.languageName = languageName;
        this.languageLevel = languageLevel;
    }
}
