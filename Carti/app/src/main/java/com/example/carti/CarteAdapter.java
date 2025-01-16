package com.example.carti;

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

        TextView numeTView = v.findViewById(R.id.numeTV);
        TextView autorTView = v.findViewById(R.id.autorTV);
        TextView anTV = v.findViewById(R.id.anTV);
        CheckBox cb = v.findViewById(R.id.cititCheckBox);

        Carte carte =(Carte)getItem(position);

        numeTView.setText(carte.getNumeCarte());
        autorTView.setText(carte.getAutorCarte());
        anTV.setText(""+carte.getAnCarte());
        cb.setChecked(carte.isCitit());


        return v;
    }
}
