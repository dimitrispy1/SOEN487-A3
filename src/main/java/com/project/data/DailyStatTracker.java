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

    // TODO: for runJob
    //Get all the stats for the current data (stats for all players that played today)
    // Update daily stat table to hold these values ------- GET STAT MAP
    // Go thru all the teams, and add up the points for based on today's score
    //When done update all team stats DB write


    //Schedule to run once a day
    @Scheduled(fixedDelay = 86400000, initialDelay = 86400000)
    public void runDailyJob(){

        HashMap<String, DailyStats> dailyLeagueStats = this.api.getDailyStats();

        List<Team> teams = teamService.findAll();
        for (Team team: teams) {
            updateTeamStats(team, dailyLeagueStats);
        }
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

        System.out.println("Recording Daily stat for team " + team.getId());
        teamService.updateTeam(team);

    }

}
