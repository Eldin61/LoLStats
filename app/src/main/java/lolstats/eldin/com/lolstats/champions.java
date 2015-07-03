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

        p = ProgressDialog.show(o, "Loading", "Retrieving champions..", true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            BaseRiotAPI.setRegion(Region.EUW);
            BaseRiotAPI.setMirror(Region.EUW);
            BaseRiotAPI.setAPIKey("ebe43318-cee4-4d2a-bf19-1c195a32aa93");

            ChampionList champs = BaseRiotAPI.getChampions();
            List<Champion> champions = new ArrayList<>(champs.getData().values());
            nrOfChamps = new String[champions.size()];
            unsortedChamps = new String[champions.size()];

            //vul array met champion namen
            for(int x = 0; x < nrOfChamps.length; x++){
                nrOfChamps [x] = champions.get(x).getName();
                unsortedChamps [x] = champions.get(x).getName();
                if(x == nrOfChamps.length - 1){
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
