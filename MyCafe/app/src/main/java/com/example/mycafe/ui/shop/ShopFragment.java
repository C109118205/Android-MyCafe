package com.example.mycafe.ui.shop;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.R;
import com.example.mycafe.databinding.FragmentShopBinding;

public class ShopFragment extends Fragment {
    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.b1;
//        final TextView textView2 = binding.b2;
//        final TextView textView3 = binding.b3;
//
//        shopViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        shopViewModel.getText().observe(getViewLifecycleOwner(), textView2::setText);
//        shopViewModel.getText().observe(getViewLifecycleOwner(), textView3::setText);

//        Toast.makeText(getActivity(), "welcome: "+check_user, Toast.LENGTH_SHORT).show();


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}