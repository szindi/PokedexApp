package com.szindi.dmit2504.pokedexviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pokeapi.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovesFragment extends BaseFragment {

    private static final String NAME = "NAME";

    protected String mPokemonName;
    protected Pokemon mPokemon;
    private ListView mListView;

    public MovesFragment() {
        // Required empty public constructor
    }

    public static MovesFragment newInstance(String pokemonName) {
        MovesFragment fragment = new MovesFragment();
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

        mListView = getView().findViewById(R.id.fragment_moves_listView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moves, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        getPokemon();
    }

    protected void getPokemon() {
        Call<Pokemon> getCall = mPokeService.getPokemonByName(mPokemonName);
        getCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    mPokemon = response.body();

                    MovesAdapter adapter = new MovesAdapter(mPokemon);
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
}