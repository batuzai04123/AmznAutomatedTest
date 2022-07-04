package com.nat.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private ExtentReportManager() {}

    public static ExtentReports createReport() {
        String currentDateStr = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        String name = "Test Automation Report " + currentDateStr;
        String fileName = "build/reports/" + name + ".html".replace("/", File.separator);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        ExtentSparkReporterConfig config = sparkReporter.config();

        config.setTheme(Theme.STANDARD);
        config.setDocumentTitle(name);
        config.setEncoding("utf-8");
        config.setReportName(name);

        ExtentReports report = new ExtentReports();
        report.attachReporter(sparkReporter);

        return report;
    }
}
