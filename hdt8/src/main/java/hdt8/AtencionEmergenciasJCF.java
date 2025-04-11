package hdt8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class AtencionEmergenciasJCF {
    public static void main(String[] args) {
        // Muestra el directorio actual para confirmar desde dónde se busca el archivo.
        System.out.println("Directorio actual: " + new File(".").getAbsolutePath());
        
        // Creamos la PriorityQueue; Paciente debe implementar Comparable.
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        
        // Lectura del archivo "pacientes.txt" (ruta relativa)
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Elimina el posible BOM y espacios extremos.
                linea = linea.replace("\uFEFF", "").trim();
                
                // Salta líneas vacías.
                if (linea.isEmpty()) {
                    continue;
                }
                
                // Se espera el formato: "Nombre, descripción del síntoma, código"
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintomas = partes[1].trim();
                    String codStr = partes[2].trim();
                    
                    if (codStr.isEmpty()) {
                        System.out.println("Formato incorrecto, código vacío en la línea: " + linea);
                        continue;
                    }
                    
                    char codigo = codStr.charAt(0);
                    Paciente paciente = new Paciente(nombre, sintomas, codigo);
                    queue.add(paciente);
                } else {
                    System.out.println("Formato incorrecto en la línea (se esperaban 3 campos): " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        // Muestra el orden de atención de los pacientes.
        System.out.println("Orden de atención usando java.util.PriorityQueue:");
        while (!queue.isEmpty()) {
            // poll() retira y retorna el elemento con mayor prioridad (según compareTo).
            Paciente pacienteAtendido = queue.poll();
            System.out.println(pacienteAtendido);
        }
    }
}
