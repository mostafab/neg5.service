package org.neg5.mappers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.neg5.MatchPlayerDTO;
import org.neg5.data.MatchPlayer;

import java.util.stream.Collectors;

@Singleton
public class MatchPlayerMapper extends AbstractObjectMapper<MatchPlayer, MatchPlayerDTO> {

    @Inject private MatchPlayerAnswerMapper matchPlayerAnswerMapper;

    protected MatchPlayerMapper() {
        super(MatchPlayer.class, MatchPlayerDTO.class);
    }

    @Override
    protected void enrichDTO(MatchPlayerDTO matchPlayerDTO, MatchPlayer matchPlayer) {
        matchPlayerDTO.setAnswers(matchPlayer.getAnswers().stream()
                .map(answer -> matchPlayerAnswerMapper.toDTO(answer)).collect(Collectors.toSet())
        );
    }
}
