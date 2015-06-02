package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Eldin on 18-5-2015.
 */
public class currentMatchPage extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentmatch);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        String userName = "";
        String currentGame = "";

        String Summoner1 = "";
        String Summoner2 = "";

        String Pick1 = "";
        String Pick2 = "";

        String Summoner1Div = "";
        String Summoner2Div = "";

        Long Summoner1Spell1 = 1L;
        Long Summoner1Spell2= 1L;
        Long Summoner2Spell1= 1L;
        Long Summoner2Spell2 = 1L;

        double blueKans = 0;
        double redKans = 0;



        ArrayList<String> SummonerNames = new ArrayList<>();
        ArrayList<String> Divisions = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("name");

            Summoner1 = extras.getString("Summoner1");
            Summoner2 = extras.getString("Summoner2");

            Pick1 = extras.getString("Pick1");
            Pick2 = extras.getString("Pick2");

            Summoner1Div = extras.getString("Summoner1Div");
            Summoner2Div = extras.getString("Summoner2Div");

            Summoner1Spell1 = extras.getLong("Summoner1Spell1");
            Summoner1Spell2 = extras.getLong("Summoner1Spell2");
            Summoner2Spell1 = extras.getLong("Summoner2Spell1");
            Summoner2Spell2 = extras.getLong("Summoner2Spell2");

            SummonerNames = extras.getStringArrayList("SummonerNames");
            Divisions = extras.getStringArrayList("Divisions");

            blueKans = extras.getDouble("blueKans");
            //redKans = extras.getDouble("redKans");

        }
        TextView tvUserName = (TextView) findViewById(R.id.Username);
        tvUserName.setText(userName + "'s match:");

        TextView tvblueKans = (TextView) findViewById(R.id.blueKans);
        tvblueKans.setText("Blue chance: " + blueKans + " %");

        TextView tvredKans = (TextView)findViewById(R.id.redKans);
        tvredKans.setText("Red chance: " + (100 - blueKans) + " %");

        TextView tvSummoner1 = (TextView) findViewById(R.id.Summoner1);
        tvSummoner1.setText(Summoner1);
        TextView tvSummoner2 = (TextView) findViewById(R.id.Summoner2);
        tvSummoner2.setText(Summoner2);

        TextView tvPick1 = (TextView) findViewById(R.id.Pick1);
        tvPick1.setText(Pick1);

        TextView tvPick2 = (TextView) findViewById(R.id.Pick2);
        tvPick2.setText(Pick2);

        TextView tvSummoner1Div = (TextView) findViewById(R.id.Summoner1Div);
        tvSummoner1Div.setText(Summoner1Div);
        ImageView Summoner1DivPic = (ImageView) findViewById(R.id.Summoner1DivPic);
        setDivPic(Summoner1Div, Summoner1DivPic);

        TextView tvSummoner2Div = (TextView) findViewById(R.id.Summoner2Div);
        tvSummoner2Div.setText(Summoner2Div);
        ImageView Summoner2DivPic = (ImageView) findViewById(R.id.Summoner2DivPic);
        setDivPic(Summoner2Div, Summoner2DivPic);

        ImageView imgSummoner1Spell1 = (ImageView) findViewById(R.id.Summoner1Spell1);
        setSumSpel(Summoner1Spell1, imgSummoner1Spell1);
        ImageView imgSummoner1Spell2 = (ImageView) findViewById(R.id.Summoner1Spell2);
        setSumSpel(Summoner1Spell2, imgSummoner1Spell2);

        ImageView imgSummoner2Spell1 = (ImageView) findViewById(R.id.Summoner2Spell1);
        setSumSpel(Summoner2Spell1, imgSummoner2Spell1);
        ImageView imgSummoner2Spell2 = (ImageView) findViewById(R.id.Summoner2Spell2);
        setSumSpel(Summoner2Spell2, imgSummoner2Spell2);

//        TextView tvCurrentGame = (TextView) findViewById(R.id.CurrentGame);
//        tvCurrentGame.setText(currentGame);
    }

    public void setData (String Summoner,String div, ImageView imgDiv,long sum1, long sum2, ImageView sumSpell1, ImageView sumSpell2 ){
        String tvSum = "tv"+Summoner;
        int idname = Integer.parseInt("R.id."+tvSum);
        TextView temp = (TextView) findViewById(idname);
        temp.setText(Summoner);
        temp.setText(div);
        setDivPic(div, imgDiv);
//        setSumSpel(sum1, sumSpell1);
//        setSumSpel(sum2, sumSpell2);


    }

    public ImageView setDivPic(String soloDiv, ImageView v) {
        if (soloDiv.contains("BRONZE")) {
            v.setImageResource(R.drawable.bronze);
        } else if (soloDiv.contains("SILVER")) {
            v.setImageResource(R.drawable.silver);
        } else if (soloDiv.contains("GOLD")) {
            v.setImageResource(R.drawable.gold);
        } else if (soloDiv.contains("PLATINUM")) {
            v.setImageResource(R.drawable.platinum);
        } else if (soloDiv.contains("DIAMOND")) {
            v.setImageResource(R.drawable.diamond);
        } else if (soloDiv.contains("MASTER")) {
            v.setImageResource(R.drawable.master);
        } else if (soloDiv.contains("CHALLENGER")) {
            v.setImageResource(R.drawable.challenger);
        }
        return null;
    }
    public ImageView setSumSpel(Long sum, ImageView img){
        if (sum == 21){
            img.setImageResource(R.drawable.barrier);
        }
        if (sum == 1){
            img.setImageResource(R.drawable.cleanse);
        }
        if (sum == 2){
            img.setImageResource(R.drawable.clairvoyance);
        }
        if (sum == 14){
            img.setImageResource(R.drawable.ignite);
        }
        if (sum == 3){
            img.setImageResource(R.drawable.exhaust);
        }
        if (sum == 4){
            img.setImageResource(R.drawable.flash);
        }
        if (sum == 6){
            img.setImageResource(R.drawable.ghost);
        }
        if (sum == 7){
            img.setImageResource(R.drawable.heal);
        }
        if (sum == 13){
            img.setImageResource(R.drawable.clarity);
        }
        if (sum == 17){
            img.setImageResource(R.drawable.garrison);
        }
        if (sum == 30){
            img.setImageResource(R.drawable.dash);
        }
        if (sum == 12){
            img.setImageResource(R.drawable.teleport);
        }
        if (sum == 11){
            img.setImageResource(R.drawable.smite);
        }
        if (sum == 32){
            img.setImageResource(R.drawable.mark);
        }
        return null;
    }
}
