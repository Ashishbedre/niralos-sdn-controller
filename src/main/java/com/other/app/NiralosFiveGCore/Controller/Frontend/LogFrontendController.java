//package com.other.app.NiralosFiveGCore.Controller.Frontend;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Frontend.LogFrontendService;
//import com.other.app.NiralosFiveGCore.Controller.Backend.DashboardWebController;
//import com.other.app.NiralosFiveGCore.Dto.LogManager.Frontend.LogDto;
//import com.other.app.NiralosFiveGCore.model.LogManager.ResponseMessage;
//
//@RestController
//@RequestMapping("/v2/log_manager")
//@CrossOrigin
//public class LogFrontendController {
//
////	private static final String downloadfilepath;
//	final Logger logger = LoggerFactory.getLogger(LogFrontendController.class);
//
//	private String downloadfilepath;
//
//	@Value("${downloadfilepath}")
//	public void setPath(String downloadfilepath) {
//		this.downloadfilepath = downloadfilepath;
//	}
//
//	@Autowired
//	LogFrontendService log5gCoreService;
//	
//	@GetMapping("/generate_file")
//	public void makeFileofDb() throws IOException {
//		log5gCoreService.getFileofDb();
//	}
//
//	@GetMapping(path = "/download/{name}")
//	public ResponseEntity<Resource> download(@PathVariable("name") String name) throws IOException {
//		try {
//			File file = new File(downloadfilepath + name);
//			Path path = Paths.get(file.getAbsolutePath());
//			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//			return ResponseEntity.ok().headers(this.headers(name)).contentLength(file.length())
//					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error("File is not found !!");
//		}
//		return null;
//	}
//
//	@GetMapping("/files")
//	public ResponseEntity<List<String>> getListOfFiles() throws Exception {
//		return new ResponseEntity<>(log5gCoreService.getListofFiles(), HttpStatus.OK);
//
//	}
//
//	private HttpHeaders headers(String name) {
//		HttpHeaders header = new HttpHeaders();
//		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
//		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		header.add("Pragma", "no-cache");
//		header.add("Expires", "0");
//		return header;
//
//	}
//
//	@GetMapping("/delete_db")
//	public void deleteWholedb() {
//		log5gCoreService.deleteDb();
//	}
//
//	@DeleteMapping("/files/filename={filename}")
//	public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {
//		String message = "";
//
//		try {
//			boolean existed = log5gCoreService.delete(filename);
//
//			if (existed) {
//				message = "Delete the file successfully: " + filename;
//				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//			}
//
//			message = "The file does not exist ";
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Could not delete the file " + filename + ". Error  " + e.getMessage();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
//		}
//	}
//
////	@GetMapping("/fetch_specific_data/level={level}/module={module}/tenentId={tenentId}/siteId={siteId}")
////	public LogFilters getSpecificData(@PathVariable("level") String level, @PathVariable("module") String module,
////			@PathVariable("tenentId") String tenentId, @PathVariable("siteId") String siteId) {
////		
////		return new LogFilters(log5gCoreService.fetchAllLogs(),
////				log5gCoreService.fetchLogData(level, module, tenentId, siteId));
////	}private void setLogLevelDynamically(String logLevel)
//	
//
//
////	    @GetMapping("/fetch_specific_data/level={level}/module={module}/tenentId={tenentId}/siteId={siteId}")
////	    public List<LogDto> getSpecificData(@PathVariable("level") String level,
////	                                       @PathVariable("module") String module,
////	                                       @PathVariable("tenentId") String tenentId,
////	                                       @PathVariable("siteId") String siteId) {
////
////	        // Step 1: Fetch log data
////	        List<LogDto> logData = log5gCoreService.fetchLogData(level, module, tenentId, siteId);
////
////	        // Step 2: Set log level dynamically using WebClient.post
////	        log5gCoreService.setLogLevelDynamically(level,module);
////	        return logData;
////
////
////	        // Step 3: Return log data
////	        
////	    }
//	    @GetMapping("/fetch_specific_data/level={level}/module={module}")
//	    public List<LogDto> getSpecificData(@PathVariable("level") String level,
//	                                        @PathVariable("module") String module
//	                                       ) {
//
//	        // Step 1: Fetch log data
//	        List<LogDto> logData = log5gCoreService.fetchLogData(level, module);
//
//	        // Step 2: Set log level dynamically using WebClient.post
//	        log5gCoreService.setLogLevelDynamically(level, module);
//
//	        // Step 3: Return log data
//	        return logData;
//	    }
//
//	    
//	}
//
