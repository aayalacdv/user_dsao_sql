package edu.upc.dsa.interfaces;

import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface IUserDAO {
    public Usuario findUsuariobyId(String id);
    public List<Usuario> findAll();
    public void addUsuario(Usuario usuario);
    public void deleteUsuario(String id);
    public void updateUsuario(String id, Usuario usuario);

}
