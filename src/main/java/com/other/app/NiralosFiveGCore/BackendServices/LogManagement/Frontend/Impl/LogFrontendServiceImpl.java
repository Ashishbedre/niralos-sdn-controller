package com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Frontend.Impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Frontend.LogFrontendService;
import com.other.app.NiralosFiveGCore.Dto.LogManager.Frontend.LogDto;
import com.other.app.NiralosFiveGCore.Repository.LogManagement.LogModelMongoRepository;
import com.other.app.NiralosFiveGCore.model.LogManager.LogModelMongo;
@Service
public class LogFrontendServiceImpl implements LogFrontendService {
//	@Autowired
//	LogRepository log5gCoreRepository;
	@Autowired
	LogModelMongoRepository logModelMongoRepository;
//	@Autowired
//	LogLevelRepository logLevelRepository;
	
	private String location;
	@Value("${location}")
	public void setLocation(String location) {
		this.location = location;
	}
	
//	private static final String filelistpath;

	
	private String filelistpath;
	@Value("${logfilelistpath}")
	public void setFilelistpath(String filelistpath) {
		this.filelistpath = filelistpath;
	}
//	Path one = currentDir.resolve("file.txt"); // one = "./file.txt"
//	  Path fileName = one.getFileName();
	private final Path root = Paths.get("C:\\logfile");

	//linux
			//private final Path root = Paths.get("/home/user/jolly/logfile/");
	
