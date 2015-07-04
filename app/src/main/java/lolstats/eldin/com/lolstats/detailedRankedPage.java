package lolstats.eldin.com.lolstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Eldin on 18-5-2015.
 */
public class detailedRankedPage extends ActionBarActivity {

    String TITLES[] = {"New Summoner","Detailed Ranked","Current Match","Overview","blabla"};
    int ICONS[] = {R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action};

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
        setContentView(R.layout.detailed_ranked);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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
            tvGames.setText(extras.getString("totalGames"));
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

        final GestureDetector mGestureDetector = new GestureDetector(detailedRankedPage.this, new GestureDetector.SimpleOnGestureListener() {

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
                        Intent intent = new Intent(detailedRankedPage.this, MainActivity.class);
                        detailedRankedPage.this.startActivity(intent);
                    }
                    if(recyclerView.getChildPosition(child) == 2){
                    }
                    if(recyclerView.getChildPosition(child) == 3){
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

    public void compare(View w){
        String name = null;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            name = extras.getString("summoner");
        }
        EditText e = (EditText) findViewById(R.id.compS);
        compare c = new compare(detailedRankedPage.this);
        c.compName = e.getText().toString();
        c.sName = name;
        new compare(detailedRankedPage.this).execute();
    }
}
