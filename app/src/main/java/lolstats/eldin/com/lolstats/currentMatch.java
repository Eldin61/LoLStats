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
import com.robrua.orianna.type.dto.staticdata.Champion;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.staticdata.SummonerSpellList;
import com.robrua.orianna.type.dto.stats.ChampionStats;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Eldin on 18-5-2015.
 */
public class currentMatch extends AsyncTask<Void, Void, Void>{
    static String sName;
    String userName;

    ArrayList<String> Summoners = new ArrayList<>();
    ArrayList<String> Champpicks = new ArrayList<>();
    ArrayList<String> Divs = new ArrayList<>();
    ArrayList<Long> SummonerSpell1 = new ArrayList<>();
    ArrayList<Long> SummonerSpell2 = new ArrayList<>();

    ArrayList<String> SummonerKills = new ArrayList<>();
    ArrayList<String> SummonerDeaths = new ArrayList<>();
    ArrayList<String> SummonerAssists = new ArrayList<>();

    Double blueKans;
    String[] sortedChamps;

    public boolean sFound;

    ProgressDialog p;

    Activity mActivity;

    int blueScore = 0;
    int redScore = 0;




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

            BaseRiotAPI.setMirror(Region.NA);
            BaseRiotAPI.setRegion(Region.NA);
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


            ChampionList champs = BaseRiotAPI.getChampions();
            Champion testchampname = BaseRiotAPI.getChampion(89);
            String test = testchampname.getName();
            List<Champion> champions = new ArrayList<>(champs.getData().values());
            String[] nrOfChamps = new String[champions.size()];
            Log.d("Test champid", champions.get(79).getName() + " " + nrOfChamps.length + ".");
            Log.d("Test title", champions.get(79).getTitle() + ".");
            Log.d("Test key", champions.get(79).getKey() + ".");
            Log.d("Test spell", champions.get(79).getSpells() + ".");


            for(int x = 0; x < nrOfChamps.length; x++){
                nrOfChamps [x] = champions.get(x).getName();
                if(x == nrOfChamps.length - 1){
                    Arrays.sort(nrOfChamps);
                    sortedChamps = nrOfChamps;

                }
            }
            Log.d("Array sorted ", Arrays.toString(sortedChamps));

                List<Participant> p = BaseRiotAPI.getCurrentGame(sd).getParticipants();

                List<ChampionStats> r = BaseRiotAPI.getRankedStats(sd).getChampions();
            for (int i = 0; i < p.size(); i++) {
                long b = 0;

                String pName = p.get(i).getSummonerName();
                long champ = p.get(i).getChampionId();
                long team = p.get(i).getTeamId();
                b = champ;
                Champion champy = BaseRiotAPI.getChampion(b);
                String chosenchamp = champy.getName();
                long pId = p.get(i).getSummonerId();
                long sums1 = p.get(i).getSpell1Id();
                long sums2 = p.get(i).getSpell2Id();
                Summoners.add(pName);
                Champpicks.add(chosenchamp);
                SummonerSpell1.add(sums1);
                SummonerSpell2.add(sums2);

                int games = r.get(i).getStats().getTotalSessionsPlayed();
                int kills = r.get(i).getStats().getTotalChampionKills();
                int deaths = r.get(i).getStats().getTotalDeathsPerSession();
                int assists = r.get(i).getStats().getTotalAssists();

                String avgKills = kda(games, kills);
                String avgDeaths = kda(games, deaths);
                String avgAssists = kda(games, assists);

                SummonerKills.add(avgKills);
                SummonerDeaths.add(avgDeaths);
                SummonerAssists.add(avgAssists);

                String div = null;

                try {
                    Map<Long, List<League>> league = BaseRiotAPI.getSummonerLeagueEntries(pId);

                    div = league.get(pId).get(0).getTier() + " " + league.get(pId).get(0).getEntries().get(0).getDivision();
                } catch (APIException e) {
                    div = "unranked";
                }
                Divs.add(div);

                if (team == 100) {
                    blueScore += (int)getdMap(div);
                    Log.d("Blue team", pName + " as  " + chosenchamp + " " + div + " "+ sums1 + " " + sums2 + " " + getdMap(div) + blueScore + " " + games  + " "+ avgKills  + " "+ avgDeaths + " " + avgAssists);
                } else {
                    Log.d("Red team", pName + " as  " + chosenchamp + " " + div + " "+ sums1 + " " + sums2 + " " + getdMap(div) + redScore);
                    redScore += (int)getdMap(div);
                }

            }

