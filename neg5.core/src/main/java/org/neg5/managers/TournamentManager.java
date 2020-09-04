package org.neg5.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import org.neg5.TournamentDTO;
import org.neg5.core.CurrentUserContext;
import org.neg5.core.UserData;
import org.neg5.daos.TournamentDAO;
import org.neg5.data.Tournament;

import org.neg5.mappers.TournamentMapper;

@Singleton
public class TournamentManager extends AbstractDTOManager<Tournament, TournamentDTO, String> {

    private final TournamentDAO rwTournamentDAO;
    private final TournamentMapper tournamentMapper;
    private final CurrentUserContext currentUserContext;

    @Inject
    public TournamentManager(TournamentDAO rwTournamentDAO,
                             TournamentMapper tournamentMapper,
                             CurrentUserContext currentUserContext) {
        this.rwTournamentDAO = rwTournamentDAO;
        this.tournamentMapper = tournamentMapper;
        this.currentUserContext = currentUserContext;
    }

    @Transactional
    @Override
    public TournamentDTO create(TournamentDTO tournament) {
        UserData currentUser = currentUserContext.getUserDataOrThrow();
        tournament.setDirectorId(currentUser.getUsername());
        TournamentDTO createdTournament = super.create(tournament);

        return createdTournament;
    }

    @Override
    protected TournamentDAO getRwDAO() {
        return rwTournamentDAO;
    }

    @Override
    protected TournamentMapper getMapper() {
        return tournamentMapper;
    }

    @Override
    protected String getIdFromDTO(TournamentDTO tournamentDTO) {
        return tournamentDTO.getId();
    }
}
