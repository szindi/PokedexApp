package com.szindi.dmit2504.pokedexstart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokeapi.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokedexAdapter extends BaseAdapter {

    private final String URL_FORMAT = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png";
    private ArrayList<Result> mResults;

    public PokedexAdapter(ArrayList<Result> results) {
        mResults = results;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public String getItem(int position) {
        return mResults.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View pokedexView = inflater.inflate(R.layout.view_pokedex, parent, false);

        TextView nameTextView = pokedexView.findViewById(R.id.view_pokedex_name);
        TextView numberTextView = pokedexView.findViewById(R.id.view_pokedex_number);

        nameTextView.setText(mResults.get(position).getName());
        numberTextView.setText(String.valueOf(position + 1));

        ImageView spriteView = pokedexView.findViewById(R.id.view_pokedex_sprite);
        Picasso.get()
                .load(String.format(URL_FORMAT, position + 1))
                .resize(500, 500)
                .centerCrop()
                .into(spriteView);

        return pokedexView;
    }
}
