package Monsters;

public class Monster {
    private int hp,maxHP,attack,defense;
    public boolean tamed;
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public int getMaxHP(){
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void takeDMG(int damage) {
        if(hp > damage){
            hp -= damage;
        }else{
            //dead
        }
    }
}
