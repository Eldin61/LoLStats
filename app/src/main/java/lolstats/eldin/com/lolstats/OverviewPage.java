package lolstats.eldin.com.lolstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Eldin on 12-5-2015.
 */
public class OverviewPage extends ActionBarActivity {
    String sLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_page);


        String message = "";
        String level = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            message = extras.getString("name");
            level = extras.getString("level");
        }

        TextView tv = (TextView) findViewById(R.id.test);
        tv.setText(message + level);
    }
}
