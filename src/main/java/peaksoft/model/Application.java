package peaksoft.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.enums.AppStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import java.time.LocalDate;

@Entity
@Table(name = "application")
@Getter
@Setter
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String developer;
    private String version;
    private AppStatus appStatus; //enum
    private String genreName;
    private LocalDate createDate;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "applications")
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "applications")
    private List<User> user;



}