            blueKans = kans(blueScore, redScore);
            sFound = true;
        }catch (APIException e){
            sFound = false;
            Log.d("apiex","apiex");
        } catch (NullPointerException e){
            sFound = false;
            Log.d("nullex","nullex");
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

            intent.putStringArrayListExtra("SummonerNames", Summoners);
            intent.putStringArrayListExtra("Picks", Champpicks);
            intent.putStringArrayListExtra("Divisions", Divs);
            intent.putStringArrayListExtra("Kills", SummonerKills);
            intent.putStringArrayListExtra("Deaths", SummonerDeaths);
            intent.putStringArrayListExtra("Assists", SummonerAssists);


            intent.putExtra("blueKans", blueKans);


            intent.putExtra("Summoner1Div", Divs.get(0));
            intent.putExtra("Summoner2Div",Divs.get(1));
            intent.putExtra("Summoner3Div",Divs.get(2));
            intent.putExtra("Summoner4Div",Divs.get(3));
            intent.putExtra("Summoner5Div",Divs.get(4));
            intent.putExtra("Summoner6Div",Divs.get(5));
            intent.putExtra("Summoner7Div",Divs.get(6));
            intent.putExtra("Summoner8Div",Divs.get(7));
            intent.putExtra("Summoner9Div",Divs.get(8));
            intent.putExtra("Summoner10Div",Divs.get(9));

            intent.putExtra("Summoner1Spell1", SummonerSpell1.get(0));
            intent.putExtra("Summoner1Spell2", SummonerSpell2.get(0));
            intent.putExtra("Summoner2Spell1", SummonerSpell1.get(1));
            intent.putExtra("Summoner2Spell2", SummonerSpell2.get(1));
            intent.putExtra("Summoner3Spell1", SummonerSpell1.get(2));
            intent.putExtra("Summoner3Spell2", SummonerSpell2.get(2));
            intent.putExtra("Summoner4Spell1", SummonerSpell1.get(3));
            intent.putExtra("Summoner4Spell2", SummonerSpell2.get(3));
            intent.putExtra("Summoner5Spell1", SummonerSpell1.get(4));
            intent.putExtra("Summoner5Spell2", SummonerSpell2.get(4));
            intent.putExtra("Summoner6Spell1", SummonerSpell1.get(5));
            intent.putExtra("Summoner6Spell2", SummonerSpell2.get(5));
            intent.putExtra("Summoner7Spell1", SummonerSpell1.get(6));
            intent.putExtra("Summoner7Spell2", SummonerSpell2.get(6));
            intent.putExtra("Summoner8Spell1", SummonerSpell1.get(7));
            intent.putExtra("Summoner8Spell2", SummonerSpell2.get(7));
            intent.putExtra("Summoner9Spell1", SummonerSpell1.get(8));
            intent.putExtra("Summoner9Spell2", SummonerSpell2.get(8));
            intent.putExtra("Summoner10Spell1", SummonerSpell1.get(9));
            intent.putExtra("Summoner10Spell2", SummonerSpell2.get(9));



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

    public Object getdMap(String div){
        HashMap dMap = new HashMap<String, Integer>();
        dMap.put("BRONZE V", 0);
        dMap.put("BRONZE IV", 1);
        dMap.put("BRONZE III", 2);
        dMap.put("BRONZE II", 3);
        dMap.put("BRONZE I", 4);

        dMap.put("SILVER V", 6);
        dMap.put("SILVER IV", 8);
        dMap.put("SILVER III", 10);
        dMap.put("SILVER II", 12);
        dMap.put("SILVER I", 14);

        dMap.put("GOLD V", 17);
        dMap.put("GOLD IV", 20);
        dMap.put("GOLD III", 23);
        dMap.put("GOLD II", 26);
        dMap.put("GOLD I", 29);

        dMap.put("PLATINUM V", 33);
        dMap.put("PLATINUM IV", 37);
        dMap.put("PLATINUM III", 41);
        dMap.put("PLATINUM II", 45);
        dMap.put("PLATINUM I", 49);

        dMap.put("DIAMOND V", 54);
        dMap.put("DIAMOND IV", 59);
        dMap.put("DIAMOND III", 64);
        dMap.put("DIAMOND II", 69);
        dMap.put("DIAMOND I", 74);

        dMap.put("MASTER I", 80);

        dMap.put("CHALLENGER 1", 90);

        dMap.put("unranked", 34);

        return dMap.get(div);

    }
    public double kans(int a, int b){
        double diff;
        diff = a - b;
        diff = diff * 1.5;
        return 50 + diff;
    }
    public String kda(int games, int data){
        String x;
        try{
             x = data / games + "";
        }
        catch (Exception e){
             x = "0";
        }
        return x;
    }
}
