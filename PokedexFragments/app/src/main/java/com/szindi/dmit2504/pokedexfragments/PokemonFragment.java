package com.szindi.dmit2504.pokedexfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pokeapi.Pokemon;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonFragment extends BaseFragment {

    protected static final String NAME = "NAME";

    protected String mPokemonName;
    protected Pokemon mPokemon;

    protected ImageView mSpriteView;
    protected TextView mNameTextView;
    protected TextView mNumberTextView;
    protected TextView mHeightTextView;
    protected TextView mWeightTextView;
    protected ListView mListView;

    public PokemonFragment() {
    }

    public static PokemonFragment newInstance(String pokemonName) {
        PokemonFragment fragment = new PokemonFragment();
        Bundle args = new Bundle();
        args.putString(NAME, pokemonName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPokemonName = getArguments().getString(NAME);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSpriteView = getView().findViewById(R.id.fragment_pokemon_sprite);
        mNameTextView = getView().findViewById(R.id.fragment_pokemon_name);
        mNumberTextView = getView().findViewById(R.id.fragment_pokemon_number);
        mHeightTextView = getView().findViewById(R.id.fragment_pokemon_height);
        mWeightTextView = getView().findViewById(R.id.fragment_pokemon_weight);
        mListView = getView().findViewById(R.id.fragment_pokemon_listView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

    @Override
    public void onResume() {
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
                    Toast.makeText(getContext(), mPokemon.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Pokemon Fetch Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(getContext(), "Pokemon Fetch Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}