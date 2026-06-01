import java.io.InputStream;
import java.util.*;

import Clases_utilizadas.alumno;
import Clases_utilizadas.asignaturas.*;
import Clases_utilizadas.clase;
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
        int cont = 0;
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
                        universidad.InsertaAlumno(a);
                        cont++;
                    } catch (Exception e) {
                        System.out.println("Alumno inválido: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + cont + " Alumnos------");
        } catch (Exception e) {
            System.out.println("Error general al leer XML: " + e.getMessage());
        }
    }
    public void deserializaClase() {
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(clase.class); //crea el contexto para pasar xml a objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller(); //se prepara para DECODIFICAR
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = getClass().getClassLoader().getResourceAsStream("data/clases.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()) {
                if (reader.isStartElement() && reader.getLocalName().equals("clase")) {
                    try {
                        clase c = (clase) unmarshaller.unmarshal(reader);
                        if (c.getCodigo().isEmpty()) {
                            throw new Exception("Codigo vacío");
                        }
                        if (c.getFecha().isEmpty()) {
                            throw new Exception("Fecha vacía");
                        }
                        if (c.getHorario().isEmpty()) {
                            throw new Exception("Horario vacío");
                        }
                        universidad.InsertaClase(c);
                        cont++;
                    } catch (Exception e) {
                        System.out.println("Clase inválida: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + cont + " Clases------");
        } catch (Exception e) {
            System.out.println("Error general al leer XML: " + e.getMessage());
        }
    }
    public void deserializaAsignatura() {
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(asignatura.class,obligatoria.class,optativa.class,pasantia.class,tesis.class); //crea el contexto para pasar xml a objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller(); //se prepara para DECODIFICAR
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = getClass().getClassLoader().getResourceAsStream("data/asignaturas.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()) {
                if (reader.isStartElement() &&
                        (reader.getLocalName().equals("obligatoria")
                        || reader.getLocalName().equals("optativa")
                        || reader.getLocalName().equals("pasantia")
                        || reader.getLocalName().equals("tesis"))){
                    try {
                        asignatura a = (asignatura)unmarshaller.unmarshal(reader); //se asigna el tipo automaticamente, ya que todos tienen los mismos parametros

                        if (a.getNombre().isEmpty()) {
                            throw new Exception("Nombre vacío");
                        }
                        if (a.getCuatrimestre() > 10 || a.getCuatrimestre() <= 0) {
                            throw new Exception("Cuatrimestre invalido");
                        }
                        if (a.getPromocionable().isEmpty()) {
                            throw new Exception("Promocionable vacía");
                        }
                        universidad.InsertaAsignatura(a);
                        cont++;
                    } catch (Exception e) {
                        System.out.println("Asignatura inválida: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + cont + " Asignaturas------");
        } catch (Exception e) {
            System.out.println("Error general al leer XML: " + e.getMessage());
        }
    }
    public void serealizaAlumnos(){
        int cont=0;
        TreeSet<alumno> listaAlumnos = universidad.getAlumnos();
        try {
            JAXBContext contexto = JAXBContext.newInstance(alumno.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            sw.write("<alumnos>\n");
            for (alumno a : listaAlumnos) {
                StringWriter writer = new StringWriter();
                marshaller.marshal(a, writer);
                String xml = writer.toString();
                String contenido = xml.substring(xml.indexOf("?>") + 2).trim();
                sw.write("    " + contenido + "\n");
                cont++;
            }
            sw.write("</alumnos>");
            File archivo = new File("src/data/alumnos.xml");
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(sw.toString());
            }
            System.out.println("------ Alumnos serializados: " + cont + " Alumnos ------");
        } catch (Exception e) {
            System.out.println("Error al serializar viajes: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void serealizaClase(){
        TreeSet<clase> listaClases = new TreeSet<>(universidad.getClases().values());
        int cont=0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(clase.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            sw.write("<clases>\n");
            for (clase c : listaClases) {
                StringWriter writer = new StringWriter();
                marshaller.marshal(c, writer);
                String xml = writer.toString();
                String contenido = xml.substring(xml.indexOf("?>") + 2).trim();
                sw.write("    " + contenido + "\n");
                cont++;
            }
            sw.write("</clases>");
            File archivo = new File("src/data/clases.xml");
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(sw.toString());
            }
            System.out.println("------ Clases serializadas: " + cont + " Clases ------");
        } catch (Exception e) {
            System.out.println("Error al serializar viajes: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void serealizaAsignatura(){
        HashMap<asignatura> listaAsign = HashMap<>(universidad.getAsignaturas());
        try {
            JAXBContext contexto = JAXBContext.newInstance(asignatura.class,obligatoria.class,optativa.class,pasantia.class,tesis.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            sw.write("<asignaturas>\n");
            for (asignatura a : listaAsign.values()) {
                StringWriter writer = new StringWriter();
                marshaller.marshal(a, writer);
                String xml = writer.toString();
                String contenido = xml.substring(xml.indexOf("?>") + 2).trim();
                sw.write("    " + contenido + "\n");
            }
            sw.write("</asignaturas>");
            File archivo = new File("src/data/asignaturas.xml");
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(sw.toString());
            }
            System.out.println("------ Asignaturas serializadas: " + listaAsign.size() + " Asignaturas ------");
        } catch (Exception e) {
            System.out.println("Error al serializar viajes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

