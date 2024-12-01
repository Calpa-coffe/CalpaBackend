package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.MemberDTO;
import pe.edu.upc.calpabackend.dtos.ProductDTO;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.serviceinterfaces.IProductServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductServices pS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody ProductDTO a) {
        ModelMapper m = new ModelMapper();
        Products ch = m.map(a, Products.class);
        pS.insert(ch);
    }

    @GetMapping //listar
    public List<ProductDTO> list() {
        return pS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, ProductDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody ProductDTO a) {
        ModelMapper m = new ModelMapper();
        Products ah = m.map(a, Products.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        pS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        pS.delete(id);
    }
}
