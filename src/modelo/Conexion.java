
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author johan
 */
public class Conexion {
    private final String bd= "rinconcito";
    private final String user= "root";
    private final String password= "";
    private final String url= "jdbc:mysql://localhost:3306/" + bd;
    private Connection con= null;//variable que obtendra la conexion, la guardara 
    //y despúes la retornara
    
    public Connection conectar(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");//controlador de conexión
            //tambien este metodo crea una instancia con el nombre de clase que
            //le mandemos en este caso ese driver, que es una api o programa
            //para conectarnos a la bd
            con= (Connection) DriverManager.getConnection(this.url,this.user,this.password);
            //este señor con es quien tiene toda la conexion, lo usaremos cada que 
            //invoquemos conectar
            //por cierto aqui java me pidio castear, es algo asi como que convirtio
            //la variable de driver a tipo connection, es como volver double a int
        }catch(Exception e){
            System.err.println(e);
        }
        return con;//enviamos el objeto tipo Connection
    }
    
}
