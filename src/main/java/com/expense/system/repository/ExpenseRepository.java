package com.expense.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expense.system.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
//	@Query(
//		value = "SELECT * FROM USERS u WHERE u.status = 1", 
//		nativeQuery = true
//	)
//	List<Expense> search();
//	@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//	List<Expense> groupBy(Integer status, String name);
//	@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//	List<Expense> groupBy();
}
