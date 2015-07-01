package lolstats.eldin.com.lolstats;

/**
 * Created by Eldin on 22-6-2015.
 */
public class Match {
    String kills;
    String deaths;
    String outcome;

    Match(String kills, String deaths, String outcome){
        this.kills = kills;
        this.deaths = deaths;
        this.outcome = outcome;
    }
}
