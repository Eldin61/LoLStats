package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.game.Player;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.AggregatedStats;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.stats.RankedStats;
import com.robrua.orianna.type.exception.APIException;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Eldin on 17-5-2015.
 */
public class loginTask extends AsyncTask<Void, Void, Void> {
    static String sName;
    String userName;
    String userLevel;
    String wins;
    String losses;
    String kills;
    String assists;
    String deaths;

    Activity mActivity;

    public loginTask (Activity activity){
        mActivity = activity;
    }

    public String getName(String input){
        sName = input;
        Log.d("getname", sName);
        return sName;
    }

    public boolean sFound;

    @Override
    protected void onPreExecute(){
        // Log.d("pre",sName);
        Log.d("pre", sName);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            RiotAPI.setMirror(Region.EUW);
            RiotAPI.setRegion(Region.EUW);
            RiotAPI.setAPIKey("42a21243-f875-49fb-93bb-e112cd9df88a");

            Log.d("back", " ");
            Summoner summoner = RiotAPI.getSummonerByName(sName);
            Log.d("back", summoner.getName() + "-" + summoner.getLevel());
            userName = summoner.getName();
            userLevel = summoner.getLevel() + "";
            wins = RiotAPI.getRankedStats(summoner).get(null).getStats().getTotalWins() + "";
                losses = RiotAPI.getRankedStats(summoner).get(null).getStats().getTotalLosses() + "";
                kills = RiotAPI.getRankedStats(summoner).get(null).getStats().getTotalKills() + "";
                assists = RiotAPI.getRankedStats(summoner).get(null).getStats().getTotalAssists() + "";
                deaths = RiotAPI.getRankedStats(summoner).get(null).getStats().getTotalDeaths() + "";

            Log.d("ranked", summoner.getLeagueEntries().get(0).getTier() + "");
            //Log.d("stats", summoner.getStats().get(PlayerStatsSummaryType.RankedSolo5x5).getAggregatedStats().getTotalKills() + "");

            sFound = true;
        } catch (APIException e){
            sFound = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        if (sFound){
            super.onPostExecute(unused);
            Intent intent = new Intent(mActivity, OverviewPage.class);
            intent.putExtra("name", userName);
            intent.putExtra("level", userLevel);
            intent.putExtra("wins", wins);
            intent.putExtra("losses", losses);
            intent.putExtra("kills", kills);
            intent.putExtra("assists", assists);
            intent.putExtra("deaths", deaths);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(mActivity);
            builder1.setMessage("Wrong username or region!");
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
