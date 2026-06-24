package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;

/**
 * clase de ranking, utilizada como struct para hacer uno de los reportes
 */
public class ranking {
    private asignatura Asignatura;
    private double Presentismo;
    private int asistencias;

    /**
     * constructor de ranking
     */
    public ranking(){
        asistencias = 0;
    }

    /**
     * setter de asignatura de ranking
     */
    public void PoneAsignatura(asignatura asig){
        Asignatura = asig;
    }

    /**
     * sumar el total de asistencias reales de la asignatura
     */
    public void SumaAsistencias(int x){
        asistencias += x;
    }

    /**
     * metodo que calcula el presentismo que hubo en cada asignatura
     * <p>
     * evaluando las asistencias reales con las teoricas totales
     */
    public void CalculaPresentismo(){
        int totasisposibles = 0;
        for(var act : universidad.getInstancia().getInscripciones()){
            if(act.getAsignatura().getCodigo() == Asignatura.getCodigo())
                totasisposibles++;
        }
        totasisposibles *= universidad.getInstancia().getAsignaturas().get(Asignatura.getCodigo()).getListadoClases().size();
        if(totasisposibles!=0)
            Presentismo = ( (double)asistencias / totasisposibles * 100 );
        else
            Presentismo = 0;
    }

    /**
     * getter del presentismo de ranking
     * @return
     */
    public double getPresentismo(){
        return Presentismo;
    }

    /**
     * getter de la asignatura de ranking
     * @return
     */
    public asignatura getAsignatura(){
        return Asignatura;
    }
}
