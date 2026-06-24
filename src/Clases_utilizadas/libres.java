package Clases_utilizadas;

/**
 * clase libres, contiene un alumno y el nombre de la asignatura en la que esta libre
 */
public class libres {
    private alumno Alumno;
    private String nombreasig;

    /**
     * setter del alumno de libres
     */
    public void setAlumno(alumno alum){
        Alumno = alum;
    }
    /**
     * setter del nombre de la asignatura de libres
     */
    public void setNombreasig(String nom){
        nombreasig = nom;
    }


    /**
     * getter del alumno de libres
     * @return
     */
    public alumno getAlumno(){
        return Alumno;
    }
    /**
     * getter del nombre de la asignatura de libres
     * @return
     */
    public String getAsignatura(){
        return nombreasig;
    }
}