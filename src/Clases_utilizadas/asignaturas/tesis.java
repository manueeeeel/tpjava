package Clases_utilizadas.asignaturas;
public class tesis extends asignatura{
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
        if((tipoalum == 'R' && asistencia >= totclases*0.75) || (tipoalum == 'C' && asistencia >= totclases*0.95))
            condicion = "Habilita";
        return condicion;
    }
}
