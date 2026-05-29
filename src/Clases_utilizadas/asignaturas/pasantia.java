package Clases_utilizadas.asignaturas;

public class pasantia extends asignatura{
    public pasantia(int cod,int cuatri,char prom,String nom){
        super(cod,cuatri,prom,nom);
    }
    public String DefinirCondicion(int totclases,int asistencia,char tipoalum){
        String condicion = "Libre";
        if((tipoalum == 'R' && asistencia >= totclases*0.75) || (tipoalum == 'C' && asistencia >= totclases*0.95))
            condicion = "Habilitado";
        return condicion;
    }
}
