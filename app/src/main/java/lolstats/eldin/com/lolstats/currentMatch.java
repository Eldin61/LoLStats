package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.currentgame.Mastery;
import com.robrua.orianna.type.dto.currentgame.Participant;
import com.robrua.orianna.type.dto.currentgame.Rune;
import com.robrua.orianna.type.dto.league.League;
import com.robrua.orianna.type.dto.staticdata.BasicDataStats;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        /**try {
            RiotAPI.setMirror(Region.EUW);
            RiotAPI.setRegion(Region.EUW);
            RiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");
            Summoner summoner = RiotAPI.getSummonerByName(sName);
            String Player = summoner.getCurrentGame().getParticipants().toString();
            userName = summoner.getName();
            getChampion(summoner);

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
        }*/
        try {

            BaseRiotAPI.setMirror(Region.EUW);
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");


            // Zoekt de summoner op stopt hem in een map
            Map<String, com.robrua.orianna.type.dto.summoner.Summoner> summoners = BaseRiotAPI.getSummonersByName(sName);
            // zorgt ervoor dat de key lowercase is en geen spaties meer bevat
            String lCase = sName.toLowerCase();
            String sumName = lCase.replaceAll("\\s+", "");

            // hier haalt die hem eruit met de key
            com.robrua.orianna.type.dto.summoner.Summoner summoner = summoners.get(sumName);

            // pakt de summoner id en naam
            userName = summoner.getName();
            long sd = summoner.getId();

            // doet de server request naar riot en haalt alle static data op van runes.
            RuneList runes = BaseRiotAPI.getRunes();

            // stopt de runes in een lijst
            List<com.robrua.orianna.type.dto.staticdata.Rune> runess = new ArrayList<>(runes.getData().values());

            // server call riot, static data masteries
            MasteryList masteryList = BaseRiotAPI.getMasteries();

            // list mastery enzo
            List<com.robrua.orianna.type.dto.staticdata.Mastery> masteryArray = new ArrayList<>(masteryList.getData().values());

            // hier zoekt die alle participants op van de current game en stopt ze in een lijst
            List<Participant> p = BaseRiotAPI.getCurrentGame(sd).getParticipants();

            // hier word het wat lastiger
            // deze loop gaat door de list heen van alle participants, elke participant pakt die dus de naam, id en masteries
            for (int i = 0; i < p.size(); i++) {
                String pName = p.get(i).getSummonerName();
                long team = p.get(i).getTeamId();
                long pId = p.get(i).getSummonerId();

                //lijst met current game masteries
                List<Mastery> g = p.get(i).getMasteries();

                // deze loop gaat door de list heen van current game masteries en pakt de mastery id's
                for (int z = 0; z < g.size(); z++) {
                    long mId = g.get(z).getMasteryId();
                    long masteryId;

                    // deze loop gaat door de static masteries heen en pakt de id's ervan
                    for (int mI = 0; mI < masteryArray.size(); mI++) {
                        masteryId = masteryArray.get(mI).getId();

                        // als allebei de masteries met elkaar overheenkomen print die dus de naam, rank en mastery tree uit
                        if (mId == masteryId) {
                            com.robrua.orianna.type.dto.staticdata.Mastery staticMa = masteryArray.get(mI);
                            Log.d("Mastery", staticMa.getName() + " Rank: " + g.get(z).getRank() + " Tree: " + staticMa.getMasteryTree());
                        }
                    }
                }

                // in principe het zelfde verhaal als met masteries
                List<Rune> r = p.get(i).getRunes();
                for (int rune = 0; rune < r.size(); rune++) {
                    long rId = r.get(rune).getRuneId();
                    long runeId;
                    for (int list = 0; list < runess.size(); list++) {
                        runeId = runess.get(list).getId();
                        if (rId == runeId) {
                            com.robrua.orianna.type.dto.staticdata.Rune currRune = runess.get(list);
                            Log.d("runename", currRune.getName() + " " + r.get(rune).getCount() + " * " + currRune.getDescription());
                        }
                    }
                }
                String div = null;

                try {
                    Map<Long, List<League>> league = BaseRiotAPI.getSummonerLeagueEntries(pId);

                    div = league.get(pId).get(0).getTier() + " " + league.get(pId).get(0).getEntries().get(0).getDivision();
                } catch (APIException e) {
                    div = "unranked";
                }

                if (team == 100) {
                    Log.d("blue team", pName + " " + div);
                } else {
                    Log.d("purple team", pName + " " + div);
                }
            }

            sFound = true;
        }catch (APIException e){
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
    /**public Summoner setSummoner(String input){
        return RiotAPI.getSummonerByName(input);
    }

    public String getChampion(Summoner input){
        try{
            for (int i = 0; i < 10; i++){
                String s = input.getCurrentGame().getParticipants().get(i).getSummonerName();
                String c = input.getCurrentGame().getParticipants().get(i).getChampion() + "";
                Participant p = input.getCurrentGame().getParticipants().get(i);
                String d = p.getSummoner().getLeagueEntries().get(0).getTier() + p.getSummoner().getLeagueEntries().get(0).getParticipantEntry().getDivision();
                String moeder = s + c + d;
                if(s.contains(input + "") && i < 5){
                    Log.d("myblue", moeder);
                } else if (i <= 5){
                    Log.d("blue", moeder);
                }
                if(s.contains(input + "") && i >= 5){
                    Log.d("mypurple", moeder);
                } else if (i > 5) {
                    Log.d("purple", moeder);
                }
            }
            return "";
        } catch (Exception e){
            return "shizzles";
        }
    }

    public String getDivision(Summoner input) {
        try {
            return input.getLeagueEntries().get(0).getTier() + " " + input.getLeagueEntries().get(0).getParticipantEntry().getDivision() + " - " + input.getLeagueEntries().get(0).getParticipantEntry().getLeaguePoints() + "LP";
        } catch (Exception e) {
            return "unranked";
        }
    }*/

}
