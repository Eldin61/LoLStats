package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;

/**
 * Created by Eldin on 18-5-2015.
 */
public class currentMatch extends AsyncTask<Void, Void, Void>{
    static String sName;
    String userName;
    String currentGame;

    String Summoner1;
    String Summoner2;
    String Summoner3;
    String Summoner4;
    String Summoner5;
    String Summoner6;
    String Summoner7;
    String Summoner8;
    String Summoner9;
    String Summoner10;

    String Summoner1Div;
    String Summoner2Div;
    String Summoner3Div;
    String Summoner4Div;
    String Summoner5Div;
    String Summoner6Div;
    String Summoner7Div;
    String Summoner8Div;
    String Summoner9Div;
    String Summoner10Div;

    public boolean sFound;

    ProgressDialog p;

    Activity mActivity;


    Activity o;
    public currentMatch(Activity activity){
        mActivity = activity;
    }

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
            String Player = summoner.getCurrentGame().getParticipants().toString();
            userName = summoner.getName();

            Player = Player.replaceAll("Participant", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("CurrentGameInfo", "");
            String[] parts = Player.split(", ");

            Summoner1 = parts[0].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner2 = parts[1].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner3 = parts[2].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner4 = parts[3].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner5 = parts[4].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner6 = parts[5].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner7 = parts[6].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner8 = parts[7].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner9 = parts[8].replaceAll("^\\s+", "").replaceAll(" as .*", "");
            Summoner10 = parts[9].replaceAll("^\\s+", "").replaceAll(" as .*", "");

            Summoner summoner1 = setSummoner(Summoner1);
            Summoner summoner2 = setSummoner(Summoner2);
            Summoner summoner3 = setSummoner(Summoner3);
            Summoner summoner4 = setSummoner(Summoner4);
            Summoner summoner5 = setSummoner(Summoner5);
            Summoner summoner6 = setSummoner(Summoner6);
            Summoner summoner7 = setSummoner(Summoner7);
            Summoner summoner8 = setSummoner(Summoner8);
            Summoner summoner9 = setSummoner(Summoner9);
            Summoner summoner10 = setSummoner(Summoner10);

            Summoner1Div = getDivision(summoner1);
            Summoner2Div = getDivision(summoner2);
            Summoner3Div = getDivision(summoner3);
            Summoner4Div = getDivision(summoner4);
            Summoner5Div = getDivision(summoner5);
            Summoner6Div = getDivision(summoner6);
            Summoner7Div = getDivision(summoner7);
            Summoner8Div = getDivision(summoner8);
            Summoner9Div = getDivision(summoner9);
            Summoner10Div = getDivision(summoner10);

//            Summoner summoner1 = RiotAPI.getSummonerByName(Summoner1);
//            Summoner summoner2 = RiotAPI.getSummonerByName(Summoner2);
//            Summoner1Div = summoner1.getLeagueEntries().get(0).getTier() + " " + summoner1.getLeagueEntries().get(0).getParticipantEntry().getDivision() + " - " + summoner1.getLeagueEntries().get(0).getParticipantEntry().getLeaguePoints() + "LP";
//            Summoner2Div = summoner2.getLeagueEntries().get(0).getTier() + " " + summoner2.getLeagueEntries().get(0).getParticipantEntry().getDivision() + " - " + summoner2.getLeagueEntries().get(0).getParticipantEntry().getLeaguePoints() + "LP";



            //currentGame = ("\n"+ Summoner1 + "\n" + Summoner2 + "\n" + Summoner3 + "\n" + Summoner4 + "\n" + Summoner5 + "\n" + Summoner6 + "\n" + Summoner7 + "\n" + Summoner8 + "\n" + Summoner9 + "\n" + Summoner10 );

            sFound = true;
        } catch (NullPointerException e)
        {
            sFound = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        p.dismiss();
        if (sFound){
            super.onPostExecute(unused);
            Intent intent = new Intent(mActivity, currentMatchPage.class);

            intent.putExtra("name", sName);

            intent.putExtra("Summoner1", Summoner1);
            intent.putExtra("Summoner2", Summoner2);
            intent.putExtra("Summoner3", Summoner3);
            intent.putExtra("Summoner4", Summoner4);
            intent.putExtra("Summoner5", Summoner5);
            intent.putExtra("Summoner6", Summoner6);
            intent.putExtra("Summoner7", Summoner7);
            intent.putExtra("Summoner8", Summoner8);
            intent.putExtra("Summoner9", Summoner9);
            intent.putExtra("Summoner10", Summoner10);

            intent.putExtra("Summoner1Div",Summoner1Div);
            intent.putExtra("Summoner2Div",Summoner2Div);
            intent.putExtra("Summoner3Div",Summoner3Div);
            intent.putExtra("Summoner4Div",Summoner4Div);
            intent.putExtra("Summoner5Div",Summoner5Div);
            intent.putExtra("Summoner6Div",Summoner6Div);
            intent.putExtra("Summoner7Div",Summoner7Div);
            intent.putExtra("Summoner8Div",Summoner8Div);
            intent.putExtra("Summoner9Div",Summoner9Div);
            intent.putExtra("Summoner10Div",Summoner10Div);

            //intent.putExtra("currentGame", currentGame);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(mActivity);
            builder1.setMessage("Summoner not in game.");
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
    public Summoner setSummoner(String input){
        return RiotAPI.getSummonerByName(input);
    }

    public String getDivision(Summoner input) {
        try {
            return input.getLeagueEntries().get(0).getTier() + " " + input.getLeagueEntries().get(0).getParticipantEntry().getDivision() + " - " + input.getLeagueEntries().get(0).getParticipantEntry().getLeaguePoints() + "LP";
        } catch (Exception e) {
            return "unranked";
        }
    }

}
