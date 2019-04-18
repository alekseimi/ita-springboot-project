package ita.springboot.application.validationTests;

import ita.springboot.application.validation.PasswordConstraintValidator;
import ita.springboot.application.validation.ValidPassword;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PasswordValidatorTest {

    @Mock
    PasswordConstraintValidator passwordConstraintValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setUp(){
        doCallRealMethod().when(passwordConstraintValidator).initialize(any());
        when(passwordConstraintValidator.isValid(any(), any())).thenCallRealMethod();

        PasswordConstraintValidatorClass testClass = new PasswordConstraintValidatorClass();
        passwordConstraintValidator.initialize(testClass);
    }

    @Test
    public void testPasswordValidation_isValid(){
        assertTrue(passwordConstraintValidator.isValid("123", constraintValidatorContext));

    }

    @Test
    public void testPasswordValidation_isInvalid_LengthConstraint(){
        assertFalse(passwordConstraintValidator.isValid("12", constraintValidatorContext));
    }

    @Test
    public void testPasswordValidation_isInvalid_WhiteSpaceConstraint(){
        assertFalse(passwordConstraintValidator.isValid("12 ", constraintValidatorContext));
    }

    @Test
    public void testPasswordValidation_isInvalid_WhiteSpaceConstraintAndLengthConstraint(){
        assertFalse(passwordConstraintValidator.isValid(" ", constraintValidatorContext));
    }

    private class PasswordConstraintValidatorClass implements ValidPassword {

        @Override
        public String message() {
            return "Test Message";
        }

        @Override
        public Class<?>[] groups() {
            return new Class[]{};
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[]{};
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return ValidPassword.class;
        }

    }

}
