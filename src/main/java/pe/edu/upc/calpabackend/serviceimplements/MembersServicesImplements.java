package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.repositories.IMembersRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IMembersServices;

import java.util.List;

@Service
public class MembersServicesImplements implements IMembersServices {
    @Autowired
    private IMembersRepository mR;

    @Override
    public void insert(Members members) {
         mR.save(members);
    }

    @Override
    public List<Members> list() {
        return mR.findAll();
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public void update(Members members) {
        mR.save(members);
    }

    @Override
    public Members listarId(int id) {
        return mR.findById(id).orElse(new Members());
    }
}
