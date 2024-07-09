package pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
    private int id_curso;
    private int id_seccion;
    private String nombre_curso;

    public Curso() {
    }

    public Curso(int id_curso, int id_seccion, String nombre_curso) {
        this.id_curso = id_curso;
        this.id_seccion = id_seccion;
        this.nombre_curso = nombre_curso;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id_curso=" + id_curso +
                ", id_seccion=" + id_seccion +
                ", nombre_curso='" + nombre_curso + '\'' +
                '}';
    }

    protected Curso(Parcel in) {
        id_curso = in.readInt();
        id_seccion = in.readInt();
        nombre_curso = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_curso);
        dest.writeInt(id_seccion);
        dest.writeString(nombre_curso);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Curso> CREATOR = new Creator<Curso>() {
        @Override
        public Curso createFromParcel(Parcel in) {
            return new Curso(in);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };

}
