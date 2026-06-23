package Clases_utilizadas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import Clases_utilizadas.asignaturas.*;

public class reportes {
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
    public static ArrayList<alumno> ReporteLibresPorFaltas(ArrayList<inscripcion> Inscripciones){
        ArrayList<alumno> Reporte = new ArrayList<>(Inscripciones.size()/2);
        for(int i = 0; i < Inscripciones.size(); i++)
            if(Inscripciones.get(i).ObtenerCondicion().equals("Libre"))
                Reporte.add(Inscripciones.get(i).getAlumno());
        Reporte.trimToSize();
        return Reporte;
    }
    public static ArrayList<ranking> ReporteRankingPresentismo(HashMap<Integer, asignatura> Asignaturas, ArrayList<inscripcion> Inscripciones){
        HashMap<Integer,ranking> MapaReporte = new HashMap<>(Asignaturas.size());
        for(var act : Asignaturas.values()){
            ranking carga = new ranking();
            carga.PoneAsignatura(act);
            MapaReporte.put(act.getCodigo(),carga);
        }
        for(int i = 0; i < Inscripciones.size(); i++){
            ranking aux = MapaReporte.get(Inscripciones.get(i).getAsignatura().getCodigo());
            if(aux!=null)
                aux.SumaAsistencias(Inscripciones.get(i).getAsistencias());
        }
        ArrayList<ranking> Reporte = new ArrayList<>(MapaReporte.values());
        for(int i = 0; i < Reporte.size(); i++)
            Reporte.get(i).CalculaPresentismo();
        Reporte.trimToSize();
        Reporte.sort(Comparator.comparing(ranking::getPresentismo));
        return Reporte;
    }
}
