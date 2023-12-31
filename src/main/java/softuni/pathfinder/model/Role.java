package softuni.pathfinder.model;

import jakarta.persistence.*;
import softuni.pathfinder.model.enums.UserRolesEnum;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRolesEnum name;

    public Role(){}

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public UserRolesEnum getName() {
        return name;
    }

    public Role setName(UserRolesEnum name) {
        this.name = name;
        return this;
    }
}
