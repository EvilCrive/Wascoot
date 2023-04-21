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
     * The weight of a model
     */
    private final int weight;

    /**
     * The height of a model
     */
    private final int height;

    /**
     * The length of a model
*/
    private final int length;

    /**
     * The depth of a model
     */
    private final int depth;

    /**
     * The rate per minute of a model
     */
    private final double ratePerMin;

    /**
     * The rate per model
     */
    private final double ratePerModel;

    /**
     * Creates a new model
     *
     * @param name
     *            the name of a model.
     * @param brand
     *            the brand of a model.
     * @param batteryLife
     *            the battery life of a model.
     * @param weight
     *            the weight of a model.
     * @param height
     *            the height of a model.
     * @param length
     *            the length of a model.
     * @param depth
     *            the depth of a model.
     * @param ratePerMin
     *            the rate_per_minute a model.
     * @param ratePerModel
     *            the rate_per_model.
     */
    public Model(final String name, final String brand, final String batteryLife, final int weight, final int height, final int length, final int depth, final double ratePerMin, final double ratePerModel) {
        this.name = name;
        this.brand = brand;
        this.batteryLife = batteryLife;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
        this.ratePerMin = ratePerMin;
        this.ratePerModel = ratePerModel;
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
    public final int getWeight() {
        return weight;
    }

    /**
     * Returns the height of the model.
     *
     * @return the height of the model.
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Returns the length of the model.
     *
     * @return the length of the model.
     */
    public final int getLength() {
        return length;
    }

    /**
     * Returns the depth of the model.
     *
     * @return the depth of the model.
     */
    public final int getDepth() {
        return depth;
    }

    /**
     * Returns the rate per minute of the model.
     *
     * @return the rate per minute of the model.
     */
    public final double getRatePerMin() {
        return ratePerMin;
    }

    /**
     * Returns the rate per model.
     *
     * @return the rate per odel.
     */
    public final double getRatePerModel() {
        return ratePerModel;
    }




    /* FOR REST API but commented coz didnt work


    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("model");

        jg.writeStartObject();

        jg.writeStringField("name", name);

        jg.writeStringField("brand", brand);

        jg.writeStringField("battery_life", battery_life);

        jg.writeNumberField("weight", weight);

        jg.writeNumberField("height", height);

        jg.writeNumberField("length", length);

        jg.writeNumberField("depth", depth);

        jg.writeNumberField("rate_per_min", rate_per_min);

        jg.writeNumberField("rate_per_model", rate_per_model);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    /**
     * Creates a {@code Model} from its JSON representation.
     *
     * @param in the input stream containing the JSON document.
     *
     * @return the {@code Model} created from the JSON representation.
     *
     * @throws IOException if something goes wrong while parsing.
     */


    public static Model fromJSON(final InputStream in) throws IOException {

        // the fields read from JSON
        String jName = null;
        String jBrand = null;
        String jBattery_life = null;
        int jWeight = -1;
        int jHeight = -1;
        int jLength = -1;
        int jDepth = -1;
        double jRate_per_min = -1.0;
        double jRate_per_model = -1.0;

        final JsonParser jp = JSON_FACTORY.createParser(in);

        // while we are not on the start of an element or the element is not
        // a token element, advance to the next element (if any)
        while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "model".equals(jp.getCurrentName()) == false) {

            // there are no more events
            if (jp.nextToken() == null) {
                throw new IOException("Unable to parse JSON: no models object found.");
            }
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {

            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "name":
                        jp.nextToken();
                        jName = jp.getText();
                        break;
                    case "brand":
                        jp.nextToken();
                        jBrand = jp.getText();
                        break;
                    case "battery_life":
                        jp.nextToken();
                        jBattery_life = jp.getText();
                        break;
                    case "weight":
                        jp.nextToken();
                        jWeight= jp.getIntValue();
                        break;
                    case "height":
                        jp.nextToken();
                        jHeight = jp.getIntValue();
                        break;
                    case "length":
                        jp.nextToken();
                        jLength= jp.getIntValue();
                        break;
                    case "depth":
                        jp.nextToken();
                        jDepth = jp.getIntValue();
                        break;
                    case "rate_per_min":
                        jp.nextToken();
                        jRate_per_min= jp.getDoubleValue();
                        break;
                    case "rate_per_model":
                        jp.nextToken();
                        jRate_per_model = jp.getDoubleValue();
                        break;
                }
            }
        }

        return new Model(jName, jBrand, jBattery_life, jWeight, jHeight, jLength, jDepth, jRate_per_min, jRate_per_model);
    }



}
