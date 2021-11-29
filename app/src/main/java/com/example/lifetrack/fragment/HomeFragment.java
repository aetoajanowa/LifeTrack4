package com.example.lifetrack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentHomeBinding;
import com.example.lifetrack.utils.Constants;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addFab.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.createTaskFragment);
        });
        getTask();
    }

    private void getTask() {
        getParentFragmentManager().setFragmentResultListener("requaer", getViewLifecycleOwner(), (requestKey, result) -> {
            String task = result.getString(Constants.USER_TASK);
            Log.e("TAG", "getTask:" + task);
        });
    }
}