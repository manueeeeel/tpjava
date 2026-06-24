package Clases_utilizadas;

import java.util.*;

public class asistido {
    private alumno Alumno;
    private HashSet<String> ClasesAsistidas = new HashSet<>();

    public alumno getAlumno(){
        return Alumno;
    }
    public void setAlumno(alumno alum){Alumno = alum;}
    public void AgregaClase(String codclase){
        ClasesAsistidas.add(codclase);
    }
    public boolean Existe(String cod){
        return ClasesAsistidas.contains(cod);
    }
}
