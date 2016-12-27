package com.kuiper.report.bo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kuiper.report.constants.Constant;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ReportFactoryBO {
	final static Logger logger = Logger.getLogger(ReportFactoryBO.class);

	public static String process(String report, Collection<?> beanCollection) throws FileNotFoundException {

		JasperDesign jasperDesign;
		JasperReport jasperReport;
		Map<String, Object> parameters;
		JasperPrint jasperPrint;

		try {

			String path = "/Kuiper/Source/KuiperReport/target/KuiperReport-0.0.1-SNAPSHOT/WEB-INF/";
			String RptDest = Constant.WTP_DEPLOY + "/KuiperReport/Reports";
			
		//	C:\Kuiper\Source\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps - wtp.deploy set in tomcat server settings
			
			jasperDesign = JRXmlLoader.load(path + "classes/"+report+".jrxml");

			JRDataSource datasource = new JRBeanCollectionDataSource(beanCollection);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			parameters = new HashMap<String, Object>();

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);

			File outDir = new File(RptDest);
			outDir.mkdirs();
				logger.info("PATH :"+outDir.getAbsolutePath());
				

			JasperExportManager.exportReportToHtmlFile(jasperPrint, RptDest + "/"+report+".html");
			JasperExportManager.exportReportToPdfFile(jasperPrint, RptDest + "/"+report+".pdf");
			
	        JRXlsExporter xlsExporter = new JRXlsExporter();

	        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(RptDest + "/"+report+".xls"));
	        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
	        xlsReportConfiguration.setOnePagePerSheet(false);
	        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
	        xlsReportConfiguration.setDetectCellType(false);
	        xlsReportConfiguration.setWhitePageBackground(false);
	        xlsExporter.setConfiguration(xlsReportConfiguration);

	        xlsExporter.exportReport();
	        

	        logger.info(report+"Generated successfully !!!");
		} catch (JRException e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
		}

		return null;
	}

}
