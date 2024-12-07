package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.AttendancesDTO;
import pe.edu.upc.calpabackend.dtos.BookingsDTO;
import pe.edu.upc.calpabackend.entities.Attendances;
import pe.edu.upc.calpabackend.entities.Bookings;
import pe.edu.upc.calpabackend.serviceinterfaces.IBookingsServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookingsController {
    @Autowired
    private IBookingsServices bS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody BookingsDTO a) {
        ModelMapper m = new ModelMapper();
        Bookings ch = m.map(a, Bookings.class);
        bS.insert(ch);
    }

    @GetMapping //listar
    public List<BookingsDTO> list() {
        return bS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, BookingsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody BookingsDTO a) {
        ModelMapper m = new ModelMapper();
        Bookings ah = m.map(a, Bookings.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        bS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        bS.delete(id);
    }

}
