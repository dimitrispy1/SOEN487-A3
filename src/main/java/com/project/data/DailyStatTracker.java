package com.project.data;

import com.project.interfaces.ITeamService;
import com.project.model.DailyStats;
import com.project.model.PlayerTeam;
import com.project.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;

@Component
public class DailyStatTracker {

    @Autowired
    private PlayerDataApi api;

    @Autowired
    private ITeamService teamService;

    public DailyStatTracker(){}

    //Schedule to run once a day
    @Scheduled(fixedDelay = 86400000, initialDelay = 86400000)
    public void runDailyJob(){

        System.out.println("Starting background process: retrieving and updating daily stats for teams");
        HashMap<String, DailyStats> dailyLeagueStats = this.api.getDailyStats();

        List<Team> teams = teamService.findAll();
        for (Team team: teams) {
            System.out.println("Calculating team score for: " + team.getName());
            updateTeamStats(team, dailyLeagueStats);
        }
        System.out.println("Completed background process");
    }

    private void updateTeamStats(Team team, HashMap<String, DailyStats> dailyStats){
        int totalPts = team.getTotalPts();
        int totalAssists = team.getTotalAssists();
        int totalRebounds = team.getTotalRebounds();

        for(PlayerTeam p: team.getPlayers()){
            String playerId = p.getPlayer().getId() + "";
            if(dailyStats.containsKey(playerId)){
                //This player, played today
                DailyStats d = dailyStats.get(playerId);
                totalPts += d.getTotalPts();
                totalAssists += d.getTotalAssists();
                totalRebounds += d.getTotalRebounds();
            }
        }
        team.setTotalPts(totalPts);
        team.setTotalAssists(totalAssists);
        team.setTotalRebounds(totalRebounds);

        teamService.updateTeam(team);

    }

}
