package patterns.structural.composite.neural;

import java.util.ArrayList;

class Neuron {

    public ArrayList<Neuron> in, out;

    public void connectTo(Neuron other) {
        out.add(other);
        other.in.add(this);
    }
}

class NeuronLayer extends ArrayList<Neuron> {


}

public class Demo {

    public static void main(String[] args) {

        Neuron neuron = new Neuron();
        Neuron neuron2 = new Neuron();

        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();
        
        neuron.connectTo(neuron2);
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer2);
    }
}