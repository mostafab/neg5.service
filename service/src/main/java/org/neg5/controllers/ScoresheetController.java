package org.neg5.controllers;

import com.google.inject.Inject;
import org.neg5.ScoresheetDTO;
import org.neg5.annotations.Controller;
import org.neg5.managers.ScoresheetManager;

@Controller("/neg5-api/scoresheets")
public class ScoresheetController extends AbstractJsonController {

    @Inject private ScoresheetManager scoresheetManager;

    @Override
    public void registerRoutes() {
        post("", ScoresheetDTO.class, scoresheet -> (req, res) -> scoresheetManager.create(scoresheet));
    }
}
