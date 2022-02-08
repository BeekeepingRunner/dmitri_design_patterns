package patterns.structural.chainofresponsibility.excercise;

import java.util.ArrayList;
import java.util.List;


public class Excercise {

    public static void main(String[] args) {

        final Game game = new Game();
        final Goblin goblin = new Goblin(game);

        game.creatures.add(goblin);
        System.out.println(goblin);

        game.creatures.add(new Goblin(game));
        System.out.println(goblin);

        final GoblinKing goblinKing = new GoblinKing(game);
        game.creatures.add(goblinKing);
        System.out.println(goblin);
        System.out.println(goblinKing);
    }
}

abstract class Creature
{
    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends Creature
{
    private Game game;

    protected int attack = 1;
    protected int defense = 1;

    public Goblin(Game game)
    {
        this.game = game;
    }

    @Override
    public int getAttack()
    {
        int attack = this.attack;
        for (Creature creature : game.creatures) {
            if (creature instanceof GoblinKing && creature != this) {
                attack += 1;
            }
        }
        return attack;
    }

    @Override
    public int getDefense()
    {
        int defense = this.defense;
        for (Creature creature : game.creatures) {
            if (creature instanceof Goblin && creature != this) {
                defense += 1;
            }
        }
        return defense;
    }

    @Override
    public String toString() {
        return "Goblin{" +
                "attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class GoblinKing extends Goblin
{

    public GoblinKing(Game game)
    {
        super(game);

        attack += 3;
        defense += 3;
    }

    @Override
    public String toString() {
        return "GoblinKing{" +
                "attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}
