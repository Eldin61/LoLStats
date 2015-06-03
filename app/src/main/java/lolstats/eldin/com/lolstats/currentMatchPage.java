package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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


        Long Summoner1Spell1 = 1L;
        Long Summoner1Spell2= 1L;
        Long Summoner2Spell1= 1L;
        Long Summoner2Spell2 = 1L;
        Long Summoner3Spell1= 1L;
        Long Summoner3Spell2 = 1L;
        Long Summoner4Spell1= 1L;
        Long Summoner4Spell2 = 1L;
        Long Summoner5Spell1= 1L;
        Long Summoner5Spell2 = 1L;
        Long Summoner6Spell1= 1L;
        Long Summoner6Spell2 = 1L;
        Long Summoner7Spell1= 1L;
        Long Summoner7Spell2 = 1L;
        Long Summoner8Spell1= 1L;
        Long Summoner8Spell2 = 1L;
        Long Summoner9Spell1= 1L;
        Long Summoner9Spell2 = 1L;
        Long Summoner10Spell1= 1L;
        Long Summoner10Spell2 = 1L;

        double blueKans = 0;
        double redKans = 0;



        ArrayList<String> SummonerNames = new ArrayList<>();
        ArrayList<String> Divisions = new ArrayList<>();
        ArrayList<String> Picks = new ArrayList<>();
        List<Long> SummonerSpell1 = new ArrayList<>();
        List<Long> SummonerSpell2 = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("name");

            SummonerNames = extras.getStringArrayList("SummonerNames");
            Divisions = extras.getStringArrayList("Divisions");
            Picks = extras.getStringArrayList("Picks");




            Summoner1Spell1 = extras.getLong("Summoner1Spell1");
            Summoner1Spell2 = extras.getLong("Summoner1Spell2");

            Summoner2Spell1 = extras.getLong("Summoner2Spell1");
            Summoner2Spell2 = extras.getLong("Summoner2Spell2");
            Summoner3Spell1 = extras.getLong("Summoner3Spell1");
            Summoner3Spell2 = extras.getLong("Summoner3Spell2");
            Summoner4Spell1 = extras.getLong("Summoner4Spell1");
            Summoner4Spell2 = extras.getLong("Summoner4Spell2");
            Summoner5Spell1 = extras.getLong("Summoner5Spell1");
            Summoner5Spell2 = extras.getLong("Summoner5Spell2");
            Summoner6Spell1 = extras.getLong("Summoner6Spell1");
            Summoner6Spell2 = extras.getLong("Summoner6Spell2");
            Summoner7Spell1 = extras.getLong("Summoner7Spell1");
            Summoner7Spell2 = extras.getLong("Summoner7Spell2");
            Summoner8Spell1 = extras.getLong("Summoner8Spell1");
            Summoner8Spell2 = extras.getLong("Summoner8Spell2");
            Summoner9Spell1 = extras.getLong("Summoner9Spell1");
            Summoner9Spell2 = extras.getLong("Summoner9Spell2");
            Summoner10Spell1 = extras.getLong("Summoner10Spell1");
            Summoner10Spell2 = extras.getLong("Summoner10Spell2");

            blueKans = extras.getDouble("blueKans");

        }
        TextView tvUserName = (TextView) findViewById(R.id.Username);
        tvUserName.setText(userName + "'s match:");

        TextView tvblueKans = (TextView) findViewById(R.id.blueKans);
        tvblueKans.setText("Blue chance: " + blueKans + " %");

        TextView tvredKans = (TextView)findViewById(R.id.redKans);
        tvredKans.setText("Red chance: " + (100 - blueKans) + " %");

        TextView tvSummoner1 = (TextView) findViewById(R.id.Summoner1);
        TextView tvPick1 = (TextView) findViewById(R.id.Pick1);
        TextView tvSummoner1Div = (TextView) findViewById(R.id.Summoner1Div);
        ImageView Summoner1DivPic = (ImageView) findViewById(R.id.Summoner1DivPic);
        ImageView imgSummoner1Spell1 = (ImageView) findViewById(R.id.Summoner1Spell1);
        ImageView imgSummoner1Spell2 = (ImageView) findViewById(R.id.Summoner1Spell2);
        setData(1, SummonerNames.get(0), Picks.get(0), Divisions.get(0), Summoner1Spell1, Summoner1Spell2, tvSummoner1, tvPick1, tvSummoner1Div, Summoner1DivPic, imgSummoner1Spell1, imgSummoner1Spell2);

        TextView tvSummoner2 = (TextView) findViewById(R.id.Summoner2);
        TextView tvPick2 = (TextView) findViewById(R.id.Pick2);
        TextView tvSummoner2Div = (TextView) findViewById(R.id.Summoner2Div);
        ImageView Summoner2DivPic = (ImageView) findViewById(R.id.Summoner2DivPic);
        ImageView imgSummoner2Spell1 = (ImageView) findViewById(R.id.Summoner2Spell1);
        ImageView imgSummoner2Spell2 = (ImageView) findViewById(R.id.Summoner2Spell2);
        setData(2, SummonerNames.get(1), Picks.get(1), Divisions.get(1), Summoner2Spell1, Summoner2Spell2, tvSummoner2, tvPick2, tvSummoner2Div, Summoner2DivPic, imgSummoner2Spell1, imgSummoner2Spell2 );

        TextView tvSummoner3 = (TextView) findViewById(R.id.Summoner3);
        TextView tvPick3 = (TextView) findViewById(R.id.Pick3);
        TextView tvSummoner3Div = (TextView) findViewById(R.id.Summoner3Div);
        ImageView Summoner3DivPic = (ImageView) findViewById(R.id.Summoner3DivPic);
        ImageView imgSummoner3Spell1 = (ImageView) findViewById(R.id.Summoner3Spell1);
        ImageView imgSummoner3Spell2 = (ImageView) findViewById(R.id.Summoner3Spell2);
        setData(3, SummonerNames.get(2), Picks.get(2), Divisions.get(2), Summoner3Spell1, Summoner3Spell2, tvSummoner3, tvPick3, tvSummoner3Div, Summoner3DivPic, imgSummoner3Spell1, imgSummoner3Spell2 );

        TextView tvSummoner4 = (TextView) findViewById(R.id.Summoner4);
        TextView tvPick4 = (TextView) findViewById(R.id.Pick4);
        TextView tvSummoner4Div = (TextView) findViewById(R.id.Summoner4Div);
        ImageView Summoner4DivPic = (ImageView) findViewById(R.id.Summoner4DivPic);
        ImageView imgSummoner4Spell1 = (ImageView) findViewById(R.id.Summoner4Spell1);
        ImageView imgSummoner4Spell2 = (ImageView) findViewById(R.id.Summoner4Spell2);
        setData(4, SummonerNames.get(3), Picks.get(3), Divisions.get(3), Summoner4Spell1, Summoner4Spell2, tvSummoner4, tvPick4, tvSummoner4Div, Summoner4DivPic, imgSummoner4Spell1, imgSummoner4Spell2 );

        TextView tvSummoner5 = (TextView) findViewById(R.id.Summoner5);
        TextView tvPick5 = (TextView) findViewById(R.id.Pick5);
        TextView tvSummoner5Div = (TextView) findViewById(R.id.Summoner5Div);
        ImageView Summoner5DivPic = (ImageView) findViewById(R.id.Summoner5DivPic);
        ImageView imgSummoner5Spell1 = (ImageView) findViewById(R.id.Summoner5Spell1);
        ImageView imgSummoner5Spell2 = (ImageView) findViewById(R.id.Summoner5Spell2);
        setData(5, SummonerNames.get(4), Picks.get(4), Divisions.get(4), Summoner5Spell1, Summoner5Spell2, tvSummoner5, tvPick5, tvSummoner5Div, Summoner5DivPic, imgSummoner5Spell1, imgSummoner5Spell2 );

        TextView tvSummoner6 = (TextView) findViewById(R.id.Summoner6);
        TextView tvPick6 = (TextView) findViewById(R.id.Pick6);
        TextView tvSummoner6Div = (TextView) findViewById(R.id.Summoner6Div);
        ImageView Summoner6DivPic = (ImageView) findViewById(R.id.Summoner6DivPic);
        ImageView imgSummoner6Spell1 = (ImageView) findViewById(R.id.Summoner6Spell1);
        ImageView imgSummoner6Spell2 = (ImageView) findViewById(R.id.Summoner6Spell2);
        setData(6, SummonerNames.get(5), Picks.get(5), Divisions.get(5), Summoner6Spell1, Summoner6Spell2, tvSummoner6, tvPick6, tvSummoner6Div, Summoner6DivPic, imgSummoner6Spell1, imgSummoner6Spell2 );

        TextView tvSummoner7 = (TextView) findViewById(R.id.Summoner7);
        TextView tvPick7 = (TextView) findViewById(R.id.Pick7);
        TextView tvSummoner7Div = (TextView) findViewById(R.id.Summoner7Div);
        ImageView Summoner7DivPic = (ImageView) findViewById(R.id.Summoner7DivPic);
        ImageView imgSummoner7Spell1 = (ImageView) findViewById(R.id.Summoner7Spell1);
        ImageView imgSummoner7Spell2 = (ImageView) findViewById(R.id.Summoner7Spell2);
        setData(7, SummonerNames.get(6), Picks.get(6), Divisions.get(6), Summoner7Spell1, Summoner7Spell2, tvSummoner7, tvPick7, tvSummoner7Div, Summoner7DivPic, imgSummoner7Spell1, imgSummoner7Spell2 );

        TextView tvSummoner8 = (TextView) findViewById(R.id.Summoner8);
        TextView tvPick8 = (TextView) findViewById(R.id.Pick8);
        TextView tvSummoner8Div = (TextView) findViewById(R.id.Summoner8Div);
        ImageView Summoner8DivPic = (ImageView) findViewById(R.id.Summoner8DivPic);
        ImageView imgSummoner8Spell1 = (ImageView) findViewById(R.id.Summoner8Spell1);
        ImageView imgSummoner8Spell2 = (ImageView) findViewById(R.id.Summoner8Spell2);
        setData(8, SummonerNames.get(7), Picks.get(7), Divisions.get(7), Summoner8Spell1, Summoner8Spell2, tvSummoner8, tvPick8, tvSummoner8Div, Summoner8DivPic, imgSummoner8Spell1, imgSummoner8Spell2 );

        TextView tvSummoner9 = (TextView) findViewById(R.id.Summoner9);
        TextView tvPick9 = (TextView) findViewById(R.id.Pick9);
        TextView tvSummoner9Div = (TextView) findViewById(R.id.Summoner9Div);
        ImageView Summoner9DivPic = (ImageView) findViewById(R.id.Summoner9DivPic);
        ImageView imgSummoner9Spell1 = (ImageView) findViewById(R.id.Summoner9Spell1);
        ImageView imgSummoner9Spell2 = (ImageView) findViewById(R.id.Summoner9Spell2);
        setData(9, SummonerNames.get(8), Picks.get(8), Divisions.get(8), Summoner9Spell1, Summoner9Spell2, tvSummoner9, tvPick9, tvSummoner9Div, Summoner9DivPic, imgSummoner9Spell1, imgSummoner9Spell2 );

        TextView tvSummoner10 = (TextView) findViewById(R.id.Summoner10);
        TextView tvPick10 = (TextView) findViewById(R.id.Pick9);
        TextView tvSummoner10Div = (TextView) findViewById(R.id.Summoner10Div);
        ImageView Summoner10DivPic = (ImageView) findViewById(R.id.Summoner10DivPic);
        ImageView imgSummoner10Spell1 = (ImageView) findViewById(R.id.Summoner10Spell1);
        ImageView imgSummoner10Spell2 = (ImageView) findViewById(R.id.Summoner10Spell2);
        setData(10, SummonerNames.get(9), Picks.get(9), Divisions.get(9), Summoner10Spell1, Summoner10Spell2, tvSummoner10, tvPick10, tvSummoner10Div, Summoner10DivPic, imgSummoner10Spell1, imgSummoner10Spell2 );

    }

    public void setData (int i, String Summoner, String pick, String div, long sum1, long sum2,TextView tv1,TextView tv2, TextView tv3, ImageView img1, ImageView img2, ImageView img3){

        tv1.setText(Summoner);
        tv2.setText(pick);
        tv3.setText(div);
        setDivPic(div, img1);
        setSumSpel(sum1, img2);
        setSumSpel(sum2, img3);

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
