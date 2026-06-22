package Clases_utilizadas;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clase")
public class clase{
    private String codigo,fecha,horario;
    private int codasig;

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
    public int getCodigoAsig(){
        return codasig;
    }
    public String getFecha(){return fecha;}
    public String getHorario(){return horario;}
}
