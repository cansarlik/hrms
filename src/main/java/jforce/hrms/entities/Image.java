package jforce.hrms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="images")
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @ManyToOne(targetEntity = Information.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    @JsonIgnore
    private Information information;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public Image(Information information, String imageUrl){
        this.information = information;
        this.imageUrl = imageUrl;
    }
}
