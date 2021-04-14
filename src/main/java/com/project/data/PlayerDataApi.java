package com.project.data;

import com.project.model.DailyStats;
import com.project.model.Player;
import com.project.util.ConfigLoader;
import com.project.util.MyHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PlayerDataApi {

    private static final String configPath = "config/config.properties";
    private MyHttpClient http;
    private String endpoint;
    private String image_endpoint;
    private String daily_endpoint;
    private String[] teamKeys;

    public PlayerDataApi(){}

    @PostConstruct
    public void setup(){
        try {
            Properties prop = ConfigLoader.load(configPath);
            this.http = new MyHttpClient();
            this.endpoint = prop.getProperty("api.endpoint.url");
            this.image_endpoint = prop.getProperty("api.endpoint.images");
            this.teamKeys = prop.getProperty("api.team.keys").split(",");
            this.daily_endpoint = prop.getProperty("api.endpoint.daily");
        }catch(Exception e){
            System.out.println("Failed to setup api service class");
        }
    }

    public List<Player> getPlayers() {
        List<Player> data = new ArrayList();

        for(int i = 0; i < teamKeys.length; i++){
            String teamKey = teamKeys[i];
            //log.info(String.format("Loading Player data by team (%s): %s of %s - player count %s",
                    //teamKey, i + 1 + "", teamKeys.length + "", data.size() + ""));
            String url = endpoint.replace("{TEAM_ID}", teamKey);
            String response = http.get(url);
            JSONArray playersJsonCurrentTeam = new JSONArray(response);
            parsePlayerData(playersJsonCurrentTeam, data);

        }

        return data;
    }

    public List<Player> getPlayerImages(){

        List<Player> images = new ArrayList();

        String response = http.get(image_endpoint);
        JSONArray playerImages = new JSONArray(response);

        for(int i =0; i < playerImages.length();i++){
            try {
                JSONObject player = playerImages.getJSONObject(i);
                int playerId = player.getInt("PlayerID");
                String image = player.getString("PhotoUrl");

                Player p = new Player();
                p.setId(playerId);
                p.setPicture(image);
                images.add(p);
            } catch (Exception e) {
                System.out.println("Error parsing a player: SKIPPED");
            }
        }

        return images;
    }

    public HashMap<String, DailyStats> getDailyStats(){
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MMM-dd").format(date.getTime());
        String url = daily_endpoint.replace("{DATE}", yesterday);

        String response = http.get(url);
        JSONArray playersDailyStats = new JSONArray(response);
        HashMap<String, DailyStats> dailyStats = new HashMap<>();

        parseDailyStats(playersDailyStats, dailyStats);
        return dailyStats;
    }

    private void parsePlayerData(JSONArray playerJson, List<Player> players){

        for(int i =0; i < playerJson.length();i++){
            try {
                players.add(getPlayer(playerJson.getJSONObject(i)));
            } catch (Exception e) {
                System.out.println("Error parsing a player: SKIPPED");
            }
        }

    }

    private Player getPlayer(JSONObject playerJson) throws Exception{

        Player player = new Player();
        int id = playerJson.getInt("PlayerID");

        double games = playerJson.getDouble("Games");

        double pts = playerJson.getDouble("Points");
        double pts_avg = round(pts / games);

        double ast = playerJson.getDouble("Assists");
        double ast_avg = round(ast / games);

        double reb = playerJson.getDouble("Rebounds");
        double reb_avg = round(reb / games);

        String name = playerJson.getString("Name");
        String pos = playerJson.getString("Position");

        player.setId(id);
        player.setPosition(pos);
        player.setName(name);
        player.setAvgAssists(ast_avg);
        player.setAvgRebounds(reb_avg);
        player.setAvgPts(pts_avg);

        return player;
    }

    private void parseDailyStats(JSONArray playerJson, HashMap<String, DailyStats> dailyStats){

        for(int i =0; i < playerJson.length();i++){
            try {
                JSONObject entry = playerJson.getJSONObject(i);
                DailyStats stat = getDailyStat(entry);
                dailyStats.put(stat.getPlayerId() + "", stat);
            } catch (Exception e) {
                System.out.println("Error parsing a player: SKIPPED");
            }
        }
    }

    private DailyStats getDailyStat(JSONObject playerJson){

        DailyStats dailyStat = new DailyStats();
        int id = playerJson.getInt("PlayerID");

        int pts = (int) playerJson.getDouble("Points");
        int ast = (int) playerJson.getDouble("Assists");
        int reb = (int) playerJson.getDouble("Rebounds");

        dailyStat.setPlayerId(id);
        dailyStat.setTotalAssists(ast);
        dailyStat.setTotalRebounds(reb);
        dailyStat.setTotalPts(pts);

        return dailyStat;
    }

    private double round(double num){
        return (double)Math.round(num * 10000000d) / 10000000d;
    }


}
