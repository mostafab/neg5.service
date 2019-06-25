package org.neg5.data.converters;

import com.google.gson.Gson;
import org.modelmapper.TypeToken;
import org.neg5.ScoresheetCycleDTO;
import org.neg5.core.GsonProvider;

import javax.persistence.AttributeConverter;
import java.util.List;

public class ScoresheetCycleListConverter implements AttributeConverter<List<ScoresheetCycleDTO>, String> {

    private final Gson gson = new GsonProvider().get();

    @Override
    public List<ScoresheetCycleDTO> convertToEntityAttribute(String s) {
        return gson.fromJson(s, new TypeToken<List<ScoresheetCycleDTO>>(){}.getType());
    }

    @Override
    public String convertToDatabaseColumn(List<ScoresheetCycleDTO> scoresheetCycleDTOS) {
        return gson.toJson(scoresheetCycleDTOS);
    }
}
