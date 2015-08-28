package edu.jhu.cvrg;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
	private static Map<Integer, String> PER_LEAD_MEASUREMENT;
	private static Map<Integer, String> PER_LEAD_MEASUREMENT_BITMASK;
	
	static{
		PER_LEAD_MEASUREMENT = new LinkedHashMap<Integer, String>();
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(1), "P Wave amplitude at P-onset"); //PONA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(2), "P wave amplitude"); //PAMP 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(3), "P wave duration"); //PDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(4), "P wave area"); //bmPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(5), "P wave intrinsicoid (time from P onset to peak of P)"); //bmPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(6), "P Prime amplitude"); //P'AMP 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(7), "P Prime duration"); //P'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(8), "P Prime area"); //bmPPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(9), "P Prime intrinsicoid (time from P onset to peak of P')"); //bmPPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(10), "Q wave amplitude"); //QAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(11), "Q wave duration"); //QDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(12), "Q wave area"); //bmQAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(13), "Q intrinsicoid (time from Q onset to peak of Q)"); //bmQI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(14), "R amplitude"); //RAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(15), "R duration"); //RDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(16), "R wave area"); //bmRAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(17), "R intrinsicoid (time from R onset to peak of R)"); //bmRI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(18), "S amplitude"); //SAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(19), "S duration"); //SDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(20), "S wave area"); //bmSAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(21), "S intrinsicoid (time from Q onset to peak of S)"); //bmSI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(22), "R Prime amplitude"); //R'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(23), "R Prime duration"); //R'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(24), "R Prime wave area"); //bmRPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(25), "R Prime intrinsicoid (time from Q onset to peak of R Prime)"); //bmRPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(26), "S Prime Amplitude"); //S'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(27), "S Prime Duration"); //S'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(28), "S Prime wave area"); //bmSPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(29), "S intriniscoid (time from Q onset to peak of S prime)"); //bmSPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(30), "STJ point, End of QRS Point Amplitude"); //STJ
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(31), "STM point, Middle of the ST Segment Amplitude"); //STM 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(32), "STE point, End of ST Segment Amplitude"); //STE 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(33), "Maximum of STJ, STM, STE Amplitudes"); //MXSTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(34), "Minimum of STJ and STM Amplitudes"); //MNSTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(35), "Special T-Wave amplitude"); //SPTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(36), "Total QRS area"); //QRSA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(37), "QRS Deflection"); //QRSDEF 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(38), "Maximum R Amplitude (R or R Prime)"); //MAXRA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(39), "Maximum S Amplitude (S or S Prime)"); //MAXSA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(40), "T amplitude"); //TAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(41), "T duration"); //TDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(42), "T wave area"); //bmTAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(43), "T intriniscoid (time from STE to peak of T)"); //bmTI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(44), "T Prime amplitude"); //T'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(45), "T Prime duration"); //TPDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(46), "T Prime area"); //bmTPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(47), "T Prime intriniscoid (time from STE to peak of T)"); //bmTPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(48), "T Amplitude at T offset"); //TEND
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(49), "P wave area, includes P and P Prime"); //PAREA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(50), "QRS area"); //QRSAR
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(51), "T wave area, include T and T Prime"); //TAREA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(52), "QRS intriniscoid (see below)"); //QRSINT
		
		PER_LEAD_MEASUREMENT_BITMASK = new TreeMap<Integer, String>();
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(2), "TTAL-Peak of T > ST measurement"); //		Bit 1 (2) TTAL 		= 0001100111010011 & 		10 =  0000000000000010 > 0 ? = T  
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(4), "STDOWN-ST Segment Depressed"); //			Bit 2 (4) STDOWN 	= 0001100111010011 & 	   100 =  0000000000000000 > 0 ? = F
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(8), "STELEV-ST Segment Elevated"); //			Bit 3 (8) STELEV	= 0001100111010011 & 	  1000 =  0000000000000000 > 0 ? = F
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(16), "JELEV-J point Elevated by 100uV"); //	Bit 4 (16) JELEV 	= 0001100111010011 & 	 10000 =  0000000000010000 > 0 ? = T
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(32), "DLTWV-Delta-Wave Detected"); //			Bit 5 (32) DLTWV 	= 0001100111010011 & 	100000 =  0000000000000000 > 0 ? = F
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(64), "STINJ-ST Segment Elevated"); //			Bit 6 (64) STINJ 	= 0001100111010011 &   1000000 =  0000000001000000 > 0 ? = T
		PER_LEAD_MEASUREMENT_BITMASK.put(Integer.valueOf(128), "PPDEEP-P Prime Area was 1000uV*ms"); //	Bit 7 (128)PPDEEP	= 0001100111010011 &  10000000 =  0000000010000000 > 0 ? = T
	}
	
	@Test
	public void testMuseExtraction(){
		
		
		String inputPath = TEST_FILE_PATH + "muse/"; 
		String fileName = "ecg_97083119_1.xml";
		
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
					processor = new MuseAnnotationsProcessor(rawMuseXML, docId);
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
				
				
				
				
				System.out.println();
				System.out.println();
				System.out.print(" LEAD,");
				for (Integer annkey : PER_LEAD_MEASUREMENT.keySet()) {
					System.out.print(PER_LEAD_MEASUREMENT.get(annkey).replaceAll(",", "") + ',');
				}
				
				for (Integer annkey : PER_LEAD_MEASUREMENT_BITMASK.keySet()) {
					System.out.print(PER_LEAD_MEASUREMENT_BITMASK.get(annkey).replaceAll(",", "") + ',');
				}
				System.out.println();
				
				for (Integer leadkey : leadList.keySet()) {
					Map<String, String> leadAnnotations = leadList.get(leadkey);
					System.out.print(" "+leadkey+",");
					for (Integer annkey : PER_LEAD_MEASUREMENT.keySet()) {
						System.out.print(leadAnnotations.get(PER_LEAD_MEASUREMENT.get(annkey)));
						System.out.print(",");
					}
					for (Integer annkey : PER_LEAD_MEASUREMENT_BITMASK.keySet()) {
						System.out.print(leadAnnotations.get(PER_LEAD_MEASUREMENT_BITMASK.get(annkey)));
						System.out.print(",");
					}
					System.out.println();
				}
				
				System.out.println();
				System.out.println();
				
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
