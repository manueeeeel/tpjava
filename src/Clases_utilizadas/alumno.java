package Clases_utilizadas;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumno")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * clase alumno
 */
public class alumno {
    private int matricula;
    private String nombre,fechanacimiento;

    /**
     * getter de la matricula del alumno
     */
    public int getMatricula(){
        return matricula;
    }
    /**
     * getter del nombre del alumno
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * getter de la fecha de nacimiento del alumno
     */
    public String getFechanacimiento() { return fechanacimiento; }

    /**
     * setter de la matricula del alumno
     */
    public void setMatricula(int mat){
        this.matricula = mat;
    }
    /**
     * setter del nombre del alumno
     */
    public void setNombre(String nom){
        this.nombre = nom;       
    }
    /**
     * setter de la fecha de nacimiento del alumno
     */
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }   
}
