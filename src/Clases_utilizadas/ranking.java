package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import java.util.ArrayList;
public class ranking {
    private asignatura Asignatura;
    private double Presentismo;
    private int asistencias;
    public ranking(){
        asistencias = 0;
    }
    public void PoneAsignatura(asignatura asig){
        Asignatura = asig;
    }
    public void SumaAsistencias(int x){
        asistencias += x;
    }
    public void CalculaPresentismo(){
        int totclases = Asignatura.getListadoClases().size();
        if(totclases!=0)
            Presentismo = ( (double)asistencias / totclases * 100 );
        else
            Presentismo = 0;
    }
    public double getPresentismo(){
        return Presentismo;
    }
    public asignatura getAsignatura(){
        return Asignatura;
    }
}
