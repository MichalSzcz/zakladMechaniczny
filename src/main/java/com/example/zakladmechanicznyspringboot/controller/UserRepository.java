package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    @Autowired
    static
    JdbcTemplate jdbcTemplate;

    //pamietac o zasadzie pojedynczej odpowiedzialnosci


//    public boolean addUserToDb(UserRegistering user){
//        jdbcTemplate.update("INSERT INTO " + user.getType() +"(firstName, lastName, email, password) values(?, ?, ?, ?)",
//                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
//        System.out.println("Dodano do bazy");
//        //jezeli wszytko sie powiedzie zwracamy true,
//        return true;
//    }

    public void createWorkshop(Zaklad zaklad) {
        jdbcTemplate.execute("CREATE TABLE " + zaklad.getName() + "(id int NOT NULL AUTO_INCREMENT," +
                "  role varchar(45) NOT NULL," +
                "  firstName varchar(45) NOT NULL," +
                "  lastName varchar(45) NOT NULL," +
                "  email varchar(45) NOT NULL," +
                "  password varchar(45) NOT NULL," +
                "  gender varchar(45) DEFAULT NULL," +
                "  PRIMARY KEY (id))");
    }

    public void addUserToDb(UserRegistering user, Zaklad zaklad) {
//        //sprawdzamy, czy konto jest tworzone dla kierownika
//        if (Objects.equals(user.getRole(), "Kierownik")) {
//            //jeśli tak, to sprawdzamy, czy jest już w bazie
//
//            if (returnKierownik(user, zaklad) != null) {
//                //jeśli zwróciło kierownika to znaczy, że kierownik w tabeli już jest, więc nie dodajemy kolejnego
//                System.out.println("W tej tabeli jest już kierownik");
//            }
//        }
//        else {
            jdbcTemplate.update("INSERT INTO " + zaklad.getName() + " (role, firstName, lastName, email, password, gender) values(?, ?, ?, ?, ?, ?)",
                    user.getRole(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender());
            System.out.println("Dodano do bazy");
//        }
    }

    //wersja funkcji ktora zwraca usera
    //zwracamy Usera
    public User returnKierownik(UserRegistering userRegistering, Zaklad zaklad){
        try{
            return jdbcTemplate.queryForObject("SELECT id, role, firstName, lastName, email, password, gender FROM " + zaklad.getName() + " WHERE " +
                    "role = ?", BeanPropertyRowMapper.newInstance(User.class), userRegistering.getRole());
        }catch (DataAccessException e){
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
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

    public User loginUser(UserLogging userLogging) {

        try {

            return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, email, password  FROM " + userLogging.getType() + " WHERE " +
                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
    }

    public List<Pracownik> getAll(){
        return jdbcTemplate.query("Select id, firstName * from freedb_zakladMechaniczny.Pracownik",
                BeanPropertyRowMapper.newInstance(Pracownik.class));
    }

}
