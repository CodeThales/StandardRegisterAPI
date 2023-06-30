package com.thalescoding.registerAPI.controller;

import com.thalescoding.registerAPI.model.User;
import com.thalescoding.registerAPI.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired()
    UserServiceInterface userService;

    @GetMapping(value = "/", produces = "application/json")
    //Retornando a lista de usuários em Json para a tela
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "userId") Long id){
        ResponseEntity response = userService.getUserById(id);
        return response;
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        ResponseEntity<Object> updatedUser = userService.updateUser(user);
        return updatedUser;
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<String> updateById(@PathVariable(value = "userId") Long id){
        ResponseEntity userDeleted = userService.deleteUser(id);
        return userDeleted;
    }

    @PostMapping(value = "/addrole", produces = "application/json")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUseForm form){
        userService.addRoleToUser(form.getUserLogin(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

//    Passagem de valores por parâmetro na URL
//    @GetMapping(value = "/", produces = "application/json")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public ResponseEntity init(@RequestParam(value = "userName", required = true, defaultValue = "Nome não informado!") String name,
//                                @RequestParam(value = "userSalary", required = false, defaultValue = "Salário não informado!") String salary){
//        return new ResponseEntity<>("Hello Spring REST API user " + name + " | Salary: " + salary, HttpStatus.OK);
//    }

//    Content Negotiation / Estrutura da URL no padrão de API REST
//    @GetMapping(value = "/{userId}/reportPDF", produces = "application/pdf")
//    public ResponseEntity<Optional<User>> userReport(@PathVariable(value = "userId") Long id){
//        Chamar o serviço que gera o relatório passando o id do user;
//        O retorno seria o relatório convertido em PDF
//        return relatorio;
//    }

//    Content Negotiation / Estrutura da URL no padrão de API REST
//    @GetMapping(value = "/{userId}/venda/{vendaId}", produces = "application/pdf")
//    public ResponseEntity<Optional<User>> userReport(@PathVariable(value = "userId") Long id,
//                                                     @PathVariable(value = "vendaId") Long vendaId){
//        Chamar o serviço que gera o relatório passando o id do user e o id da venda;
//        O retorno seria o relatório convertido em PDF
//        return relatorio;
//    }

}

class RoleToUseForm{
    private String userLogin;
    private String roleName;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
