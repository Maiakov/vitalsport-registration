package com.vitalsport.registration.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vitalsport.registration.model.Status;

import java.io.IOException;

public class StatusSerialization extends JsonSerializer<Status> {

    @Override
    public void serialize(Status value, JsonGenerator generator,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        generator.writeStartObject();
        generator.writeFieldName("status");
        generator.writeString(value.name());
        generator.writeFieldName("message");
        generator.writeString(value.getMessage());
        generator.writeEndObject();
    }
}
