package Clases_utilizadas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class asistido {
    private alumno Alumno;
    private HashSet<String> ClasesAsistidas = new HashSet<>();

    public asistido(){}
    public HashSet<String> getClasesAsistidas(){return ClasesAsistidas;}
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
