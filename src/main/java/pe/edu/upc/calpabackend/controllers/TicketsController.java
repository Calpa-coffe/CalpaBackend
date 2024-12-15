package pe.edu.upc.calpabackend.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.AttendancesByYearDTO;
import pe.edu.upc.calpabackend.dtos.QuantityProdCategoryDTO;
import pe.edu.upc.calpabackend.dtos.TicketsByDTO;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.serviceinterfaces.ITicketsServices;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pe.edu.upc.calpabackend.serviceimplements.PDFGenerator.generatePDF;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TicketsController {
    @Autowired
    private ITicketsServices tS;

    @PostMapping("/Registro")
    public ResponseEntity<Tickets> registrar(@RequestBody TicketsDTO a) {
        ModelMapper m = new ModelMapper();
        Tickets ch = m.map(a, Tickets.class);
        Tickets createdTicket = tS.insert(ch);
        return ResponseEntity.ok(createdTicket); // Devolver el ticket creado con su ID
    }

    @GetMapping //listar
    public List<TicketsDTO> list() {
        return tS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TicketsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo TicketDTO
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

    @GetMapping("/ticketsByDatePay")
    public List<TicketsByDTO> ticketsByDatePay(
            @RequestParam("findDate") LocalDate findDate,
            @RequestParam("username") String username) {
        List<String[]> filaLista = tS.getTicketsByDatepay(findDate, username);
        List<TicketsByDTO> dtoLista = new ArrayList<>();
        for (String[] columna : filaLista) {
            TicketsByDTO dto = new TicketsByDTO();
            dto.setTotal(Double.valueOf(columna[0]));
            dto.setDatepay((columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    @GetMapping("/quantityprodcat")
    public List<QuantityProdCategoryDTO> quantityprodcategory(@RequestParam("categoryname") String categoryname,
                                                              @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                              @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<String[]> filaLista = tS.getquantitypercategory(categoryname,startDate,endDate);
        List<QuantityProdCategoryDTO> dtoLista = new ArrayList<>();
        for (String[] columna : filaLista) {
            QuantityProdCategoryDTO dto = new QuantityProdCategoryDTO();
            dto.setNameproduct(columna[0]);
            dto.setQuantity(Integer.parseInt(columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    @GetMapping("/mostquantityprodcat")
    public List<QuantityProdCategoryDTO> mostquantityprodcategory(@RequestParam("categoryname") String categoryname,
                                                                  @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                  @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<String[]> filaLista = tS.getmostproductsellcat(categoryname, startDate, endDate);
        List<QuantityProdCategoryDTO> dtoLista = new ArrayList<>();
        for (String[] columna : filaLista) {
            QuantityProdCategoryDTO dto = new QuantityProdCategoryDTO();
            dto.setNameproduct(columna[0]);
            dto.setQuantity(Integer.parseInt(columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }


    @GetMapping("/{id}/download")
    public ResponseEntity<?> downloadTicket(@PathVariable Integer id, HttpServletResponse response) {
        try {
            TicketsDTO ticketDTO = tS.getTicketById(id);
            if (ticketDTO == null) {
                return ResponseEntity.status(404).body("Ticket no encontrado con ID: " + id);
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=boleta_" + id + ".pdf");

            try (OutputStream outputStream = response.getOutputStream()) {
                generatePDF(ticketDTO, outputStream);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al generar o descargar el PDF: " + e.getMessage());
        }
    }

}
