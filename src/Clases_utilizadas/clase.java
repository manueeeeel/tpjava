package Clases_utilizadas;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clase")
public class clase{
    private String codigo,fecha,horario, asignatura;

    public void setAsignatura(String asig){
        asignatura = asig;
    }
    
    public void setCodigo(String cod){
        codigo = cod;
    }
    public void setFecha(String fec){
        fecha = fec;
    }
    public void setHorario(String ho){
        horario = ho;
    }
    public String getCodigo(){
        return codigo;
    }
    public String getAsignatura(){ return asignatura;}
    public String getFecha(){return fecha;}
    public String getHorario(){return horario;}
}
