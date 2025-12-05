package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Users table created");

        userService.saveUser("John", "Doe", (byte) 39);
        userService.saveUser("Martin", "Kim", (byte) 57);
        userService.saveUser("Marina", "Diamond", (byte) 28);
        userService.saveUser("Joshua", "Doe", (byte) 49);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println("Users table cleaned");
        userService.dropUsersTable();
        System.out.println("Users table dropped");
    }
}
