package org.neg5.mappers;

import com.google.inject.Singleton;
import org.neg5.ScoresheetDTO;
import org.neg5.data.Scoresheet;

@Singleton
public class ScoresheetMapper extends AbstractObjectMapper<Scoresheet, ScoresheetDTO> {

    protected ScoresheetMapper() {
        super(Scoresheet.class, ScoresheetDTO.class);
    }
}
