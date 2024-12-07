package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.ProductDTO;
import pe.edu.upc.calpabackend.dtos.SuppliersDTO;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.Suppliers;
import pe.edu.upc.calpabackend.serviceinterfaces.ISuppliersServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SuppliersController {
    @Autowired
    private ISuppliersServices sS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody SuppliersDTO a) {
        ModelMapper m = new ModelMapper();
        Suppliers ch = m.map(a, Suppliers.class);
        sS.insert(ch);
    }

    @GetMapping //listar
    public List<SuppliersDTO> list() {
        return sS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, SuppliersDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody SuppliersDTO a) {
        ModelMapper m = new ModelMapper();
        Suppliers ah = m.map(a, Suppliers.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        sS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        sS.delete(id);
    }
}
