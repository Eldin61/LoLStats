package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.staticdata.Champion;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.dto.staticdata.ChampionSpell;
import com.robrua.orianna.type.dto.staticdata.Passive;
import com.robrua.orianna.type.exception.APIException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Orlando on 16-6-2015.
 */
public class championDetail extends AsyncTask<Void, Void, Void> {

    Activity o;
    ProgressDialog p;
    int sentId = championsPage.champId;
    String champName;
    String champTitle;
    Passive champPassive;
    List<ChampionSpell> champSpells = new ArrayList<>();
    String bloom;
    double moeder;

    public championDetail(Activity a){o = a;}

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
            //comment
            List<Champion> champions = new ArrayList<>(champs.getData().values());

            champName = champions.get(sentId).getName();
            champTitle = champions.get(sentId).getTitle();
            champPassive = champions.get(sentId).getPassive();
            champSpells = champions.get(sentId).getSpells();
            bloom = champPassive + "";
            String niek = "eldin";

            Log.d("Test champid", champions.get(sentId).getName()  + ".");
            Log.d("Test title", champions.get(sentId).getTitle() + ".");
            Log.d("Test spell", champions.get(sentId).getSpells() + ".");

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

        Intent intent = new Intent(o, champDetailPage.class);
        intent.putExtra("ChampionName", champName);
        intent.putExtra("ChampionTitle", champTitle);
        intent.putExtra("ChampionPassive", bloom);

        o.startActivity(intent);
    }
}
