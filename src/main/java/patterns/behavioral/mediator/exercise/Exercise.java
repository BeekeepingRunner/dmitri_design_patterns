package patterns.behavioral.mediator.exercise;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    int value = 0;
    Mediator mediator;

    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public void say(int n)
    {
        this.mediator.broadcast(this, n);
    }

    public void receive(int n) {
        this.value += n;
    }
}

class Mediator
{
    public List<Participant> participants = new ArrayList<>();

    public void broadcast(Participant sender, int value) {
        for (Participant p : participants) {
            if (p != sender)
                p.receive(value);
        }
    }
}

public class Exercise {

    public static void main(String[] args) {

        final Mediator mediator = new Mediator();
        final Participant p1 = new Participant(mediator);
        final Participant p2 = new Participant(mediator);

        mediator.participants.add(p1);
        mediator.participants.add(p2);

        System.out.println("P1 value = " + p1.value);
        System.out.println("P2 value = " + p2.value);

        p1.say(3);
        System.out.println("P1 broadcasts value 3");
        System.out.println("P1 value = " + p1.value);
        System.out.println("P2 value = " + p2.value);

        p2.say(2);
        System.out.println("P2 broadcasts value 2");
        System.out.println("P1 value = " + p1.value);
        System.out.println("P2 value = " + p2.value);
    }
}
