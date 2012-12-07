package math.controllers;

import math.calculator.Calculator;
import math.configuration.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigurationController {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	Configuration config;

	@RequestMapping(value = "/saveConfiguration", method = RequestMethod.GET)
	public @ResponseBody String saveConfiguration(@RequestParam Integer expressionLength,
			@RequestParam Integer historyLength,
			@RequestParam Integer historyAutofreshMil,
			@RequestParam Boolean saveHistory,
			@RequestParam Boolean clearInputAfterCalculation,
			@RequestParam Boolean isCachingUsed,
			@RequestParam Boolean saveDuplicatedExpToHistory) {
		String result = "";
		config.setClearInputAfterCalculation(clearInputAfterCalculation);
		config.setExpressionLength(expressionLength);
		config.setHistoryAutofreshMil(historyAutofreshMil);
		config.setHistoryLength(historyLength);
		config.setSaveHistory(saveHistory);
		config.setCachingUsed(isCachingUsed);
		config.setSaveDuplicatedExpToHistory(saveDuplicatedExpToHistory);
		return result;
	}
	
	@RequestMapping("/configuration")
    public ModelAndView configuration() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("clearInputAfterCalculation", config.isClearInputAfterCalculation());
        mav.addObject("expressionLength", config.getExpressionLength());
        mav.addObject("historyAutofreshMil", config.getHistoryAutofreshMil());
        mav.addObject("historyLength", config.getHistoryLength());
        mav.addObject("saveHistory", config.isSaveHistory());
        mav.addObject("isCachingUsed", config.isCachingUsed());
        mav.addObject("saveDuplicatedExpToHistory", config.isSaveDuplicatedExpToHistory());
        return mav;
    }
}
