package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="inventories")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    @JsonIgnore
    private Information information;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "deliverer_staff", nullable = false)
    private String delivererStaff;

    @Column(name = "start_year", nullable = false)
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    public Inventory(Information information, String itemName, String delivererStaff, int startYear, int endYear){
        this.information = information;
        this.itemName = itemName;
        this.delivererStaff = delivererStaff;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
