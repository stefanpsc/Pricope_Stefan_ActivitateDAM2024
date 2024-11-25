package com.example.seminar4_1098;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {

    private List<ImaginiDomeniu> imagni =null;

    private Context ctx;

    private int resursaLayout;

    public ImagineAdapter(List<ImaginiDomeniu> imagni, Context ctx, int resursaLayout) {
        this.imagni = imagni;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImaginiDomeniu imagine = (ImaginiDomeniu) getItem(position);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView descriereTV = v.findViewById(R.id.descriereTV);

        imagineIV.setImageBitmap(imagine.getImagine());
        descriereTV.setText((imagine.getTextAfisat()));
        return null;
    }
}
