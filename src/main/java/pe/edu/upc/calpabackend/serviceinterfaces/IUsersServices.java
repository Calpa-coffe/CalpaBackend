package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Users;

import java.util.List;

public interface IUsersServices {
    Users insert(Users user);
    public List<Users> list();
    public void delete(Long id);
    public void update(Users user);
    public Users listarId(Long id);
    public Users finduser(String username);

}
