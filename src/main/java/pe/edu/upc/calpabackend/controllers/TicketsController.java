package pe.edu.upc.calpabackend.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.SuppliersDTO;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.entities.Suppliers;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.exception.ResourceNotFoundException;
import pe.edu.upc.calpabackend.serviceinterfaces.ITicketsServices;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import static pe.edu.upc.calpabackend.serviceimplements.PDFgenerator.generatePDF;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    private ITicketsServices tS;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody TicketsDTO a) {
        ModelMapper m = new ModelMapper();
        Tickets ch = m.map(a, Tickets.class);
        tS.insert(ch);
    }

    @GetMapping //listar
    public List<TicketsDTO> list() {
        return tS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TicketsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody TicketsDTO a) {
        ModelMapper m = new ModelMapper();
        Tickets ah = m.map(a, Tickets.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        tS.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        tS.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketsDTO> getTicket(@PathVariable Integer id) {
        TicketsDTO ticketDTO = tS.getTicketById(id);
        return ResponseEntity.ok(ticketDTO);
    }

    @GetMapping("/{id}/download")
    public void downloadTicket(@PathVariable Integer id, HttpServletResponse response) throws Exception {
        TicketsDTO ticketDTO = tS.getTicketById(id);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleta_" + id + ".pdf");

        OutputStream outputStream = response.getOutputStream();
        generatePDF(ticketDTO, outputStream);
        outputStream.flush();
    }




}
