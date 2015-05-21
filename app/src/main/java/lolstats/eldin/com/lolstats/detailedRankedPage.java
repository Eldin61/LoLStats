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

        String divison = "";
        String games = "";
        String KDA = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            divison = extras.getString("division");
            games = extras.getString("games");
            KDA = extras.getString("KDA");
        }

        TextView tvDiv = (TextView) findViewById(R.id.divisionDet);
        tvDiv.setText(divison);

        TextView tvGam = (TextView) findViewById(R.id.games);
        tvGam.setText(games);

        TextView tvKDA = (TextView) findViewById(R.id.tvKDA);
        tvKDA.setText(KDA);

    }
}
