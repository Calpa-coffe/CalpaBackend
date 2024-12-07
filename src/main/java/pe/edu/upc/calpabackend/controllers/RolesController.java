package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.ProductDTO;
import pe.edu.upc.calpabackend.dtos.RolesDTO;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.Roles;
import pe.edu.upc.calpabackend.serviceinterfaces.IRolesServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RolesController {
    @Autowired
    private IRolesServices rS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody RolesDTO s){
        ModelMapper m = new ModelMapper();
        Roles sh=m.map(s, Roles.class);
        rS.insert(sh);
    }

    @GetMapping //listar
    public List<RolesDTO> list(){
        return rS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, RolesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Long id, @RequestBody RolesDTO re){
        ModelMapper m = new ModelMapper();
        Roles rh = m.map(re, Roles.class);
        rh.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        rS.update(rh);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Long id){
        rS.delete(id);
    }
}
