package com.kuiper.report.servlet;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public class ItemReportSample {
	 
	public static void main(String[] args) throws JRException, IOException {	}
//		try {
//	//		 ReportFactoryBO.process();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void test(){
//		//JasperDesign jasperDesign = JRXmlLoader.load("C:\\Kuiper\\Source\\KuiperReport\\src\\com\\kuiper\\report\\reports\\report1.jrxml");
//		JasperDesign jasperDesign = JRXmlLoader.load("C:\\Kuiper\\Source\\KuiperReport\\target\\KuiperReport-0.0.1-SNAPSHOT\\WEB-INF\\classes\\ItemReport.jrxml");
//		JsonDataSource jsondatassource = new JsonDataSource(new File("C:\\Kuiper\\Source\\KuiperReport\\target\\KuiperReport-0.0.1-SNAPSHOT\\WEB-INF\\classes\\test.json"));
//		// Compile jrxml file.
//	    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//	
//	    // Parameters for report
//	    Map<String, Object> parameters = new HashMap<String, Object>();
//	
//	    // DataSource
//	    // This is simple example, no database.
//	    // then using empty datasource.
//	    JRDataSource dataSource = new JREmptyDataSource();
//	
//	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
//	            parameters, jsondatassource);
//	
//	 
//	    // Make sure the output directory exists.
//	    File outDir = new File("C:/jasperoutput");
//	    outDir.mkdirs();
//	
//	    // Export to PDF.
//	    JasperExportManager.exportReportToPdfFile(jasperPrint,
//	            "C:/jasperoutput/StyledTextReport.pdf");
//	    JasperExportManager.exportReportToHtmlFile(jasperPrint,
//	            "C:/jasperoutput/StyledTextReport.html");
//	    System.out.println("Done!");
//	}
}
