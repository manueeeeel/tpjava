package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "inscripcion")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * clase inscripcion, contiene un alumno, la asignatura a la que esta inscripto
 * <p>
 * en que modalidad esta cursando y sus asistencias
 */
public class inscripcion {
    private alumno Alumno;
    private asignatura Asignatura;
    private String tipoalum;
    private int asistencias;

    /**
     * metodo que registra la asistencia a una clase
     */
    public void RegistraAsistencia(){
        asistencias++;
    }

    /**
     * metodo que retorna la condicion del alumno en la asignatura
     * @return
     */
    public CONDICION ObtenerCondicion(){
        return Asignatura.DefinirCondicion(Asignatura.getListadoClases().size(),asistencias,tipoalum);
    }


    /**
     * getter de la asignatura de inscripcion
     * @return
     */
    public asignatura getAsignatura(){
        return Asignatura;
    }
    /**
     * getter del alumno de inscripcion
     * @return
     */
    public alumno getAlumno(){
        return Alumno;
    }
    /**
     * getter de las asistencias de inscripcion
     * @return
     */
    public int getAsistencias(){
        return asistencias;
    }
    /**
     * getter de la modalidad de cursado de inscripcion
     * @return
     */
    public String getTipoalum(){
        return tipoalum;
    }

    /**
     * setter del alumno de inscripcion
     */
    public void setAlumno(alumno alu) {
        Alumno = alu;
    }
    /**
     * setter de la asignatura de inscripcion
     */
    public void setAsignatura(asignatura asig) {
        Asignatura = asig;
    }
    /**
     * setter de la modalidad de cursado de inscripcion
     */
    public void setTipoalum(String tipo) {
        tipoalum = tipo;
    }
}
