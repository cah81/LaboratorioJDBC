package datos;

import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class UsuarioDao {
    private static final String SQL_SELECT = "SELECT id_usuario,usuario,password  FROM usuario";
    private static final String QUERY = "SELECT id_usuario,usuario,password FROM usuario WHERE id_usuario = ?";

    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password ) VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";

    private static final String SQL_DELETE = "DELETE FROM usuario where id_usuario = ?";

    public List<Usuario> listarUsuarios(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs= null;
        Usuario usuarioN = null;
        List<Usuario> usuarios = new ArrayList<>();


        try {

            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idUsuario =  rs.getInt("id_usuario");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                usuarioN = new Usuario(idUsuario,usuario,password);
                usuarios.add(usuarioN);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
                try{
                    close(rs);
                    close(preparedStatement);
                    close(conn);
                }catch (SQLException ex){
                    ex.printStackTrace(System.out);
                }
        }
        return usuarios;
    }

    public int insertar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros =0;
        try {
            conn = getConnection();//la conexion siempre va
            stmt = conn.prepareStatement(SQL_INSERT);//la query que se va a ejecutar
            stmt.setString(1, usuario.getUsuario());//los campos de la tabla que estan a modificar en la tabla
            stmt.setString(2, usuario.getPassword());

            registros = stmt.executeUpdate();//modifica estado de la base de datos se usa para insert update o  delete

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int actualizar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE); //prepara la query a ejecutar

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());//en este caso el id_persona es tipo int


            registros = stmt.executeUpdate();//ejecucion de la query
            System.out.println(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public int eliminar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE); //prepara la query a ejecutar
            stmt.setInt(1, usuario.getIdUsuario());//en este caso el id_persona es tipo int


            registros = stmt.executeUpdate();//ejecucion de la query
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public Usuario findById(Usuario usuario)  {
        //definimos variables que vamos a usar
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs =null;

        Usuario usuarioBuscado = null;

        try {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(QUERY);

            preparedStatement.setInt(1, usuario.getIdUsuario());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_usuario");
                String usuario1 = rs.getString("usuario");
                String password = rs.getString("password");

                System.out.println(id + "," + usuario1 + "," + password);
                usuarioBuscado = new Usuario(id,usuario1,password);
                System.out.println(usuarioBuscado + " este es el usuario encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            try{
                Conexion.close(preparedStatement);
                Conexion.close(rs);
                close(conn);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return usuarioBuscado;
    }





}
