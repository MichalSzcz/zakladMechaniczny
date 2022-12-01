package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.User;
import com.example.zakladmechanicznyspringboot.model.UserLogging;
import com.example.zakladmechanicznyspringboot.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.DriverManager;
import java.util.List;


@Repository
public class UserRepository {

    @Autowired

    private UserRepository repository;
    @Autowired
            private ReportService service;
    JdbcTemplate jdbcTemplate;
    private List<User> query;

    //pamietac o zasadzie pojedynczej odpowiedzialnosci

    // Michal metoda zwracajaca zaklad
    public List<User> getAll(){

        query = jdbcTemplate.query("SELECT * FROM test", BeanPropertyRowMapper.newInstance(User.class));
        return query;


    }


    public boolean addUserToDb(User user){
        jdbcTemplate.update("INSERT INTO User(firstName, lastName, email, password) values(?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        System.out.println("Dodano do bazy");
        //jezeli wszytko sie powiedzie zwracamy true,
        return true;
    }


    //wersja funkcji ktora zwraca usera
    //zwracamy Usera
//    public User loginUser(UserLogging userLogging){
//        try{
//            return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, email, password  FROM User WHERE " +
//                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
//        }catch (DataAccessException e){
//            e.printStackTrace();
//            System.out.println("nie udalo sie znalesc takiego usera");
//            return null;
//        }
//    }


    public User loginUser(UserLogging userLogging){

        try{

            return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, email, password  FROM "  + userLogging.getType() + " WHERE " +
                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
        }catch (DataAccessException e){
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
    }



   @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format){
       try {
           return service.exportReport(format);
       } catch (JRException e) {
           throw new RuntimeException(e);
       }
   }
}
