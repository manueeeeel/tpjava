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
            for(var act : Inscripciones)
                if(act.getAsignatura().getCodigo() == codasig)
                    act.IncrementaClases();
        else
            System.out.println("Clase inexistente");
    }
}
