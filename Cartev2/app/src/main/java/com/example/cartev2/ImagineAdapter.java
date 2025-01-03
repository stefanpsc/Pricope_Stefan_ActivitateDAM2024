package com.example.cartev2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {

    private List<ImagineDomeniu> imagini;

    private Context ctx;

    private int resursaLayout;

    public ImagineAdapter(List<ImagineDomeniu> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
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
        View v = inflater.inflate(resursaLayout, parent, false);

        ImageView imagineIv = v.findViewById(R.id.imaginiIv);
        TextView descriereTv = v.findViewById(R.id.descriereTv);

        ImagineDomeniu imagini = (ImagineDomeniu) getItem(position);

        imagineIv.setImageBitmap(imagini.getImagine());
        descriereTv.setText(imagini.getTextAfisat());
        return v;
    }
}
