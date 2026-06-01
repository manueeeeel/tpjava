package Clases_utilizadas.asignaturas;
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
    public char getPromocionable(){
        return promocionable;
    }
    public int getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
}

