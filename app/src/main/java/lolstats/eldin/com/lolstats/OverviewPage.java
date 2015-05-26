package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Eldin on 12-5-2015.
 */
public class OverviewPage extends ActionBarActivity {

    public void btnCurr(View w){
        startSeq();
    }

    public void btnRanked(View w){
        startRanked();
    }

    private void startRanked(){
        TextView tvS = (TextView) findViewById(R.id.test);
        detailedRank d = new detailedRank(OverviewPage.this);
        d.sName = tvS.getText() + "";
        new detailedRank(OverviewPage.this).execute();
    }

    private void setName(){
        TextView tvS = (TextView) findViewById(R.id.test);
        currentMatch c = new currentMatch(OverviewPage.this);
        c.sName = tvS.getText() + "";
    }

    private void startSeq(){
        setName();
        new currentMatch(OverviewPage.this).execute();
    }

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_page);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        String message = "";
        String soloDiv = "";
        String teamDiv = "";
        String threeDiv = "";
        String soloWin = "";
        String teamWin = "";
        String threeWin = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            message = extras.getString("name");
            soloDiv = extras.getString("soloDiv");
            teamDiv = extras.getString("teamDiv");
            threeDiv = extras.getString("threeDiv");

            soloWin = extras.getString("soloWin");
            teamWin = extras.getString("teamWin");
            threeWin = extras.getString("threeWin");
        }

        TextView tv = (TextView) findViewById(R.id.test);
        tv.setText(message);

        TextView tvSolo = (TextView) findViewById(R.id.solodiv);
        tvSolo.setText(soloDiv);

        TextView tvTeam = (TextView) findViewById(R.id.teamdiv);
        tvTeam.setText(teamDiv);

        TextView tvThree = (TextView) findViewById(R.id.threediv);
        tvThree.setText(threeDiv);

        TextView tvSoloWins = (TextView) findViewById(R.id.solowin);
        tvSoloWins.setText(soloWin);

        TextView tvTeamWins = (TextView) findViewById(R.id.teamwin);
        tvTeamWins.setText(teamWin);

        TextView tvThreeWins = (TextView) findViewById(R.id.threewin);
        tvThreeWins.setText(threeWin);

    }
}
