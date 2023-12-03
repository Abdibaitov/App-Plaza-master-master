package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

//import java.time.LocalDate;
@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String describsion;
    private LocalDate createDate;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "genre")
    private List<Application> applications;

}
