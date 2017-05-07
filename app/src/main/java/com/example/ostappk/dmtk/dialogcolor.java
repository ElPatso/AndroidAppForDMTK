package com.example.ostappk.dmtk;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by OstapPK on 13.11.2015.
 */
public class dialogcolor extends DialogFragment  {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        final View v = inflater.inflate(R.layout.buttoncolor, null);
        v.findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
            Button btn = (Button) v.findViewById(R.id.button2);
            @Override
            public void onClick(View v) {


            }
        });

        return v;
    }
}
