package pe.edu.upc.calpabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.AttendancesByYearDTO;
import pe.edu.upc.calpabackend.dtos.AttendancesDTO;
import pe.edu.upc.calpabackend.entities.Attendances;
import pe.edu.upc.calpabackend.serviceinterfaces.IAttendancesServices;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendances")
public class AttendancesController {
    @Autowired
    private IAttendancesServices aS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody AttendancesDTO a) {
        ModelMapper m = new ModelMapper();
        Attendances ch = m.map(a, Attendances.class);
        aS.insert(ch);
    }

    @GetMapping //listar
    public List<AttendancesDTO> list() {
        return aS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AttendancesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody AttendancesDTO a) {
        ModelMapper m = new ModelMapper();
        Attendances ah = m.map(a, Attendances.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        aS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        aS.delete(id);
    }

    @GetMapping("/{id}")
    public AttendancesDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        AttendancesDTO dto = m.map(aS.listarId(id), AttendancesDTO.class);
        return dto;
    }

    @GetMapping("/attendancesyear")
    public List<AttendancesByYearDTO> cantidadIncidentesPorMes(@RequestParam LocalDate startDate, LocalDate endDate){
        List<String[]> filaLista = aS.getAttendancesByYear(startDate, endDate);
        List<AttendancesByYearDTO> dtoLista = new ArrayList<>();
        for(String[] columna: filaLista){
            AttendancesByYearDTO dto = new AttendancesByYearDTO();
            dto.setVendedor(columna[0]);
            dto.setAnio(Double.valueOf(columna[1]));
            dto.setMes(Double.valueOf(columna[2]));
            dto.setTotal_asistencias(Integer.parseInt(columna[3]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

}
