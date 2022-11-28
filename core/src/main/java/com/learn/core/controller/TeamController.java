package com.learn.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.core.entity.Employee;
import com.learn.core.entity.Team;
import com.learn.core.repository.TeamRepository;
import com.learn.core.service.EmployeeDataService;

@RestController
public class TeamController {
    @Autowired
    private EmployeeDataService service;

    @Autowired
    private TeamRepository teamRepository;

    @PostMapping("/team/save")
    public String saveTeamRecord(@RequestBody Team team) {
        return service.saveTeamRecord(team);
    }

    @GetMapping("/team/getAll")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/team/getAllEmployees")
    public List<Employee> getAllEmployees(@RequestParam int teamId) {
        return service.getAllEmployees(teamId);
    }
}
