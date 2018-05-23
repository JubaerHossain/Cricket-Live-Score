package com.aalasolutions.apps.cricketlivescores.adapter;



import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aalasolutions.apps.cricketlivescores.R;
import com.aalasolutions.apps.cricketlivescores.model.MatchModel;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context activity;
    private LayoutInflater inflater;
    private List<MatchModel> matchItems;

    public CustomListAdapter(Context activity, List<MatchModel> matchItems) {
        this.activity = activity;
        this.matchItems = matchItems;
    }

    @Override
    public int getCount() {
        return matchItems.size();
    }

    @Override
    public Object getItem(int location) {
        return matchItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView team1score = (TextView) convertView.findViewById(R.id.genre);
        TextView team2score = (TextView) convertView.findViewById(R.id.team2Score);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        title.setGravity(Gravity.CENTER_HORIZONTAL);
        team1score.setGravity(Gravity.CENTER_HORIZONTAL);
        team2score.setGravity(Gravity.CENTER_HORIZONTAL);
        year.setGravity(Gravity.CENTER_HORIZONTAL);

        MatchModel m = matchItems.get(position);

        title.setText(m.team1 + " vs " + m.team2);


        team1score.setText(m.team1_score);

        team2score.setText(m.team2_score);

        // TODO add the date or year of match
        year.setText(String.valueOf(2015));

        return convertView;
    }

}