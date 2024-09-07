/**
# Autor: Jonathan Hernández
# Fecha: 07 Septiembre 2024
# Descripción: Código para simulacion de el triangulo recursivo de Sierpinski
# GitHub: https://github.com/Jona163
*/

/**
 * Triángulo de Sierpinski con recursividad y un toque de estilo gráfico.
 * @author Jony
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 
 public class TrianguloRecursivo extends JPanel {
 
     // Parámetros iniciales del triángulo
     private final int ladoInicial = 400;
     private final int xInicial = 100;
     private final int yInicial = 500;
 
     // Constructor: Configura el evento del mouse
     public TrianguloRecursivo() {
         addMouseListener(new MouseAdapter() {
             @Override
             public void mousePressed(MouseEvent e) {
                 repaint();  // Redibuja cuando se hace clic en el panel
             }
         });
     }
 
     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);  // Llama a la implementación del JPanel para limpiar la pantalla
         setBackground(Color.WHITE);  // Fondo blanco para un look limpio
         Graphics2D g2d = (Graphics2D) g;
 
         // Habilitar suavizado de bordes para gráficos más suaves
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
         // Dibujar el triángulo recursivo
         dibujarTriangulo(g2d, xInicial, yInicial, ladoInicial, 0);
     }
 
     // Método recursivo para dibujar el triángulo de Sierpinski
     private void dibujarTriangulo(Graphics2D g, int x, int y, int lado, int nivel) {
         // Calcular los vértices del triángulo
         int x2 = x + lado / 2;
         int y2 = y - lado;
         int x3 = x + lado;
 
         // Crear un degradado de color basado en el nivel de recursividad
         Color colorInicial = new Color(30, 144, 255);  // Azul
         Color colorFinal = new Color(255, 69, 0);  // Naranja/Rojo
         float ratio = Math.min(1.0f, nivel / 10.0f);
         Color colorActual = interpolarColor(colorInicial, colorFinal, ratio);
 
         // Dibujar el triángulo con color
         g.setColor(colorActual);
         g.drawLine(x, y, x2, y2);  // Primer lado
         g.drawLine(x2, y2, x3, y);  // Segundo lado
         g.drawLine(x3, y, x, y);    // Tercer lado
 
         // Reducir el tamaño del triángulo para la recursividad
         lado /= 2;
 
         // Condición base: si el triángulo es muy pequeño, detener la recursividad
         if (lado > 10) {
             // Dibujar los triángulos hijos (izquierda, derecha y superior)
             dibujarTriangulo(g, x, y, lado, nivel + 1);  // Triángulo izquierdo
             dibujarTriangulo(g, x + lado, y, lado, nivel + 1);  // Triángulo derecho
             dibujarTriangulo(g, x + lado / 2, y - lado, lado, nivel + 1);  // Triángulo superior
         }
     }
 
     // Método para interpolar entre dos colores
     private Color interpolarColor(Color c1, Color c2, float ratio) {
         int rojo = (int) (c1.getRed() * (1 - ratio) + c2.getRed() * ratio);
         int verde = (int) (c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
         int azul = (int) (c1.getBlue() * (1 - ratio) + c2.getBlue() * ratio);
         return new Color(rojo, verde, azul);
     }
 
     // Método principal para ejecutar la aplicación
     public static void main(String[] args) {
         JFrame ventana = new JFrame("Triángulo Recursivo de Sierpinski");
         TrianguloRecursivo panel = new TrianguloRecursivo();
 
         // Configuración de la ventana
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.add(panel);
         ventana.setSize(600, 600);  // Tamaño ajustado para mostrar mejor el triángulo
         ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
         ventana.setVisible(true);
     }
 }
 
