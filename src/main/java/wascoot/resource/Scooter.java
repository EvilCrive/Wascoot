package wascoot.resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

public class Scooter {
    package wascoot.resource;


    public class Scooter {
import com.fasterxml.jackson.core .*;

import java.io .*;

        public class Scooter extends Resource {
            /**
             * The identifier of the scooter  (PK)
             */
            public final String getAddress() {
                return address;
            }


            @Override


            public void toJSON(OutputStream out) throws IOException {

                final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

                jg.writeStartObject();

                jg.writeFieldName("scooter");

                jg.writeStartObject();

                jg.writeNumberField("id", id);

                jg.writeNumberField("DateOfPurchase", DateOfPurchase);

                jg.writeNumberField("KmTraveled", KmTraveled);

                jg.writeStringField("Model", Model);

                jg.writeStringField("ReaminingBatteryLife", RemainingBatteryLife);

                jg.writeEndObject();

                jg.writeEndObject();
                jg.flush();
            }
        }
    }
