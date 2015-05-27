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
import com.robrua.orianna.type.dto.champion.Champion;
import com.robrua.orianna.type.dto.league.League;
import com.robrua.orianna.type.dto.league.LeagueEntry;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import junit.framework.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;


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
            BaseRiotAPI.setMirror(Region.EUW);
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");
            //RiotAPI.setLoadPolicy(LoadPolicy.LAZY);
            Map<String, Summoner> summoners = BaseRiotAPI.getSummonersByName(sName);
            Summoner summoner = summoners.get(sName);

            userName = summoner.getName();


            long sd = summoner.getId();
            Map<Long, List<League>> league = BaseRiotAPI.getSummonerLeagueEntries(sd);

            divisionSolo = league.get(sd).get(0).getTier() + " " + league.get(sd).get(0).getEntries().get(0).getDivision();
            divisionThree = league.get(sd).get(1).getTier() + " " + league.get(sd).get(1).getEntries().get(0).getDivision();
            divisionTeam = league.get(sd).get(2).getTier() + " " + league.get(sd).get(2).getEntries().get(0).getDivision();

            String g = league.get(sd).get(0).getEntries().get(0).getWins() + "";

            Log.d("test", g);


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
    /**
    private void backup(){

    }*/

        }
