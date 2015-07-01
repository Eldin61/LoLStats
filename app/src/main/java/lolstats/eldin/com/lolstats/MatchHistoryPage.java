package lolstats.eldin.com.lolstats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eldin on 21-6-2015.
 */
public class MatchHistoryPage extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> myDataset;

    List<Match> m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        createData();
        mAdapter = new RecyclerAdapter(m);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void createData(){
        m = new ArrayList<>();
        m.add(new Match("10", "2", "loss"));
        m.add(new Match("10", "3", "loss"));
        m.add(new Match("10", "4", "loss"));
        m.add(new Match("10", "5", "win"));
    }

}