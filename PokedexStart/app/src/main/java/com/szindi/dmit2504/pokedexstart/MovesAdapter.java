package com.szindi.dmit2504.pokedexstart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pokeapi.Move;
import com.pokeapi.Pokemon;

import java.util.List;

public class MovesAdapter extends BaseAdapter {

    Pokemon mPokemon;
    List<Move> mMoves;

    public MovesAdapter(Pokemon pokemon) {
        mPokemon = pokemon;
        mMoves = pokemon.getMoves();
    }

    @Override
    public int getCount() {
        return mMoves.size();
    }

    @Override
    public Move getItem(int position) {
        return mMoves.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View moveView = inflater.inflate(R.layout.view_moves, parent, false);

        TextView nameTextView = moveView.findViewById(R.id.view_moves_name);
        nameTextView.setText(mMoves.get(position).getMove().getName());

        TextView levelTextView = moveView.findViewById(R.id.view_moves_level);
        TextView methodTextView = moveView.findViewById(R.id.view_moves_method);

        levelTextView.setText(mMoves.get(position).getVersionGroupDetails().get(0).getLevelLearnedAt().toString());
        methodTextView.setText(mMoves.get(position).getVersionGroupDetails().get(0).getMoveLearnMethod().getName());

        return moveView;
    }
}
