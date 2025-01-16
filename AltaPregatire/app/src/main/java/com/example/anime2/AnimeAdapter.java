package com.example.anime2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AnimeAdapter extends BaseAdapter {

    private List<Anime> anime = null;

    private Context ctx;

    private int resursaLayout;

    public AnimeAdapter(List<Anime> anime, int resursaLayout, Context ctx) {
        this.anime = anime;
        this.resursaLayout = resursaLayout;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return anime.size();
    }

    @Override
    public Object getItem(int position) {
        return anime.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater infalter = LayoutInflater.from(ctx);
        View v = infalter.inflate(resursaLayout, parent, false);

        TextView denumireTv =v.findViewById(R.id.denumireTV);
        TextView anAparitieTv= v.findViewById(R.id.anTV);
        TextView genreTv = v.findViewById(R.id.genreTV);
        CheckBox cbTv = v.findViewById(R.id.finishedTV);
        TextView erEpTv = v.findViewById(R.id.nrEpTV);

        Anime anime = (Anime)getItem(position);

        denumireTv.setText(anime.getDenumire());
        anAparitieTv.setText(""+anime.getAnAparitie());
        genreTv.setText(anime.getGenre());
        cbTv.setChecked(anime.isFinished());
        erEpTv.setText(""+anime.getNrEpisoade());

        return v;
    }
}
