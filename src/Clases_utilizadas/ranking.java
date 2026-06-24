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
        int totinscriptos = 0;
        for(var act : universidad.getInstancia().getInscripciones()){
            if(act.getAsignatura().getCodigo() == Asignatura.getCodigo())
                totinscriptos++;
        }
        int totasisposibles = totinscriptos * universidad.getInstancia().getAsignaturas().get(Asignatura.getCodigo()).getListadoClases().size();
        if(totasisposibles!=0)
            Presentismo = ( (double)asistencias / totasisposibles * 100 );
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
