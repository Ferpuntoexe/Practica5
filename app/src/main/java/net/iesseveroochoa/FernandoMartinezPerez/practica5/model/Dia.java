package net.iesseveroochoa.FernandoMartinezPerez.practica5.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Dia implements Parcelable {
    public static final String TABLE_NAME = "diario";
    public static final String ID = BaseColumns._ID;
    public static final String VALORACION_DIA = "valoracion_dia";
    public static final String FECHA = "fecha";
    public static final String CONTENIDO = "contenido";
    public static final String FOTO_URI = "foto_uri";
    public static final String RESUMEN = "resumen";
    private int id;
    private Date fecha;
    private int valoracionDia;
    private String resumen;
    private String contenido;
    private String fotoUri;

    public Dia(Date fecha, int valoracionDia, String resumen, String contenido, String fotoUri) {
        this.fecha = fecha;
        this.valoracionDia = valoracionDia;
        this.resumen = resumen;
        this.contenido = contenido;
        this.fotoUri = fotoUri;
    }

    public Dia(Date fecha, int valoracionDia, String resumen, String contenido) {
        this.fecha = fecha;
        this.valoracionDia = valoracionDia;
        this.resumen = resumen;
        this.contenido = contenido;
        this.fotoUri = "";
    }

    protected Dia(Parcel in) {
        id = in.readInt();
        valoracionDia = in.readInt();
        resumen = in.readString();
        contenido = in.readString();
        fotoUri = in.readString();
    }

    public static final Creator<Dia> CREATOR = new Creator<Dia>() {
        @Override
        public Dia createFromParcel(Parcel in) {
            return new Dia(in);
        }

        @Override
        public Dia[] newArray(int size) {
            return new Dia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.valoracionDia);
        dest.writeString(this.resumen);
        dest.writeString(this.contenido);
        dest.writeString(this.fotoUri);
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getID() {
        return ID;
    }

    public static String getFECHA() {
        return FECHA;
    }

    public static String getValoracionDia() {
        return VALORACION_DIA;
    }

    public String getResumen() {
        return resumen;
    }

    public String getContenido() {
        return contenido;
    }

    public static String getRESUMEN() {
        return RESUMEN;
    }

    public static String getCONTENIDO() {
        return CONTENIDO;
    }

    public static String getFotoUri() {
        return FOTO_URI;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setValoracionDia(int valoracionDia) {
        this.valoracionDia = valoracionDia;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    public String getFechaFormatoLocal() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
                Locale.getDefault());
        return df.format(fecha);
    }

    public int getValoracionResumida() {
        if (valoracionDia < 5) {
            return 1;
        } else if (valoracionDia >= 5 && valoracionDia < 8) {
            return 2;
        } else {
            return 3;
        }
    }

    public static int getValorRes(int valor) {
        if (valor < 5) {
            return 1;
        } else if (valor >= 5 && valor < 8) {
            return 2;
        } else {
            return 3;
        }
    }
}
