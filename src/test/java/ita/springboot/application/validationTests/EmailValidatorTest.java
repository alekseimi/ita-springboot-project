package ita.springboot.application.validationTests;

import ita.springboot.application.web.dto.UserRegistrationDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
public class EmailValidatorTest {

    private static Validator validator;

    private static UserRegistrationDto userRegistrationDto;

    //Password regex
    //^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$

    @BeforeClass
    public static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("firstName");
        userRegistrationDto.setLastName("lastName");
        //Password must be atleast 3 characters long and not contain whitespace
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setConfirmPassword("password");

    }

    @Test
    public void testWellFormedEmail_isValid(){
        userRegistrationDto.setEmail("janez.novak@gmail.com");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        Assert.assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testMalformedMailNoAt_isInvalid(){
        userRegistrationDto.setEmail("janez.novakgmail.com");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        Assert.assertEquals(1, constraintViolations.size());
    }


    @Test
    public void testMalformedMailNoDomain_isInvalid(){
        userRegistrationDto.setEmail("janez.novakgmail");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        Assert.assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testMalformedMailIsEmpty_isInvalid(){
        userRegistrationDto.setEmail("");
        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistrationDto);
        //Expected 2 because @Empty annotation in UserRegistrationDto
        Assert.assertEquals(2, constraintViolations.size());
    }

}
