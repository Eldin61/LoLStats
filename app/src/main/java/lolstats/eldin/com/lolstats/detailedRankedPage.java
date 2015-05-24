package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Eldin on 18-5-2015.
 */
public class detailedRankedPage extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_ranked);

        TextView tvGames = (TextView) findViewById(R.id.totalGames);
        TextView tvWon = (TextView) findViewById(R.id.gamesWon);
        TextView tvLost = (TextView) findViewById(R.id.gamesLost);

        TextView tvGold = (TextView) findViewById(R.id.totalGold);
        TextView tvMinions = (TextView) findViewById(R.id.totalMinions);
        TextView tvNeutral = (TextView) findViewById(R.id.totalNeutral);

        TextView tvKills = (TextView) findViewById(R.id.totalKills);
        TextView tvAssists = (TextView) findViewById(R.id.totalAssists);
        TextView tvDeaths = (TextView) findViewById(R.id.totalDeaths);
        TextView tvPenta = (TextView) findViewById(R.id.totalPenta);
        TextView tvQuadra = (TextView) findViewById(R.id.totalQuadra);
        TextView tvTriple = (TextView) findViewById(R.id.totalTriple);
        TextView tvDouble = (TextView) findViewById(R.id.totalDouble);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tvGames.setText(extras.getString("totalgames"));
            tvWon.setText(extras.getString("won"));
            tvLost.setText(extras.getString("lost"));

            tvGold.setText(extras.getString("gold"));
            tvMinions.setText(extras.getString("minions"));
            tvNeutral.setText(extras.getString("neutral"));
            tvPenta.setText(extras.getString("penta"));
            tvQuadra.setText(extras.getString("quadra"));
            tvTriple.setText(extras.getString("triple"));
            tvDouble.setText(extras.getString("double"));
            tvKills.setText(extras.getString("kills"));
            tvAssists.setText(extras.getString("assists"));
            tvDeaths.setText(extras.getString("deaths"));
        }

    }
}
