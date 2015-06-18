package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.staticdata.Champion;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.exception.APIException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Orlando on 11-6-2015.
 */
public class champions extends AsyncTask<Void, Void, Void> {
    Activity o;
    ProgressDialog p;
    String[] sortedChamps;
    String[] unsortedChamps;
    String[] nrOfChamps;

    public champions(Activity a){o = a;}

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

            ChampionList champs = BaseRiotAPI.getChampions();
            //Champion testchampname = BaseRiotAPI.getChampion(89);
            //String test = testchampname.getName();
            List<Champion> champions = new ArrayList<>(champs.getData().values());
            nrOfChamps = new String[champions.size()];
            unsortedChamps = new String[champions.size()];

//            Log.d("Test champid", champions.get(79).getName() + " " + nrOfChamps.length + ".");
//            Log.d("Test title", champions.get(79).getTitle() + ".");
//            Log.d("Test key", champions.get(79).getKey() + ".");
//            Log.d("Test spell", champions.get(79).getSpells() + ".");


            for(int x = 0; x < nrOfChamps.length; x++){
                nrOfChamps [x] = champions.get(x).getName();
                unsortedChamps [x] = champions.get(x).getName();
                Log.d("Champ nr + name: ", x + " " + nrOfChamps[x]);
                if(x == nrOfChamps.length - 1){
                    Log.d("Array to string test: ", Arrays.toString(nrOfChamps));
                    //unsortedChamps = nrOfChamps;
                    Arrays.sort(nrOfChamps);
                    sortedChamps = nrOfChamps;

                }
            }

        }catch (APIException e){
            Log.d("apiex", "apiex");
        }catch (NullPointerException e) {
            Log.d("nullex","nullex");
        }
        return null;
    }

//    public static void championClicked(int i){
//        try {
//            BaseRiotAPI.setRegion(Region.EUW);
//            BaseRiotAPI.setMirror(Region.EUW);
//            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");
//
//            ChampionList champs = BaseRiotAPI.getChampions();
//            //Champion testchampname = BaseRiotAPI.getChampion(89);
//            //String test = testchampname.getName();
//            List<Champion> champions = new ArrayList<>(champs.getData().values());
//
//
//            Log.d("Test champid", champions.get(i).getName() + ".");
//            Log.d("Test title", champions.get(i).getTitle() + ".");
//            Log.d("Test key", champions.get(i).getKey() + ".");
//            Log.d("Test spell", champions.get(i).getSpells() + ".");
//        } catch (APIException e){
//            Log.d("apiex", "apiex");
//        }catch (NullPointerException e) {
//            Log.d("nullex","nullex");
//        }
//    }

    @Override
    protected void onPostExecute(Void unused) {
        p.dismiss();
        super.onPostExecute(unused);

        Intent intent = new Intent(o, championsPage.class);
        intent.putExtra("ChamplistSorted", sortedChamps);
        intent.putExtra("ChamplistUnsorted", unsortedChamps);

        o.startActivity(intent);
    }
}
