package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import Clases_utilizadas.*;
import java.util.*;
public class universidad {
    private static universidad instancia = null;
    private HashMap<Integer, alumno> Alumnosmap = new HashMap<>();
    private ArrayList<alumno> Alumnoslist = new ArrayList<>();
    private HashMap<Integer, asignatura> Asignaturas = new HashMap<>();
    private HashMap<String, clase> Clases = new HashMap<>();
    private ArrayList<inscripcion> Inscripciones = new ArrayList<>();
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
        Comparator<alumno> criterio = Comparator.comparing(alumno::getNombre)
                        .thenComparing(alumno::getMatricula);
        Alumnosmap.put(alum.getMatricula(),alum);
        Alumnoslist.add(alum);
        Alumnoslist.sort(criterio);
    }
    public void RegistraAsistencia(String codclase,int mat,int codmat){
        boolean flag = false;
        inscripcion act = null;
        if(Clases.containsKey(codclase)){
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
        else
            System.out.println("Clase inexistente");
    }
    public void RegistrarClase(String codclase,String fecha,String hora,asignatura Asignatura){
        clase nuevo = new clase(codclase,fecha,hora);
        Clases.put(codclase,nuevo);
        for(var act: Inscripciones)
          if(act.getAsignatura().equals(Asignatura))
             act.IncrementaClases();
    }
}
