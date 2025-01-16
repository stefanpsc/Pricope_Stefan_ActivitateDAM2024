package com.example.seminar4_1098;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class CarteAdapter extends BaseAdapter {

    private List<Carte> carte = null;

    private Context ctx;

    private int resursaLayout;

    public CarteAdapter(List<Carte> carte, Context ctx, int resursaLayout) {
        this.carte = carte;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return carte.size();
    }

    @Override
    public Object getItem(int position) {
        return carte.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        TextView numeCarteTview =v.findViewById(R.id.numeCarteTv);
        TextView autorCarteTview = v.findViewById(R.id.autorCarteTv);
        TextView anCarteTview = v.findViewById(R.id.anCarteTv);
        CheckBox cititaCbox = v.findViewById(R.id.cititaTv);

        Carte carte = (Carte)getItem(position);

        numeCarteTview.setText(carte.getNumeCarte());
        autorCarteTview.setText(carte.getAutorCarte());
        anCarteTview.setText(""+carte.getAnCarte());
        cititaCbox.setChecked(carte.isCitita());

        return v;
    }
}
