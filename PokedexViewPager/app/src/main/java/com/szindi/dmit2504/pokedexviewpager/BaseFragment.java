package com.szindi.dmit2504.pokedexviewpager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseFragment extends Fragment {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    protected PokeApiService mPokeService;
    protected Retrofit mRetrofit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mPokeService = mRetrofit.create(PokeApiService.class);
    }
}
