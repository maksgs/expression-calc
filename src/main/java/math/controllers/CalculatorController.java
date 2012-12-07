package math.controllers;

import java.security.Principal;
import java.util.Date;

import math.calculator.Calculator;
import math.configuration.Configuration;
import math.hibernate.HistoryMethods;
import math.hibernate.UserMethods;
import math.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {

	@Autowired
	Configuration config;
	
	HistoryMethods hitoryMethods = new HistoryMethods();
    UserMethods um = new UserMethods();

	@RequestMapping(value = "/calculateExpression", method = RequestMethod.GET)
	public @ResponseBody
	String getTime(@RequestParam String expression, Principal principal) {
		//does expression exist in history
		if (config.getExpressionLength() < expression.length()){
			expression = expression.substring(0, config.getExpressionLength());
		}	
		boolean b = hitoryMethods.doesExpExistInHistory(expression);
		String result = "";
		boolean validExp = false;
		History history = null;
		//if exist and caching is used then take result from DB
		if ( b && config.isCachingUsed()){
			try{
				history = hitoryMethods.getExpressionResult(expression);
				result = history.getResult();
				validExp = history.getIsValid();
			}catch (Exception e){
				result = "";
			}
		}else{
			Calculator calc = new Calculator();
			try {
				result = calc.calculateExpression(expression);
				validExp = true;
			} catch (Exception e) {
				result = calc.getCalculationStatus();
				
			}
		}
		//save or not result to db depends on boolean config.isSaveHistory
		if (config.isSaveHistory() && ( !b || config.isSaveDuplicatedExpToHistory())){
			if (history == null){
				history = new History();
				history.setExpression(expression);
				history.setIsValid(validExp);
				history.setResult(result);
				history.setDate(new Date());
                history.setUser(um.findByLogin(principal.getName()));
			}
			hitoryMethods.saveHistory(history);
		}
		return result;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView homepage(Principal principal) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("clearInputAfterCalculation",
				config.isClearInputAfterCalculation());
        mav.addObject("expressionLength", config.getExpressionLength());
		mav.addObject("historyAutofreshMil", config.getHistoryAutofreshMil());
		mav.addObject("historyLength", config.getHistoryLength());
        mav.addObject("userName",principal.getName());
		return mav;
	}
	
	@RequestMapping(value={"/getHistoryList"}, method=RequestMethod.GET)
    public @ResponseBody String getHistoryList(@RequestParam Integer valid, Principal principal){
        return hitoryMethods.getUserHistory(valid,um.findByLogin(principal.getName())).toString();
	}


}
