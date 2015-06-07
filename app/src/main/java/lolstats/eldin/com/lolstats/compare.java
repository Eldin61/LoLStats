package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
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

    public compare(Activity a){
        m = a;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setMirror(Region.EUW);
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
                    Log.d("user", c.getStats().getTotalSessionsPlayed() + "");
                }
            }
            for ( int i = 0; i < ct2.size(); i++){
                ChampionStats c = ct2.get(i);
                if (c.getId() == 0){
                    Log.d("other", c.getStats().getTotalSessionsPlayed() + "");
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        m.startActivity(intent);
    }
}
