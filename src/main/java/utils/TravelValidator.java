package utils;

import entities.Trip;
import entities.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class TravelValidator {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public Set<ConstraintViolation<Trip>> getViolationsFor(Trip t){
        return validator.validate(t);
    }

    public Set<ConstraintViolation<User>> getViolationsFor(User u){
        return validator.validate(u);
    }
}
