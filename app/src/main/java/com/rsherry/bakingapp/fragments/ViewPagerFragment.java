package com.rsherry.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.fragments.IngredientsFragment;
import com.rsherry.bakingapp.fragments.StepsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends Fragment {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, view);

        // Getting ingredient and step lists
        Bundle bundle = this.getArguments();


        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        final StepsFragment stepsFragment = new StepsFragment();

        // Passing lists to fragments

        ingredientsFragment.setArguments(bundle);
        stepsFragment.setArguments(bundle);

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? stepsFragment : ingredientsFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Steps":"Ingredients";
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }
}
