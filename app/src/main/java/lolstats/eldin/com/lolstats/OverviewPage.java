package lolstats.eldin.com.lolstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eldin on 12-5-2015.
 */
public class OverviewPage extends ActionBarActivity {

    String region1;

    private void startRanked(){
        TextView tvS = (TextView) findViewById(R.id.test);
        detailedRank d = new detailedRank(OverviewPage.this);
        d.setName(tvS.getText().toString());
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

    private void startChamplist(){
        champions c = new champions(OverviewPage.this);
        new champions(OverviewPage.this).execute();
    }

    private void startMatchHistory(){
        TextView tv = (TextView) findViewById(R.id.test);
        MatchHistory m = new MatchHistory();
        m.sName = tv.getText().toString();
        new MatchHistory().execute();
        Intent i = new Intent(OverviewPage.this, MatchHistoryPage.class);
        OverviewPage.this.startActivity(i);
    }


    String TITLES[] = {"New Summoner","Detailed Ranked","Current Match","Overview","Champions", "Match history"};
    int ICONS[] = {R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action};

    String NAME = "New Summoner";
    String EMAIL = "";
    int PROFILE = R.drawable.azir;

    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_page);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
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
            region1 = extras.getString("region");
        }

        ImageView v = (ImageView) findViewById(R.id.div1);

        if(soloDiv.contains("BRONZE")){
            v.setImageResource(R.drawable.bronze);
        } else if (soloDiv.contains("SILVER")){
            v.setImageResource(R.drawable.silver);
        } else if (soloDiv.contains("GOLD")){
            v.setImageResource(R.drawable.gold);
        } else if (soloDiv.contains("PLATINUM")){
            v.setImageResource(R.drawable.platinum);
        } else if (soloDiv.contains("DIAMOND")){
            v.setImageResource(R.drawable.diamond);
        } else if (soloDiv.contains("MASTER")){
            v.setImageResource(R.drawable.master);
        } else if (soloDiv.contains("CHALLENGER")){
            v.setImageResource(R.drawable.challenger);
        }

        ImageView v2 = (ImageView) findViewById(R.id.div2);

        if(teamDiv.contains("BRONZE")){
            v2.setImageResource(R.drawable.bronze);
        } else if (teamDiv.contains("SILVER")){
            v2.setImageResource(R.drawable.silver);
        } else if (teamDiv.contains("GOLD")){
            v2.setImageResource(R.drawable.gold);
        } else if (teamDiv.contains("PLATINUM")){
            v2.setImageResource(R.drawable.platinum);
        } else if (teamDiv.contains("DIAMOND")){
            v2.setImageResource(R.drawable.diamond);
        } else if (teamDiv.contains("MASTER")){
            v2.setImageResource(R.drawable.master);
        } else if (teamDiv.contains("CHALLENGER")){
            v2.setImageResource(R.drawable.challenger);
        }

        ImageView v3 = (ImageView) findViewById(R.id.div3);

        if(threeDiv.contains("BRONZE")){
            v3.setImageResource(R.drawable.bronze);
        } else if (threeDiv.contains("SILVER")){
            v3.setImageResource(R.drawable.silver);
        } else if (threeDiv.contains("GOLD")){
            v3.setImageResource(R.drawable.gold);
        } else if (threeDiv.contains("PLATINUM")){
            v3.setImageResource(R.drawable.platinum);
        } else if (threeDiv.contains("DIAMOND")){
            v3.setImageResource(R.drawable.diamond);
        } else if (threeDiv.contains("MASTER")){
            v3.setImageResource(R.drawable.master);
        } else if (threeDiv.contains("CHALLENGER")){
            v3.setImageResource(R.drawable.challenger);
        }


        ImageView c1 = (ImageView) findViewById(R.id.img1);
        c1.setImageResource(R.drawable.amumu);

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

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);

        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }



        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();



        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);

        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(OverviewPage.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Drawer.closeDrawers();
                    if(recyclerView.getChildPosition(child) == 1) {
                        Intent intent = new Intent(OverviewPage.this, MainActivity.class);
                        OverviewPage.this.startActivity(intent);
                    }
                    if(recyclerView.getChildPosition(child) == 2){
                        startRanked();
                    }
                    if(recyclerView.getChildPosition(child) == 3){
                        startSeq();
                    }
                    if(recyclerView.getChildPosition(child) == 5){
                        startChamplist();
                    }
                    if(recyclerView.getChildPosition(child) == 6){
                        startMatchHistory();
                    }
                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }



        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
}
