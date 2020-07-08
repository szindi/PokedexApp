package com.szindi.dmit2504.pokedexstart;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.pokeapi.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovesActivity extends BaseActivity {

    private static final String NAME = "NAME";

    private String mPokemonName;
    private Pokemon mPokemon;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_moves);

        mListView = findViewById(R.id.fragment_moves_listView);

        mPokemonName = getIntent().getStringExtra(NAME);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPokemon();
    }

    protected void getPokemon() {

        Call<Pokemon> getCall = mPokeService.getPokemonByName(mPokemonName);
        getCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    mPokemon = response.body();

                    MovesAdapter adapter = new MovesAdapter(mPokemon);
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
}