package pojo;

import java.sql.Date;

public class Asistencia {

    private int id_asistencia;
    private int id_estudiante;

    private String nombreEstudiante;
    private String fecha;
    private int estado;
    private String seccion;

    public Asistencia() {
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public Asistencia(int id_asistencia, int id_estudiante, String nombreEstudiante, String fecha, int estado, String seccion) {
        this.id_asistencia = id_asistencia;
        this.id_estudiante = id_estudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.fecha = fecha;
        this.estado = estado;
        this.seccion = seccion;
    }

    public int getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "id_asistencia=" + id_asistencia +
                ", id_estudiante=" + id_estudiante +
                ", nombreEstudiante='" + nombreEstudiante + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", seccion='" + seccion + '\'' +
                '}';
    }
}
