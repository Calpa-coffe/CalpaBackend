package pe.edu.upc.calpabackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.calpabackend.dtos.UsersDTO;
import pe.edu.upc.calpabackend.entities.Users;
import pe.edu.upc.calpabackend.serviceinterfaces.IUsersServices;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/User")
public class UsersController {
    @Autowired
    private IUsersServices uS;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/Registro")
    public ResponseEntity<UsersDTO> registrar(@RequestBody UsersDTO userDTO) {
        ModelMapper m = new ModelMapper();
        Users us = m.map(userDTO, Users.class);
        String encodedPassword = passwordEncoder.encode(us.getPassword());
        us.setPassword(encodedPassword);
        Users newUser = uS.insert(us);
        UsersDTO userResponse = m.map(newUser, UsersDTO.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping //listar
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UsersDTO> list(){
        return uS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UsersDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Long id, @RequestBody UsersDTO u){
        ModelMapper m = new ModelMapper();
        Users uh = m.map(u, Users.class);
        uh.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        uS.update(uh);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Long id){
        uS.delete(id);
    }

}
