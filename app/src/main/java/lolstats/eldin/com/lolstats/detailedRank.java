package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

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

    public detailedRank(Activity a){
        o = a;
    }

    @Override
    protected void onPreExecute(){
        p = ProgressDialog.show(o, "Loading", "Retrieving and calculating data..", true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            RiotAPI.setMirror(Region.EUW);
            RiotAPI.setRegion(Region.EUW);
            RiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            Summoner summoner = RiotAPI.getSummonerByName(sName);

            gameWon = summoner.getRankedStats().get(null).getStats().getTotalWins() + "";
            gameLost = summoner.getRankedStats().get(null).getStats().getTotalLosses() + "";
            totalGames = summoner.getRankedStats().get(null).getStats().getTotalGamesPlayed() + "";

            totalGold = summoner.getRankedStats().get(null).getStats().getTotalGoldEarned() + "";
            totalMinions = summoner.getRankedStats().get(null).getStats().getTotalMinionKills() + "";
            totalNeutral = summoner.getRankedStats().get(null).getStats().getTotalNeutralMinionsKilled() + "";

            pKills = summoner.getRankedStats().get(null).getStats().getTotalKills() + "";
            assists = summoner.getRankedStats().get(null).getStats().getTotalAssists() + "";
            deaths = summoner.getRankedStats().get(null).getStats().getTotalDeaths() + "";
            penta = summoner.getRankedStats().get(null).getStats().getTotalPentaKills() + "";
            quadra = summoner.getRankedStats().get(null).getStats().getTotalQuadraKills() + "";
            triple = summoner.getRankedStats().get(null).getStats().getTotalTripleKills() + "";
            doubleK = summoner.getRankedStats().get(null).getStats().getTotalDoubleKills() + "";

            found = true;
        } catch (APIException e){
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
