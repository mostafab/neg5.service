package org.neg5.data.converters;

import com.google.gson.Gson;
import org.neg5.ScoresheetCycleDTO;
import org.neg5.core.GsonProvider;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ScoresheetCycleConverter implements AttributeConverter<ScoresheetCycleDTO, String> {

    private final Gson gson = new GsonProvider().get();

    @Override
    public String convertToDatabaseColumn(ScoresheetCycleDTO scoresheetCycleDTO) {
        return gson.toJson(scoresheetCycleDTO);
    }

    @Override
    public ScoresheetCycleDTO convertToEntityAttribute(String s) {
        return gson.fromJson(s, ScoresheetCycleDTO.class);
    }
}
