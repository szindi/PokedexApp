package com.szindi.dmit2504.pokedexfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class PokemonFragmentActivity extends BaseActivity {

    private String mPokemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_fragment);

        mPokemonName = getIntent().getStringExtra("NAME");

        PokemonFragment pokemonFragment = new PokemonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NAME", mPokemonName);
        pokemonFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_pokemon_fragment_pokemon, pokemonFragment)
                .commit();

        StatsFragment statsFragment = new StatsFragment();
        statsFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_pokemon_fragment_stats, statsFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_fragments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.options_menu_fragments_moves_abilities:
                Intent intent = new Intent(this, AbilitiesMovesFragmentActivity.class);
                intent.putExtra("NAME", mPokemonName);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}