package com.szindi.dmit2504.pokedexfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pokeapi.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilitiesFragment extends BaseFragment {

    private static final String NAME = "NAME";

    public Pokemon mPokemon;
    private String mPokemonName;
    private ListView mListView;

    public AbilitiesFragment() {
        // Required empty public constructor
    }

    public static AbilitiesFragment newInstance(String pokemonName) {
        AbilitiesFragment fragment = new AbilitiesFragment();
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

        mListView = getView().findViewById(R.id.fragment_abilities_listView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abilities, container, false);
    }

    private void getPokemon() {
        Call<Pokemon> getCall = mPokeService.getPokemonByName(mPokemonName);
        getCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                mPokemon = response.body();
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    AbilitiesAdapter adapter = new AbilitiesAdapter(mPokemon);
                    mListView.setAdapter(adapter);
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

    @Override
    public void onResume() {
        super.onResume();

        getPokemon();
    }
}