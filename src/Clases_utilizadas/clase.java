package Clases_utilizadas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
/**
 * class clase
 */
@XmlRootElement(name = "clase")
@XmlAccessorType(XmlAccessType.FIELD)


public class clase {
    private String codigo;
    private String fecha;
    private String horario;
    
    @XmlElement(name = "codasig")
    private int codasig;

    /**
     * constructor de clase
     */
    public clase() {}

    /**
     * setter del codigo de la clase
     */
    public void setCodigo(String cod) { this.codigo = cod; }
    /**
     * setter de la fecha de la clase
     */
    public void setFecha(String fec) { this.fecha = fec; }
    /**
     * setter del horario de la clase
     */
    public void setHorario(String ho) { this.horario = ho; }
    /**
     * setter del codigo de la asignatura a la que corresponde la clase
     */
    public void setCodigoAsig(int cod) { this.codasig = cod; }


    /**
     * getter del codigo de la clase
     * @return
     */
    public String getCodigo() { return codigo; }
    
    @XmlElement(name = "codasig")
    /**
     * getter del codigo de la asignatura de la clase
     * @return
     */
    public int getCodigoAsig() { return codasig; }

    /**
     * getter de la fecha de la clase
     * @return
     */
    public String getFecha() { return fecha; }
    /**
     * getter del horario de la clase
     * @return
     */
    public String getHorario() { return horario; }
}