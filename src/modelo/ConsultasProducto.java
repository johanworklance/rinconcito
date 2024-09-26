
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultasProducto extends Conexion{
    
    public boolean registrar(Producto pro){
        
        PreparedStatement ps= null;//esta es una variable que prepara
        //sentencias SQL más seguras con el uso de parametros esos ?
        //que veremos abajo, con eso evitar injección sql, es decir, que se puedan
        //enviar sentencias sql desde el formulario
        Connection con = conectar();
        
        String sql= "INSERT INTO producto (codigo, nombre, descripcion, precio, cantidad,rutaImagen) VALUES (?,?,?,?,?,?)";
        
        try{//para toda sentencia sql, java pide try catch
            
            ps= con.prepareStatement(sql);
            ps.setString(1,pro.getCodigo());
            ps.setString(2,pro.getNombre());
            ps.setString(3,pro.getDescripcion());
            ps.setDouble(4,pro.getPrecio());
            ps.setInt(5, pro.getCantidad());
            ps.setString(6,pro.getRutaImagen());
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
    
    public boolean modificar(Producto pro){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "UPDATE producto SET codigo=?, nombre=?, descripcion=?, precio=?, cantidad=?, rutaImagen=? WHERE id=?";
        
        try{
            System.out.println(pro.getNombre() +" " +pro.getRutaImagen());
            ps= con.prepareStatement(sql);
            ps.setString(1,pro.getCodigo());
            ps.setString(2,pro.getNombre());
            ps.setString(3,pro.getDescripcion());
            ps.setDouble(4,pro.getPrecio());
            ps.setInt(5, pro.getCantidad());
            ps.setString(6,pro.getRutaImagen());
            ps.setInt(7,pro.getId());
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
    
    public boolean eliminar (Producto pro){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        
        String sql= "DELETE FROM producto WHERE id=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setInt(1,pro.getId());
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
    
    public boolean buscar (Producto pro){
        
        PreparedStatement ps= null;
        Connection con = conectar();
        ResultSet rs= null;
        
        String sql= "SELECT * FROM producto WHERE codigo=?";
        //las posiciones en los setString o setInt se refieren al ?, por ejemplo
        //aquí es uno
        
        try{
            
            ps= con.prepareStatement(sql);
            ps.setString(1,pro.getCodigo());
            rs= ps.executeQuery();//trae los resultados de la consulta, no es como el
            //otro execute que no manda la consulta de vuelta
            
            if(rs.next()){//llego una consulta, osea una fila entera
                pro.setId(Integer.parseInt(rs.getString("id")));
                //se parsea por que en la clase se supone el id es int
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                //parece ser que hay un getDouble, pero no lo usare, parseare
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setRutaImagen(rs.getString("rutaImagen"));
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



