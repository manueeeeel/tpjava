package Clases_utilizadas;

public class libres {
    private alumno Alumno;
    private String nombreasig;
    public void setAlumno(alumno alum){
        Alumno = alum;
    }
    public void setNombreasig(String nom){
        nombreasig = nom;
    }
    public alumno getAlumno(){
        return Alumno;
    }
    public String getAsignatura(){
        return nombreasig;
    }
}