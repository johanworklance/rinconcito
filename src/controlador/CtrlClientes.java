
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ConsultasCliente;
import vista.frmCliente;




public class CtrlClientes implements ActionListener{
    
    private Cliente client;
    private ConsultasCliente clientC;
    private frmCliente frm;

    public CtrlClientes(Cliente client, ConsultasCliente clientC, frmCliente frm) {
        
        this.client = client;
        this.clientC = clientC;
        this.frm = frm;
        
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }
    
    
    
    @Override//esta linea la pone java en un error del actionaPerformed
    
    
    public void actionPerformed(ActionEvent e){//el e detectara que boton se presiono
        //a juro debe llevar el nombre actionPerfomed, es algo de la clase Action listener
        //que implementa el controlador producto
        
        if(e.getSource() == frm.btnGuardar){
            
            client.setNombre(frm.txtNombre.getText());
            
            client.setApellidos(frm.txtApellidos.getText());
            client.setCedula(Integer.parseInt(frm.txtCedula.getText()));
            client.setTelefono(frm.txtTelefono.getText());
            client.setCorreo(frm.txtCorreo.getText());
            
            if(clientC.registrar(client)){
                
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnModificar){
            
            client.setId(Integer.parseInt(frm.txtId.getText()));
            client.setNombre(frm.txtNombre.getText());
            client.setApellidos(frm.txtApellidos.getText());
            client.setCedula(Integer.parseInt(frm.txtCedula.getText()));
            client.setTelefono(frm.txtTelefono.getText());
            client.setCorreo(frm.txtCorreo.getText());
            
            if(clientC.modificar(client)){
                
                JOptionPane.showMessageDialog(null, "Registro modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnEliminar){
            
            client.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(clientC.eliminar(client)){
                
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
            
            
            
        }
        
        if(e.getSource() == frm.btnBuscar){
            
            
            client.setCedula(Integer.parseInt(frm.txtCedula.getText()));
            
            
            if(clientC.buscar(client)){
                
                
                frm.txtId.setText(String.valueOf(client.getId()));
                
                frm.txtNombre.setText(client.getNombre());
                frm.txtApellidos.setText(client.getApellidos());
                frm.txtCedula.setText(String.valueOf(client.getCedula()));
                frm.txtTelefono.setText(client.getTelefono());
                frm.txtCorreo.setText(client.getCorreo());
                
                
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
        
        frm.txtNombre.setText(null);
        frm.txtApellidos.setText(null);
        frm.txtCedula.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtCorreo.setText(null);
    }

    
}

