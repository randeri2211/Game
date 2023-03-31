package Monsters;
import static com.badlogic.gdx.math.MathUtils.random;

public class Monster {
    private int hp,maxHP,attack,defense;
    public boolean tamed;

    public Monster(){
        hp=random(10,15);
        maxHP=random(10,15);
        attack=random(10,15);
        defense=random(10,15);
    }

    public Monster(int _hp,int _maxHP,int _attack,int _defense){
//        hp=_hp;
//        maxHP=_maxHP;
//        attack=_attack;
//        defense=_defense
    }

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
