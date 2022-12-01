package com.example.zakladmechanicznyspringboot.service;

import com.example.zakladmechanicznyspringboot.controller.UserRepository;
import com.example.zakladmechanicznyspringboot.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service

public class ReportService {

    @Autowired
    private UserRepository repository;

    public String exportReport(String reportFormat) throws JRException {
        String path="C:\\Users\\micha\\Desktop";
        List<User> pracownicy = null; repository.getAll();
        //wczytanie pliku i skompilowanie go
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath.pracownicy.jrxml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JasperReport jasperReport;
        try {
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pracownicy);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, (Map<String, Object>) dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\pracownicy.html");

        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"pracownicy.pdf");
        }
        return "Generator raportu w sciezce : "+path;

    }


}
