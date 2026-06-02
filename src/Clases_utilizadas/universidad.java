package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import Clases_utilizadas.*;
import java.util.*;
public class universidad {
    private static universidad instancia = null;
    private TreeSet<alumno> Alumnos = new TreeSet<>
            (Comparator.comparing(alumno::getNombre, String.CASE_INSENSITIVE_ORDER)
            .thenComparing(alumno::getMatricula));
    private HashMap<Integer, asignatura> Asignaturas = new HashMap<>();
    private HashMap<String, clase> Clases = new HashMap<>();
    private ArrayList<inscripcion> Inscripciones = new ArrayList<>();

    /**crea la agencia */
    private universidad(){}

    public static universidad getInstancia(){
        if(instancia == null)
            instancia = new universidad();
        return instancia;
    }

    public TreeSet<alumno> getAlumnos() {return Alumnos;}
    public HashMap<Integer, asignatura> getAsignaturas() {return Asignaturas;}
    public HashMap<String, clase> getClases() {return Clases;}

    public void InsertaClase(clase clas){
        Clases.put(clas.getCodigo(),clas);
    }
    public void InsertaAsignatura(asignatura asig){
        Asignaturas.put(asig.getCodigo(),asig);
    }
    public void InsertaInscripcion(inscripcion ins){
        Inscripciones.add(ins);
    }
    public void InsertaAlumno(alumno alum){
        Alumnos.add(alum);
    }
    public void RegistraAsistencia(int mat,int codmat){
        boolean flag = false;
        inscripcion act = null;
        int i=0;
        while(i < Inscripciones.size() && !flag){
            act = Inscripciones.get(i);
            if(act.getAsignatura().getCodigo() == codmat && act.getAlumno().getMatricula() == mat)
                flag = true;
            i++;
        }
        if(flag)
            act.RegistraAsistencia();
        else
            System.out.println("Alumno no inscripto en la materia");
    }
    public void RegistrarClase(String codclase,int codasig){
        if(Clases.containsKey(codclase))
            for (var act : Inscripciones)
                if (act.getAsignatura().getCodigo() == codasig)
                    act.IncrementaClases();
        else
            System.out.println("Clase inexistente");
    }
    public ArrayList<inscripcion> ReporteAlumnosAsignatura(int codasig){
        ArrayList<inscripcion> Reporte = new ArrayList<>();
        if(Asignaturas.containsKey(codasig)) {
            for (var act : Inscripciones)
                if (act.getAsignatura().getCodigo() == codasig)
                    Reporte.add(act);
            return Reporte;
        }
        else {
            System.out.println("Asignatura inexistente\n");
            return null;
        }
    }
    public ArrayList<alumno> ReporteLibresPorFaltas(){
        ArrayList<alumno> Reporte = new ArrayList<>();
        for(var act : Inscripciones)
            if(act.ObtenerCondicion() == "Libre")
                Reporte.add(act.getAlumno());
        return Reporte;
    }
    public ArrayList<ranking> ReporteRankingPresentismo(){
        HashMap<Integer,ranking> MapaReporte = new HashMap<>();
        for(var act : Asignaturas.values()){
            ranking carga = new ranking();
            carga.PoneAsignatura(act);
            MapaReporte.put(act.getCodigo(),carga);
        }
        for(var act : Inscripciones){
            ranking aux = MapaReporte.get(act.getAsignatura().getCodigo());
            if(aux!=null) {
                aux.PoneTotalClases(act.getTotclases());
                aux.SumaAsistencias(act.getAsistencias());
            }
        }
        ArrayList<ranking> Reporte = new ArrayList<>(MapaReporte.values());
        for(var act : Reporte)
            act.CalculaPresentismo();
        Reporte.sort(Comparator.comparing(ranking::getPresentismo));
        return Reporte;
    }
}
