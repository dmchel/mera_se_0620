package game.spells;

import game.Character;
import game.Magician;
import game.Scene;

public class MostersExpelling extends Spell {

    public MostersExpelling() {
        super("Изгнание монстров", 4);
    }

    @Override
    public void cast(Scene scene, Magician originator) {
        Character[] opponents = scene.findAllMonsters();
        System.out.println(originator.getTypeName() + " " + originator.getName() + " читает заклинание " + this.name + "..");
        if (opponents.length == 0) {
            System.out.println(" - Нет монстров.");
            return;
        }
        for (Character opponent : opponents) {
            opponent.takeDamage(this.damage);
            System.out.println(" * " + opponent.getTypeName() + " " + opponent.getName() + " поражен на " +
                    this.damage + ". Теперь у него " + opponent.getHealth() + " здоровья");
        }
    }

    public String description() {
        return name + ", здоровье -" + damage + " (всем монстрам)";
    }

}
