package com.example.seminar4_1098;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Carte implements Parcelable {

    public String numeCarte;


    public String autorCarte;

    public int anCarte;

    public boolean citita;

    protected Carte(Parcel in) {
        numeCarte = in.readString();
        autorCarte = in.readString();
        anCarte = in.readInt();
        citita = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeCarte);
        dest.writeString(autorCarte);
        dest.writeInt(anCarte);
        dest.writeByte((byte) (citita ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public String getNumeCarte() {
        return numeCarte;
    }

    public void setNumeCarte(String numeCarte) {
        this.numeCarte = numeCarte;
    }

    public String getAutorCarte() {
        return autorCarte;
    }

    public void setAutorCarte(String autorCarte) {
        this.autorCarte = autorCarte;
    }

    public int getAnCarte() {
        return anCarte;
    }

    public void setAnCarte(int anCarte) {
        this.anCarte = anCarte;
    }

    public boolean isCitita() {
        return citita;
    }

    public void setCitita(boolean citita) {
        this.citita = citita;
    }

    public Carte(String numeCarte, String autorCarte, int anCarte, boolean citita) {
        this.numeCarte = numeCarte;
        this.autorCarte = autorCarte;
        this.anCarte = anCarte;
        this.citita = citita;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carte{");
        sb.append("numeCarte='").append(numeCarte).append('\'');
        sb.append(", autorCarte='").append(autorCarte).append('\'');
        sb.append(", anCarte=").append(anCarte);
        sb.append(", citita=").append(citita);
        sb.append('}');
        return sb.toString();
    }

}
