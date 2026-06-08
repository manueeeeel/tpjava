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
    
    //Setters
    public void setMatricula(int mat){
        this.matricula = mat;
    }
    public void setNombre(String nom){
        this.nombre = nom;       
    }
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }   
}
