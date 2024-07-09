package pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Actividad implements Parcelable {

    private int id_actividad;
    private String titulo;
    private String descripcion;
    private String indicaciones;
    private String plazo_entrega;
    private int id_curso;

    public Actividad() {
    }

    public Actividad(int id_actividad, String titulo, String descripcion, String indicaciones, String plazo_entrega, int id_curso) {
        this.id_actividad = id_actividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.plazo_entrega = plazo_entrega;
        this.id_curso = id_curso;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getPlazo_entrega() {
        return plazo_entrega;
    }

    public void setPlazo_entrega(String plazo_entrega) {
        this.plazo_entrega = plazo_entrega;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id_actividad=" + id_actividad +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", indicaciones='" + indicaciones + '\'' +
                ", plazo_entrega='" + plazo_entrega + '\'' +
                ", id_curso=" + id_curso +
                '}';
    }

    protected Actividad(Parcel in) {
        id_actividad = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        indicaciones = in.readString();
        plazo_entrega = in.readString();
        id_curso = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_actividad);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeString(indicaciones);
        dest.writeString(plazo_entrega);
        dest.writeInt(id_curso);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Actividad> CREATOR = new Parcelable.Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

}
