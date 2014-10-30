package edu.jhu.cvrg;

import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.jhu.cvrg.annotations.processors.AnnotationsProcessor;
import edu.jhu.cvrg.annotations.processors.MuseAnnotationsProcessor;
import edu.jhu.cvrg.annotations.processors.Philips103AnnotationsProcessor;
import edu.jhu.cvrg.annotations.processors.Philips104AnnotationsProcessor;
import edu.jhu.cvrg.annotations.processors.SchillerAnnotationsProcessor;
import edu.jhu.icm.ecgFormatConverter.ECGformatConverter;
import edu.jhu.icm.ecgFormatConverter.ECGformatConverter.fileFormat;

public class AppTest extends TestCase{

	private Logger log = Logger.getLogger(this.getClass());
	protected static String TEST_FILE_PATH = "/opt/liferay/mavenTestResources/annotations/"; 
	private static String OUTPUT_PATH = TEST_FILE_PATH + "out";
	
	@Test
	public void testMuseExtraction(){
		
		
		String inputPath = TEST_FILE_PATH + "muse/";
		String fileName = "1304_54QC_waveforms.xml";
		
		ECGformatConverter.fileFormat inputFormat = ECGformatConverter.fileFormat.MUSEXML;
		ECGformatConverter.fileFormat outputFormat = ECGformatConverter.fileFormat.WFDB;
		
		dataConversion(OUTPUT_PATH, inputPath, fileName, inputFormat,outputFormat);
	}
	
	@Test
	public void testSchillerExtraction(){
		
		String inputPath = TEST_FILE_PATH + "schiller/";
		String fileName = "APACE2.xml";
		
		ECGformatConverter.fileFormat inputFormat = ECGformatConverter.fileFormat.SCHILLER;
		ECGformatConverter.fileFormat outputFormat = ECGformatConverter.fileFormat.WFDB;
		
		dataConversion(OUTPUT_PATH, inputPath, fileName, inputFormat,outputFormat);
	}

	@Test
	public void testPhilips103Extraction(){
		
		String inputPath = TEST_FILE_PATH + "philips103/";
		String fileName = "ecg_97082525_1.xml";
		
		ECGformatConverter.fileFormat inputFormat = ECGformatConverter.fileFormat.PHILIPS103;
		ECGformatConverter.fileFormat outputFormat = ECGformatConverter.fileFormat.WFDB;
		
		dataConversion(OUTPUT_PATH, inputPath, fileName, inputFormat,outputFormat);
	}
	
	@Test
	public void testPhilips104Extraction(){
		
		String inputPath = TEST_FILE_PATH + "philips104/";
		String fileName = "ecg_97086579_1.xml";
		
		ECGformatConverter.fileFormat inputFormat = ECGformatConverter.fileFormat.PHILIPS104;
		ECGformatConverter.fileFormat outputFormat = ECGformatConverter.fileFormat.WFDB;
		
		dataConversion(OUTPUT_PATH, inputPath, fileName, inputFormat,outputFormat);
	}
	
	
	
	
	private void dataConversion(String outputPath, String inputPath, String fileName, ECGformatConverter.fileFormat inputFormat, ECGformatConverter.fileFormat outputFormat) {
		
		String errorMessage;
		ECGformatConverter conv = new ECGformatConverter();
		log.info(fileName + " this is the file sent to the converter ECGformatConverter()");
		String recordName = fileName.substring(0, fileName.lastIndexOf(".")); // trim off the extension
		
		long docId = 0;
		long userId = 0;
		
		try{
			
			boolean ret = conv.read(inputFormat, fileName, 0, inputPath, recordName);
			log.info("File read returned: " + ret);
			
			if(!ret){
				errorMessage = "Error: On File read.";
				log.error(errorMessage);
			}
			
			log.info(conv.getSamplingRate() + " this is the sampling rate returned.");
			log.info(conv.getChannelCount() + " this is the channel count returned.");
			log.info(conv.getNumberOfPoints() + " this is the Number of points returned.");

			
			errorMessage = String.valueOf(docId);
			
			long writeTime = java.lang.System.currentTimeMillis();
			
			int rowsWritten;
			
			rowsWritten = conv.write(outputFormat, outputPath, recordName);
			
			log.info("rowsWritten: " + rowsWritten);
			log.info(" +++++ Conversion completed successfully, results will be transfered.");
			writeTime = java.lang.System.currentTimeMillis() - writeTime;
			
			log.info("["+docId+"]The runtime for writing the new file is = " + writeTime + " milliseconds");
			
			long  annotationTime = java.lang.System.currentTimeMillis();
			
			Map<String, String> nonLeadList = null;
			Map<Integer, Map<String, String>> leadList = null;
			AnnotationsProcessor processor = null;
			
			// Now do annotations from Muse or Philips files
			if(fileFormat.PHILIPS103.equals(inputFormat)) {
				
				org.sierraecg.schema.Restingecgdata ecgData = (org.sierraecg.schema.Restingecgdata) conv.getPhilipsRestingecgdata();
				processor = new Philips103AnnotationsProcessor(ecgData);
			
			}else if(fileFormat.PHILIPS104.equals(inputFormat)) {
				
				org.cvrgrid.philips.jaxb.beans.Restingecgdata ecgData = (org.cvrgrid.philips.jaxb.beans.Restingecgdata) conv.getPhilipsRestingecgdata();
				processor = new Philips104AnnotationsProcessor(ecgData);
				
			}else if(fileFormat.SCHILLER.equals(inputFormat)) {
				                                               
				org.cvrgrid.schiller.jaxb.beans.ComXiriuzSemaXmlSchillerEDISchillerEDI ecgData = (org.cvrgrid.schiller.jaxb.beans.ComXiriuzSemaXmlSchillerEDISchillerEDI) conv.getComXiriuzSemaXmlSchillerEDISchillerEDI();
				processor = new SchillerAnnotationsProcessor(ecgData);
				
			}else if(fileFormat.MUSEXML.equals(inputFormat)) {
				String rawMuseXML = conv.getMuseRawXML();
				if(rawMuseXML != null) {
					processor = new MuseAnnotationsProcessor(rawMuseXML, docId, userId);
				}
			}
			
			
			if(processor != null){
				processor.processAll();
				
				nonLeadList = processor.getGlobalAnnotations();
				leadList = processor.getLeadAnnotations();
				
				System.out.println("GLOBAL ANNOTATIONS");
				for (String key : nonLeadList.keySet()) {
					System.out.println("Key [" + key +"] Value ["+ nonLeadList.get(key)+ "]");
				}
				System.out.println();
				System.out.println("LEAD ANNOTATIONS");
				
				for (Integer key : leadList.keySet()) {
					System.out.println("LEAD ("+key+") ANNOTATIONS");
					Map<String, String> lead = leadList.get(key);
					for (String keyLead : lead.keySet()) {
						System.out.println("Key [" + keyLead +"] Value ["+ lead.get(keyLead)+ "]");
					}
				}

			}
			
			long anotationTotalTime = java.lang.System.currentTimeMillis() - annotationTime;
			
			log.info("["+docId+"]The runtime to process the annotations is = " + anotationTotalTime + " milliseconds");
			
			
			assertTrue(nonLeadList != null && nonLeadList.size() > 0 && leadList != null && leadList.size() > 0);
			
		} catch (Exception ex) {
			errorMessage = "Error: " + ex.toString();
			log.error(errorMessage);
			ex.printStackTrace();
		}
	}
}
