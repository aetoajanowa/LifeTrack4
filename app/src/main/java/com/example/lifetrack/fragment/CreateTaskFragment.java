package com.example.lifetrack.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentCreateTaskBinding;
import com.example.lifetrack.model.TaskModel;
import com.example.lifetrack.utils.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;


public class CreateTaskFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    FragmentCreateTaskBinding binding;
    String userTask;
    String deadline;
    String repeatCount;
    int startYear;
    int startMonth;
    int starDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        setTask(navController);
    }

    private void initClickers() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passModelToHomeFragment();
            }
        });
        binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        binding.repeatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRepeatDialog();
            }

        });
    }

    private void showRepeatDialog() {

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.repeat_dilog, null);
        Dialog alertDialog = new Dialog(requireContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.show();
        RadioButton neverBtn = alertDialog.findViewById(R.id.never);
        neverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(neverBtn.getText().toString());
                repeatCount = neverBtn.getText().toString();
                alertDialog.dismiss();
            }
        });
        RadioButton dayBtn = alertDialog.findViewById(R.id.every_day);
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(dayBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton weekBtn = alertDialog.findViewById(R.id.every_week);
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(weekBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton monthBtn = alertDialog.findViewById(R.id.every_month);
        monthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(monthBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton yearBtn = alertDialog.findViewById(R.id.every_year);
        yearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(yearBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton customBtn = alertDialog.findViewById(R.id.custom);
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(customBtn.getText().toString());
                alertDialog.dismiss();
            }
        });

    }

    private void showDialog() {
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        starDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(), this::onDateSet, startYear, startMonth, starDay);
        datePickerDialog.show();
    }


    private void passModelToHomeFragment() {
        userTask = binding.taskEd.getText().toString();
        TaskModel taskModel = new TaskModel(userTask, deadline, repeatCount);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        binding.dateTv.setText(year + "." + month + "." + day);
    }
}