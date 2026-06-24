package Clases_utilizadas;

import jakarta.xml.bind.annotation.*;

import java.util.*;
/**
 * clase asistido, contiene un alumno y las clases a alas que asistio
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class asistido {
    private alumno Alumno;
    @XmlElementWrapper(name = "ClasesAsistidas")
    @XmlElement(name = "codigo")
    private HashSet<String> ClasesAsistidas = new HashSet<>();

    /**
     * constructor de asistido
     */
    public asistido(){}


    /**
     * getter del set de clases asistidas del alumno
     * @return
     */
    public HashSet<String> getClasesAsistidas(){return ClasesAsistidas;}
    /**
     * getter del alumno
     * @return
     */
    public alumno getAlumno(){
        return Alumno;
    }

    /**
     * setter del alumno
     */
    public void setAlumno(alumno alum){Alumno = alum;}
    /**
     * agregar una clase asistida al set
     */
    public void AgregaClase(String codclase){
        ClasesAsistidas.add(codclase);
    }

    /**
     * metodo que evalua si la clase a la que se quiere asistir ya existe en las clases asistidas
     * @param cod
     * @return
     */
    public boolean Existe(String cod){
        return ClasesAsistidas.contains(cod);
    }
}
