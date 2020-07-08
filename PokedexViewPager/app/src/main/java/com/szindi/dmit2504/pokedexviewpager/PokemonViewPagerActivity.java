package com.szindi.dmit2504.pokedexviewpager;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PokemonViewPagerActivity extends BaseActivity {

    private String mPokemonName;
    private ViewPager2 mViewPager;
    private PokemonFragmentStateAdapter mStateAdapter;
    private String[] mTabTitles = new String[]{"Pokemon", "Moves", "Abilities", "Stats"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view_pager);

        ActionBar actionBar = getSupportActionBar();


        mPokemonName = getIntent().getStringExtra("NAME");

        mViewPager = findViewById(R.id.activity_pokemon_view_pager_viewPager);
        mStateAdapter = new PokemonFragmentStateAdapter(this, mPokemonName);
        mViewPager.setAdapter(mStateAdapter);

        TabLayout tabLayout = findViewById(R.id.activity_pokemon_view_pager_tabLayout);
        new TabLayoutMediator(tabLayout, mViewPager,
                (tab, position) -> {
                    tab.setText(mTabTitles[position]);
                }).attach();
    }
}