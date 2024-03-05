package com.prowings.one_to_many_bi;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.prowings.dao.HibernateUtil;

public class TestOneToManyBidirectionalAssociation {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Account account1 = new Account();
		account1.setAccountName("Savings");
		account1.setAccountNumber(1111);

		Account account2 = new Account();
		account2.setAccountName("Dmat");
		account2.setAccountNumber(2222);

		Account account3 = new Account();
		account3.setAccountName("Salary");
		account3.setAccountNumber(3333);
		
		Set<Account> setOfAcc = new HashSet<Account>();
		setOfAcc.add(account1);
		setOfAcc.add(account2);
		setOfAcc.add(account3);
		
		Employee employee = new Employee();
		employee.setName("Ram");
		employee.setSalary(1234567);
		employee.setAccount(setOfAcc);
		
		session.persist(employee);
		
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();

		session2.delete(employee);
		
		transaction2.commit();
		sessionFactory.close();
		
		

	}

}
