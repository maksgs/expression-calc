package math.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import math.model.User;
import math.hibernate.UserMethods;

public class RegistrarionValidator implements Validator
{
    UserMethods um = new UserMethods();
    @Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
//        ValidationUtils.rejectIfEmpty(errors,"password","required.password");
        ValidationUtils.rejectIfEmpty(errors,"confirmPassword","required.confirmPassword");

        User user = (User) target;
        if(!(user.getPassword().equals(user.getConfirmPassword()))){
            errors.rejectValue("password","notmatch.password");
        }else {
            if(user.getPassword().length()<3){errors.rejectValue("password","length.password");}
        }
        if (user.getName().length()<3){errors.rejectValue("name","length.name");}
        if(!errors.hasFieldErrors("name")){
            User u = um.findByLogin(user.getName());
            if(u!=null && (user.getId() != u.getId())){errors.rejectValue("name","exist.name");}
        }

    }
}
