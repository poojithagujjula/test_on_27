package com.ems.EMS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ems.EMS.model.Address;
import com.ems.EMS.model.Department;
import com.ems.EMS.model.Employee;
import com.ems.EMS.model.HR;
import com.ems.EMS.model.Payroll;
import com.ems.EMS.model.Performance;

@Configuration
@ComponentScan(basePackages="com.ems.EMS.*")
@PropertySource("classpath:application.properties")
public class EmsAppConfig {

	//Address Bean
/*	public Address address() {
		Address address = new Address();
		address.setStreet("Pratap nagar");
		address.setCity("Jaipur");
		address.setState("Rajasthan");
		address.setZipcode("501033");

		return address;
	}
	@Bean
	public Department department() {
		
		Department department = new Department();
		department.setDeptId(2);
		department.setDeptName("Operations");
		return department;
	}
	
	//skills list
	@Bean
	public List<String>skills(){
		return Arrays.asList("Java", "MySQL","SpringFramework");
	}
	
	@Bean
	public Employee employee() {
		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("Poojitha");
		employee.setEmail("poojitha@gmail.com");
		employee.setPhone("123456789");
		employee.setDesignation("Java Associate");

		employee.setAddress(address());
		employee.setDepartment(department());
		employee.setSkills(skills());
		return employee;
	}
	
	//per,pay
	@Bean
	public Payroll payroll() {
		Payroll payroll = new Payroll();
		payroll.setBaseSalary(25000);
		return payroll;
	}
	@Bean
	public Performance performance() {
		Performance performance = new Performance();
		performance.setEmployeeId(0);
		performance.setRating(4.5);
		return performance;
	}
	
	
	//hr
	public Map<Integer, Employee> empRecords(){
		Map<Integer, Employee> records = new HashMap<>();
		records.put(1,  employee());
		return records;
	}
	@Bean
	public HR hr() {
		
		HR hr = new HR();
		hr.setEmployeeRecords(empRecords());
		return hr;
	}
 
 */

	@Value("${address.street}")
	private String street;
	@Value("${address.city}")
	private String city;
	@Value("${address.state}")
	private String state;
	@Value("${address.zipcode}")
	private String zipcode;
	
	@Bean
	public Address address() {
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZipcode(zipcode);
		return address;
	}
	
	//Department Bean
	@Value("${department.id}")
	private int deptId;
	@Value("${department.name}")
	private String deptName;
	 
	@Bean
	public Department department() {
		Department department = new Department();
		department.setDeptId(deptId);
		return department;
	}
	 
	@Value("${employee.skills}")
	private String skillsString;
	 
	@Bean
	public List<String> skills(){
		return Arrays.asList(skillsString.split(","));
	}
	 
	//employee bean
	@Value("${employee.id}")
	private int empId;
	@Value("${employee.name}")
	private String empName;
	@Value("${employee.email}")
	private String email;
	@Value("${employee.phone}")
	private String phone;
	@Value("${employee.salary}")
	private double salary;
	@Value("${employee.designation}")
	private String designation;

	@Bean
	public Employee employee() {
		Employee employee = new Employee();
		employee.setId(empId);
		employee.setName(empName);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setSalary(salary);
		employee.setDesignation(designation);
		employee.setAddress(address());
		employee.setDepartment(department());
		employee.setSkills(skills());
		return employee;
	
	}
	
	@Value("${performance.employeeId}")

	private int employeeId;

	@Value("${performance.rating}")

	private double rating;

	@Value("${performance.feedback}")

	private String feedback;

	@Value("${performance.eligibleForPromotion}")

	private boolean eligibleForPromotion;
	
	@Value("${performace.projectsHandled}")
	private String projectsHandled;
	 
	@Bean
	public List<String> projectsHandled(){
		return Arrays.asList(projectsHandled.split(","));
	}

	@Bean

	public Performance performance() {

		Performance performance = new Performance();

		performance.setEmployeeId(employeeId);

	    performance.setRating(rating);

		performance.setFeedback(feedback);

		return performance;

	}
	
	@Bean
	public Map<Integer,Employee> empRecords(){
		Map<Integer, Employee> records = new HashMap<>();
		records.put(1, employee());
		return records;
	}
	@Bean
	public HR hr() {
		HR hr=new HR();
		hr.setEmployeeRecords(empRecords());;
		return hr;
	}
	// Payroll bean
    @Value("${payroll.baseSalary}")
    private double baseSalary;
    @Value("${payroll.bonuses}")
    private double bonuses;
    @Value("${payroll.deduction}")
    private double deduction; 
    @Bean
    public Payroll payroll() {
        Payroll payroll = new Payroll();
        payroll.setBaseSalary(baseSalary);
        payroll.setBonuses(bonuses);
        payroll.setDeductions(deduction);
        return payroll;
    }
 
	
}