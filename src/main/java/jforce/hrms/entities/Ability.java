package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="abilities")
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    @JsonIgnore
    private Information information;

    @Column(name = "ability_name", nullable = false)
    private String abilityName;

    public Ability(Information information, String abilityName){
        this.information = information;
        this.abilityName = abilityName;
    }
}