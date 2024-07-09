package pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;

public class Seccion implements Parcelable {
    private int id_seccion;
    private String descripcion;
    private Bitmap qr;

    public Seccion() {
    }

    public Seccion(int id_seccion, String descripcion, Bitmap qr) {
        this.id_seccion = id_seccion;
        this.descripcion = descripcion;
        this.qr = qr;
    }

    protected Seccion(Parcel in) {
        id_seccion = in.readInt();
        descripcion = in.readString();
        byte[] byteArray = in.createByteArray();
        qr = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static final Creator<Seccion> CREATOR = new Creator<Seccion>() {
        @Override
        public Seccion createFromParcel(Parcel in) {
            return new Seccion(in);
        }

        @Override
        public Seccion[] newArray(int size) {
            return new Seccion[size];
        }
    };

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getQr() {
        return qr;
    }

    public void setQr(Bitmap qr) {
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "Seccion{" +
                "id_seccion=" + id_seccion +
                ", descripcion='" + descripcion + '\'' +
                ", qr=" + qr +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_seccion);
        dest.writeString(descripcion);

        if (qr != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            qr.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            dest.writeInt(byteArray.length);
            dest.writeByteArray(byteArray);
        } else {
            dest.writeInt(0); // Indicar que el array de bytes está vacío
        }

    }
}
