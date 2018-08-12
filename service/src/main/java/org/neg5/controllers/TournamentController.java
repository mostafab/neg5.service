package org.neg5.controllers;


import com.google.inject.Inject;
import org.neg5.TournamentDTO;

import org.neg5.managers.TournamentManager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/neg5-api/tournaments")
public class TournamentController {

    @Inject
    private TournamentManager tournamentManager;

    @GetMapping(path = "/{id}")
    public TournamentDTO test(@PathVariable("id") String id) {
        return tournamentManager.get(id);
    }
}
