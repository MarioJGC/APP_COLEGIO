package pojo;

import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Estudiante implements Parcelable {
    private int id_estudiante;
    private int id_seccion;
    private String nombre;
    private String email;
    private String contraseña;
    private String tipo;

    public Estudiante() {
    }

    public Estudiante(int id_estudiante, int id_seccion, String nombre, String email, String contraseña, String tipo) {
        this.id_estudiante = id_estudiante;
        this.id_seccion = id_seccion;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id_estudiante=" + id_estudiante +
                ", id_seccion=" + id_seccion +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    protected Estudiante(Parcel in) {
        id_estudiante = in.readInt();
        id_seccion = in.readInt();
        nombre = in.readString();
        email = in.readString();
        contraseña = in.readString();
        tipo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_estudiante);
        dest.writeInt(id_seccion);
        dest.writeString(nombre);
        dest.writeString(email);
        dest.writeString(contraseña);
        dest.writeString(tipo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };
}
