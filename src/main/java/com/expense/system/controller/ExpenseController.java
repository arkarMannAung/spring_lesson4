package com.expense.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expense.system.entity.Expense;
import com.expense.system.repository.ExpenseRepository;

@Controller
public class ExpenseController {
	@Autowired
	ExpenseRepository er;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
//		Expense ep = new Expense();
		List<Expense> expenses = er.findAll();
		model.addAttribute("expenseList", er.findAll());
		int total = 0;
		for(int x=0; x<expenses.size();x++  ) {
			total += expenses.get(x).getExpense();
		}
		model.addAttribute("total", total);
		return "components/home";
	}
}
