package com.mjrfusion.app.calcfusion.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mjrfusion.app.calcfusion.fragment.FirstOperationsFragment;
import com.mjrfusion.app.calcfusion.fragment.SecondOperationsFragment;

import java.util.ArrayList;

public class OperatorsAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment> fragments;

    public OperatorsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        fragments = new ArrayList<>();
        fragments.add(new FirstOperationsFragment());
        fragments.add(new SecondOperationsFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
