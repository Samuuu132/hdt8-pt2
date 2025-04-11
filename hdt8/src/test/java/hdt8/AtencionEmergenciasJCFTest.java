package hdt8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.PriorityQueue;


public class AtencionEmergenciasJCFTest {


    @Test
    public void testPacienteComparison() {
        Paciente p1 = new Paciente("Paciente 1", "Síntoma 1", 'A');
        Paciente p2 = new Paciente("Paciente 2", "Síntoma 2", 'B');
        Paciente p3 = new Paciente("Paciente 3", "Síntoma 3", 'A');

        assertEquals(0, p1.compareTo(p3), "Dos pacientes con prioridad 'A' deben tener compareTo=0");
        assertTrue(p1.compareTo(p2) < 0, "El paciente con código 'A' debe tener mayor prioridad que el de código 'B'");
        assertTrue(p2.compareTo(p1) > 0, "El paciente con código 'B' debe venir después que el de código 'A'");
    }


    @Test
    public void testPriorityQueueInsertionAndPeek() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        
        Paciente p1 = new Paciente("Paciente 1", "Síntoma 1", 'C');
        Paciente p2 = new Paciente("Paciente 2", "Síntoma 2", 'A'); 
        Paciente p3 = new Paciente("Paciente 3", "Síntoma 3", 'B');
        
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);

        Paciente peeked = queue.peek();
        assertNotNull(peeked, "El método peek() no debería retornar null");
        assertEquals('A', peeked.getCodigo(), "peek() debe retornar el paciente con código 'A'");
    }


    @Test
    public void testPriorityQueueRemovalOrder() {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        
        // Insertar pacientes en orden aleatorio
        Paciente p1 = new Paciente("Juan Perez", "fractura de pierna", 'C');
        Paciente p2 = new Paciente("Maria Ramirez", "apendicitis", 'A');
        Paciente p3 = new Paciente("Lorenzo Toledo", "chikunguya", 'E');
        Paciente p4 = new Paciente("Carmen Sarmientos", "dolores de parto", 'B');
        
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        queue.add(p4);
        

        Paciente removed1 = queue.poll();
        assertEquals('A', removed1.getCodigo(), "El primer paciente removido debe tener código 'A'");
        
        Paciente removed2 = queue.poll();
        assertEquals('B', removed2.getCodigo(), "El segundo paciente removido debe tener código 'B'");
        
        Paciente removed3 = queue.poll();
        assertEquals('C', removed3.getCodigo(), "El tercer paciente removido debe tener código 'C'");
        
        Paciente removed4 = queue.poll();
        assertEquals('E', removed4.getCodigo(), "El cuarto paciente removido debe tener código 'E'");
        
        assertTrue(queue.isEmpty(), "La cola debe estar vacía después de remover todos los elementos");
    }
}
