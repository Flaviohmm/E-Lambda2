package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		List<Employee> listEmp = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = scanner.nextLine();

		try (BufferedReader buffReader = new BufferedReader(new FileReader(path))) {
			String line = buffReader.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				listEmp.add(new Employee(fields[0], fields[1], Double.valueOf(fields[2])));
				line = buffReader.readLine();
			}
			
			System.out.print("Enter Salary: ");
			double salary = scanner.nextDouble();
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> email = listEmp.stream()
					.filter(p -> p.getPrice() > salary)
					.map(p -> p.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			email.forEach(System.out::println);
			
			Double price = listEmp.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getPrice())
					.reduce(0.0, (x,y) -> x + y);
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", price));
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		scanner.close();
	}

}
