
package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * heredamos de  swing JPanel
 * toda esta clase nos servira en exclusivo para dibujar imagenes
 */
public class Imagen extends javax.swing.JPanel {
    
    String ruta;
    
    public Imagen(int x, int y, String ruta){
        
        this.setSize(x,y);//setSize metodo de la librería Java Swing, pasamos ancho altura
        //no necesitamos usar un objeto para activarla es como una especie de clase static
        //aunque no lo es, tecnicamente es del objeto en si, por eso el uso de this
        //aquí tomara el tamaño del panel donde estara la imagen
        this.ruta= ruta;
    }
    
    /*
    *Varios puntos: Graphics clase de java para dibujar
    *Dimension: objetos que contiene el alto y ancho de un componente
    *getSize() tamaño del objeto tipo dimension
    */
    public void paint(Graphics g){//por pura convención de la 
        //libería java swing, a juro este metodo debe llamarse paint
        //o no funcionara
        
        Dimension tamaño= this.getSize();
        ImageIcon img= new ImageIcon(getClass().getResource(ruta));//clase para crear iconos, aquí la usaremos para la
        //del logo, el getClass es un metodo que trae datos de la clase en donde estemos, puede
        //ser nombre de la misma, pero en este caso es para usar el metodo getResource el cual
        //nos trae el recurso que indiquemos, en este caso la imagen
        
        g.drawImage(img.getImage(),0,0,tamaño.width,tamaño.height,null);//este metodo
        //es el que dibuja la imagen, sus parametros traen la imagen de la variable tipo ImageIcon
        //coordenadas, tamaño, y el último no se por que es null
        
        setOpaque(false);//originalmente las imagenes no se ven, con esto se vera en el panel
        super.paintComponent(g);//aquí coloco esto por convención, es decir
        //el super es porque usamos un metodo de la clase padre en este caso java swing
        //y ese metodo paintComponent, supongo que dibuja finalmente
        
        
        
        
    }
    
}
