package controladora;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;
import Clases_utilizadas.asignaturas.*;
import Clases_utilizadas.*;
import jakarta.xml.bind.*;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/** CLASE CONTROLADORA DEL DOMINIO
 *  <p>
 *  Esta clase se encarga de conectar universidad con la GIU, los XML, y los Txt
 */

public class controladora {
    private universidad universidad; //atributo que guarda la instancia
    public controladora(){
        universidad = universidad.getInstancia();
    }


    public ArrayList<inscripcion> getReporteAlumnosAsignatura(int codasig){
        return reportes.ReporteAlumnosAsignatura(codasig,universidad.getAsignaturas(),universidad.getInscripciones());
    }
    public ArrayList<alumno> getReporteLibresPorFaltas(){
        return reportes.ReporteLibresPorFaltas(universidad.getInscripciones());
    }
    public ArrayList<ranking> getReporteRankingPresentismo(){
        return reportes.ReporteRankingPresentismo(universidad.getAsignaturas(),universidad.getInscripciones());
    }

    public HashMap<Integer, asignatura> getAsignaturas(){
        return universidad.getAsignaturas();
    }
    public TreeSet<alumno> getAlumnos(){
        return universidad.getAlumnos();
    }

    public void cargarDatosXML(){
        deserializaAlumnos();
        deserializaAsignatura();
        deserializaClase();
        deserializaInscripciones();
    }
    public void guardarDatosXML(){
        serializaInscripciones();
    }
    public void RegistraAsistencia(int matricula,int codmateria,String codclase){
        universidad.RegistraAsistencia(matricula, codmateria,codclase);
    }

    private void deserializaAlumnos() {
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(alumno.class); //crea el contexto para pasar xml a objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller(); //se prepara para DECODIFICAR
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = new FileInputStream("src/data/alumnos.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                if (reader.isStartElement() && reader.getLocalName().equals("alumno")) {
                    try {
                        alumno a = (alumno) unmarshaller.unmarshal(reader);
                        if (a.getNombre().isEmpty()) {
                            throw new Exception("Nombre vacío");
                        }
                        if (a.getMatricula() <= 100000 || a.getMatricula() >= 999999) {
                            throw new Exception("Matrícula inválida");
                        }
                        if (a.getFechanacimiento().isEmpty()) { //AAAAMMDD?
                            throw new Exception("Fecha vacía");
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
            System.out.println("Error general al leer XML alumnos: " + e.getMessage());
        }
    }
    private void deserializaClase() {
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(clase.class);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = new FileInputStream("src/data/clases.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()) {
                if (reader.isStartElement() && reader.getLocalName().equals("clase")) {
                    try {
                        clase c = (clase) unmarshaller.unmarshal(reader);
                        System.out.println("[DEBUG] Leyendo clase: " + c.getCodigo() + " - CodAsig XML: " + c.getCodigoAsig());
                        if (c.getCodigo() == null || c.getCodigo().trim().isEmpty()) {
                            throw new Exception("Código de clase vacío o nulo");
                        }
                        if (c.getCodigoAsig() <= 0) {
                            throw new Exception("Código de asignatura inválido (debe ser mayor a 0)");
                        }
                        if (c.getFecha() == null || c.getFecha().trim().isEmpty()) {
                            throw new Exception("Fecha vacía o nula");
                        }
                        if (c.getHorario() == null || c.getHorario().trim().isEmpty()) {
                            throw new Exception("Horario vacío o nulo");
                        }
                        universidad.InsertaClase(c);
                        cont++;
                    } catch (Exception e) {
                        System.out.println("Clase ignorada - Motivo: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + cont + " Clases ------");
        } catch (Exception e) {
            System.out.println("Error general al leer XML clases: " + e.getMessage());
        }
    }
    private void deserializaAsignatura() {
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(asignatura.class,obligatoria.class,optativa.class,pasantia.class,tesis.class); //crea el contexto para pasar xml a objeto
            Unmarshaller unmarshaller = contexto.createUnmarshaller(); //se prepara para DECODIFICAR
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = new FileInputStream("src/data/asignaturas.xml");
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
            System.out.println("Error general al leer XML asignaturas: " + e.getMessage());
        }
    }
    private void deserializaInscripciones(){
        int cont = 0;
        try {
            JAXBContext contexto = JAXBContext.newInstance(inscripcion.class, alumno.class, asignatura.class, obligatoria.class, optativa.class, pasantia.class, tesis.class);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            XMLInputFactory factory = XMLInputFactory.newFactory();
            InputStream is = new FileInputStream("src/data/inscripciones.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while(reader.hasNext()) {
                if (reader.isStartElement() && reader.getLocalName().equals("inscripcion")) {
                    try {
                        inscripcion ins = (inscripcion) unmarshaller.unmarshal(reader);
                        if (ins.getAlumno() == null) {
                            throw new Exception("Alumno nulo");
                        }
                        if (ins.getAsignatura() == null) {
                            throw new Exception("Asignatura nula");
                        }

                        asignatura asigReal = universidad.getAsignaturas().get(ins.getAsignatura().getCodigo());
                        if (asigReal == null) {
                            throw new Exception("Asignatura código " + ins.getAsignatura().getCodigo() + " no existe");
                        }
                        ins.setAsignatura(asigReal);

                        universidad.InsertaInscripcion(ins);
                        cont++;
                    } catch (Exception e) {
                        System.out.println("Inscripción inválida: " + e.getMessage());
                    }
                }
                reader.next();
            }
            reader.close();
            System.out.println("------ Carga completa, se cargaron: " + cont + " Inscripciones------");
        }catch (Exception e) {
            System.out.println("Error general al leer XML inscripciones: " + e.getMessage());
        }
    }
    private void serializaInscripciones(){
        ArrayList<inscripcion> listaInscripciones = universidad.getInscripciones();
        try {
            JAXBContext contexto = JAXBContext.newInstance(inscripcion.class, alumno.class, asignatura.class, obligatoria.class, optativa.class, pasantia.class, tesis.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            sw.write("<inscripciones>\n");
            for (inscripcion i : listaInscripciones) {
                StringWriter writer = new StringWriter();
                marshaller.marshal(i, writer);
                String xml = writer.toString();
                String contenido = xml.substring(xml.indexOf("?>") + 2).trim();
                sw.write("    " + contenido + "\n");
            }
            sw.write("</inscripciones>");
            File archivo = new File("src/data/inscripciones.xml");
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(sw.toString());
            }
        } catch (Exception e) {
            System.out.println("Error al serializar inscripciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

