package com.expense.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.ui.Model;

import com.expense.system.entity.Expense;
import com.expense.system.form.ExpenseByMonth;
import com.expense.system.repository.ExpenseRepository;

@Service
public class ExpenseSystemService {
	@Autowired
	ExpenseRepository er;

	public List<ExpenseByMonth> index() {
		List<Expense> expenses = er.findAll();
		Map<String,List<Expense>> expenseByMonth = new HashMap<>();
		for(int x=0;x<expenses.size();x++) {
			Expense current = expenses.get(x);
			String yearMonth = (current.getDate().toString()).substring(0,7);
			if(expenseByMonth.containsKey(yearMonth)) {
				List<Expense> oldExpense = expenseByMonth.get(yearMonth);
				oldExpense.add(current);
				expenseByMonth.put(yearMonth, oldExpense);
			}else {
				List<Expense> newArrayList = new ArrayList<>();
				newArrayList.add(current);
				expenseByMonth.put(yearMonth, newArrayList);
			}
		}
		List<ExpenseByMonth> expenseByMonths = new ArrayList<>();
		
		for (String key : expenseByMonth.keySet()) {
			String year = key.substring(0,4);
			String month = key.substring(5);
	        
	        int total = 0;
	        for(Expense exp: expenseByMonth.get(key)) {
	        	total += exp.getExpense();
	        }
	        
	        ExpenseByMonth byMonth = new ExpenseByMonth();
	        byMonth.setYear(year);
	        byMonth.setMonth(month);
	        byMonth.setTotal(total);
	        expenseByMonths.add(byMonth);
	    }
		return expenseByMonths;
	}
	
	public void filterByYearMonth(Model model,String year,String month) {
		List<Expense> expenses = er.findAll();
		List<Expense> filteredItems = new ArrayList<Expense>();
		int total = 0;
		for(int x=0;x<expenses.size();x++) {
			Expense current = expenses.get(x);
			String yearMonth = (current.getDate().toString()).substring(0,7);
			String cu_year = yearMonth.substring(0,4);
			String cu_month = yearMonth.substring(5);
			if(year.equals(cu_year) && month.equals(cu_month)) {
				total += current.getExpense();
				filteredItems.add(current);
			}
		}
		model.addAttribute("monthlyExpense", filteredItems);
		model.addAttribute("total", total);
	}
	
	public void addExpense(Expense expense) {
		try {
			this.er.save(expense);
		}catch( Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	public Expense getUpdateExpense(int id) {
		@SuppressWarnings("deprecation")
		Expense toUpdateExpense = er.getById(id);
		return toUpdateExpense;
	}
	
	public void updateExpense(Expense expense) {
		try {
			this.er.save(expense);
		}catch( Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}
}