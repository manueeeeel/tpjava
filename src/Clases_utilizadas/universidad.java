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
    private HashMap<Integer,asistido> Asistencias = new HashMap<>();
    private ArrayList<inscripcion> Inscripciones = new ArrayList<>();

    /**crea la agencia */
    private universidad(){}

    public static universidad getInstancia(){
        if(instancia == null)
            instancia = new universidad();
        return instancia;
    }
    public HashMap<Integer,asistido> getAsistencias(){return Asistencias;}
    public TreeSet<alumno> getAlumnos() {return Alumnos;}
    public HashMap<Integer, asignatura> getAsignaturas() {return Asignaturas;}

    public void InsertaClase(clase clas){

        if(Asignaturas.containsKey(clas.getCodigoAsig()))
            Asignaturas.get(clas.getCodigoAsig()).AgregarClase(clas);
        else
            System.out.println("Codigo de asignatura no corresponde a ninguna asignatura");
    }
    public void InsertaAsignatura(asignatura asig){
        Asignaturas.put(asig.getCodigo(),asig);
    }
    public void InsertaInscripcion(inscripcion ins){
        Inscripciones.add(ins);
        System.out.println("Se ha inscripto al Alumno correctamente");
    }
    public void InsertaAsistencia(asistido a){
        Asistencias.put(a.getAlumno().getMatricula(),a);
    }
    public void InsertaClaseAsistencia(int mat, String codclase){
        if(Asistencias.containsKey(mat))
            Asistencias.get(mat).AgregaClase(codclase);
    }
    public void InsertaAlumno(alumno alum){
        Alumnos.add(alum);
    }
    public void InsertaListaAsistencia(alumno alum){
        asistido a = new asistido();
        a.setAlumno(alum);
        Asistencias.put(a.getAlumno().getMatricula(),a);
    }
    public void RegistraAsistencia(int mat,int codmat,String codclase){
        boolean flag = false;
        inscripcion act = null;
        int i=0;
        while(i < Inscripciones.size() && !flag){
            act = Inscripciones.get(i);
            if(act.getAsignatura().getCodigo() == codmat && act.getAlumno().getMatricula() == mat)
                flag = true;
            else i++;
        }
        if(flag){
            if(Asistencias.containsKey(mat)){
                if(!Asistencias.get(mat).Existe(codclase)){
                    Asistencias.get(mat).AgregaClase(codclase);
                    Inscripciones.get(i).RegistraAsistencia();
                }
                else
                    throw new RuntimeException("Alumno ya asistio a esta clase.");
            }
        }else
            throw new RuntimeException("El alumno no está inscripto en esta materia.");
    }
    public ArrayList<inscripcion> getInscripciones() {
        return Inscripciones;
    }
}
