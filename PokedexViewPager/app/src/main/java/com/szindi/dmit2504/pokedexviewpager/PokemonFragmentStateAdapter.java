package com.szindi.dmit2504.pokedexviewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PokemonFragmentStateAdapter extends FragmentStateAdapter {

    public String mPokemonName;

    public PokemonFragmentStateAdapter(FragmentActivity fragmentActivity, String pokemonName) {
        super(fragmentActivity);
        mPokemonName = pokemonName;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new MovesFragment();
                break;
            case 2:
                fragment = new AbilitiesFragment();
                break;
            case 3:
                fragment = new StatsFragment();
                break;
            default:
                fragment = new PokemonFragment();
                break;
        }

        Bundle args = new Bundle();
        args.putString("NAME", mPokemonName);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
