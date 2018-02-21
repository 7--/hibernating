package com.revature.hibernating;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.beans.Employee;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Session s = getSession();
		List<Employee> employeeList = s.createQuery("from " + Employee.class.getName()).list();
		s.close();
		System.out.println(employeeList.size() + Employee.class.getName());
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
	}

	private static SessionFactory sessionFactory(String filename) {
		Configuration config = new Configuration().configure(filename);
		ServiceRegistry serviceR = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		return config.buildSessionFactory(serviceR);
	}

	public static Session getSession() {
		return sessionFactory("hibernate.cfg.xml").openSession();
	}
}
