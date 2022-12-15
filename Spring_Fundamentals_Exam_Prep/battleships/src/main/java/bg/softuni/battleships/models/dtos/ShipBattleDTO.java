package bg.softuni.battleships.models.dtos;

public class ShipBattleDTO {
    private Long id;
    private String name;
    private String power;
    private String health;

    public ShipBattleDTO setHealth(String health) {
        this.health = health;
        return this;
    }

    public String getHealth() {
        return health;
    }

    public Long getId() {
        return id;
    }

    public ShipBattleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipBattleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPower() {
        return power;
    }

    public ShipBattleDTO setPower(String power) {
        this.power = power;
        return this;
    }
}
