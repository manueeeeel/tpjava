package Clases_utilizadas.asignaturas;

public class optativa extends asignatura {
    public optativa(int cod,int cuatri,char prom,String nom){
        super(cod,cuatri,prom,nom);
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
