package wascoot.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import sun.jvm.hotspot.debugger.Address;
import wascoot.resource.Resource;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.OutputStream;

public class ResourceModel package wascoot.resource;

        -public class Model {
+import com.fasterxml.jackson.core.*;
+
        +import java.io.*;
+
        +public class Model extends Resource {
        /**
         * The identifier of the Model
         */
        public final String getModel() {
            return Model();
        }
+
        +    @Override
+    public void toJSON(OutputStream out) throws IOException {
            +
                    +        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);
            +
                    +        jg.writeStartObject();
            +
                    +        jg.writeFieldName("Model");
            +
                    +        jg.writeStartObject();
            +
                    +        jg.writeNumberField("name", name);
            +
                    +        jg.writeNumberField("brand", brand);
            +
                    +        jg.writeNumberField("battery-life", battery-life);
            +
                    +        jg.writeStringField("price-per-min", price-per-min);
            +
                    +        jg.writeEndObject();
            +
                    +        jg.writeEndObject();
            +
                    +        jg.flush();
            +    }
    }{
}
