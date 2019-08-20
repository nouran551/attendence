package com.nouran.yaraab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Logout_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.activity_login,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Intent myIntent = new Intent(view.getContext(), loginActivity.class);
        startActivity (myIntent);
        // startActivity (new Intent (Logout_fragment.class,loginActivity.class));
         super.onViewCreated (view, savedInstanceState);
    }
}
