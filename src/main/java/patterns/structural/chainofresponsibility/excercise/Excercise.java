package patterns.structural.chainofresponsibility.excercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class Excercise {

    public static void main(String[] args) {

        Game game = new Game();
        Goblin g1 = new Goblin(game);
        game.creatures.add(g1);
        Goblin g2 = new Goblin(game);
        game.creatures.add(g2);

        final GoblinKing goblinKing = new GoblinKing(game);
        game.creatures.add(goblinKing);
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(goblinKing);
    }
}

class Event<Args>
{
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler)
    {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key)
    {
        handlers.remove(key);
    }

    public void fire(Args args)
    {
        for (Consumer<Args> handler : handlers.values())
            handler.accept(args);
    }
}

class Query
{
    public Creature creature;

    public Statistic statistic;

    public int result;

    public Query(Creature creature, Statistic statistic, int result) {
        this.creature = creature;
        this.statistic = statistic;
        this.result = result;
    }
}

class CreatureModifier
{
    protected Game game;
    protected Creature creature;
    protected int modifier;

    public CreatureModifier(Game game, Creature creature, int modifier) {
        this.game = game;
        this.creature = creature;
        this.modifier = modifier;
    }
}

class AttackModifier extends CreatureModifier
        implements AutoCloseable {

    private final int token;

    public AttackModifier(Game game, Creature creature, int modifier) {
        super(game, creature, modifier);

        token = game.queries.subscribe(query ->
        {
            if (query.creature.equals(creature)
                    && query.statistic == Statistic.ATTACK)
            {
                query.result += modifier;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class DefenseModifier extends CreatureModifier
        implements AutoCloseable {

    private final int token;

    public DefenseModifier(Game game, Creature creature, int modifier) {
        super(game, creature, modifier);

        token = game.queries.subscribe(query ->
        {
            if (query.creature.equals(creature)
                    && query.statistic == Statistic.DEFENSE)
            {
                query.result += modifier;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
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
        for (Creature creature : game.creatures)
        {
            if (creature instanceof Goblin)
            {
                new DefenseModifier(game, creature, 1);

                // because this goblin is not in the game yet
                new DefenseModifier(game, this, 1);
            }
        }
    }

    @Override
    public int getAttack()
    {
        Query q = new Query(this, Statistic.ATTACK, attack);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public int getDefense()
    {
        Query q = new Query(this, Statistic.DEFENSE, defense);
        game.queries.fire(q);
        return q.result;
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

        for (Creature creature : game.creatures)
        {
            if (creature instanceof Goblin)
            {
                new AttackModifier(game, creature, 1);

                // because this goblin is not in the game yet
                new AttackModifier(game, this, 1);
            }
        }

        attack += 2;
        defense += 2;
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
    public Event<Query> queries = new Event<>();

}
