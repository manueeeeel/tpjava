package Clases_utilizadas.asignaturas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({obligatoria.class, optativa.class, pasantia.class, tesis.class})

abstract public class asignatura{
    private int codigo,cuatrimestre;
    private char promocionable;
    private String nombre;
    public asignatura(int cod,int cuatri,char prom,String nom){
        codigo = cod;
        cuatrimestre = cuatri;
        promocionable = prom;
        nombre = nom;
    }
    abstract public String DefinirCondicion(int totclases,int asistencia,char tipoalum);
    public String getNombre(){return nombre;}
    public char getPromocionable(){
        return promocionable;
    }
    public int getCodigo(){
        return codigo;
    }
    public int getCuatrimestre(){
        return cuatrimestre;
    }
}

