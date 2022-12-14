package bg.softuni.battleships.models.dtos;

import bg.softuni.battleships.models.enums.CategoryNames;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@ToString
public class CreateShipDTO {
    @NotEmpty
    @Size(min = 2, max = 10)
    private String name;
    @Positive
    private long power;
    @Positive
    private long health;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    @Positive
    private int category = -1;

    public String getName() {
        return name;
    }

    public CreateShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public CreateShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public CreateShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CreateShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public CreateShipDTO setCategory(int category) {
        this.category = category;
        return this;
    }
}
