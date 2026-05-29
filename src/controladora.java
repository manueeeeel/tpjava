import java.io.InputStream;
import java.util.*;

import Clases_utilizadas.alumno;
import Clases_utilizadas.universidad;
import jakarta.xml.bind.*;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/** CLASE CONTROLADORA DEL DOMINIO
 *  <p>
 *  Esta clase se encarga de conectar universidad con la GIU, los XML, y los Txt
 */

public class controladora {
    private universidad universidad; //atributo que guarda la instancia
    public controladora(){ universidad = universidad.getInstancia(); }

    //hay que cambiar a private despues y hacer public un CargaXML
    public void deserializaAlumnos() {
        TreeSet<alumno> alumnos = universidad.getAlumnos();
        try {
            JAXBContext contexto = JAXBContext.newInstance(alumno.class); //crea el contexto para pasar xml a objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller(); //se prepara para DECODIFICAR
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = getClass().getClassLoader().getResourceAsStream("data/alumnos.xml");

            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                if (reader.isStartElement() && reader.getLocalName().equals("alumno")) {
                    try {
                        alumno a = (alumno) unmarshaller.unmarshal(reader);
                        if (a.getNombre().isEmpty()) {
                            throw new Exception("Nombre vacío");
                        }
                        if (a.getMatricula() <= 0 || a.getMatricula() >= 999999) {
                            throw new Exception("Matrícula inválida");
                        }
                        if (Integer.parseInt(a.getFechanacimiento()) <= 10000101) { //AAAAMMDD?
                            throw new Exception("Fecha inválida");
                        }
                        alumnos.add(a);
                    } catch (Exception e) {
                        System.out.println("Alumno inválido: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + alumnos.size() + " Alumnos------");
        } catch (Exception e) {
            System.out.println("Error general al leer XML: " + e.getMessage());
        }
    }
}