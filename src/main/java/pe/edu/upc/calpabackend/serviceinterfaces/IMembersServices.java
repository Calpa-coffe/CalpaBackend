package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Members;

import java.util.List;

public interface IMembersServices {
    Members insert(Members members);
    public List<Members> list();
    public void delete(int id);
    public void update(Members members);
}
