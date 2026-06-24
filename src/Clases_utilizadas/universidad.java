package Clases_utilizadas;
import Clases_utilizadas.asignaturas.*;
import Clases_utilizadas.*;
import java.util.*;

/**
 * clase singleton, contiene todos los datos del sistema
 */
public class universidad {
    /**
     * instancia de universidad
     */
    private static universidad instancia = null;
    /**
     * treeset de alumnos
     * <p>
     * ordenado automaticamente por nombre y apellido
     * <p>
     * si el nombre ya existe, se ordena por matricula
     */
    private TreeSet<alumno> Alumnos = new TreeSet<>
            (Comparator.comparing(alumno::getNombre, String.CASE_INSENSITIVE_ORDER)
            .thenComparing(alumno::getMatricula));
    /**
     * hashmap de asignaturas
     */
    private HashMap<Integer, asignatura> Asignaturas = new HashMap<>();
    /**
     * hashmap de asistencias
     */
    private HashMap<Integer,asistido> Asistencias = new HashMap<>();
    /**
     * vector de inscripciones
     */
    private ArrayList<inscripcion> Inscripciones = new ArrayList<>();


    /**
     * constructor privado para evitar crear mas de un objeto
     */
    private universidad(){}

    /**
     * getter de la instancia, permite tener unicamente un objeto creado
     * @return
     */
    public static universidad getInstancia(){
        if(instancia == null)
            instancia = new universidad();
        return instancia;
    }

    /**
     * getter de las asistencias
     * <p>
     * asistencias es un hashmap de asistido
     * @return
     */
    public HashMap<Integer,asistido> getAsistencias(){return Asistencias;}

    /**
     * getter del treeset de alumnos
     *
     * @return
     */
    public TreeSet<alumno> getAlumnos() {return Alumnos;}
    /**
     * getter del hashmap de asignaturas
     * @return
     */
    public HashMap<Integer, asignatura> getAsignaturas() {return Asignaturas;}

    /**
     * metodo que inserta una nueva clase al vector de clases totales de una asignatura
     */
    public void InsertaClase(clase clas){

        if(Asignaturas.containsKey(clas.getCodigoAsig()))
            Asignaturas.get(clas.getCodigoAsig()).AgregarClase(clas);
        else
            System.out.println("Codigo de asignatura no corresponde a ninguna asignatura");
    }

    /**
     * metodo que inserta una asignatura al hashmap de asignaturas
     */
    public void InsertaAsignatura(asignatura asig){
        Asignaturas.put(asig.getCodigo(),asig);
    }

    /**
     * metodo que inserta una inscripcion al vector de inscriptos
     */
    public void InsertaInscripcion(inscripcion ins){
        Inscripciones.add(ins);
        System.out.println("Se ha inscripto al Alumno correctamente");
    }

    /**
     * metodo que inserta una clase asistida en el hashmap de asistencias del respectivo alumno
     */
    public void InsertaClaseAsistencia(int mat, String codclase){
        if(Asistencias.containsKey(mat))
            Asistencias.get(mat).AgregaClase(codclase);
    }

    /**
     * metodo que inserta un alumno al treeset de alumnos
     */
    public void InsertaAlumno(alumno alum){
        Alumnos.add(alum);
    }

    /**
     * metodo que inserta un alumno en el hashmap de asistencias
     */
    public void InsertaListaAsistencia(alumno alum){
        asistido a = new asistido();
        a.setAlumno(alum);
        Asistencias.put(a.getAlumno().getMatricula(),a);
    }

    /**
     * metodo que registra la asistencia de un alumno a una clase
     * <p>
     * primero valida que el alumno este inscripto a la asignatura
     * <p>
     * si esta inscripto, valida que no haya asistido a la clase
     * <p>
     * si no asistio, se registra la asistencia
     */
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

    /**
     * getter del vector de inscripciones
     * @return
     */
    public ArrayList<inscripcion> getInscripciones() {
        return Inscripciones;
    }
}
