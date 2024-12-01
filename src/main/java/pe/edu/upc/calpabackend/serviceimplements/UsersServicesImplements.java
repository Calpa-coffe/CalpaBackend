package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.entities.Users;
import pe.edu.upc.calpabackend.repositories.IUsersRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IUsersServices;

import java.util.List;

@Service
public class UsersServicesImplements implements IUsersServices {
    @Autowired
    private IUsersRepository uR;


    @Override
    public Users insert(Users user) {
        return uR.save(user);
    }

    @Override
    public List<Users> list() {
        return uR.findAll();
    }

    @Override
    public void delete(Long id) {
        uR.deleteById(id);
    }

    @Override
    public void update(Users user) {
        uR.save(user);
    }

    @Override
    public Users listarId(Long id) {
        return uR.findById(id).orElse(new Users());
    }
}
