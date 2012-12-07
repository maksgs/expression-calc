package math.controllers;

import math.Validators.RegistrarionValidator;
import math.hibernate.RoleMethods;
import math.hibernate.UserMethods;
import math.model.Role;
import math.model.User;
import org.springframework.beans.PropertyValues;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController
{
    private UserMethods um = new UserMethods();
    private RoleMethods rm = new RoleMethods();

//    public UserController(){
//        setCommandClass(User.class);
//
//        setCommandName("user");
//    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String regInit(ModelAndView mav){
//
      return "registration";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String addUser(@RequestParam String name,
                          @RequestParam String pwd1,
                          @RequestParam String pwd2
                        ){
//        RegistrarionValidator rv = new RegistrarionValidator();
//        User user = new User();
//        DataBinder binder = new DataBinder(user);
//        binder.setDisallowedFields(new String[]{"id", "role", "date", "enabled"});
//        binder.setValidator(rv);
//        binder.validate();
//        BindingResult result =  binder.getBindingResult();
//        binder.bind(PropertyValues);
//       if(result.hasErrors()){
//            return "registration";
//        }else{
        User usr = new User();
            usr.setName(name);
            usr.setPassword(pwd1);
            usr.setEnabled(true);
            usr.setDate();
            usr.setRole(rm.getUserRole());
            um.saveUser(usr);
            return "redirect:/login.html";
    //}

    }

    /*@InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        dataBinder.setDisallowedFields(new String[] {"id"});

        dataBinder.setRequiredFields(new String[] {"name", "area", "population", "currency"});

        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }       */
}
