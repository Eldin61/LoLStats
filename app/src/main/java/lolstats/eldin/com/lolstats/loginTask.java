package lolstats.eldin.com.lolstats;

import android.os.AsyncTask;
import android.util.Log;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;

public class loginTask extends AsyncTask<Void, Void, Void> {
    static String sName;

    public String getName(String input){
        sName = input;
        Log.d("getname", sName);
        return sName;
    }
    @Override
    protected void onPreExecute(){
       // Log.d("pre",sName);
        Log.d("pre",sName);
    }

    @Override
    protected Void doInBackground(Void... params) {
        RiotAPI.setMirror(Region.EUW);
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey("42a21243-f875-49fb-93bb-e112cd9df88a");

        Log.d("back"," ");
        Summoner summoner = RiotAPI.getSummonerByName(sName);
        Log.d("back", summoner.getName() + "-" + summoner.getLevel());
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        Log.d("post", "hoi");
    }
}
