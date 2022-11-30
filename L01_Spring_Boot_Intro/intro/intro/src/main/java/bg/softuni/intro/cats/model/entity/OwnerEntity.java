package bg.softuni.intro.cats.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String ownerName;
    @OneToMany(mappedBy = "owner",targetEntity = CatEntity.class,cascade = CascadeType.ALL)
    private List<CatEntity> cats = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public OwnerEntity addCat(CatEntity cat) {
        this.cats.add(cat);
        return this;
    }
    public OwnerEntity setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public List<CatEntity> getCats() {
        return cats;
    }

    public OwnerEntity setCats(List<CatEntity> cats) {
        this.cats = cats;
        return this;
    }
}
