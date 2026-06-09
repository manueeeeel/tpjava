package Clases_utilizadas;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clase")
public class clase{
    private String codigo,fecha,horario;
    public clase() {}
    public clase(String cod,String fec,String ho){
        codigo = cod;
        fecha = fec;
        horario = ho;
    }
    public String getCodigo(){
        return codigo;
    }
    public String getFecha(){return fecha;}
    public String getHorario(){return horario;}
}
