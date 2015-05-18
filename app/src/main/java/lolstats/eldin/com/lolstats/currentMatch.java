package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by Eldin on 18-5-2015.
 */
public class currentMatch extends AsyncTask<Void, Void, Void>{

    Activity o;
    public currentMatch(Activity a){
        o = a;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        super.onPostExecute(unused);
        Intent intent = new Intent(o, currentMatchPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        o.startActivity(intent);
    }
}
