package Clases_utilizadas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumno")
@XmlAccessorType(XmlAccessType.FIELD)

public class alumno {
    private int matricula;
    private String nombre,fechanacimiento;

    public int getMatricula(){
        return matricula;
    }
    public String getNombre(){
        return nombre;
    }
    public String getFechanacimiento() { return fechanacimiento; }
}
