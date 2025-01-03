package com.example.cartev3;

import android.os.Parcel;
import android.os.Parcelable;

public class Carte implements Parcelable {
    private String numeCarte;
    private int anCarte;
    private String autorCarte;
    private Boolean citit;

    public Carte(String numeCarte, int anCarte, String autorCarte, Boolean citit) {
        this.numeCarte = numeCarte;
        this.anCarte = anCarte;
        this.autorCarte = autorCarte;
        this.citit = citit;
    }

    protected Carte(Parcel in) {
        numeCarte = in.readString();
        anCarte = in.readInt();
        autorCarte = in.readString();
        byte tmpCitit = in.readByte();
        citit = tmpCitit == 0 ? null : tmpCitit == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeCarte);
        dest.writeInt(anCarte);
        dest.writeString(autorCarte);
        dest.writeByte((byte) (citit == null ? 0 : citit ? 1 : 2));
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

    public int getAnCarte() {
        return anCarte;
    }

    public void setAnCarte(int anCarte) {
        this.anCarte = anCarte;
    }

    public String getAutorCarte() {
        return autorCarte;
    }

    public void setAutorCarte(String autorCarte) {
        this.autorCarte = autorCarte;
    }

    public Boolean getCitit() {
        return citit;
    }

    public void setCitit(Boolean citit) {
        this.citit = citit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carte{");
        sb.append("numeCarte='").append(numeCarte).append('\'');
        sb.append(", anCarte=").append(anCarte);
        sb.append(", autorCarte='").append(autorCarte).append('\'');
        sb.append(", citit=").append(citit);
        sb.append('}');
        return sb.toString();
    }
}
