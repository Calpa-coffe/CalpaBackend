package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.BookingsDTO;
import pe.edu.upc.calpabackend.dtos.DetailSupplierDTO;
import pe.edu.upc.calpabackend.entities.Bookings;
import pe.edu.upc.calpabackend.entities.DetailSuppliers;
import pe.edu.upc.calpabackend.serviceinterfaces.IDetailSuppliersServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detailsupplier")
public class DetailSupplierController {
    @Autowired
    private IDetailSuppliersServices dS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody DetailSupplierDTO a) {
        ModelMapper m = new ModelMapper();
        DetailSuppliers ch = m.map(a, DetailSuppliers.class);
        dS.insert(ch);
    }

    @GetMapping //listar
    public List<DetailSupplierDTO> list() {
        return dS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, DetailSupplierDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody DetailSupplierDTO a) {
        ModelMapper m = new ModelMapper();
        DetailSuppliers ah = m.map(a, DetailSuppliers.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        dS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        dS.delete(id);
    }
}
