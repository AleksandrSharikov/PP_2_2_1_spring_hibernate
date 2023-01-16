package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru","model5",12345));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru","model6",123456));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());

         if(user.getCar()!=null) {
            System.out.println();
            System.out.println("Cad_id = "+ user.getCar().getId());
            System.out.println("User's car = "+user.getCar().getModel());
            System.out.println("User's car's seies = "+user.getCar().getSeries());
         }
         System.out.println();
      }

      User user = userService.getUserByCar("model5",  12345);

         System.out.println("======================================================================================");

         System.out.println("Car User");
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
         System.out.println("Cad_id = "+ user.getCar().getId());

            System.out.println("User's car = "+user.getCar().getModel());
            System.out.println("User's car's seies = "+user.getCar().getSeries());

         System.out.println();


      context.close();
   }
}
