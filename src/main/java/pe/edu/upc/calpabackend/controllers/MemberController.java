package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.DetailSupplierDTO;
import pe.edu.upc.calpabackend.dtos.MemberDTO;
import pe.edu.upc.calpabackend.entities.DetailSuppliers;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.serviceinterfaces.IMembersServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MemberController {
    @Autowired
    private IMembersServices mS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody MemberDTO a) {
        ModelMapper m = new ModelMapper();
        Members ch = m.map(a, Members.class);
        mS.insert(ch);
    }

    @GetMapping //listar
    public List<MemberDTO> list() {
        return mS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, MemberDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody MemberDTO a) {
        ModelMapper m = new ModelMapper();
        Members ah = m.map(a, Members.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        mS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        mS.delete(id);
    }

    @GetMapping("/{id}")
    public MemberDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        MemberDTO dto = m.map(mS.listarId(id), MemberDTO.class);
        return dto;
    }

}
