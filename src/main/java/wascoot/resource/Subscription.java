package wascoot.resource;

import java.sql.Time;

public class Subscription {

    private  int id;
    private final String type;
    private final int dailyUnlocks;
    private final Time validityPerDay;
    private final double fixedPrice;

    public Subscription(int id, final String type, final int dailyUnlocks, final Time validityPerDay, final double fixedPrice){
        this.id = id;
        this.type = type;
        this.dailyUnlocks = dailyUnlocks;
        this.validityPerDay = validityPerDay;
        this.fixedPrice = fixedPrice;
    }

    public Subscription(final String type, final int dailyUnlocks, final Time validityPerDay, final double fixedPrice){
        this.type = type;
        this.dailyUnlocks = dailyUnlocks;
        this.validityPerDay = validityPerDay;
        this.fixedPrice = fixedPrice;
    }

    public int getId(){ return id; }
    public final String getType(){ return type; }
    public final int getDailyUnlocks(){ return dailyUnlocks; }
    public final Time getValidityPerDay(){ return validityPerDay; }
    public final double getFixedPrice(){ return fixedPrice; }
}
