package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.dtos.TypePaymentsDTO;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.serviceinterfaces.ITypePaymentServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/typepayment")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypePaymentController {
    @Autowired
    private ITypePaymentServices tyS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody TypePaymentsDTO a) {
        ModelMapper m = new ModelMapper();
        TypePayments ch = m.map(a, TypePayments.class);
        tyS.insert(ch);
    }

    @GetMapping //listar
    public List<TypePaymentsDTO> list() {
        return tyS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TypePaymentsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody TypePaymentsDTO a) {
        ModelMapper m = new ModelMapper();
        TypePayments ah = m.map(a, TypePayments.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        tyS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        tyS.delete(id);
    }

    @GetMapping("/{id}")
    public TypePaymentsDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        TypePaymentsDTO dto = m.map(tyS.listarId(id), TypePaymentsDTO.class);
        return dto;
    }

}
