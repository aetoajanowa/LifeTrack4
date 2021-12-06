package com.example.lifetrack.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentBoardFragmentsBinding;
import com.example.lifetrack.databinding.FragmentBoardFragmentsBinding;
import com.example.lifetrack.utils.Constants;

public class BoardFragments extends Fragment {
    FragmentBoardFragmentsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardFragmentsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPositionFromAdapter();
    }

    private void getPositionFromAdapter() {
        if (getArguments()!=null){
            int position = getArguments().getInt(Constants.FRAGMENT_POSITION);
            switch (position){
                case 0:
                    binding.description.setText("1");
                    break;
                case 1:
                    binding.description.setText("2");
                    break;
                case 2:
                    binding.description.setText("3");
                    break;
            }
        }
    }
}