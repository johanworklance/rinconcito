
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultasCliente extends Conexion{
    
    public boolean registrar(Cliente client){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "INSERT INTO cliente  (nombre, apellidos, cedula, telefono, correo) VALUES (?,?,?,?,?)";
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setString(1,client.getNombre());
            ps.setString(2,client.getApellidos());
            
            ps.setInt(3,client.getCedula());
            ps.setString(4,client.getTelefono());
            ps.setString(5,client.getCorreo());
            
            ps.execute();
            
            return true;
            
            
        }catch(Exception e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Cliente client){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "UPDATE cliente SET nombre=?, apellidos=?, cedula=?, telefono=?, correo=? WHERE id=?";
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setString(1,client.getNombre());
            ps.setString(2,client.getApellidos());
            ps.setInt(3,client.getCedula());
            ps.setString(4,client.getTelefono());
            ps.setString(5,client.getCorreo());
            ps.setInt(6,client.getId());
            ps.execute();
            
            return true;
            
            
        }catch(Exception e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
    public boolean eliminar (Cliente client){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "DELETE FROM cliente WHERE id=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setInt(1,client.getId());
            ps.execute();
            
            return true;
            
            
        }catch(Exception e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
    public boolean buscar (Cliente client){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        ResultSet rs= null;
        
        String sql= "SELECT * FROM cliente WHERE cedula=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setInt(1,client.getCedula());
            rs= ps.executeQuery();//trae los resultados de la consulta
            
            if(rs.next()){
                client.setId(Integer.parseInt(rs.getString("id")));
                client.setNombre(rs.getString("nombre"));
                client.setApellidos(rs.getString("apellidos"));
                client.setCedula(rs.getInt("cedula"));
                client.setTelefono(rs.getString("telefono"));
                client.setCorreo(rs.getString("correo"));
                return true;
            }
            
            return false;
            
            
        }catch(Exception e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
}




