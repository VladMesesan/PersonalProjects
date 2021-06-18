package GameRPG.Characters;

public class NPC extends Character{

    private int id;

    public NPC(int hp, int attackPower, String name, int experience, int level, int armour, int maxHp, int skillPoints, int id) {
        super(hp, attackPower, name, experience, level, armour, maxHp, skillPoints);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
