package com.szindi.dmit2504.pokedexfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pokeapi.PokedexReturn;
import com.pokeapi.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends BaseActivity {

    private static final int mNumberOfPokemon = 151;

    private ListView mListView;
    private ArrayList<Result> mPokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        mListView = findViewById(R.id.activity_pokedex_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PokemonFragmentActivity.class);
                intent.putExtra("NAME", mPokedex.get(position).getName());
                startActivity(intent);
            }
        });

        mPokedex = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPokedex();
    }

    private void getPokedex() {
        Call<PokedexReturn> getCall = mPokeService.getAllPokemon(mNumberOfPokemon);
        getCall.enqueue(new Callback<PokedexReturn>() {
            @Override
            public void onResponse(Call<PokedexReturn> call, Response<PokedexReturn> response) {
                PokedexReturn responseBody = response.body();
                if (response.isSuccessful()) {
                    mPokedex = new ArrayList<>();
                    List<Result> results = responseBody.getResults();
                    mPokedex.addAll(results);

                    PokedexAdapter adapter = new PokedexAdapter(mPokedex);
                    mListView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Pokedex Fetch Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PokedexReturn> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Pokedex Fetch Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}