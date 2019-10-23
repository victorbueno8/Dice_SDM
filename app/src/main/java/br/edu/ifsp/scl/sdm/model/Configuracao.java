package br.edu.ifsp.scl.sdm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Configuracao implements Parcelable {
    private int numDados;
    private int numFaces;

    public Configuracao() {
    }

    public Configuracao(int numDados, int numFaces) {
        this.numDados = numDados;
        this.numFaces = numFaces;
    }

    protected Configuracao(Parcel in) {
        numDados = in.readInt();
        numFaces = in.readInt();
    }

    public static final Creator<Configuracao> CREATOR = new Creator<Configuracao>() {
        @Override
        public Configuracao createFromParcel(Parcel in) {
            return new Configuracao(in);
        }

        @Override
        public Configuracao[] newArray(int size) {
            return new Configuracao[size];
        }
    };

    public int getNumDados() {
        return numDados;
    }

    public void setNumDados(int numDados) {
        this.numDados = numDados;
    }

    public int getNumFaces() {
        return numFaces;
    }

    public void setNumFaces(int numFaces) {
        this.numFaces = numFaces;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numDados);
        parcel.writeInt(numFaces);
    }
}
