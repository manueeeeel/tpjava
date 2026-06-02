package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
public class inscripcion {
    alumno Alumno;
    asignatura Asignatura;
    char tipoalum;
    int totclases,asistencias;
    public void IncrementaClases(){
        totclases++;
    }
    public void RegistraAsistencia(){
        asistencias++;
    }
    public String ObtenerCondicion(){
        if(tipoalum == 'O')
            return "Libre (oyente)";
        else
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
    public void Muestra(){
        if(totclases > 0)
          System.out.println("Cantidad de clases: " + totclases
                 + "\nPorcentaje de asistencias: " + asistencias/totclases*100
                 + "\nModalidad: " + tipoalum +
                 "\nCondicion: " + ObtenerCondicion() + "\n");
        else
            System.out.println("Cantidad de clases: 0"
                    +  "\nPorcentaje de asistencias: 0"
                    + "\nModalidad: " + tipoalum +
                    "\nCondicion: " + ObtenerCondicion() + "\n");
    }
}
