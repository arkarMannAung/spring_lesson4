package com.expense.system.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expense.system.entity.Expense;
import com.expense.system.repository.ExpenseRepository;

@Controller
public class ExpenseController {
//	@Autowired
//	ExpenseRepository er;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
//		Expense ep = new Expense();
//		ep.setDate(new Date());
//		ep.setTitle("eat");
//		ep.setExpense(1500);
//		er.save(ep);
		return "index";
	}
}
