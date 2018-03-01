package com.example.matthewschwarz.gitreference2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matthewschwarz on 3/1/18.
 */

public class Adapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList<GitReference> mDataSources;

    public Adapter(Context context, ArrayList<GitReference> commandList){
        mContext = context;
        mDataSources = commandList;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount(){
        return mDataSources.size();
    }
    @Override
    public Object getItem(int i){
        return mDataSources.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        View rowView = view;
        if (rowView == null){
            rowView = mInflator.inflate(R.layout.row_view, viewGroup, false);
        }
        //View rowView = mInflator.inflate(R.layout.row_view, viewGroup, false);
        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView sectionView = rowView.findViewById(R.id.section);
        TextView explanationView = rowView.findViewById(R.id.explanation);

        GitReference cmd = (GitReference) getItem(position);
        commandView.setText(cmd.getCommand());
        exampleView.setText(cmd.getExample());
        sectionView.setText(cmd.getSection());
        explanationView.setText(cmd.getExplanation());

        return rowView;
    }
}
