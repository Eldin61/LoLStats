package lolstats.eldin.com.lolstats;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;

public class championsPage extends ActionBarActivity {

    public static int champId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champions);

        Bundle extras = getIntent().getExtras();
        String[] sortedChamps = extras.getStringArray("ChamplistSorted");
        final String[] unsortedChamps = extras.getStringArray("ChamplistUnsorted");

        //maakt listview
        ListAdapter champAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sortedChamps);
        ListView allChamps = (ListView)findViewById(R.id.champlist);

        allChamps.setAdapter(champAdapter);

        //als er op de champs wordt geklikt
        allChamps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //naam van champ waar op wordt geklikt
                String championPicked = String.valueOf(parent.getItemAtPosition(position));

                //zoekt de naam van de gekozen champ en geeft bijbehorende positie in de array en dus id
                champId = Arrays.asList(unsortedChamps).indexOf(championPicked);

                sendChampId();

            }
        });
    }

    public void sendChampId(){new championDetail(championsPage.this).execute(); }


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
