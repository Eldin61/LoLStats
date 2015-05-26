package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

/**
 * Created by Eldin on 17-5-2015.
 */
public class loginTask extends AsyncTask<Void, Void, Void> {
    static String sName;
    String userName;
    String userLevel;
    String divisionSolo;
    String divisionTeam;
    String divisionThree;
    String soloWins;
    String teamWins;
    String threeWins;
    ProgressDialog p;

    Activity mActivity;

    public loginTask (Activity activity){
        mActivity = activity;
    }

    public String getName(String input){
        sName = input;
        return sName;
    }

    public boolean sFound;

    @Override
    protected void onPreExecute(){
        p = ProgressDialog.show(mActivity, "Loading", "Retrieving and calculating data..", true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            RiotAPI.setMirror(Region.EUW);
            RiotAPI.setRegion(Region.EUW);
            RiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            Summoner summoner = RiotAPI.getSummonerByName(sName);
            userName = summoner.getName();

                try {
                    divisionSolo = summoner.getLeagueEntries().get(0).getTier() + " " + summoner.getLeagueEntries().get(0).getParticipantEntry().getDivision() + " - " + summoner.getLeagueEntries().get(0).getParticipantEntry().getLeaguePoints() + "LP";
                    soloWins = "Wins: " + summoner.getLeagueEntries().get(0).getParticipantEntry().getWins() + "";
                } catch (IndexOutOfBoundsException e){
                    divisionSolo = "No ranked games available..";
                    soloWins ="Wins: " +  "0";
                } catch (APIException e){
                    divisionSolo = "No ranked games available..";
                    soloWins ="Wins: " +  "0";
                }

                try{
                    divisionTeam = summoner.getLeagueEntries().get(1).getTier() + " " + summoner.getLeagueEntries().get(1).getParticipantEntry().getDivision() + " - " + summoner.getLeagueEntries().get(1).getParticipantEntry().getLeaguePoints() + "LP";
                    teamWins ="Wins: " +  summoner.getLeagueEntries().get(1).getParticipantEntry().getWins() + "";
                } catch (IndexOutOfBoundsException e){
                    divisionTeam = "No ranked games available..";
                    teamWins ="Wins: " +  "0";
                } catch (APIException e){
                    divisionSolo = "No ranked games available..";
                    soloWins ="Wins: " +  "0";
                }

                try{
                    divisionThree = summoner.getLeagueEntries().get(2).getTier() + " " + summoner.getLeagueEntries().get(2).getParticipantEntry().getDivision() + " - " + summoner.getLeagueEntries().get(2).getParticipantEntry().getLeaguePoints() + "LP";
                    threeWins ="Wins: " +  summoner.getLeagueEntries().get(2).getParticipantEntry().getWins() + "";
                } catch (IndexOutOfBoundsException e){
                    divisionThree = "No ranked games available..";
                    threeWins ="Wins: " +  "0";
                } catch (APIException e){
                    divisionSolo = "No ranked games available..";
                    soloWins ="Wins: " +  "0";
                }


            Log.d("","");
            sFound = true;

        } catch (APIException e){
            sFound = false;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        p.dismiss();
        if (sFound){
            super.onPostExecute(unused);
            Intent intent = new Intent(mActivity, OverviewPage.class);
            intent.putExtra("name", userName);
            intent.putExtra("soloDiv", divisionSolo);
            intent.putExtra("teamDiv", divisionTeam);
            intent.putExtra("threeDiv", divisionThree);

            intent.putExtra("soloWin", soloWins);
            intent.putExtra("teamWin", teamWins);
            intent.putExtra("threeWin", threeWins);
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
