package Clases_utilizadas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clase")
@XmlAccessorType(XmlAccessType.FIELD)
public class clase {
    private String codigo;
    private String fecha;
    private String horario;
    
    @XmlElement(name = "codasig")
    private int codasig;

    public clase() {}

    public void setCodigo(String cod) { this.codigo = cod; }
    public void setFecha(String fec) { this.fecha = fec; }
    public void setHorario(String ho) { this.horario = ho; }
    public void setCodigoAsig(int cod) { this.codasig = cod; }
    
    public String getCodigo() { return codigo; }
    
    @XmlElement(name = "codasig")
    public int getCodigoAsig() { return codasig; }
    
    public String getFecha() { return fecha; }
    public String getHorario() { return horario; }
}