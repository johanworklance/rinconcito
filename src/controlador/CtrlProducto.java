
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultasProducto;
import modelo.Producto;
import vista.frmProducto;


public class CtrlProducto implements ActionListener{//esto de implementar a ActionListener
    //es que usara los metodos de esa clase, pero no es una herencia como tal, porque
    //no usa sus atributos, en este caso usara el metodo mas abajo actionPerfomed
    
    private Producto mod;
    private ConsultasProducto modC;
    private frmProducto frm;

    public CtrlProducto(Producto mod, ConsultasProducto modC, frmProducto frm) {
        
        //el controlador en su constructor se encarga de instanciar, el producto, sus consultas
        //y su formulario, a parte de activarle su escuchador de eventos a los botones del
        //formulario
        
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        //esto no lo entiendo muy bien, es como si los eventos van a funcionar desde aquí
        //no con los eventos del formulario en si, en documentacion le llaman suscribir
        //por cierto el this dentro del metodo, se refiere a la clase entera en si
        //que es la que hace implements a actionListener, es convención/regla
    }
    
    
    public void iniciar(){
        
        /*frm.setTitle("Productos");//titulamos la vista
        frm.setLocationRelativeTo(null);//lo centramos
        frm.txtId.setVisible(false);//escondemos el txt del id pa que no se vea cuando
        //modificamos o eliminamos, por que primero buscamos y despues el proceso siguiente*/
       
    }
    
    @Override//esta linea la pone java en un error del actionaPerformed
    
    
    public void actionPerformed(ActionEvent e){//el e detectara que boton se presiono
        //a juro debe llevar el nombre actionPerfomed, es algo de la clase Action listener
        //que implementa el controlador de los producto osea esta clase
        
        if(e.getSource() == frm.btnGuardar){
            
            //primero usamos los metodos set del objeto producto que llega aqui desde
            //el constructor
            
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCodigo.getText()));
            mod.setRutaImagen(frm.nombreFoto);
            
            if(modC.registrar(mod)){//despues registramos con la instancia de las consultas
                //producto, es aqui donde se le envia la instancia del producto al controlador
                //del mismo
                
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnModificar){
            
            mod.setId(Integer.parseInt(frm.txtId.getText()));//el id llega en la consulta
            //lo mandamos para que pueda modificar con dicho parametro
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCodigo.getText()));
            mod.setRutaImagen(frm.nombreFoto);
            
            if(modC.modificar(mod)){
                
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnEliminar){
            
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(modC.eliminar(mod)){
                
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnBuscar){
            
            
            mod.setCodigo(frm.txtCodigo.getText());
            
            
            if(modC.buscar(mod)){
                
                
                frm.txtId.setText(String.valueOf(mod.getId()));
                //recordar que llega de la bd como un int, pero en el txt del form pide string
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombre.setText(mod.getNombre());
                frm.txtDescripcion.setText(mod.getDescripcion());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                frm.lbArchivo.setText(mod.getRutaImagen());
                frm.nombreFoto= mod.getRutaImagen();
                
                
            }else{
                JOptionPane.showMessageDialog(null, "No encontro el resultado");
                
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnLimpiar){
            limpiar();
        }
        
    }
    
    public void limpiar(){
        
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtDescripcion.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtCantidad.setText(null);
        frm.lbArchivo.setText(null);
    }

    
}
