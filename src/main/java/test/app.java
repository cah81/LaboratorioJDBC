package test;

import datos.UsuarioDao;
import domain.Usuario;

import java.util.List;

public class app {
    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        //usando una funcion lamda se obtiene el mismo resultado
        usuarios.forEach(usuario -> {
            System.out.println("usuario: " + usuario);
        });

        //insertando un nuevo objeto  tipo persona

        Usuario usuarioNuevo = new Usuario("luis", "123");
        usuarioDao.insertar(usuarioNuevo);
        usuarios.forEach(usuario -> {
            System.out.println("usuario" + usuario);
        });
        System.out.println("registros insertados " + usuarioDao.insertar(usuarioNuevo));


        //actualizando objeto persona

        Usuario usuarioModificar = new Usuario(3, "cah119", "123");
        System.out.println(usuarioDao.actualizar(usuarioModificar)+ " USUARIO MODIFICADO");

        usuarios.forEach(usuario -> {
            System.out.println("Nueva lista");
            System.out.println("Usuarios :  " + usuario);
        });
        //eliminar objeto persona
        Usuario personaEliminar = new Usuario(12);
        usuarioDao.eliminar(personaEliminar);

        usuarios.forEach(usuario -> {
            System.out.println("Nueva lista despues de eliminada");
            System.out.println("Usuario:  " + usuario);
        });

       // buscar Objeto persona por id
        Usuario usuario = new Usuario(14);
        System.out.println("-------------------------------");
        System.out.println("la persona con id es : " + usuarioDao.findById(usuario));

    }
    }

