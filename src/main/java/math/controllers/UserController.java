package math.controllers;

import math.Validators.RegistrarionValidator;
import math.hibernate.RoleMethods;
import math.hibernate.UserMethods;
import math.model.Role;
import math.model.User;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController
{
    private UserMethods um = new UserMethods();
    private RoleMethods rm = new RoleMethods();

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView regInit(ModelAndView mav){
    mav.addObject("user", new User());
      return mav;
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String addUser(   User user,
                            BindingResult result){
        RegistrarionValidator rv = new RegistrarionValidator();
        rv.validate(user,result);
        if(result.hasErrors()){
            return "registration";
        }
        else{
        User usr = new User();
            usr.setName(user.getName());
            usr.setPassword(user.getPassword());
            usr.setEnabled(true);
            usr.setDate();
            usr.setRole(rm.getUserRole());
            um.saveUser(usr);
            return "redirect:/login.html";
        }
    }

}
