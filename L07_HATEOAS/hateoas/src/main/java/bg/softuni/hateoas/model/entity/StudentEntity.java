package bg.softuni.hateoas.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "students")
@Entity
public class StudentEntity extends BaseEntity {
    private String name;
    private int age;
    private boolean deleted;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<OrderEntity> orders;

    public String getName() {
        return name;
    }

    public StudentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public StudentEntity setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public StudentEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
