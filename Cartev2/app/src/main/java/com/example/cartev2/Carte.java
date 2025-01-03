package com.example.cartev2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Carti")
public class Carte implements Parcelable {
    @NotNull
    @PrimaryKey
    private String numeCarte;

    private int anCarte;

    private String autorCarte;

    private boolean citita;

    public Carte(String numeCarte, boolean citita, int anCarte, String autorCarte) {
        this.numeCarte = numeCarte;
        this.citita = citita;
        this.anCarte = anCarte;
        this.autorCarte = autorCarte;
    }

    protected Carte(Parcel in) {
        numeCarte = in.readString();
        anCarte = in.readInt();
        autorCarte = in.readString();
        citita = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeCarte);
        dest.writeInt(anCarte);
        dest.writeString(autorCarte);
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

    public boolean isCitita() {
        return citita;
    }

    public void setCitita(boolean citita) {
        this.citita = citita;
    }

    public String getKey(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.numeCarte);
        sb.append("-");
        sb.append(this.autorCarte);
        return (sb.toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carte{");
        sb.append("numeCarte='").append(numeCarte).append('\'');
        sb.append(", anCarte=").append(anCarte);
        sb.append(", autorCarte='").append(autorCarte).append('\'');
        sb.append(", citita=").append(citita);
        sb.append('}');
        return sb.toString();
    }
}
