package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.stats.ChampionStats;
import com.robrua.orianna.type.dto.stats.RankedStats;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.List;
import java.util.Map;

/**
 * Created by Eldin on 18-5-2015.
 */
public class detailedRank extends AsyncTask<Void, Void, Void>{
    Activity o;
    static String sName;
    ProgressDialog p;
    Boolean found;

    String totalGames;
    String gameWon;
    String gameLost;

    String totalGold;
    String totalMinions;
    String totalNeutral;

    String pKills;
    String assists;
    String deaths;
    String penta;
    String quadra;
    String triple;
    String doubleK;

    String userName;

    public detailedRank(Activity a){
        o = a;
    }

    public String setName(String name){
        sName = name;
        return sName;
    }

    @Override
    protected void onPreExecute(){
        p = ProgressDialog.show(o, "Loading", "Retrieving and calculating data..", true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setMirror(Region.EUW);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            Map<String, Summoner> summoners = BaseRiotAPI.getSummonersByName(sName);
            String lCase = sName.toLowerCase();
            String sumName = lCase.replaceAll("\\s+", "");

            Summoner summoner = summoners.get(sumName);

            userName = summoners.get(sumName).getName();


            long sd = summoner.getId();

            List<ChampionStats> r = BaseRiotAPI.getRankedStats(sd).getChampions();

            for (int i = 0; i < r.size(); i++) {
                ChampionStats c = r.get(i);
                if (c.getId() == 0) {
                    totalGames = c.getStats().getTotalSessionsPlayed() + "";
                    gameWon = c.getStats().getTotalSessionsWon() + "";
                    gameLost = c.getStats().getTotalSessionsLost() + "";
                    totalGold = c.getStats().getTotalGoldEarned() + "";
                    totalMinions = c.getStats().getTotalMinionKills() + "";
                    totalNeutral = c.getStats().getTotalNeutralMinionsKilled() + "";
                    pKills = c.getStats().getTotalChampionKills() + "";
                    assists = c.getStats().getTotalAssists() + "";
                    deaths = c.getStats().getTotalDeathsPerSession() + "";
                    penta = c.getStats().getTotalPentaKills() + "";
                    quadra = c.getStats().getTotalQuadraKills() + "";
                    triple = c.getStats().getTotalTripleKills() + "";
                    doubleK = c.getStats().getTotalDoubleKills() + "";
                }
            }


            found = true;
        }catch (APIException e){
            found = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        p.dismiss();
        if (found) {
            super.onPostExecute(unused);
            Intent intent = new Intent(o, detailedRankedPage.class);
            intent.putExtra("totalGames", totalGames);
            intent.putExtra("won", gameWon);
            intent.putExtra("lost", gameLost);
            intent.putExtra("gold", totalGold);
            intent.putExtra("minions", totalMinions);
            intent.putExtra("neutral", totalNeutral);
            intent.putExtra("kills", pKills);
            intent.putExtra("assists", assists);
            intent.putExtra("deaths", deaths);
            intent.putExtra("penta", penta);
            intent.putExtra("quadra", quadra);
            intent.putExtra("triple", triple);
            intent.putExtra("double", doubleK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            o.startActivity(intent);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(o);
            builder1.setMessage("Oops! Something went wrong! Please try again later.");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            final AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
