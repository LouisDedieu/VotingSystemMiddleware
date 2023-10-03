package utils;

import model.User;

public class UsersList {
    private static final User[] users = {
            new User("123", "123"),
            new User("2018-00002-MN-1", "password1"),
            new User("2018-00003-MN-2", "password2")
    };

    public static User[] getUsers() {
        return users;
    }

    public boolean isValidUser(String studentNumber, String password) {
        for (User user : users) {
            if (user.getStudentNumber().equals(studentNumber) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
