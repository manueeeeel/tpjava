package Clases_utilizadas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import Clases_utilizadas.asignaturas.*;

/**
 * clase reportes, existe para crear y devolver los reportes solicitados
 */
public class reportes {
    /**
     * metodo que crea el reporte de los alumnos que estan cursando una asignatura ingresada por el usuario
     * @return
     */
    public static ArrayList<inscripcion> ReporteAlumnosAsignatura(int codasig, HashMap<Integer, asignatura> Asignaturas, ArrayList<inscripcion> Inscripciones){
        if(Asignaturas.containsKey(codasig)) {
            ArrayList<inscripcion> Reporte = new ArrayList<>(Inscripciones.size()/2);
            for (int i = 0; i < Inscripciones.size(); i++)
                if (Inscripciones.get(i).getAsignatura().getCodigo() == codasig)
                    Reporte.add(Inscripciones.get(i));
            Reporte.trimToSize();
            return Reporte;
        }
        else {
            System.out.println("Asignatura inexistente\n");
            return null;
        }
    }

    /**
     * reporte de alumnos que estan libres por faltar mas de lo permitido
     * @return
     */
    public static ArrayList<libres> ReporteLibresPorFaltas(ArrayList<inscripcion> Inscripciones){
        ArrayList<libres> Reporte = new ArrayList<>(Inscripciones.size()/2);
        for(int i = 0; i < Inscripciones.size(); i++)
            if(Inscripciones.get(i).ObtenerCondicion() == CONDICION.LIBRE) {
                libres aux = new libres();
                aux.setAlumno(Inscripciones.get(i).getAlumno());
                aux.setNombreasig(Inscripciones.get(i).getAsignatura().getNombre());
                Reporte.add(aux);
            }
        Reporte.trimToSize();
        return Reporte;
    }

    /**
     * reporte de ranking de presentismo
     * <p>
     * ordena las asignaturas de mayor a menor por su presentismo total
     * @return
     */
    public static ArrayList<ranking> ReporteRankingPresentismo(HashMap<Integer, asignatura> Asignaturas, ArrayList<inscripcion> Inscripciones){
        HashMap<Integer,ranking> MapaReporte = new HashMap<>(Asignaturas.size());
        for(var act : Asignaturas.values()){
            ranking carga = new ranking();
            carga.PoneAsignatura(act);
            MapaReporte.put(act.getCodigo(),carga);
        }
        for(int i = 0; i < Inscripciones.size(); i++){
            if(MapaReporte.containsKey(Inscripciones.get(i).getAsignatura().getCodigo()))
                MapaReporte.get(Inscripciones.get(i).getAsignatura().getCodigo()).SumaAsistencias(Inscripciones.get(i).getAsistencias());
        }
        ArrayList<ranking> Reporte = new ArrayList<>(MapaReporte.values());
        for(int i = 0; i < Reporte.size(); i++)
            Reporte.get(i).CalculaPresentismo();
        Reporte.trimToSize();
        Reporte.sort(Comparator.comparing(ranking::getPresentismo));
        return Reporte;
    }
}
