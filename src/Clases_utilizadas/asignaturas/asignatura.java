package Clases_utilizadas.asignaturas;
import Clases_utilizadas.*;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
/**
 * asignatura es una clase abstracta, padre de las demas asignaturas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({obligatoria.class, optativa.class, pasantia.class, tesis.class})

abstract public class asignatura{
    private int codigo,cuatrimestre;
    private String promocionable,nombre;
    private ArrayList<clase> Clases = new ArrayList<>();

    /**
     *  setters de codigo de la asignatura
     */
    public void setCodigo(int cod){
        codigo = cod;
    }
    /**
     *  setters de cuatrimestre de la asignatura
     */
    public void setCuatrimestre(int cuatri){
        cuatrimestre = cuatri;
    }
    /**
     *  setters del dato promocionable de asignatura
     */
    public void setPromocionable(String prom){
        promocionable = prom;
    }
    /**
     *  setters del nombre de la asignatura
     */
    public void setNombre(String nom){
        nombre = nom;
    }
    /**
     *  setters de una clase de la asignatura
     */
    public void AgregarClase(clase clas){
        Clases.add(clas);
    }

    /**
     * metodo abstracto para definir la condicion del alumno
     * @param totclases
     * @param asistencia
     * @param tipoalum
     * @return
     */
    abstract public CONDICION DefinirCondicion(int totclases,int asistencia,String tipoalum);

    /**
     * getter del nombre de la asignatura
     * @return
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * getter de si la asignatura es promocionable
     * @return
     */
    public String getPromocionable(){
        return promocionable;
    }

    /**
     * getter del codigo de la asignatura
     * @return
     */
    public int getCodigo(){
        return codigo;
    }

    /**
     * getter del cuatrimestre de la asignatura
     * @return
     */
    public int getCuatrimestre(){
        return cuatrimestre;
    }

    /**
     * getter del listado de clases de la asignatura
     * @return
     */
    public ArrayList<clase> getListadoClases(){
        return Clases;
    }
}

