package com.expense.system.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.expense.system.entity.Expense;
import com.expense.system.form.ExpenseByMonth;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}