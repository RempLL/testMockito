package com.example.testmockito;

import com.example.testmockito.Exception.IncorrectNameException;
import com.example.testmockito.Exception.IncorrectSurnameException;
import com.example.testmockito.Service.ValidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateServiceTest {
    private final ValidateService validateService = new ValidateService();

    @Test
    public void validateNameTest() {
        String actual = validateService.validateName("Петя");
        String expected = "Петя";

        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void validateSurnameTest() {
        String actual = validateService.validateSurname("Ника");
        String expected = "Ника";

        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void validateNameNegativeTest(){
        Assertions.assertThrows(IncorrectNameException.class,() -> validateService.validateName("Ника2"));
        Assertions.assertThrows(IncorrectNameException.class,() -> validateService.validateName("Никаs-"));
    }

    @Test
    public void validateSurnameNegativeTest(){
        Assertions.assertThrows(IncorrectSurnameException.class,() -> validateService.validateSurname("Чивапч1чис"));
        Assertions.assertThrows(IncorrectSurnameException.class,() -> validateService.validateSurname("Ник-ла"));
    }
}
