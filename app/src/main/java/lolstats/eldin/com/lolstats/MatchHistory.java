package lolstats.eldin.com.lolstats;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.game.Game;
import com.robrua.orianna.type.dto.game.RawStats;
import com.robrua.orianna.type.dto.matchhistory.MatchSummary;
import com.robrua.orianna.type.dto.matchhistory.PlayerHistory;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Eldin on 21-6-2015.
 */
public class MatchHistory extends AsyncTask<Void, Void, Void> {
    static String sName;
    String userName;
    String typeMatch;
    String kills;
    String deaths;
    String assists;
    String gold;
    String minions;
    List<Match> data;

    @Override
    protected Void doInBackground(Void... params) {
        try{
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setMirror(Region.EUW);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            Map<String, Summoner> summoners = BaseRiotAPI.getSummonersByName(sName);
            String lCase = sName.toLowerCase();
            String sumName = lCase.replaceAll("\\s+", "");
            Summoner summoner = summoners.get(sumName);
            Log.d("name", sName);
            Log.d("name2", summoner + "");
            Log.d("name3", summoners + "");
            userName = summoner.getName();
            long sd = summoner.getId();

            data = new ArrayList<>();
        List<Game> g = BaseRiotAPI.getRecentGames(sd).getGames();
            for (int i = 0; i < g.size(); i++){
                RawStats r = g.get(i).getStats();
                Log.d("win/loss", r.getWin() + " " + g.get(i).getSubType() + " K/D/A: " + r.getChampionsKilled() + "/" + r.getNumDeaths() + "/" + r.getAssists() );
                boolean win = r.getWin();
                if (!win){
                    data.add(new Match(r.getChampionsKilled() + "", r.getNumDeaths() + "", "Loss"));
                } else {
                    data.add(new Match(r.getChampionsKilled() + "", r.getNumDeaths() + "", "Win"));
                }
            }
        } catch (APIException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void unused){

    }
}
