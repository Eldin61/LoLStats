package lolstats.eldin.com.lolstats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.robrua.orianna.api.core.AsyncRiotAPI;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends ActionBarActivity {
    String s;
    String level;
    long getId;

    public final static String sName = "lolstatseldin.com.lolstats.MESSAGE";
    public final static String sLevel = "lolstatseldin.com.lolstats.MESSAGELevel";

    public void onRadioClicked(View w){
        //niks nog
    }

    public void login(View w){
        getName();
    }

    public void getName(){
        Connect c = new Connect();
        c.connectRiot();
        EditText etName = (EditText) findViewById(R.id.etName);
        s = etName.getText().toString();
        getLevel(s);
    }

    protected void runTest(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
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

    private void getLevel(final String name) {
        AsyncRiotAPI.getSummonerByName(new Action<Summoner>() {
            @Override
            public void handle(APIException e) {
                Log.d("handle", "");
            }

            @Override
            public void perform(Summoner summoner) {
                Log.d("log", "Succesfully found summoner" + name);
                returnLevel(summoner.getLevel() + "");
                getId = summoner.getID();
                setNewActivity();
                Log.d("perform", "");
            }
        }, name);
    }

    private String returnLevel(String levelReturn){
        level = levelReturn;
        Log.d("returnlevel", level);
        return level;
    }

    private void setNewActivity(){
        Intent intent = new Intent(this, OverviewPage.class);
        intent.putExtra("name", s);
        intent.putExtra("level", level);
        Log.d("intent", level);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
