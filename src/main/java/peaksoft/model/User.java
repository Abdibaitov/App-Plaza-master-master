package peaksoft.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.enums.Role;
import peaksoft.model.enums.SubscriptionStatus;

import java.time.LocalDate;
import java.util.List;

//import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean subscribeToTheNewsletter;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus; //enum
    private LocalDate createData;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_applications",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "application_id"))
    private List<Application> applications;


}
