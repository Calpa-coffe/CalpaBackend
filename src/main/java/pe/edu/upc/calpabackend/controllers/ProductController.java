package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.AttendancesByYearDTO;
import pe.edu.upc.calpabackend.dtos.MemberDTO;
import pe.edu.upc.calpabackend.dtos.ProductDTO;
import pe.edu.upc.calpabackend.dtos.ProductsByCategoryQueryDTO;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.serviceinterfaces.IProductServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200"})
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

    @GetMapping("/{id}")
    public ProductDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        ProductDTO dto = m.map(pS.listarId(id), ProductDTO.class);
        return dto;
    }

    @GetMapping("/productsByCategory")
    public List<ProductDTO> productsByCategory(@RequestParam String typecategory){
        List<String[]> filaLista = pS.getProductsByCategoryProduct(typecategory);
        List<ProductDTO> dtoLista = new ArrayList<>();
        for(String[] columna: filaLista){
            ProductDTO dto = new ProductDTO();
            dto.setId(Integer.parseInt(columna[0]));
            dto.setNameproduct(columna[1]);
            dto.setDescription((columna[2]));
            dto.setImage((columna[3]));
            dto.setPrice(Double.parseDouble(columna[4]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

}
