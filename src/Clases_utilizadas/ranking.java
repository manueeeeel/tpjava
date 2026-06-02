package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import java.util.ArrayList;
public class ranking {
    private asignatura Asignatura;
    private double Presentismo;
    private int totclases,asistencias;
    public ranking(){
        asistencias = 0;
    }
    public void PoneAsignatura(asignatura asig){
        Asignatura = asig;
    }
    public void PoneTotalClases(int x){
        totclases = x;
    }
    public void SumaAsistencias(int x){
        asistencias += x;
    }
    public void CalculaPresentismo(){
        if(totclases!=0)
            Presentismo = asistencias/totclases*100;
        else
            Presentismo = 0;
    }
    public double getPresentismo(){
        return Presentismo;
    }
    public asignatura getAsignatura(){
        return Asignatura;
    }
    public void Muestra(){
        System.out.println("Asignatura: " + Asignatura.getNombre()
                + "\nPresentismo: " + Presentismo + "\n"); //hola
    }
}
