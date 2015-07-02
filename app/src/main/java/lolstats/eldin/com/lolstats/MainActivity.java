package lolstats.eldin.com.lolstats;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    String s;
    String region;

    public void login(View w){
        EditText etName = (EditText) findViewById(R.id.etName);
        s = etName.getText().toString();
        savePref();
        loginTask l = new loginTask(MainActivity.this);
        l.getName(s);
        l.setRegion(region);
        testMethod();
    }

    public void naRegion(View w){
        region = "NA";
    }

    public void euwRegion(View w){
        region = "EUW";
    }
    public void euneRegion(View w){
        region = "EUNE";
    }

    public void logPref(View w){
            TextView etName = (TextView) findViewById(R.id.prefName);
            s = etName.getText().toString();
        loginTask l = new loginTask(MainActivity.this);
        l.getName(s);
        l.setRegion(region);
            testMethod();
    }

    public void testMethod(){
        new loginTask(MainActivity.this).execute();
    }

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);




        View pref = findViewById(R.id.pref1);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String name;
        for(int i = 0; i < 10; i++){
            name = settings.getString("summoner" + i, null);
            if(name != null) {
                pref.setVisibility(View.VISIBLE);
                TextView t = (TextView) findViewById(R.id.prefName);
                Log.d("tag", name);
                t.setText(name);
                break;
            } else {
                pref.setVisibility(View.GONE);
                Log.d("nothing", "hide");
            }
        }

    }

    public void deletePref(View w){
        getSharedPreferences(PREFS_NAME, 0).edit().clear().commit();
        View pref = findViewById(R.id.pref1);
        pref.setVisibility(View.GONE);
    }

    public static final String PREFS_NAME = "MyPrefsFile";
    private static int value = 0;

    public void savePref(){
        TextView tvS = (TextView) findViewById(R.id.etName);
        String test = tvS.getText() + "";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
            editor.putString("summoner" + value, test);
        Log.d("saved", "summoner " + test);
        value++;

        // Commit the edits!
        editor.apply();


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
    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context

    }
}
