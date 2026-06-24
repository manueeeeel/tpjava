package Clases_utilizadas.asignaturas;
import jakarta.xml.bind.annotation.*;
/**
 * clase hija de asignatura
 */
@XmlRootElement(name = "pasantia")
@XmlAccessorType(XmlAccessType.FIELD)
public class pasantia extends asignatura{
    /**
     * setters de codigo llamando a los setter del padre
     */
    public void setCodigo(int cod){
        super.setCodigo(cod);
    }
    /**
     * setters de cuatrimestre llamando a los setter del padre
     */
    public void setCuatrimestre(int cuatri){
        super.setCuatrimestre(cuatri);
    }
    /**
     * setters de promocionable llamando a los setter del padre
     */
    public void setPromocionable(String prom){
        super.setPromocionable(prom);
    }
    /**
     * setters del nombre llamando a los setter del padre
     */
    public void setNombre(String nom){
        super.setNombre(nom);
    }

    /**
     * metodo abstracto aplicado para polimorfismo
     * @param totclases
     * @param asistencia
     * @param tipoalum
     * @return
     */
    public CONDICION DefinirCondicion(int totclases,int asistencia,String tipoalum){
        CONDICION condicion = CONDICION.LIBRE;
        if(totclases > 0 && ((tipoalum == "R" && asistencia >= totclases*0.75) || (tipoalum == "C" && asistencia >= totclases*0.95)))
            condicion = CONDICION.HABILITA;
        return condicion;
    }
}
