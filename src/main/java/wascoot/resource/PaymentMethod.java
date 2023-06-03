package wascoot.resource;

public class PaymentMethod implements Comparable<PaymentMethod> {

    private int id;
    private final String type;
    private final String activation;

    public PaymentMethod(final int id, final String type, final String activation){

        this.id = id;
        this.type =type;
        this.activation = activation;
    }

    public PaymentMethod(final String type, final String activation){

        this.type = type;
        this.activation = activation;
    }

    public int getId(){ return id;}
    public final String getType(){ return type; }
    public final String getActivation(){ return activation; }


    @Override
    public int compareTo(PaymentMethod o) {
        if (this.id < o.id) return -1;
        if (this.id == o.id)  return 0;
        return 1;
    }
}
