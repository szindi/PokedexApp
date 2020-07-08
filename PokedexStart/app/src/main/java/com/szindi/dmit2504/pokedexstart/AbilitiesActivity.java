package com.szindi.dmit2504.pokedexstart;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.pokeapi.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilitiesActivity extends BaseActivity {

    public Pokemon mPokemon;
    private String mPokemonName;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_abilities);

        mPokemonName = getIntent().getStringExtra("NAME");

        mListView = findViewById(R.id.fragment_abilities_listView);
    }

    private void getPokemon() {
        Call<Pokemon> getCall = mPokeService.getPokemonByName(mPokemonName);
        getCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                mPokemon = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), mPokemon.getName(), Toast.LENGTH_SHORT).show();

                    AbilitiesAdapter adapter = new AbilitiesAdapter(mPokemon);
                    mListView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Pokemon Fetch Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Pokemon Fetch Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPokemon();
    }
}