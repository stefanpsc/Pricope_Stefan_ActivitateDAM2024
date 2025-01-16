package com.example.carti;

import android.os.Parcel;
import android.os.Parcelable;

public class Carte implements Parcelable {

    public String numeCarte;

    public String autorCarte;

    public int anCarte;

    public boolean citit;

    public Carte(String numeCarte, int anCarte, String autorCarte, boolean citit) {
        this.numeCarte = numeCarte;
        this.anCarte = anCarte;
        this.autorCarte = autorCarte;
        this.citit = citit;
    }

    protected Carte(Parcel in) {
        numeCarte = in.readString();
        autorCarte = in.readString();
        anCarte = in.readInt();
        citit = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeCarte);
        dest.writeString(autorCarte);
        dest.writeInt(anCarte);
        dest.writeByte((byte) (citit ? 1 : 0));
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

    public boolean isCitit() {
        return citit;
    }

    public void setCitit(boolean citit) {
        this.citit = citit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carte{");
        sb.append("numeCarte='").append(numeCarte).append('\'');
        sb.append(", autorCarte='").append(autorCarte).append('\'');
        sb.append(", anCarte=").append(anCarte);
        sb.append(", citit=").append(citit);
        sb.append('}');
        return sb.toString();
    }


}
