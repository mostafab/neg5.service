package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.TournamentCollaboratorDTO;
import org.neg5.enums.TournamentAccessLevel;
import org.neg5.managers.TournamentCollaboratorManager;
import org.neg5.security.TournamentAccessManager;
import org.neg5.util.RequestHelper;

public class TournamentCollaboratorController extends AbstractJsonController {

    private final TournamentCollaboratorManager collaboratorManager;
    private final TournamentAccessManager accessManager;
    private final RequestHelper requestHelper;

    @Inject
    public TournamentCollaboratorController(TournamentCollaboratorManager collaboratorManager,
                                            TournamentAccessManager accessManager,
                                            RequestHelper requestHelper) {
        this.collaboratorManager = collaboratorManager;
        this.accessManager = accessManager;
        this.requestHelper = requestHelper;
    }

    @Override
    public void registerRoutes() {
        post("", (request, response) -> {
            TournamentCollaboratorDTO collaborator = requestHelper
                    .readFromRequest(request, TournamentCollaboratorDTO.class);
            accessManager.requireAccessLevel(
                    collaborator.getTournamentId(),
                    TournamentAccessLevel.OWNER
            );
            return collaboratorManager.addOrUpdateCollaborator(collaborator);
        });
    }

    @Override
    protected String getBasePath() {
        return "/neg5-api/collaborators";
    }
}
