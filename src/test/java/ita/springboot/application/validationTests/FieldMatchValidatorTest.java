package ita.springboot.application.validationTests;


import ita.springboot.application.web.dto.UserRegistrationDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class FieldMatchValidatorTest {

    private static Validator validator;

    private static UserRegistrationDto userRegistrationDto;

    @BeforeClass
    public static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("firstName");
        userRegistrationDto.setLastName("lastName");
        userRegistrationDto.setEmail("name.lastName@email.com");
    }

    @Test
    public void testIdenticalPasswords_isValid(){
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setConfirmPassword("password");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        Assert.assertEquals(constraintViolations.size(), 0);
    }

    @Test
    public void testIdenticalPasswords_isInvalid(){
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setConfirmPassword("malformed_password");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        Assert.assertEquals(constraintViolations.size(), 1);
    }
}
