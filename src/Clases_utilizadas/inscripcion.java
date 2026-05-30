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
    public String ObetenerCondicion(){
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
}
