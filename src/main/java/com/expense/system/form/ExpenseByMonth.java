package com.expense.system.form;

import java.util.Date;

import lombok.Data;

@Data
public class ExpenseByMonth {
	String year;
	String month;
	int total;
}