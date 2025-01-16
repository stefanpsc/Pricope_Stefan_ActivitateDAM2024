package com.example.anime2;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {

    public String denumire;

    public int anAparitie;

    public String genre;

    public boolean finished;

    public int nrEpisoade;

    protected Anime(Parcel in) {
        denumire = in.readString();
        anAparitie = in.readInt();
        genre = in.readString();
        finished = in.readByte() != 0;
        nrEpisoade = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeInt(anAparitie);
        dest.writeString(genre);
        dest.writeByte((byte) (finished ? 1 : 0));
        dest.writeInt(nrEpisoade);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNrEpisoade() {
        return nrEpisoade;
    }

    public void setNrEpisoade(int nrEpisoade) {
        this.nrEpisoade = nrEpisoade;
    }

    public Anime(String denumire, String genre, int anAparitie, boolean finished, int nrEpisoade) {
        this.denumire = denumire;
        this.genre = genre;
        this.anAparitie = anAparitie;
        this.finished = finished;
        this.nrEpisoade = nrEpisoade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anime{");
        sb.append("denumire='").append(denumire).append('\'');
        sb.append(", anAparitie=").append(anAparitie);
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", finished=").append(finished);
        sb.append(", nrEpisoade=").append(nrEpisoade);
        sb.append('}');
        return sb.toString();
    }


}
