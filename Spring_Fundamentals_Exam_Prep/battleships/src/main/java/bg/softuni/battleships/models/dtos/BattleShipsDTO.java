package bg.softuni.battleships.models.dtos;

import javax.validation.constraints.Positive;

public class BattleShipsDTO {
    @Positive
    private Long defenderId;
    @Positive
    private Long attackerId;

    public Long getDefenderId() {
        return defenderId;
    }

    public BattleShipsDTO setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
        return this;
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public BattleShipsDTO setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
        return this;
    }
}
