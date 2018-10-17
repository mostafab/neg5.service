package org.neg5.data.transformers;

import com.google.gson.Gson;
import org.hibernate.transform.ResultTransformer;
import org.neg5.core.GsonProvider;
import org.neg5.data.transformers.data.Match;
import org.neg5.data.transformers.data.Phase;
import org.neg5.data.transformers.data.TeamInMatch;
import org.postgresql.util.PGobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchTransformer implements ResultTransformer {

    private final GsonProvider gsonProvider;

    public MatchTransformer() {
        gsonProvider = new GsonProvider();
    }

    @Override
    public Match transformTuple(Object[] tuple, String[] aliases) {
        Gson gson = gsonProvider.get();

        Match match = new Match();
        match.setId((String) tuple[1]);
        match.setTournamentId((String) tuple[2]);
        match.setRound((Integer) tuple[3]);
        match.setTossupsHeard((Integer) tuple[4]);
        match.setPhases(getPhases(gson, tuple));
        match.setTeams(getTeams(gson, tuple));
        return match;
    }

    @Override
    public List<Match> transformList(List collection) {
        return new ArrayList<>(collection);
    }

    private Set<Phase> getPhases(Gson gson, Object[] tuple) {
        return Arrays.stream((Object[]) tuple[0])
                .map(object -> {
                    PGobject phaseObj = (PGobject) object;
                    return gson.fromJson(phaseObj.getValue(), Phase.class);
                })
                .collect(Collectors.toSet());
    }

    private Set<TeamInMatch> getTeams(Gson gson, Object[] tuple) {
        return Arrays.stream((Object[]) tuple[5])
                .map(object -> {
                    PGobject phaseObj = (PGobject) object;
                    return gson.fromJson(phaseObj.getValue(), TeamInMatch.class);
                })
                .collect(Collectors.toSet());
    }
}
