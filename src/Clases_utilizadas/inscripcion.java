package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "inscripcion")
@XmlAccessorType(XmlAccessType.FIELD)

public class inscripcion {
    private alumno Alumno;
    private asignatura Asignatura;
    private String tipoalum;
    private int asistencias;
    public void RegistraAsistencia(){
        asistencias++;
        System.out.println("AAAAAAAAAAX");
    }
    public String ObtenerCondicion(){
        return Asignatura.DefinirCondicion(Asignatura.getListadoClases().size(),asistencias,tipoalum);
    }

    public asignatura getAsignatura(){
        return Asignatura;
    }
    public alumno getAlumno(){
        return Alumno;
    }
    public int getAsistencias(){
        return asistencias;
    }
    public String getTipoalum(){
        return tipoalum;
    }
    //setters
    public void setAlumno(alumno alu) {
        Alumno = alu;
    }
    public void setAsignatura(asignatura asig) {
        Asignatura = asig;
    }
    public void setTipoalum(String tipo) {
        tipoalum = tipo;
    }
}
