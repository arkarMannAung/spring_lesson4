package com.expense.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.system.entity.Expense;
import com.expense.system.service.ExpenseSystemService;

import jakarta.validation.Valid;

@Controller
public class ExpenseController {
	@Autowired(required = true)
	ExpenseSystemService expenseService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("monthlyExpense", this.expenseService.index());
		return "components/home";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam("year") String year, @RequestParam("month") String month) {

		this.expenseService.filterByYearMonth(model, year, month);
		return "components/montly_detail";
	}

	@RequestMapping(value = "/add_expense", method = RequestMethod.GET)
	public String addExpense(Model model) {
		model.addAttribute("form", new Expense());
		return "components/add_expense";
	}

//	@RequestMapping(value = "/add_expense", method = RequestMethod.POST)
//	public String addExpenseConfirm(Model model, @ModelAttribute("form") Expense expense) {
//		this.expenseService.addExpense(expense);
//		return "redirect:/";
//	}
//	
	@PostMapping("/add_expense")
	public String addExpense(@Valid Expense expense, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("form", new Expense());
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				if (error.getField().equals("title")) {
					model.addAttribute("title_error", "title cannot be empty.");
				}
				if (error.getField().equals("date")) {
					model.addAttribute("date_error", "date cannot be empty.");
				}
				if (error.getField().equals("expense")) {
					model.addAttribute("expense_error", "expense cannot be empty.");
				}
			}
			model.addAttribute("form", expense);
			return "components/add_expense";
		} else if (expense.getExpense() < 1) {
			model.addAttribute("expense_error", "expense should be more then 0.");
			model.addAttribute("form", expense);
			return "components/add_expense";
		}
		this.expenseService.addExpense(expense);
		return "redirect:/";
	}

	@RequestMapping(value = "/update_expense", method = RequestMethod.GET)
	public String updateExpense(Model model, @RequestParam("id") int id) {
		model.addAttribute("form", this.expenseService.getUpdateExpense(id));
		return "components/update_expense";
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/update_expense")
	public String updateExpense(@Valid Expense expense, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("form", new Expense());
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				if (error.getField().equals("title")) {
					model.addAttribute("title_error", "title cannot be empty.");
				}
				if (error.getField().equals("date")) {
					model.addAttribute("date_error", "date cannot be empty.");
				}
				if (error.getField().equals("expense")) {
					model.addAttribute("expense_error", "expense cannot be empty.");
				}
			}
			model.addAttribute("form", expense);
			return "components/add_expense";
		} else if (expense.getExpense() < 1) {
			model.addAttribute("expense_error", "expense should be more then 0.");
			model.addAttribute("form", expense);
			return "components/add_expense";
		}
		this.expenseService.updateExpense(expense);
				
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yearMonth = (sdf.format(expense.getDate()).toString()).substring(0,7);
		String cu_year = yearMonth.substring(0,4);
		String cu_month = yearMonth.substring(5);
		System.out.print(sdf.format(expense.getDate()).toString());
		return "redirect:/detail?year="+cu_year+"&month="+cu_month;
	}
}