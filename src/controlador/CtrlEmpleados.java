
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultasEmpleado;
import modelo.Empleado;
import vista.frmEmpleado;



public class CtrlEmpleados implements ActionListener{//esto de implementar a ActionListener
    //es que usara los metodos de esa clase, pero no es una herencia como tal, porque
    //no usa sus atributos, en este caso usara el metodo mas abajo actionPerfomed
    
    private Empleado emp;
    private ConsultasEmpleado empC;
    private frmEmpleado frm;

    public CtrlEmpleados(Empleado emp, ConsultasEmpleado empC, frmEmpleado frm) {
        
        //el controlador en su constructor se encarga de instanciar, el producto, sus consultas
        //y su formulario, a parte de activarle su escuchador de eventos a los botones del
        //formulario
        
        this.emp = emp;
        this.empC = empC;
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
       
    }//antes usaba esto, pero en el mismo administracion invoco al formulario
    
    @Override//esta linea la pone java en un error del actionaPerformed
    
    
    public void actionPerformed(ActionEvent e){//el e detectara que boton se presiono
        //a juro debe llevar el nombre actionPerfomed, es algo de la clase Action listener
        //que implementa el controlador de los producto osea esta clase
        
        if(e.getSource() == frm.btnGuardar){
            
            //primero usamos los metodos set del objeto producto que llega aqui desde
            //el constructor
            
            emp.setNombre(frm.txtNombre.getText());
            emp.setApellidos(frm.txtApellidos.getText());
            emp.setClave(frm.txtClave.getText());
            emp.setCedula(frm.cedula.getText());
            emp.setTelefono(frm.txtTelefono.getText());
            emp.setCorreo(frm.txtCorreo.getText());
            
            if(empC.registrar(emp)){//despues registramos con la instancia de las consultas
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
            
            emp.setId(Integer.parseInt(frm.txtId.getText()));//el id llega en la consulta
            //lo mandamos para que pueda modificar con dicho parametro
            emp.setApellidos(frm.txtApellidos.getText());
            emp.setNombre(frm.txtNombre.getText());
            emp.setClave(frm.txtClave.getText());
            emp.setCedula(frm.cedula.getText());
            emp.setTelefono(frm.txtTelefono.getText());
            emp.setCorreo(frm.txtCorreo.getText());
            
            if(empC.modificar(emp)){
                
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnEliminar){
            
            emp.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(empC.eliminar(emp)){
                
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnBuscar){
            
            
            emp.setCedula(frm.cedula.getText());
            
            
            if(empC.buscar(emp)){
                
                
                frm.txtId.setText(String.valueOf(emp.getId()));
                //recordar que llega de la bd como un int, pero en el txt del form pide string
                frm.txtApellidos.setText(emp.getApellidos());
                frm.txtNombre.setText(emp.getNombre());
                frm.txtClave.setText(emp.getClave());
                frm.cedula.setText(emp.getCedula());
                frm.txtTelefono.setText(emp.getTelefono());
                frm.txtCorreo.setText(emp.getCorreo());
                
                
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
        frm.txtApellidos.setText(null);
        frm.txtNombre.setText(null);
        frm.txtClave.setText(null);
        frm.cedula.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtCorreo.setText(null);
    }

    
}
