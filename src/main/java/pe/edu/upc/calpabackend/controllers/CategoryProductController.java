package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.CategoryProductDTO;
import pe.edu.upc.calpabackend.entities.CategoryProduct;
import pe.edu.upc.calpabackend.serviceinterfaces.ICategoryProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoryproduct")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryProductController {
    @Autowired
    private ICategoryProductService mS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody CategoryProductDTO a) {
        ModelMapper m = new ModelMapper();
        CategoryProduct ch = m.map(a, CategoryProduct.class);
        mS.insert(ch);
    }

    @GetMapping //listar
    public List<CategoryProductDTO> list() {
        return mS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, CategoryProductDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody CategoryProductDTO a) {
        ModelMapper m = new ModelMapper();
        CategoryProduct ah = m.map(a, CategoryProduct.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        mS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        mS.delete(id);
    }

    @GetMapping("/{id}")
    public CategoryProductDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        CategoryProductDTO dto = m.map(mS.listarId(id), CategoryProductDTO.class);
        return dto;
    }
}
