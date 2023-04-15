package wascoot.resource;

import com.fasterxml.jackson.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class Model {
    /**
     * The identifier of the model PK
     */
    private final String name;

    /**
     * The brand of the model
     */
    private final String brand;

    /**
     * The battery life of a model
     */
    private final String batteryLife;
    /**
     * The rate per minute of a model
     */
    private final double pricePerMin;


    /**
     * Creates a new model
     *
     * @param name
     *            the name of a model.
     * @param brand
     *            the brand of a model.
     * @param batteryLife
     *            the battery life of a model.
     * @param pricePerMin
     *            the rate_per_minute a model.

     */
    public Model(final String name, final String brand, final String batteryLife, final double pricePerMin) {
        this.name = name;
        this.brand = brand;
        this.batteryLife = batteryLife;
        this.pricePerMin = pricePerMin;
    }

    /**
     * Returns the name of the model.
     *
     * @return the name of the model.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the brand of the model.
     *
     * @return the brand of the model.
     */
    public final String getBrand() {
        return brand;
    }

    /**
     * Returns the battery life of the model.
     *
     * @return the battery life of the model.
     */
    public final String getBatteryLife() {
        return batteryLife;
    }

    /**
     * Returns the weight of the model.
     *
     * @return the weight of the model.
     */

    /**
     * Returns the rate per minute of the model.
     *
     * @return the rate per minute of the model.
     */
    public final double getPricePerMin() {
        return pricePerMin;
    }


}
