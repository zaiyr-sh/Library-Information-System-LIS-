package Tests;

import classes.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user = new User("Zaiyr", "Sharsheev", "zaiyr.00@gmail.com", "qwerty", "m", "+996222203068", "Pr. Aytmatov");

    @Test
    public void getFirstNameTest() {
        assertEquals("Zaiyr", user.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        user.setFirstName("Zaiyr");
        assertEquals("Zaiyr", user.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        assertEquals("Sharsheev", user.getLastName());
    }

    @Test
    public void setLastNameTest() {
        user.setLastName("Sharsheev");
        assertEquals("Sharsheev", user.getLastName());
    }

    @Test
    public void getUserNameTest() {
        assertEquals("zaiyr.00@gmail.com", user.getUserName());
    }

    @Test
    public void setUserNameTest() {
        user.setUserName("zaiyr.00@gmail.com");
        assertEquals("zaiyr.00@gmail.com", user.getUserName());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        user.setPassword("qwerty");
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void getGenderTest() {
        assertEquals("m", user.getGender());
    }

    @Test
    public void setGenderTest() {
        user.setGender("m");
        assertEquals("m", user.getGender());
    }

    @Test
    public void getPhoneNumberTest() {
        assertEquals("+996222203068", user.getPhoneNumber());
    }

    @Test
    public void setPhoneNumberTest() {
        user.setPhoneNumber("+996222203068");
        assertEquals("+996222203068", user.getPhoneNumber());
    }

    @Test
    public void getAddressTest() {
        assertEquals("Pr. Aytmatov", user.getAddress());
    }

    @Test
    public void setAddressTest() {
        user.setAddress("Pr. Aytmatov");
        assertEquals("Pr. Aytmatov", user.getAddress());
    }

    @Test
    public void toStringTest() {
        assertEquals("User{firstName='Zaiyr', lastName='Sharsheev', userName='zaiyr.00@gmail.com', password='qwerty', gender='m', phoneNumber='+996222203068', address='Pr. Aytmatov'}", user.toString());
    }
}