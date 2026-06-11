package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inscripcion")
@XmlAccessorType(XmlAccessType.FIELD)

public class inscripcion {
    private alumno Alumno;
    private asignatura Asignatura;
    private char tipoalum;
    private int totclases, asistencias;
    public void IncrementaClases(){
        totclases++;
    }
    public void RegistraAsistencia(){
        asistencias++;
    }
    public String ObtenerCondicion(){
        return Asignatura.DefinirCondicion(totclases,asistencias,tipoalum);
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
    public char getTipoalum(){
        return tipoalum;
    }
    public int getTotclases(){
        return totclases;
    }
    //setters 
    public void setAlumno(alumno alu) {
        Alumno = alu;
    }
    public void setAsignatura(asignatura asig) {
        Asignatura = asig;
    }
    public void setTipoalum(char tipo) {
        tipoalum = tipo;
    } 

    public void Muestra(){
        if(totclases > 0)
          System.out.println("Cantidad de clases: " + totclases
                 + "\nPorcentaje de asistencias: " + ( (double)asistencias / totclases*100 )
                 + "\nModalidad: " + tipoalum +
                 "\nCondicion: " + ObtenerCondicion() + "\n");
        else
            System.out.println("Cantidad de clases: 0"
                    +  "\nPorcentaje de asistencias: 0"
                    + "\nModalidad: " + tipoalum +
                    "\nCondicion: " + ObtenerCondicion() + "\n");
    }
}
