
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultasEmpleado extends Conexion{
    
    public boolean registrar(Empleado emp){
        
        PreparedStatement ps= null;//esta es una variable que prepara
        //sentencias SQL más seguras con el uso de parametros esos ?
        //que veremos abajo, con eso evitar injección sql, es decir, que se puedan
        //enviar sentencias sql desde el formulario
        Connection con = conectar();
        
        String sql= "INSERT INTO empleado (nombre, apellidos, clave, cedula, telefono,correo, administradora) VALUES (?,?,?,?,?,?,?)";
        
        try{//para toda sentencia sql, java pide try catch
            
            ps= con.prepareStatement(sql);
            ps.setString(1,emp.getNombre());
            ps.setString(2,emp.getApellidos());
            ps.setString(3,emp.getClave());
            ps.setString(4,emp.getCedula());
            ps.setString(5,emp.getTelefono());
            ps.setString(6,emp.getCorreo());
            ps.setInt(7, 0);
            
            //del objeto tipo producto, asignamos que se llevara a la base de datos
            //los numeros corresponden a los parametros es decir ?, y dependiendo
            //de que tipo de dato se insertara se usa distintos metodos set
            ps.execute();
            
            return true;
            
            
        }catch(Exception e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();//aqui en el finally es que cerramos la conexion
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Empleado emp){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "UPDATE empleado SET nombre=?, apellidos=?, clave=?, cedula=?, telefono=?, correo=?  WHERE id=?";
        
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1,emp.getNombre());
            ps.setString(2,emp.getApellidos());
            ps.setString(3,emp.getClave());
            ps.setString(4,emp.getCedula());
            ps.setString(5,emp.getTelefono());
            ps.setString(6,emp.getCorreo());
             
            ps.setInt(7, emp.getId());
            
            
            
            ps.executeUpdate();
            
           
            
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
    
    public boolean eliminar (Empleado emp){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "DELETE FROM empleado WHERE id=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setInt(1,emp.getId());
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
    
    public boolean buscar (Empleado emp){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        ResultSet rs= null;
        
        String sql= "SELECT * FROM empleado WHERE cedula=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setString(1,emp.getCedula());
            rs= ps.executeQuery();//trae los resultados de la consulta, no es como el
            //otro execute que no manda la consulta de vuelta
            
            if(rs.next()){//llego una consulta, osea una fila entera
                emp.setId(Integer.parseInt(rs.getString("id")));
                //se parsea por que en la clase se supone el id es int
                
                emp.setNombre(rs.getString("nombre"));
                emp.setApellidos(rs.getString("apellidos"));
                emp.setClave(rs.getString("clave"));
                emp.setCedula(rs.getString("cedula"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setCorreo(rs.getString("correo"));
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



