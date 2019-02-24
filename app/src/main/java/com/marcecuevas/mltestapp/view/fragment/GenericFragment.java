package com.marcecuevas.mltestapp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.marcecuevas.mltestapp.R;
import com.marcecuevas.mltestapp.model.MLError;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public abstract class GenericFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * Returns the layout id for the inflater so the view can be populated
     */
    protected abstract int getLayout();


    protected abstract void init();


    public void addFragmentWithBackStack(Class myNewFragmentClass, Boolean withBackstack) {
        Fragment myNewFragment = Fragment.instantiate(this.getActivity().getApplicationContext(), myNewFragmentClass.getName());
        String newFragment = myNewFragment.getClass().getName();
        FragmentTransaction t = getChildFragmentManager().beginTransaction();
        if (withBackstack) {
            t.setCustomAnimations(R.anim.pull_in_right, R.anim.push_out_left, R.anim.pull_in_left, R.anim.push_out_right);
            t.addToBackStack(newFragment);
        }
        t.add(R.id.fragment, myNewFragment, newFragment);
        t.commit();
    }

    public Fragment replaceFragmentWithBackStack(Class myNewFragmentClass, Boolean withBackstack) {
        return this.replaceFragmentWithBackStack(myNewFragmentClass, withBackstack, null);
    }

    public Fragment replaceFragmentWithBackStack(Class myNewFragmentClass, Boolean withBackstack, Bundle bundle) {
        Fragment myNewFragment = Fragment.instantiate(this.getActivity().getApplicationContext(), myNewFragmentClass.getName());
        String newFragment = myNewFragment.getClass().getName();
        FragmentTransaction t = getChildFragmentManager().beginTransaction();
        if (withBackstack) {
            t.setCustomAnimations(R.anim.pull_in_right, R.anim.push_out_left, R.anim.pull_in_left, R.anim.push_out_right);
            t.addToBackStack(newFragment);
        }
        myNewFragment.setArguments(bundle);
        t.replace(R.id.fragment, myNewFragment, newFragment);
        t.commit();
        return myNewFragment;
    }

    public Fragment replaceFragmentWithBackStackAnimated(Class myNewFragmentClass, Boolean withBackstack, Bundle bundle) {
        Fragment myNewFragment = Fragment.instantiate(this.getActivity().getApplicationContext(), myNewFragmentClass.getName());
        String newFragment = myNewFragment.getClass().getName();
        FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.setCustomAnimations(R.anim.pull_in_right, R.anim.push_out_left, R.anim.pull_in_left, R.anim.push_out_right);
        if (withBackstack) {
            t.addToBackStack(newFragment);
        }
        myNewFragment.setArguments(bundle);
        t.replace(R.id.fragment, myNewFragment, newFragment);
        t.commit();
        return myNewFragment;
    }
}