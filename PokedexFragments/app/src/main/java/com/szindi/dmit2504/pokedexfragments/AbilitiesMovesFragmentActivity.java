package com.szindi.dmit2504.pokedexfragments;

import android.os.Bundle;

public class AbilitiesMovesFragmentActivity extends BaseActivity {

    private String[] mTabTitles = new String[]{"Pokemon", "Moves", "Abilities", "Stats"};
    private String mPokemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities_moves_fragment);

        mPokemonName = getIntent().getStringExtra("NAME");

        AbilitiesFragment abilitiesFragment = new AbilitiesFragment();
        Bundle abilitiesBundle = new Bundle();
        abilitiesBundle.putString("NAME", mPokemonName);
        abilitiesFragment.setArguments(abilitiesBundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_pokemon_fragment_abilities, abilitiesFragment)
                .commit();

        MovesFragment movesFragment = new MovesFragment();
        Bundle movesBundle = new Bundle();
        movesBundle.putString("NAME", mPokemonName);
        movesFragment.setArguments(movesBundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_pokemon_fragment_moves, movesFragment)
                .commit();
    }
}