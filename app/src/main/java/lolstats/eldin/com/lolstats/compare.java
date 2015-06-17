package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.api.dto.MatchAPI;
import com.robrua.orianna.api.dto.MatchHistoryAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.matchhistory.MatchSummary;
import com.robrua.orianna.type.dto.matchhistory.PlayerHistory;
import com.robrua.orianna.type.dto.stats.AggregatedStats;
import com.robrua.orianna.type.dto.stats.ChampionStats;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.List;
import java.util.Map;

/**
 * Created by Eldin on 7-6-2015.
 */
public class compare extends AsyncTask<Void, Void, Void> {
    public static String compName = null;
    public static String sName = null;
    Activity m;
    String total1;
    String total2;
    String won1;
    String won2;
    String lost1;
    String lost2;
    String totalGold1;
    String totalGold2;
    String minions1;
    String minions2;
    String neutral1;
    String neutral2;
    String kills1;
    String kills2;
    String deaths1;
    String deaths2;
    String assists1;
    String assists2;
    String penta1;
    String penta2;
    String quadra1;
    String quadra2;
    String triple1;
    String triple2;
    String double1;
    String double2;


    public compare(Activity a){
        m = a;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            BaseRiotAPI.setRegion(Region.NA);
            BaseRiotAPI.setMirror(Region.NA);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            Map<String, Summoner> summoners = BaseRiotAPI.getSummonersByName(sName, compName);
            String lCase = sName.toLowerCase();
            String sumName = lCase.replaceAll("\\s+", "");

            String lCase2 = compName.toLowerCase();
            String sumName2 = lCase2.replaceAll("\\s+", "");

            Summoner s1 = summoners.get(sumName);
            Summoner s2 = summoners.get(sumName2);


            long id1 = s1.getId();
            long id2 = s2.getId();

            List<ChampionStats> ct1 = BaseRiotAPI.getRankedStats(id1).getChampions();
            List<ChampionStats> ct2 = BaseRiotAPI.getRankedStats(id2).getChampions();

            for (int i = 0; i < ct1.size(); i++){
                ChampionStats c = ct1.get(i);
                if(c.getId() == 0){
                    AggregatedStats a = c.getStats();
                    total1 = a.getTotalSessionsPlayed() + "";
                    won1 = a.getTotalSessionsWon() + "";
                    lost1 = a.getTotalSessionsLost() + "";
                    totalGold1 = a.getTotalGoldEarned() + "";
                    minions1 = a.getTotalMinionKills() + "";
                    neutral1 = a.getTotalNeutralMinionsKilled() + "";
                    kills1 = a.getTotalChampionKills() + "";
                    assists1 = a.getTotalAssists() + "";
                    deaths1 = a.getTotalDeathsPerSession() + "";
                    penta1 = a.getTotalPentaKills() + "";
                    quadra1 = a.getTotalQuadraKills() + "";
                    triple1 = a.getTotalTripleKills() + "";
                    double1 = a.getTotalDoubleKills() + "";
                }
            }
            for ( int i = 0; i < ct2.size(); i++){
                ChampionStats c = ct2.get(i);
                if (c.getId() == 0){
                    AggregatedStats a = c.getStats();
                    total2 = a.getTotalSessionsPlayed() + "";
                    won2 = a.getTotalSessionsWon() + "";
                    lost2 = a.getTotalSessionsLost() + "";
                    totalGold2 = a.getTotalGoldEarned() + "";
                    minions2 = a.getTotalMinionKills() + "";
                    neutral2 = a.getTotalNeutralMinionsKilled() + "";
                    kills2 = a.getTotalChampionKills() + "";
                    assists2 = a.getTotalAssists() + "";
                    deaths2 = a.getTotalDeathsPerSession() + "";
                    penta2 = a.getTotalPentaKills() + "";
                    quadra2 = a.getTotalQuadraKills() + "";
                    triple2 = a.getTotalTripleKills() + "";
                    double2 = a.getTotalDoubleKills() + "";
                }
            }

        } catch (APIException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v){
        Intent intent = new Intent(m, compPage.class);
        intent.putExtra("total1", total1);
        intent.putExtra("won1", won1);
        intent.putExtra("lost1", lost1);
        intent.putExtra("totalGold1", totalGold1);
        intent.putExtra("minions1", minions1);
        intent.putExtra("neutral1", neutral1);
        intent.putExtra("kills1", kills1);
        intent.putExtra("assists1", assists1);
        intent.putExtra("deaths1", deaths1);
        intent.putExtra("double1", double1);
        intent.putExtra("penta1", penta1);
        intent.putExtra("quadra1", quadra1);
        intent.putExtra("triple1", triple1);

        intent.putExtra("total2", total2);
        intent.putExtra("won2", won2);
        intent.putExtra("lost2", lost2);
        intent.putExtra("totalGold2", totalGold2);
        intent.putExtra("minions2", minions2);
        intent.putExtra("neutral2", neutral2);
        intent.putExtra("kills2", kills2);
        intent.putExtra("assists2", assists2);
        intent.putExtra("deaths2", deaths2);
        intent.putExtra("double2", double2);
        intent.putExtra("penta2", penta2);
        intent.putExtra("quadra2", quadra2);
        intent.putExtra("triple2", triple2);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        m.startActivity(intent);
    }
}
