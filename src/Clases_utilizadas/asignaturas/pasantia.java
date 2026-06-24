package Clases_utilizadas.asignaturas;
import jakarta.xml.bind.annotation.*;
@XmlRootElement(name = "pasantia")
@XmlAccessorType(XmlAccessType.FIELD)
public class pasantia extends asignatura{
    public void setCodigo(int cod){
        super.setCodigo(cod);
    }
    public void setCuatrimestre(int cuatri){
        super.setCuatrimestre(cuatri);
    }
    public void setPromocionable(String prom){
        super.setPromocionable(prom);
    }
    public void setNombre(String nom){
        super.setNombre(nom);
    }
    public CONDICION DefinirCondicion(int totclases,int asistencia,String tipoalum){
        CONDICION condicion = CONDICION.LIBRE;
        if(totclases > 0 && ((tipoalum == "R" && asistencia >= totclases*0.75) || (tipoalum == "C" && asistencia >= totclases*0.95)))
            condicion = CONDICION.HABILITA;
        return condicion;
    }
}
