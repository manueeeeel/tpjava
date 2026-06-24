package Clases_utilizadas;

import jakarta.xml.bind.annotation.*;

import java.util.*;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class asistido {
    private alumno Alumno;
    @XmlElementWrapper(name = "ClasesAsistidas")
    @XmlElement(name = "codigo")
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
