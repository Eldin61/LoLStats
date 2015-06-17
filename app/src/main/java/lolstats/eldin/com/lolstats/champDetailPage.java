package lolstats.eldin.com.lolstats;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.robrua.orianna.type.dto.staticdata.Passive;


public class champDetailPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champ_detail);

        Bundle extras = getIntent().getExtras();
        String champName = extras.getString("ChampionName");
        String champTitle = extras.getString("ChampionTitle");
        String champPassive = extras.getString("ChampionPassive");

        TextView tvCname = (TextView)findViewById(R.id.champname);
        tvCname.setText(champName);

        TextView tvCtitle = (TextView)findViewById(R.id.champtitle);
        tvCtitle.setText(champTitle);

        TextView tvCpassive = (TextView)findViewById(R.id.champpassive);
        tvCpassive.setText(champPassive);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_champ_detail_page, menu);
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
