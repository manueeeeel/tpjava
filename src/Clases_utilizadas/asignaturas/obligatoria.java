package Clases_utilizadas.asignaturas;

import jakarta.xml.bind.annotation.*;
@XmlRootElement(name = "obligatoria")
@XmlAccessorType(XmlAccessType.FIELD)
public class obligatoria extends asignatura {
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
    public String DefinirCondicion(int totclases,int asistencia,char tipoalum){
        String condicion = "Libre";
        switch (tipoalum){
            case 'R':{
                if(super.getPromocionable().equals("S") && asistencia >= totclases*0.8)
                    condicion = "Promociona";
                else if (asistencia >= totclases*0.6)
                    condicion = "Habilita";
            }break;
            case 'C':{
                if(super.getPromocionable().equals("S") && asistencia == totclases)
                    condicion = "Promociona";
                else if (asistencia >= totclases*0.8)
                    condicion = "Habilita";
            }break;
        }
        return condicion;
    }
}
