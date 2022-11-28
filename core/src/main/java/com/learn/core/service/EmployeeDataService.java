package com.learn.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.core.entity.Employee;
import com.learn.core.entity.Team;
import com.learn.core.repository.EmployeeRepository;
import com.learn.core.repository.TeamRepository;

@Service
public class EmployeeDataService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Employee save(Employee employeeObj) {
        return employeeRepository.save(employeeObj);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public String saveTeamRecord(Team team) {
        // Team teamRecordStatus =
        // teamRepository.findById(team.getTeamId()).orElse(null);
        // if(teamRecordStatus != null)
        // return "Team record already exists, want me to just add the employees data?";
        // teamRepository.save(team);
        // for(Employee emp: team.getEmployees()) {
        // emp.setTeam(team);
        // employeeRepository.save(emp);
        // }
        // teamRepository.save(team);
        // employeeRepository.saveAll(team.getEmployees());


        // teamRepository.save(team);
        Team teamRecord = teamRepository.findById(team.getTeamId()).orElse(null);
        Team t;
        if (teamRecord != null) {
            return "Team record already exists, want me to just add the employees data?";
        } 
        List<Employee> emps = team.getEmployees();
        team.setEmployees(new ArrayList<Employee>());
        t = teamRepository.save(team);
        for (Employee emp : emps) {
            emp.setTeam(t);
            employeeRepository.save(emp);
        }

        /*
         * ACTUAL PROCEDURE:
         * Team teamRecord = teamRepository.findById(team.getTeamId()).orElse(null);
         * if(teamRecord != null) {
         * Team t = teamRepository.findById(team.getTeamId()).get();
         * Employee e = new Employee();
         * e.setEmpid(4321);
         * e.setName("naresh");
         * e.setEmail("naresh@gmail.com");
         * e.setTeam(t);
         * employeeRepository.save(e);
         * return "Employee record is added to existed team";
         * }
         * Team t = new Team();
         * t.setTeamId(0);
         * t.setName("Ops");
         * teamRepository.save(t);
         * Employee e = new Employee();
         * e.setEmpid(4321);
         * e.setName("naresh");
         * e.setEmail("naresh@gmail.com");
         * e.setTeam(t);
         * employeeRepository.save(e);
         * 
         * // team object (t)
         * System.out.println("Length is " + t.getEmployees().size());
         * for(Employee emp: t.getEmployees()) {
         * System.out.println(emp.getName() + " " + emp.getEmail());
         * }
         * 
         * List<Employee> empList = teamRepository.findById(0).get().getEmployees();
         * System.out.println("\nLength is " +
         * teamRepository.findById(0).orElse(null).getEmployees().size());
         * for(Employee emp: empList) {
         * System.out.println(emp.getName() + " " + emp.getEmail());
         * }
         */
        return "Team Record Saved Successfully!";
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Employee> getAllEmployees(int teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null)
            return null;
        for (Employee emp : team.getEmployees()) {
            System.out.println(emp.getName() + " " +
                    emp.getEmail() + " " +
                    emp.getTeam().getName() + " " +
                    emp.getTeam().toString());
        }
        System.out.println();
        // List<Employee> result = teamRepository.findById(teamId).get().getEmployees();
        return null;
    }
}
