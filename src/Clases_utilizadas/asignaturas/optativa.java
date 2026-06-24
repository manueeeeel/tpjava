package Clases_utilizadas.asignaturas;
import jakarta.xml.bind.annotation.*;
@XmlRootElement(name = "optativa")
@XmlAccessorType(XmlAccessType.FIELD)

public class optativa extends asignatura {
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
        if(totclases > 0) {
            switch (tipoalum) {
                case "R": {
                    if (super.getPromocionable().equals("S") && asistencia >= totclases * 0.6)
                        condicion = CONDICION.PROMOCIONA;
                    else if (asistencia >= totclases * 0.5)
                        condicion = CONDICION.HABILITA;
                }
                ;
                break;
                case "C": {
                    if (super.getPromocionable().equals("S") && asistencia >= totclases * 0.8)
                        condicion = CONDICION.PROMOCIONA;
                    else if (asistencia >= totclases * 0.7)
                        condicion = CONDICION.HABILITA;
                }
                ;
                break;
            }
        }
        return condicion;
    }
}
