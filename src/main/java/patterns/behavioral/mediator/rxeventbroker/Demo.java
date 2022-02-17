package patterns.behavioral.mediator.rxeventbroker;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import java.util.ArrayList;
import java.util.List;

class EventBroker extends Observable<Integer>
{
    private List<Observer<? super Integer>> observers = new ArrayList<>();

    @Override
    protected void subscribeActual(@NonNull Observer<? super Integer> observer) {
        observers.add(observer);
    }

    public void publish(int n) {
        for (Observer<? super Integer> o : observers)
            o.onNext(n);
    }
}

class FootballPlayer
{
    public String name;
    private int goalsScored = 0;
    private EventBroker broker;

    public FootballPlayer(String name, EventBroker broker) {
        this.name = name;
        this.broker = broker;
    }

    public void score()
    {
        broker.publish(++goalsScored);
    }
}

class FootballCoach
{
    public FootballCoach(EventBroker broker) {
        broker.subscribe(i -> {
            System.out.println("Hey, you scored " + i + " goals!");
        });
    }
}

public class Demo {

    public static void main(String[] args) {

        final EventBroker broker = new EventBroker();
        final FootballPlayer player = new FootballPlayer("jones", broker);
        final FootballCoach coach = new FootballCoach(broker);

        player.score();
        player.score();
        player.score();
    }
}
