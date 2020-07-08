package com.szindi.dmit2504.pokedexfragments;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pokeapi.Pokemon;
import com.pokeapi.Stat;

import java.util.List;

public class StatsAdapter extends BaseAdapter {

    Pokemon mPokemon;
    List<Stat> mStats;

    public StatsAdapter(Pokemon pokemon) {
        mPokemon = pokemon;
        mStats = mPokemon.getStats();
    }

    @Override
    public int getCount() {
        return mStats.size();
    }

    @Override
    public Stat getItem(int position) {
        return mStats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View statsView = inflater.inflate(R.layout.view_stats, parent, false);

        TextView nameTextView = statsView.findViewById(R.id.view_stats_name);
        nameTextView.setText(mStats.get(position).getStat().getName());

        TextView baseTextView = statsView.findViewById(R.id.view_stats_base);
        baseTextView.setText(mStats.get(position).getBaseStat().toString());

        return statsView;
    }
}
