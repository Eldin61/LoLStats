package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Eldin on 18-5-2015.
 */
public class currentMatchPage extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentmatch);

        String userName = "";
        String currentGame = "";

        String Summoner1 = "";
        String Summoner2 = "";

        String Summoner1Div = "";
        String Summoner2Div = "";



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            userName = extras.getString("name");

            Summoner1 = extras.getString("Summoner1");
            Summoner2 = extras.getString("Summoner2");

            Summoner1Div = extras.getString("Summoner1Div");
            Summoner2Div = extras.getString("Summoner2Div");




        }
        TextView tvUserName = (TextView) findViewById(R.id.Username);
        tvUserName.setText(userName + "'s match:");

        TextView tvSummoner1 = (TextView) findViewById(R.id.Summoner1);
        tvSummoner1.setText(Summoner1);
        TextView tvSummoner2 = (TextView) findViewById(R.id.Summoner2);
        tvSummoner2.setText(Summoner2);

        TextView tvSummoner1Div = (TextView) findViewById(R.id.Summoner1Div);
        tvSummoner1Div.setText(Summoner1Div);

        TextView tvSummoner2Div = (TextView) findViewById(R.id.Summoner2Div);
        tvSummoner2Div.setText(Summoner2Div);
        //TextView tvCurrentGame = (TextView) findViewById(R.id.CurrentGame);
        //tvCurrentGame.setText(currentGame);
    }
}
