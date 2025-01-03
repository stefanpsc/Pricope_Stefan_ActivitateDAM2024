package com.example.cartev2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CarteAdapter extends BaseAdapter {
    private List<Carte> carte = null;

    private Context ctx;

    private int resursaLayout;

    public CarteAdapter(List<Carte> carte, int resursaLayout, Context ctx) {
        this.carte = carte;
        this.resursaLayout = resursaLayout;
        this.ctx = ctx;
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

        TextView numeCarte = v.findViewById(R.id.numeCarteTv);
        TextView autorCarte = v.findViewById(R.id.autorCarteTv);
        TextView anCarte = v.findViewById(R.id.anCarteTv);
        CheckBox citit = v.findViewById(R.id.cititaTv);

        Carte carte = (Carte) getItem(position);

        numeCarte.setText(carte.getNumeCarte());
        autorCarte.setText(carte.getAutorCarte());
        anCarte.setText(""+carte.getAnCarte());
        citit.setChecked(carte.isCitita());
        return v;
    }
}
