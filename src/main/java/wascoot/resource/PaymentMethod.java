package wascoot.resource;

public class PaymentMethod {

    private final int id;
    private final String type;
    private final String activation;

    public PaymentMethod(final int id, final String type, final String activation){
        this.id = id;
        this.type = type;
        this.activation = activation;
    }

    public final int getId(){ return id; }
    public final String getType(){ return type; }
    public final String getActivation(){ return activation; }
}
