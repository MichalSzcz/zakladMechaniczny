package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.User;
import com.example.zakladmechanicznyspringboot.model.UserLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class IndexController {

    //wstrzykujemy zależność userRepository
    //jest to tzw bean ktory utworzy nam obiekt tej klasy gdy jej potrzebujemy
    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String welcomePage() {

//            return "rejestracja";
        return "stronaPowitalna";
    }


    //    action zalogowany jest w htmlu stronaPowitalna
    @PostMapping("/zalogowany")
    public String logowanie(@ModelAttribute UserLogging userLogging, Model model) {
        //tutaj trzeba będzie zaimplenetować logowanie do systemu

        if (Objects.isNull(userRepository.loginUser(userLogging))) {
            System.out.println("mamy nula");
            System.out.println("nie ma takiego usera w wybranej tabeli");
            return "stronaPowitalna";
        } else {

            //jezeli nie zwroci nulla to tworzymy obiekt
            User zalogowany = new User(userRepository.loginUser(userLogging));

            if (Objects.equals(userLogging.getType(), "Wlasciciel")) {

                //tutaj bedziemu tworzyc poszczegolne pbiekty
                return "widokHomeWlasciciela";

            } else if (Objects.equals(userLogging.getType(), "Kierownik")) {
                System.out.println("Loguje sie Kierownik");

                System.out.println(zalogowany);
                return "widokHomeKierownika";


            } else if (Objects.equals(userLogging.getType(), "Pracownik")) {
                System.out.println(zalogowany);
                return "widokHomePracownika";
            }

        }

        return "welcome";
    }


//        return "zalogowanyUser";

//    }


//    @GetMapping("/")
//    public String registerPage(){
//        return "stronaPowitalna";
//    }
//
//
//    //teraz metoda typu posta ktora pobierze input
//    //sprawdzamy który przycisk user
//    @PostMapping("/wyborLoginRegister")
//    public String logowanie(@RequestBody String wybor){
//        System.out.println(wybor);
//
//        if(wybor.equals("upvote=Logowanie")){
//            System.out.println("uzytkownik ma konto");
//            return "logowanie";
//            //tu w zależności od wyboru
//
//        }else if(wybor.equals("upvote=Rejestracja")){
//            System.out.println("rejestracja nowego uzytkownika");
//            return "rejestracja";
//        }
//
//        return null;
//
//    }


//    @PostMapping("/rejestracja")
//    public String register(@ModelAttribute User user, Model model) {
//        //ten model to jest interfejs przechowujacy dane
//        System.out.println(user.toString());
//        userRepository.addUserToDb(user);
//        //zwracamy template welcome, ktory znajduje sie w tempaltes
//        //wswietlimy z nim pierwsz eoraz drugie imie usera
//        model.addAttribute("firstname", user.getFirstName());
//        model.addAttribute("lastname", user.getLastName());
//        return "welcome";
//    }


}
