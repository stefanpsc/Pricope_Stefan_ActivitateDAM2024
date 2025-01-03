package com.example.pregatiretest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImagineDomeniu> imagini= null;

    private Context ctx;

    private int resuraLayout=0;

    public ImagineAdapter(List<ImagineDomeniu> imagini, int resuraLayout, Context ctx) {
        this.imagini = imagini;
        this.resuraLayout = resuraLayout;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resuraLayout, parent, false);

        ImageView imagineIv = v.findViewById(R.id.imagineIv);
        TextView descriereTv = v.findViewById(R.id.descriereTv);

        ImagineDomeniu imagini = (ImagineDomeniu) getItem(position);

        imagineIv.setImageBitmap(imagini.getImagine());
        descriereTv.setText(imagini.getTextAfisat());
        return v;
    }
}