	@Override
	public List<LogDto> fetchLogData(String level, String module) {
	    try {
	    	logModelMongoRepository.findByLogLevelAndModule(level, module);

	        Sort sort = Sort.by(Sort.Direction.DESC, "id");
	        PageRequest pageable = PageRequest.of(0, 700, sort);
	        Page<LogModelMongo> pageResult = logModelMongoRepository.findAll(pageable);
	        List<LogModelMongo> coreModels = pageResult.getContent();

	        List<LogDto> logDtos = new ArrayList<>();
	        for (LogModelMongo log5gCoreModel : coreModels) {
	            if (level.equals(log5gCoreModel.getLogLevel()) &&
	                module.equals(log5gCoreModel.getModule()) ) {

	                LogDto logDto = new LogDto();
	                logDto.setDateTime(log5gCoreModel.getDateTime());
	                logDto.setLogDescription(log5gCoreModel.getLogDescription());
	                logDto.setLogLevel(log5gCoreModel.getLogLevel());
	                logDto.setModule(log5gCoreModel.getModule());
	                logDto.setProtocol(log5gCoreModel.getProtocol());
	                logDtos.add(logDto);
	            }
	        }
	        return logDtos;
	    } catch (Exception e) {
	        // Handle the exception (e.g., log it) if needed
	        return Collections.emptyList(); // Return an empty list or another default value
	    }
	}
	@Override
	public void setLogLevelDynamically(String logLevel, String module) {
		//AMF
	    // Check if module is "amf" before setting the log level
		if ("amf".equalsIgnoreCase(module)) {
		    // Step 4: Create a WebClient instance for "amf"
		    WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9095").build();

		    // Step 5: Prepare JSON payload for log level
		    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";

		    try {
		        // Step 6: Make a POST request to set the log level using the amfClient
		        String response = amfClient.post()
		                .uri("/set_log_level")
		                .contentType(MediaType.APPLICATION_JSON)
		                .bodyValue(logLevelJson)
		                .retrieve()
		                .bodyToMono(String.class)
		                .timeout(Duration.ofSeconds(10))
		                .block();

		        // Step 7: Print or handle the response if needed
		        if (response != null) {
		            System.out.println("AMF Log Level Set Successfully. Response from set_log_level API: " + response);
		        } else {
		            System.out.println("AMF Log Level Set Successfully, but the response is null.");
		        }
		    } catch (Exception e) {
		        System.err.println("AMF Error setting log level for 'amf': " + e.getMessage());
		    }

		} else if ("nrf".equalsIgnoreCase(module)) {
			 WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9090").build();

			    // Step 5: Prepare JSON payload for log level
			    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";
			    System.out.println("ssssssssssssssssssssssss"+ module);

			    try {
			        // Step 6: Make a POST request to set the log level using the amfClient
			        String response = amfClient.post()
			                .uri("/set_log_level")
			                .contentType(MediaType.APPLICATION_JSON)
			                .bodyValue(logLevelJson)
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .block();

			        // Step 7: Print or handle the response if needed
			        if (response != null) {
			            System.out.println("NRF Log Level Set Successfully. Response from set_log_level API: " + response);
			        } else {
			            System.out.println("NRF Log Level Set Successfully, but the response is null.");
			        }
			    } catch (Exception e) {
			        System.err.println("NRF Error setting log level for 'amf': " + e.getMessage());
			    }
			    } else if ("upf".equalsIgnoreCase(module)) {
					 WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9092").build();

					    // Step 5: Prepare JSON payload for log level
					    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";

					    try {
					        // Step 6: Make a POST request to set the log level using the amfClient
					        String response = amfClient.post()
					                .uri("/set_log_level")
					                .contentType(MediaType.APPLICATION_JSON)
					                .bodyValue(logLevelJson)
					                .retrieve()
					                .bodyToMono(String.class)
					                .timeout(Duration.ofSeconds(1))
					                .block();

					        // Step 7: Print or handle the response if needed
					        if (response != null) {
					            System.out.println("UPF Log Level Set Successfully. Response from set_log_level API: " + response);
					        } else {
					            System.out.println("UPF Log Level Set Successfully, but the response is null.");
					        }
					    } catch (Exception e) {
					        System.err.println("UPF Error setting log level for 'amf': " + e.getMessage());
					    }

		} else if ("udr".equalsIgnoreCase(module)) {
			 WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9092").build();

			    // Step 5: Prepare JSON payload for log level
			    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";

			    try {
			        // Step 6: Make a POST request to set the log level using the amfClient
			        String response = amfClient.post()
			                .uri("/set_log_level")
			                .contentType(MediaType.APPLICATION_JSON)
			                .bodyValue(logLevelJson)
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .block();

			        // Step 7: Print or handle the response if needed
			        if (response != null) {
			            System.out.println("UDR Log Level Set Successfully. Response from set_log_level API: " + response);
			        } else {
			            System.out.println("UDR Log Level Set Successfully, but the response is null.");
			        }
			    } catch (Exception e) {
			        System.err.println("UDR Error setting log level for 'amf': " + e.getMessage());
			    }

		} else if ("smf".equalsIgnoreCase(module)) {
			 WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9094").build();

			    // Step 5: Prepare JSON payload for log level
			    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";

			    try {
			        // Step 6: Make a POST request to set the log level using the amfClient
			        String response = amfClient.post()
			                .uri("/set_log_level")
			                .contentType(MediaType.APPLICATION_JSON)
			                .bodyValue(logLevelJson)
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .block();

			        // Step 7: Print or handle the response if needed
			        if (response != null) {
			            System.out.println("SMF Log Level Set Successfully. Response from set_log_level API: " + response);
			        } else {
			            System.out.println("SMF Log Level Set Successfully, but the response is null.");
			        }
			    } catch (Exception e) {
			        System.err.println("SMF Error setting log level for 'amf': " + e.getMessage());
			    }

		} else if ("nssf".equalsIgnoreCase(module)) {
			 WebClient amfClient = WebClient.builder().baseUrl("http://172.16.195.128:9096").build();

			    // Step 5: Prepare JSON payload for log level
			    String logLevelJson = "{\"level\":\"" + logLevel + "\"}";

			    try {
			        // Step 6: Make a POST request to set the log level using the amfClient
			        String response = amfClient.post()
			                .uri("/set_log_level")
			                .contentType(MediaType.APPLICATION_JSON)
			                .bodyValue(logLevelJson)
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .block();

			        // Step 7: Print or handle the response if needed
			        if (response != null) {
			            System.out.println(" NSSF Log Level Set Successfully. Response from set_log_level API: " + response);
			        } else {
			            System.out.println("NSSF Log Level Set Successfully, but the response is null.");
			        }
			    } catch (Exception e) {
			        System.err.println("NSSF Error setting log level for 'amf': " + e.getMessage());
			    }

		}  else {
		    // Default case if the module is not recognized
		    System.out.println("Log level not set because the module is not recognized.");
		}
	}




//	"C:\\logfile 

//	@Override
//	public void getFileofDb() throws IOException {
//		// Get a Calendar and set it to the current time.
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(Date.from(Instant.now()));
//		final String file =String.format(location+"logFile-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal);
//		 File newFile = new File(file);
//		     newFile.createNewFile();
//		    System.out.println("successfully file is created");
//		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//		    List<LogModel> str = log5gCoreRepository.findAll();
//		    for (LogModel log5gCoreModel : str) {
//		    	String logs = log5gCoreModel.getDateTime() +":" + "["+log5gCoreModel.getLogLevel()+"]"+ "["+log5gCoreModel.getModule()+"]" + "["+log5gCoreModel.getProtocol()+"]" +":"+ log5gCoreModel.getLogDescription() + "\r\n";
//		    writer.append(logs);		
//		    }
//		    writer.close();
//		    
//	}
	@Override
	public void getFileofDb() throws IOException {
		// Get a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
		final String file =String.format(location+"logFile-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal);
		 File newFile = new File(file);
		     newFile.createNewFile();
		    System.out.println("successfully file is created");
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    List<LogModelMongo> str = logModelMongoRepository.findAll();
		    for (LogModelMongo log5gCoreModel : str) {
		    	String logs = log5gCoreModel.getDateTime() +":" + "["+log5gCoreModel.getLogLevel()+"]"+ "["+log5gCoreModel.getModule()+"]" + "["+log5gCoreModel.getProtocol()+"]" +":"+ log5gCoreModel.getLogDescription() + "\r\n";
		    writer.append(logs);		
		    }
		    writer.close();
		    
	}
//MYSQL
//	@Override
//	public List<LogModel> fetchAllLogs() {
//		return log5gCoreRepository.findAll();
//		
//	}
	//MONGODB
	@Override
	public List<LogModelMongo> fetchAllLogs() {
		return logModelMongoRepository.findAll();
		
	}


	@Override
	public void deleteDb() {
		logModelMongoRepository.deleteAll();
	}
	
	  @Override
	  public boolean delete(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      
	      return Files.deleteIfExists(file);
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	@Override
	public List<LogDto> filterDateTimeLogs(String startdate, String enddate) {
		List<LogModelMongo> coreModels = logModelMongoRepository.findAll();
		List<LogDto> logDtos = new ArrayList<>();
		for (LogModelMongo log5gCoreModel : coreModels) {
			if(log5gCoreModel.getDateTime().equals(startdate) && log5gCoreModel.getDateTime().equals(enddate)){
				  LogDto logDto = new LogDto();
				  logDto.setDateTime(log5gCoreModel.getDateTime());
				  logDto.setLogDescription(log5gCoreModel.getLogDescription());
				  logDto.setLogLevel(log5gCoreModel.getLogLevel());
				  logDto.setModule(log5gCoreModel.getModule());
				  logDto.setProtocol(log5gCoreModel.getProtocol());
				  logDtos.add(logDto);
			}
		}		
		return logDtos;
	}
	
	@Override
	public List<String> getListofFiles() throws Exception {

		List<String> list = new ArrayList<String>();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String name : fileList) {
			list.add(name);
		}

		return list;

	}
}
