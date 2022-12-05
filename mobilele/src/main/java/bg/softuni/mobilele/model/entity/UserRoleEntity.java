package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum userRoleEnum;

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserRoleEntity setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }

    public long getId() {
        return id;
    }

    public UserRoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", userRoleEnum=" + userRoleEnum +
                '}';
    }
}