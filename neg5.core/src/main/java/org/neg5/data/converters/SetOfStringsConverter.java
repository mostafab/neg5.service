package org.neg5.data.converters;

import com.google.gson.Gson;
import org.modelmapper.TypeToken;
import org.neg5.core.GsonProvider;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Set;

@Converter
public class SetOfStringsConverter implements AttributeConverter<Set<String>, String> {

    private final Gson gson = new GsonProvider().get();

    @Override
    public Set<String> convertToEntityAttribute(String s) {
        return gson.fromJson(s, new TypeToken<Set<String>>(){}.getType());
    }

    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        return gson.toJson(strings);
    }
}
