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

        //skill names
        String champPassive = extras.getString("ChampionPassiveName");
        String champQ = extras.getString("ChampionQname");
        String champW = extras.getString("ChampionWname");
        String champE = extras.getString("ChampionEname");
        String champR = extras.getString("ChampionRname");

        //skill descriptions
        String pasDescr = extras.getString("ChampionPassiveDescr");
        String qDescr = extras.getString("ChampionQdescr");
        String wDescr = extras.getString("ChampionWdescr");
        String eDescr = extras.getString("ChampionEdescr");
        String rDescr = extras.getString("ChampionRdescr");

        //naam en titel van champ
        TextView tvCname = (TextView)findViewById(R.id.champname);
        tvCname.setText(champName);

        TextView tvCtitle = (TextView)findViewById(R.id.champtitle);
        tvCtitle.setText(champTitle);

        //champ skills
        TextView tvCpassive = (TextView)findViewById(R.id.passivename);
        tvCpassive.setText("Passive " + champPassive);

        TextView tvQname = (TextView)findViewById(R.id.qname);
        tvQname.setText("Q -" + champQ);

        TextView tvWname = (TextView)findViewById(R.id.wname);
        tvWname.setText("W -" + champW);

        TextView tvEname = (TextView)findViewById(R.id.ename);
        tvEname.setText("E -" + champE);

        TextView tvRname = (TextView)findViewById(R.id.rname);
        tvRname.setText("R -" + champR);

        //champ skill descriptions
        TextView tvPassivedescr = (TextView)findViewById(R.id.passive);
        tvPassivedescr.setText(pasDescr);

        TextView tvQdescr = (TextView)findViewById(R.id.qdescription);
        tvQdescr.setText(qDescr);

        TextView tvWdescr = (TextView)findViewById(R.id.wdescription);
        tvWdescr.setText(wDescr);

        TextView tvEdescr = (TextView)findViewById(R.id.edescription);
        tvEdescr.setText(eDescr);

        TextView tvRdescr = (TextView)findViewById(R.id.rdescription);
        tvRdescr.setText(rDescr);

        String d;

    }

   /** @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //
      //  getMenuInflater().inflate(R.menu.menu_champ_detail_page, menu);
        return true;
    }*/

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
