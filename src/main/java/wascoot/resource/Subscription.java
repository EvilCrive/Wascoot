package wascoot.resource;

public class Subscription {

    private final int id;
    private final String type;
    private final int dailyUnlocks;
    private final String validityPerDay;
    private final double fixedPrice;

    public Subscription(final int id, final String type, final int dailyUnlocks, final String validityPerDay, final double fixedPrice){
        this.id = id;
        this.type = type;
        this.dailyUnlocks = dailyUnlocks;
        this.validityPerDay = validityPerDay;
        this.fixedPrice = fixedPrice;
    }

    public final int getId(){ return id; }
    public final String getType(){ return type; }
    public final int getDailyUnlocks(){ return dailyUnlocks; }
    public final String getValidityPerDay(){ return validityPerDay; }
    public final double getFixedPrice(){ return fixedPrice; }
}
