package pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Profesor implements Parcelable {
    private int id_profesor;
    private String nombre;
    private String email;
    private String contraseña;
    private String tipo;

    public Profesor() {
    }

    public Profesor(int id_profesor, String nombre, String email, String contraseña, String tipo) {
        this.id_profesor = id_profesor;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    protected Profesor(Parcel in) {
        id_profesor = in.readInt();
        nombre = in.readString();
        email = in.readString();
        contraseña = in.readString();
        tipo = in.readString();
    }

    public static final Creator<Profesor> CREATOR = new Creator<Profesor>() {
        @Override
        public Profesor createFromParcel(Parcel in) {
            return new Profesor(in);
        }

        @Override
        public Profesor[] newArray(int size) {
            return new Profesor[size];
        }
    };

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
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
        return "Profesor{" +
                "id_profesor=" + id_profesor +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_profesor);
        dest.writeString(nombre);
        dest.writeString(email);
        dest.writeString(contraseña);
        dest.writeString(tipo);
    }
}

