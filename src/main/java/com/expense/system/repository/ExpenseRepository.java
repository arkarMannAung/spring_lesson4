package com.expense.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.system.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
}
