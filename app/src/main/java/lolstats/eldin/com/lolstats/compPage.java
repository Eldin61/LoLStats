package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Eldin on 7-6-2015.
 */
public class compPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_page);

        TextView tvVs = (TextView) findViewById(R.id.vs);
        TextView tvPerc = (TextView) findViewById(R.id.perc1);
        TextView tvTotal = (TextView) findViewById(R.id.total1);
        TextView tvWin = (TextView) findViewById(R.id.won1);
        TextView tvLost = (TextView) findViewById(R.id.lost1);
        TextView tvGold = (TextView) findViewById(R.id.totalGold1);
        TextView tvMinion = (TextView) findViewById(R.id.minions1);
        TextView tvNeutral = (TextView) findViewById(R.id.neutral1);
        TextView tvKils = (TextView) findViewById(R.id.kills1);
        TextView tvAssists = (TextView) findViewById(R.id.assists1);
        TextView tvDeaths = (TextView) findViewById(R.id.deaths1);
        TextView tvPenta = (TextView) findViewById(R.id.penta1);
        TextView tvQuadra = (TextView) findViewById(R.id.quadra1);
        TextView tvTriple = (TextView) findViewById(R.id.triple1);
        TextView tvDouble = (TextView) findViewById(R.id.double1);

        TextView tvPerc2 = (TextView) findViewById(R.id.perc2);
        TextView tvTotal2 = (TextView) findViewById(R.id.total2);
        TextView tvWin2 = (TextView) findViewById(R.id.won2);
        TextView tvLost2 = (TextView) findViewById(R.id.lost2);
        TextView tvGold2 = (TextView) findViewById(R.id.totalGold2);
        TextView tvMinion2 = (TextView) findViewById(R.id.minions2);
        TextView tvNeutral2 = (TextView) findViewById(R.id.neutral2);
        TextView tvKils2 = (TextView) findViewById(R.id.kills2);
        TextView tvAssists2 = (TextView) findViewById(R.id.assists2);
        TextView tvDeaths2 = (TextView) findViewById(R.id.deaths2);
        TextView tvPenta2 = (TextView) findViewById(R.id.penta2);
        TextView tvQuadra2 = (TextView) findViewById(R.id.quadra2);
        TextView tvTriple2 = (TextView) findViewById(R.id.triple2);
        TextView tvDouble2 = (TextView) findViewById(R.id.double2);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tvTotal.setText(extras.getString("total1"));
            tvWin.setText(extras.getString("won1"));
            tvLost.setText(extras.getString("lost1"));
            tvGold.setText(extras.getString("totalGold1"));
            tvMinion.setText(extras.getString("minions1"));
            tvNeutral.setText(extras.getString("neutral1"));
            tvKils.setText(extras.getString("kills1"));
            tvAssists.setText(extras.getString("assists1"));
            tvDeaths.setText(extras.getString("deaths1"));
            tvPenta.setText(extras.getString("penta1"));
            tvQuadra.setText(extras.getString("quadra1"));
            tvTriple.setText(extras.getString("triple1"));
            tvDouble.setText(extras.getString("double1"));

            tvTotal2.setText(extras.getString("total2"));
            tvWin2.setText(extras.getString("won2"));
            tvLost2.setText(extras.getString("lost2"));
            tvGold2.setText(extras.getString("totalGold2"));
            tvMinion2.setText(extras.getString("minions2"));
            tvNeutral2.setText(extras.getString("neutral2"));
            tvKils2.setText(extras.getString("kills2"));
            tvAssists2.setText(extras.getString("assists2"));
            tvDeaths2.setText(extras.getString("deaths2"));
            tvPenta2.setText(extras.getString("penta2"));
            tvQuadra2.setText(extras.getString("quadra2"));
            tvTriple2.setText(extras.getString("triple2"));
            tvDouble2.setText(extras.getString("double2"));
        }
    }
}
