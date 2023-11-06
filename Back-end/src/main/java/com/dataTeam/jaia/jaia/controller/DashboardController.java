package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.DTO.DashboardDTO;
import com.dataTeam.jaia.jaia.service.Dashboard.DashboardService;

@RestController
@RequestMapping(value = "/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/os-departamento")
    public DashboardDTO hangleGetOsByDepartment() {
        return dashboardService.getOsByDepartment();
    }

    @GetMapping("/os-status")
    public DashboardDTO hangleGetOsByStatus() {
        return dashboardService.getOsByStatus();
    }

    @GetMapping("/os-reprovada")
    public DashboardDTO hangleGetOsReprovedByDepartment() {
        return dashboardService.getOsReprovedByDepartment();
    }

    @GetMapping("/os-time")
    public DashboardDTO hangleGetOsByAllTime() {
        return dashboardService.getReqByMonths();
    }
}
