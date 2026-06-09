package Clases_utilizadas.asignaturas;

public class optativa extends asignatura {
    public void setCodigo(int cod){
        super.setCodigo(cod);
    }
    public void setCuatrimestre(int cuatri){
        super.setCuatrimestre(cuatri);
    }
    public void setPromocionable(char prom){
        super.setPromocionable(prom);
    }
    public void setNombre(String nom){
        super.setNombre(nom);
    }
    public String DefinirCondicion(int totclases,int asistencia,char tipoalum){
        String condicion = "Libre";
        switch (tipoalum){
            case 'R':{
                if(super.getPromocionable() == 'S' && asistencia >= totclases*0.6)
                    condicion = "Promociona";
                else if (asistencia >= totclases*0.5)
                    condicion = "Habilita";
            };break;
            case 'C':{
                if(super.getPromocionable() == 'S' && asistencia >= totclases*0.8)
                    condicion = "Promociona";
                else if (asistencia >= totclases*0.7)
                    condicion = "Habilita";
            };break;
        }
        return condicion;
    }
}
