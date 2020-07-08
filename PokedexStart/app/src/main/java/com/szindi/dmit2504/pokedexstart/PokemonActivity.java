package com.szindi.dmit2504.pokedexstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.pokeapi.Pokemon;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonActivity extends BaseActivity {

    private static final String NAME = "NAME";

    private String mPokemonName;
    private Pokemon mPokemon;

    private ImageView mSpriteView;
    private TextView mNameTextView;
    private TextView mNumberTextView;
    private TextView mHeightTextView;
    private TextView mWeightTextView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pokemon);

        mPokemonName = getIntent().getStringExtra(NAME);

        mSpriteView = findViewById(R.id.fragment_pokemon_sprite);
        mNameTextView = findViewById(R.id.fragment_pokemon_name);
        mNumberTextView = findViewById(R.id.fragment_pokemon_number);
        mHeightTextView = findViewById(R.id.fragment_pokemon_height);
        mWeightTextView = findViewById(R.id.fragment_pokemon_weight);
        mListView = findViewById(R.id.fragment_pokemon_listView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPokemon();
    }

    private void getPokemon() {
        Call<Pokemon> getCall = mPokeService.getPokemonByName(mPokemonName);
        getCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    mPokemon = new Pokemon(response.body());

                    Picasso.get()
                            .load(mPokemon.getSprites().getFrontDefault())
                            .resize(500, 500)
                            .centerCrop()
                            .into(mSpriteView);

                    mNameTextView.setText(mPokemon.getName());
                    mNumberTextView.setText(mPokemon.getId().toString());
                    mHeightTextView.setText(String.valueOf((double) mPokemon.getHeight() / 10));
                    mWeightTextView.setText(String.valueOf((double) mPokemon.getWeight() / 10));

                    TypeAdapter adapter = new TypeAdapter(mPokemon);
                    mListView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(), mPokemon.getName(), Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.options_menu_moves:
                Intent movesIntent = new Intent(this, MovesActivity.class);
                movesIntent.putExtra("NAME", mPokemonName);
                startActivity(movesIntent);
                return true;
            case R.id.options_menu_abilities:
                Intent abilitiesIntent = new Intent(this, AbilitiesActivity.class);
                abilitiesIntent.putExtra("NAME", mPokemonName);
                startActivity(abilitiesIntent);
                return true;
            case R.id.options_menu_stats:
                Intent statsIntent = new Intent(this, StatsActivity.class);
                statsIntent.putExtra("NAME", mPokemonName);
                startActivity(statsIntent);
                return true;
            default:
                return false;
        }
    }
}