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
    private String promocionable,nombre;
    public void setCodigo(int cod){
        codigo = cod;
    }
    public void setCuatrimestre(int cuatri){
        cuatrimestre = cuatri;
    }
    public void setPromocionable(String prom){
        promocionable = prom;
    }
    public void setNombre(String nom){
        nombre = nom;
    }
    abstract public String DefinirCondicion(int totclases,int asistencia,char tipoalum);
    public String getNombre(){return nombre;}
    public String getPromocionable(){
        return promocionable;
    }
    public int getCodigo(){
        return codigo;
    }
    public int getCuatrimestre(){
        return cuatrimestre;
    }
}

