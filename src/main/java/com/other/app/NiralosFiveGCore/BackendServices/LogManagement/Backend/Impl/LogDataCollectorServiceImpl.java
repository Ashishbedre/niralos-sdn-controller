package com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Backend.Impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Backend.LogDataCollectorService;

@Service
@Configuration
@EnableScheduling
public class LogDataCollectorServiceImpl implements LogDataCollectorService {
//	@Autowired
//	UriProtocol uriProtocol;
//	@Autowired
//	InternalDataService internalDataService;
//	@Autowired
//	WebClient.Builder customWebClientBuilder;
//	@Autowired
//	LogModelMongoRepository logModelMongoRepository;
//
//	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
//	final Logger logger = LoggerFactory.getLogger(LogDataCollectorServiceImpl.class);
//	@Autowired
//	@Qualifier("firstMongoTemplate") // Use @Qualifier to specify the bean name
//	private MongoTemplate firstMongoTemplate;
//
////	@Scheduled(fixedRate = 3000)
//	@Override
//	public void CollectingAmfLogData() {
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String amfIp = internalDataService.getAmfIp();
//		String amfPort = internalDataService.getAmfPort();
//		logger.info("Log Web Scrapper has started!");
//		if (siteId != null && tenentID != null) {
////		 AMF LOG
//		for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
//			String networkFunctionName = nfServiceImpl.amfName + count;
//			try {
//				WebClient amfClient = WebClient.builder().baseUrl("http://"+networkFunctionName+":"+amfPort).build();
////				WebClient amfClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+ amfIp + ":" + amfPort).build();
////			WebClient amfClient = WebClient.builder().baseUrl("http://" + amfIp + ":" + amfPort)
////					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//				Root root = amfClient.get().uri(uriProtocol.getFivegCoreLogData()).retrieve().bodyToMono(Root.class)
//						.timeout(Duration.ofSeconds(1)).block();
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("amf");
//
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeofAmf);
//					firstMongoTemplate.save(logModel);
//					logger.info("Amf log data sync 5Gcore to Agent");
//				}
//			} catch (Exception e) {
//				logger.info("Cant get AMF log files here(Also when Debug Mode is on)");
//			}
//		}
//	}}
//
//	// SMF LOG
////	 @Scheduled(fixedRate = 3000)
//	@Override
//	public void CollectingSmfLogData() {
//
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String smfIp = internalDataService.getSmfIp();
//		String smfPort = internalDataService.getSmfPort();
//		if (siteId != null && tenentID != null) {
//		for (int count = 1; count <= Integer.parseInt(smfIp); count++) {
//			String networkFunctionName = nfServiceImpl.smfName + count;
//			try {
//				WebClient smfClient = WebClient.builder().baseUrl("http://"+networkFunctionName+ ":" + smfPort).build();
////				WebClient smfClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+smfIp + ":" + smfPort).build();
////						WebClient smfClient = WebClient.builder().baseUrl("http://" + smfIp + ":" + smfPort)
////								.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//				Root root = smfClient.get().uri(uriProtocol.getFivegCoreLogData()).accept(MediaType.APPLICATION_JSON)
//						.retrieve().bodyToMono(Root.class).timeout(Duration.ofSeconds(1)).block();
////			            System.out.println("Get the SMF logs!");
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("smf");
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeofSmf);
//					logger.info("smf log data sync 5Gcore to Agent");
//					firstMongoTemplate.save(logModel);
//				}
//			} catch (Exception e) {
//				logger.info("Cant get SMF log files here(Also when Debug Mode is on)");
//			}
//		}}
//	}
//
//	// UPF LOG
////	 @Scheduled(fixedRate =3000)
//	@Override
//	public void CollectingUpfLogData() {
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String upfIp = internalDataService.getupfIp();
//		String upfPort = internalDataService.getupfPort();
//		if (siteId != null && tenentID != null) {
//		for (int count = 1; count <= Integer.parseInt(upfIp); count++) {
//			String networkFunctionName = nfServiceImpl.upfName + count;
//			try {
//				WebClient upfClient = WebClient.builder().baseUrl("http://"+networkFunctionName+ ":" + upfPort).build();
////				WebClient upfClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+ upfIp + ":" + upfPort).build();
////						WebClient upfClient = WebClient.builder().baseUrl("http://" + upfIp + ":" + upfPort)
////								.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//				Root root = upfClient.get().uri(uriProtocol.getFivegCoreLogData()).accept(MediaType.APPLICATION_JSON)
//						.retrieve().bodyToMono(Root.class).timeout(Duration.ofSeconds(1)).block();
////						System.out.println("Get the UPF logs!");
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("upf");
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeofUpf);
//					logger.info("upf log data sync 5Gcore to Agent");
//					firstMongoTemplate.save(logModel);
//				}
//			} catch (Exception e) {
//				logger.info("Cant get UPF log files here(Also when Debug Mode is on)");
//			}
//		}
//	}}
//
//	// NRF LOG
////	 @Scheduled(fixedRate = 3000)
//	@Override
//	public void CollectingNrfLogData() {
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String nrfIp = internalDataService.getnrfIp();
//		String nrfPort = internalDataService.getnrfPort();
//		if (siteId != null && tenentID != null) {
//		for (int count = 1; count <= Integer.parseInt(nrfIp); count++) {
//			String networkFunctionName = nfServiceImpl.nrfName + count;
//			try {
//				WebClient nrfClient = WebClient.builder().baseUrl("http://"+networkFunctionName+ ":" + nrfPort).build();
////				WebClient nrfClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+nrfIp +":" + nrfPort).build();
////			WebClient nrfClient = WebClient.builder().baseUrl("http://" + nrfIp +":" + nrfPort)
////					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//				Root root = nrfClient.get().uri(uriProtocol.getFivegCoreLogData()).accept(MediaType.APPLICATION_JSON)
//						.retrieve().bodyToMono(Root.class).timeout(Duration.ofSeconds(1)).block();
////			System.out.println("Get the NRF logs!");
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("nrf");
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeofNrf);
//					logger.info("nrf log data sync 5Gcore to Agent");
//					firstMongoTemplate.save(logModel);
//				}
//			} catch (Exception e) {
//				logger.info("Cant get NRF log files here(Also when Debug Mode is on)");
//			}
//		}}
//	}
//
//	// NSSF LOG
////	 @Scheduled(fixedRate = 4000)
//	@Override
//	public void CollectingNssfLogData() {
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String nssfIp = internalDataService.getnssfIp();
//		String nssfport = internalDataService.getnssfPort();
//		if (siteId != null && tenentID != null) {
//		for (int count = 1; count <= Integer.parseInt(nssfIp); count++) {
//			String networkFunctionName = nfServiceImpl.smfName + count;
//			try {
//				WebClient nssfClient = WebClient.builder().baseUrl("http://"+networkFunctionName + ":" + nssfport).build();
////				WebClient nssfClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+ nssfIp + ":" + nssfport).build();
////			WebClient nssfClient = WebClient.builder().baseUrl("http://" + nssfIp + ":" + nssfport)
////					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//				Root root = nssfClient.get().uri(uriProtocol.getFivegCoreLogData()).accept(MediaType.APPLICATION_JSON)
//						.retrieve().bodyToMono(Root.class).timeout(Duration.ofSeconds(1)).block();
////			System.out.println("Get the NSSF logs!");
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("nssf");
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeofNssf);
//
//					logger.info("nssf log data sync 5Gcore to agent");
//					firstMongoTemplate.save(logModel);
//				}
//			} catch (Exception e) {
//				logger.info("Cant get NSSF log files here(Also when Debug Mode is on)");
//			}
//		}}
//	}
//
//	// UDR LOG --HERE FACING SOME ISSUE ABOUT SCHEDULEDING
////	 @Scheduled(fixedRate = 4000)
//	@Override
//	public void CollectingUdrLogData() {
//		String agentId = internalDataService.getNiralControllerClientId();
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
//
//		String udrIp = internalDataService.getudrIp();
//		String udrPort = internalDataService.getudrPort();
//		if (siteId != null && tenentID != null) {
//		for (int count = 1; count <= Integer.parseInt(udrIp); count++) {
//			String networkFunctionName = nfServiceImpl.udrName + count;
//			try {
//				WebClient udrClient = WebClient.builder().baseUrl("http://"+networkFunctionName+ ":" + udrPort).build();
////				WebClient udrClient = WebClient.builder().baseUrl(uriProtocol.getFivegcoreProtocol()+ udrIp + ":" + udrPort).build();
////					WebClient udrClient = WebClient.builder().baseUrl("http://" + udrIp + ":" + udrPort)
////							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//				Root root = udrClient.get().uri(uriProtocol.getFivegCoreLogData()).accept(MediaType.APPLICATION_JSON)
//						.retrieve().bodyToMono(Root.class).timeout(Duration.ofSeconds(1)).block();
////					System.out.println("Get the UDR logs!");
//
//				for (String string : root.getLog()) {
//					String[] firstPass = string.split(":", 1000);
//					LogModelMongo logModel = new LogModelMongo();
//					for (int i = 0; i < firstPass.length; i++) {
//						if (i == 0) {
//							logModel.setDateTime(firstPass[i]);
//						} else if (i == 1) {
//							String line = firstPass[i];
//							String[] lineArray = StringUtils.substringsBetween(line, "[", "]");
//							logModel.setProtocol(lineArray[1]);
//							logModel.setLogLevel(lineArray[2]);
//						} else if (i == 2) {
//							logModel.setLogDescription(firstPass[i]);
//						}
//					}
//					logModel.setModule("udr");
//					logModel.setAgentId(agentId);
//					logModel.setTenentId(tenentID);
//					logModel.setSiteId(siteId);
//					logModel.setNfName(networkFunctionName);
//					logModel.setNfType(nfServiceImpl.nfTypeUdr);
//					logger.info("udr log data sync 5Gcore to agent");
//					firstMongoTemplate.save(logModel);
//				}
//			} catch (Exception e) {
//				logger.info("Cant get UDR log files here(Also when Debug Mode is on)");
//			}
//
//		}
//		}
//	}

}
