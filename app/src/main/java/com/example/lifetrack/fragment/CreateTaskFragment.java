package com.example.lifetrack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentCreateTaskBinding;
import com.example.lifetrack.utils.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CreateTaskFragment extends BottomSheetDialogFragment {
    FragmentCreateTaskBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        setTask(navController);
    }

    private void setTask(NavController navController) {
        binding.addBtn.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            String userTask = "";
            bundle.putString(Constants.USER_TASK, userTask);
            getParentFragmentManager().setFragmentResult("requaer", bundle);
            close(navController);
        });

    }

    private void close(NavController navController) {
        navController.navigate(R.id.homeFragment);
    }
}
