package com.aalasolutions.apps.cricketlivescores.classes;

import android.app.Application;

import com.aalasolutions.apps.cricketlivescores.model.MatchDetail;

import java.util.ArrayList;


public class CricketLiveScore extends Application {
    public boolean parsing;
    public int matchDetailssubmissions;
    public ArrayList<MatchDetail> matchDetails;

    @Override
    public void onCreate() {
        super.onCreate();
        parsing = false;
        matchDetails = new ArrayList<>();
        matchDetailssubmissions = 0;

    }

    public boolean isMatchDetailsEmpty() {
        return (matchDetails.isEmpty()) ? true : false;
    }

    public void cleanMatchDetails() {
        matchDetails.clear();
        matchDetailssubmissions = 0;
    }

    public void addMatchDetails(MatchDetail data) {
        matchDetails.add(data);
        matchDetailssubmissions++;
    }
}
