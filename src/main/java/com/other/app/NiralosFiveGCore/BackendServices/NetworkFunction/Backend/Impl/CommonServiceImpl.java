package com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.CommonServices;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.NetworkTopologyRepo;
import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;

@EnableScheduling
@Configuration
@Service
public class CommonServiceImpl implements CommonServices {
	@Autowired
	WebClient.Builder customWebClientBuilder;

	@Autowired
	InternalDataService internalDataService;

	@Autowired
	NetworkTopologyRepo networkTopologyRepo;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	public static String nrfName = "niralos-nrf-";
	public static String nfTypeofNrf = "nrf";
	public static String amfName = "niralos-amf-";
	public static String nfTypeofAmf = "amf";
	public static String smfName = "niralos-smf-";
	public static String nfTypeofSmf = "smf";
	public static String nssfName = "niralos-nssf-";
	public static String nfTypeofNssf = "nssf";
	public static String upfName = "niralos-upf-";
	public static String nfTypeofUpf = "upf";
	public static String udrName = "niralos-udr-";
	public static String nfTypeUdr = "udr";
	public static String pcfName = "niralos-pcf-";
	public static String nfTypePcf = "pcf";
	public static String udmName = "niralos-udm-";
	public static String nfTypeofUdm = "udm";
	public static String bsfName = "niralos-bsf-";
	public static String nfTypeofBsf = "bsf";
	public static String scpName = "niralos-scp-";
	public static String nfTypeofScp = "scp";
	public static String ausfName = "niralos-ausf-";
	public static String nfTypeofAusf = "ausf";
	
	public static String nefName = "niralos-nef-";
	public static String nfTypeofNef = "nef";
	
	public static String nwdafName = "niralos-nwdaf-";
	public static String nfTypeofNwdaf = "nwdaf";
	
	public static String n3iwfName = "niralos-n3iwf-";
	public static String nfTypeofN3iwf = "n3iwf";
	
	
	public static String icscfName = "niralos-icscf-";
	public static String nfTypeofIcscf = "icscf";
	
	public static String pcscfName = "niralos-pcscf-";
	public static String nfTypeofPcscf = "pcscf";
	
	public static String scscfName = "niralos-scscf-";
	public static String nfTypeofScscf = "scscf"; 
	
	public static String device1 = "5G-Core-Control-Plane";
	public static String device2 = "5G-Core-User-Plane";
	
	 @Autowired
	 private WebClient webClient;


	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-BACKEND:CommonServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

//	@Scheduled(fixedRate = 3000)
	@Override
	public void amfIpWebclient() {
			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			
			
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = amfName + count;
					try {
						String AmfbaseUrl = "http://" + amfName + count + ":" + amfPort;
						WebClient.ResponseSpec amfResponseSpec = webClient.get().uri(AmfbaseUrl).retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {

							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofAmf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofAmf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("amfIpWebclient: Updating Niral Agent that Niral 5G Core Amf is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAmf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("amfIpWebclient: Updating Niral Agent that Data count>0. Amf is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("amfIpWebclient: liveness data of AMF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAmf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("amfIpWebclient: Updating Niral Agent that Niral 5G Core Amf is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAmf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("amfIpWebclient: Updating Niral Agent that Niral 5G Core Amf is In-Active");
					}
				}
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> nrfIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String nrfIp = internalDataService.getnrfIp();
			String nrfPort = internalDataService.getnrfPort();

				for (int count = 1; count <= Integer.parseInt(nrfIp); count++) {
					String networkFunctionName = nrfName + count;
					try {
						WebClient nrfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + nrfPort).build();
//			WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
						WebClient.ResponseSpec amfResponseSpec = nrfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofNrf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofNrf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);

//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("nrfIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofNrf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("nrfIpWebclient: Updating Niral Agent that Data count>0. NRF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("nrfIpWebclient: liveness data of NRF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNrf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("nrfIpWebclient: Updating Niral Agent that Niral 5G Core NRF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNrf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("nrfIpWebclient: Updating Niral Agent that Niral 5G Core NRF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> smfIpWebclient() {
//		internalDataFrontendService.fetchDeployedItemInformation().block();
			String smfIp = internalDataService.getSmfIp();
			String smfPort = internalDataService.getSmfPort();

				for (int count = 1; count <= Integer.parseInt(smfIp); count++) {
					String networkFunctionName = smfName + count;
					try {
						WebClient smfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + smfPort).build();
//			WebClient smfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9094").build();
						WebClient.ResponseSpec amfResponseSpec = smfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();
//		
						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofSmf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofSmf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("smfIpWebclient: Updating Niral Agent that Niral 5G Core SMF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofSmf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("smfIpWebclient: Updating Niral Agent that Data count>0. SMF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("smfIpWebclient: liveness data of SMF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofSmf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("smfIpWebclient: Updating Niral Agent that Niral 5G Core SMF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofSmf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("smfIpWebclient: Updating Niral Agent that Niral 5G Core SMF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> nssfIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String nssfIp = internalDataService.getnssfIp();
			String nssfport = internalDataService.getnssfPort();

				for (int count = 1; count <= Integer.parseInt(nssfIp); count++) {
					String networkFunctionName = nssfName + count;
					try {
						WebClient nssfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + nssfport).build();
						WebClient.ResponseSpec amfResponseSpec = nssfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofNssf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofNssf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("nssfIpWebclient: Updating Niral Agent that Niral 5G Core NSSF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofNssf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("nssfIpWebclient: Updating Niral Agent Data count>0. NSSF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("nssfIpWebclient: liveness data of NSSF in progress to sync to Agent" + e);

							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNssf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("nssfIpWebclient: Updating Niral Agent that Niral 5G Core NSSF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNssf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("nssfIpWebclient: Updating Niral Agent that Niral 5G Core NSSF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> upfIpWebclient() {
//		  internalDataFrontendService.fetchDeployedItemInformation().block();
			String upfIp = internalDataService.getupfIp();
			String upfPort = internalDataService.getupfPort();

				for (int count = 1; count <= Integer.parseInt(upfIp); count++) {
					String networkFunctionName = upfName + count;
//		String networkFunctionName=networkFunctionName;// need to remove
					try {
						WebClient upfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + upfPort).build();
//			WebClient upfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9094").build();

						WebClient.ResponseSpec amfResponseSpec = upfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofUpf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofUpf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device2);
									//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("upfIpWebclient: Updating Niral Agent that Niral 5G Core UPF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofUpf,
											"1", networkFunctionName,device2);
									loggerTypeB.info("upfIpWebclient: Data count>0. UPF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("upfIpWebclient: liveness data of UPF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofUpf, "0",
									networkFunctionName, device2);
							loggerTypeB.info("upfIpWebclient: Updating Niral Agent that Niral 5G Core UPF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofUpf, "0",
								networkFunctionName, device2);
						loggerTypeB.error("upfIpWebclient: Updating Niral Agent that Niral 5G Core UPF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> udrIpWebclient() {
//		internalDataFrontendService.fetchDeployedItemInformation().block();
			String udrIp = internalDataService.getudrIp();
			String udrPort = internalDataService.getudrPort();

				for (int count = 1; count <= Integer.parseInt(udrIp); count++) {
					String networkFunctionName = udrName + count;
//		String networkFunctionName=networkFunctionName;//need to remove
					try {
						WebClient udrClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + udrPort).build();
//			WebClient udrClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9098").build();
						WebClient.ResponseSpec amfResponseSpec = udrClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeUdr, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeUdr);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("udrIpWebclient: Updating Niral Agent that Niral 5G Core UDR is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeUdr, "1",
											networkFunctionName, device1);
									loggerTypeB.info("udrIpWebclient: Data count>0. UDR is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("udrIpWebclient: liveness data of UDR in progress to sync to Agent" + e);

							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeUdr, "0",
									networkFunctionName, device1);
							loggerTypeB.info("udrIpWebclient: Updating Niral Agent that Niral 5G Core UDR is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeUdr, "0",
								networkFunctionName, device1);
						loggerTypeB.error("udrIpWebclient: Updating Niral Agent that Niral 5G Core UDR is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> pcfIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String pcfIp = internalDataService.getpcfIp();
			String pcfPort = internalDataService.getpcfPort();

				for (int count = 1; count <= Integer.parseInt(pcfIp); count++) {
					String networkFunctionName = pcfName + count;
//		String networkFunctionName=networkFunctionName;
					try {
						WebClient pcfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + pcfPort).build();

						WebClient.ResponseSpec amfResponseSpec = pcfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypePcf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypePcf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("pcfIpWebclient: Updating Niral Agent that Niral 5G Core PCF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypePcf, "1",
											networkFunctionName, device1);
									loggerTypeB.info("pcfIpWebclient: Data count>0. PCF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("pcfIpWebclient: liveness data of PCF in progress to sync to Agent" + e);

							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypePcf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("pcfIpWebclient: Updating Niral Agent that Niral 5G Core PCF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypePcf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("pcfIpWebclient: Updating Niral Agent that Niral 5G Core PCF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> udmIpWebclient() {
//		 internalDataFrontendService.fetchDeployedItemInformation().block();
			String udmIp = internalDataService.getudmIp();
			String udmPort = internalDataService.getudmPort();
				for (int count = 1; count <= Integer.parseInt(udmIp); count++) {
					String networkFunctionName = udmName + count;
					try {
						WebClient udmClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + udmPort).build();

						WebClient.ResponseSpec amfResponseSpec = udmClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofUdm, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofUdm);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("udmIpWebclient: Updating Niral Agent that Niral 5G Core UDM is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofUdm,
											"1", networkFunctionName, device1);
									loggerTypeB.info("udmIpWebclient: Data count>0. UDM is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("udmIpWebclient: liveness data of UDM in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofUdm, "0",
									networkFunctionName,device1);
							loggerTypeB.info("udmIpWebclient: Updating Niral Agent that Niral 5G Core UDM is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofUdm, "0",
								networkFunctionName, device1);
						loggerTypeB.error("udmIpWebclient: Updating Niral Agent that Niral 5G Core UDM is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> bsfIpWebclient() {
//		 internalDataFrontendService.fetchDeployedItemInformation().block();
			String bsfIp = internalDataService.getbsfIp();
			String bsfPort = internalDataService.getbsfPort();
				for (int count = 1; count <= Integer.parseInt(bsfIp); count++) {
					String networkFunctionName = bsfName + count;
					try {
						WebClient amfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + bsfPort).build();
//			WebClient amfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "7776").build();
						WebClient.ResponseSpec amfResponseSpec = amfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

//	String networkFunctionName = networkFunctionName;
						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofBsf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofBsf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
									//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("bsfIpWebclient: Updating Niral Agent that Niral 5G Core BSF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofBsf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("bsfIpWebclient: data count > 0.BSF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("bsfIpWebclient: liveness data of BSF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofBsf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("bsfIpWebclient: Updating Niral Agent that Niral 5G Core BSF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofBsf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("bsfIpWebclient: Updating Niral Agent that Niral 5G Core BSF is In-Active");
					}
				}
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> scpIpWebclient() {
//		  internalDataFrontendService.fetchDeployedItemInformation().block();
			String scpIp = internalDataService.getscpIp();
			String scpPort = internalDataService.getscpPort();

				for (int count = 1; count <= Integer.parseInt(scpIp); count++) {
					String networkFunctionName = scpName + count;
					try {
						WebClient scpClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + scpPort).build();
//			WebClient amfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "7778").build();

						WebClient.ResponseSpec amfResponseSpec = scpClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();
						loggerTypeB.info("IP ping captured Success :::::: ");
						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofScp, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofScp);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
									//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("scpIpWebclient: Updating Niral Agent that Niral 5G Core SCP is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofScp,
											"1", networkFunctionName, device1);
									loggerTypeB.info("scpIpWebclient: data count > 0.SCP is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("scpIpWebclient: liveness data of SCP in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofScp, "0",
									networkFunctionName, device1);
							loggerTypeB.info("Updating Niral Agent that Niral 5G Core SCP is In-Active ");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofScp, "0",
								networkFunctionName, device1);
						loggerTypeB.error("scpIpWebclient: Updating Niral Agent that Niral 5G Core SCP is In-Active " + e);
					}
				}
	
		return null;
	}

//	@Scheduled(fixedRate = 3000)
	@Override
	public List<String> ausfIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String ausfIp = internalDataService.getausfIp();
			String ausfPort = internalDataService.getausfPort();
				for (int count = 1; count <= Integer.parseInt(ausfIp); count++) {
					String networkFunctionName = ausfName + count;
//	String networkFunctionName=niralos-ausf-1;
					try {
						WebClient ausfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + ausfPort).build();
//			WebClient amfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "7777").build();
						WebClient.ResponseSpec ausfResponseSpec = ausfClient.get().retrieve();
						HttpStatus ausfHttpStatus = ausfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(2))
								.block().getStatusCode();
						String ausfResponseCode = ausfHttpStatus.toString();

						if (ausfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofAusf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofAusf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);
//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("ausfIpWebclient:Updating Niral Agent that Niral 5G Core AUSF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAusf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("ausfIpWebclient: data count > 0. AUSF is active.");
								}
							} catch (Exception e) {
								loggerTypeB.error("ausfIpWebclient: liveness data of AUSF in progress to sync to Agent" + e);

							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAusf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("ausfIpWebclient: Updating Niral Agent that Niral 5G Core AUSF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofAusf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("ausfIpWebclient: Updating Niral Agent that Niral 5G Core AUSF is In-Active " + e);
						// e.printStackTrace();
					}
				}
		return null;
	}

	@Override
	public List<String> nefIpWebclient() {
		String nefIp = internalDataService.getnefIp();
		String nefPort = internalDataService.getnefPort();
			for (int count = 1; count <= Integer.parseInt(nefIp); count++) {
				String networkFunctionName = nefName + count;
//String networkFunctionName=niralos-ausf-1;
				try {
					WebClient nefClient = WebClient.builder()
							.baseUrl("http://" + networkFunctionName + ":" + nefPort).build();
//		WebClient amfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "7777").build();
					WebClient.ResponseSpec nefResponseSpec = nefClient.get().retrieve();
					HttpStatus nefHttpStatus = nefResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(2))
							.block().getStatusCode();
					String nefResponseCode = nefHttpStatus.toString();

					if (nefResponseCode.equals("200 OK")) {
						try {
							if (networkTopologyRepo.countofNetworkData(nfTypeofNef, networkFunctionName) == 0) {
								NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
								networkTopologyModel.setNfName(networkFunctionName);
								networkTopologyModel.setNfType(nfTypeofNef);
								networkTopologyModel.setNfIp(networkFunctionName);
								networkTopologyModel.setNfStatus("1");
								networkTopologyModel.setDevice(device1);
//				Mark it as active
								networkTopologyRepo.save(networkTopologyModel);
								loggerTypeB.info("nefIpWebclient:Updating Niral Agent that Niral 5G Core nef is Active");
							} else {
								networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNef,
										"1", networkFunctionName, device1);
								loggerTypeB.info("nefIpWebclient: data count > 0. nef is active.");
							}
						} catch (Exception e) {
							loggerTypeB.error("nefIpWebclient: liveness data of nef in progress to sync to Agent" + e);

						}
					} else {
//				Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNef, "0",
								networkFunctionName, device1);
						loggerTypeB.info("ausfIpWebclient: Updating Niral Agent that Niral 5G Core nef is In-Active");
					}
				} catch (Exception e) {
//				Mark it as Inactive
					networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNef, "0",
							networkFunctionName, device1);
					loggerTypeB.error("nefIpWebclient: Updating Niral Agent that Niral 5G Core nef is In-Active " + e);
					// e.printStackTrace();
				}
			}
	return null;
	}
	
	
   //@Scheduled(fixedRate = 3000)
	@Override
	public List<String> nwdafIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String nwdafIp = internalDataService.getnwdafIp();
			String nwdafPort = internalDataService.getnwdafPort();

				for (int count = 1; count <= Integer.parseInt(nwdafIp); count++) {
					String networkFunctionName = nwdafName + count;
					try {
						WebClient nwdafClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName+ ":" + nwdafPort).build();
//			WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
						WebClient.ResponseSpec amfResponseSpec = nwdafClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofNwdaf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofNwdaf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);

//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("nwdafIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofNwdaf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Data count>0. NRF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("nwdafIpWebclien: liveness data of NRF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNwdaf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofNwdaf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
					}
				}
		return null;
	}
   
  // @Scheduled(fixedRate = 3000)
	@Override
	public List<String> n3iwfIpWebclient() {
//		   internalDataFrontendService.fetchDeployedItemInformation().block();
			String n3iwfIp = internalDataService.getn3iwfIp();
			String n3iwfPort = internalDataService.getnwdafPort();

				for (int count = 1; count <= Integer.parseInt(n3iwfIp); count++) {
					String networkFunctionName = n3iwfName + count;
					try {
						WebClient nrfClient = WebClient.builder()
								.baseUrl("http://" + networkFunctionName + ":" + n3iwfPort).build();
//			WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
						WebClient.ResponseSpec amfResponseSpec = nrfClient.get().retrieve();
						HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
								.block().getStatusCode();
						String amfResponseCode = amfHttpStatus.toString();

						if (amfResponseCode.equals("200 OK")) {
							try {
								if (networkTopologyRepo.countofNetworkData(nfTypeofN3iwf, networkFunctionName) == 0) {
									NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
									networkTopologyModel.setNfName(networkFunctionName);
									networkTopologyModel.setNfType(nfTypeofN3iwf);
									networkTopologyModel.setNfIp(networkFunctionName);
									networkTopologyModel.setNfStatus("1");
									networkTopologyModel.setDevice(device1);

//					Mark it as active
									networkTopologyRepo.save(networkTopologyModel);
									loggerTypeB.info("nwdafIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
								} else {
									networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofN3iwf,
											"1", networkFunctionName, device1);
									loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Data count>0. NRF is Active");
								}
							} catch (Exception e) {
								loggerTypeB.info("nwdafIpWebclien: liveness data of NRF in progress to sync to Agent" + e);
							}
						} else {
//					Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofN3iwf, "0",
									networkFunctionName, device1);
							loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
						}
					} catch (Exception e) {
//					Mark it as Inactive
						networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofN3iwf, "0",
								networkFunctionName, device1);
						loggerTypeB.error("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
					}
				}
		return null;
	}
	
	// @Scheduled(fixedRate = 3000)
		@Override
		public List<String> icscfIpWebclient() {
//			   internalDataFrontendService.fetchDeployedItemInformation().block();
				String icscfIp = internalDataService.geticscfIp();
				String icscfPort = internalDataService.geticscfPort();

					for (int count = 1; count <= Integer.parseInt(icscfIp); count++) {
						String networkFunctionName = icscfName + count;
						try {
							WebClient icscfClient = WebClient.builder()
									.baseUrl("http://" + networkFunctionName + ":" + icscfPort+"/health_Check").build();
//				WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
							WebClient.ResponseSpec amfResponseSpec = icscfClient.get().retrieve();
							HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
									.block().getStatusCode();
							String amfResponseCode = amfHttpStatus.toString();

							if (amfResponseCode.equals("200 OK")) {
								try {
									if (networkTopologyRepo.countofNetworkData(nfTypeofIcscf, networkFunctionName) == 0) {
										NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
										networkTopologyModel.setNfName(networkFunctionName);
										networkTopologyModel.setNfType(nfTypeofIcscf);
										networkTopologyModel.setNfIp(networkFunctionName);
										networkTopologyModel.setNfStatus("1");
										networkTopologyModel.setDevice(device1);

//						Mark it as active
										networkTopologyRepo.save(networkTopologyModel);
										loggerTypeB.info("nwdafIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
									} else {
										networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofIcscf,
												"1", networkFunctionName, device1);
										loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Data count>0. NRF is Active");
									}
								} catch (Exception e) {
									loggerTypeB.info("nwdafIpWebclien: liveness data of NRF in progress to sync to Agent" + e);
								}
							} else {
//						Mark it as Inactive
								networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofIcscf, "0",
										networkFunctionName, device1);
								loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
							}
						} catch (Exception e) {
//						Mark it as Inactive
							networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofIcscf, "0",
									networkFunctionName, device1);
							loggerTypeB.error("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
						}
					}
			return null;
		}
		
		
		// @Scheduled(fixedRate = 3000)
				@Override
				public List<String> scscfIpWebclient() {
//					   internalDataFrontendService.fetchDeployedItemInformation().block();
						String scscfIp = internalDataService.getscscfIp();
						String scscfPort = internalDataService.getscscfPort();

							for (int count = 1; count <= Integer.parseInt(scscfIp); count++) {
								String networkFunctionName = scscfName + count;
								try {
									WebClient scscfClient = WebClient.builder()
											.baseUrl("http://" + networkFunctionName + ":" + scscfPort+"/health_Check").build();
//						WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
									WebClient.ResponseSpec amfResponseSpec = scscfClient.get().retrieve();
									HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
											.block().getStatusCode();
									String amfResponseCode = amfHttpStatus.toString();

									if (amfResponseCode.equals("200 OK")) {
										try {
											if (networkTopologyRepo.countofNetworkData(nfTypeofScscf, networkFunctionName) == 0) {
												NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
												networkTopologyModel.setNfName(networkFunctionName);
												networkTopologyModel.setNfType(nfTypeofScscf);
												networkTopologyModel.setNfIp(networkFunctionName);
												networkTopologyModel.setNfStatus("1");
												networkTopologyModel.setDevice(device1);

//								Mark it as active
												networkTopologyRepo.save(networkTopologyModel);
												loggerTypeB.info("nwdafIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
											} else {
												networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofScscf,
														"1", networkFunctionName, device1);
												loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Data count>0. NRF is Active");
											}
										} catch (Exception e) {
											loggerTypeB.info("nwdafIpWebclien: liveness data of NRF in progress to sync to Agent" + e);
										}
									} else {
//								Mark it as Inactive
										networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofScscf, "0",
												networkFunctionName, device1);
										loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
									}
								} catch (Exception e) {
//								Mark it as Inactive
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofScscf, "0",
											networkFunctionName, device1);
									loggerTypeB.error("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
								}
							}
					return null;
				}
				
				// @Scheduled(fixedRate = 3000)
				@Override
				public List<String> pcscfIpWebclient() {
//					   internalDataFrontendService.fetchDeployedItemInformation().block();
						String pcscfIp = internalDataService.getpcscfIp();
						String pcscfPort = internalDataService.getpcscfPort();

							for (int count = 1; count <= Integer.parseInt(pcscfIp); count++) {
								String networkFunctionName = pcscfName + count;
								try {
									WebClient pcscfClient = WebClient.builder()
											.baseUrl("http://" + networkFunctionName + ":" + pcscfPort +"/health_Check").build();
//						WebClient nrfClient = WebClient.builder().baseUrl("http://"+"192.168.96.128"+ ":" + "9096").build();
									WebClient.ResponseSpec amfResponseSpec = pcscfClient.get().retrieve();
									HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().timeout(Duration.ofSeconds(1))
											.block().getStatusCode();
									String amfResponseCode = amfHttpStatus.toString();

									if (amfResponseCode.equals("200 OK")) {
										try {
											if (networkTopologyRepo.countofNetworkData(nfTypeofPcscf, networkFunctionName) == 0) {
												NetworkTopologyModel networkTopologyModel = new NetworkTopologyModel();
												networkTopologyModel.setNfName(networkFunctionName);
												networkTopologyModel.setNfType(nfTypeofPcscf);
												networkTopologyModel.setNfIp(networkFunctionName);
												networkTopologyModel.setNfStatus("1");
												networkTopologyModel.setDevice(device1);

//								Mark it as active
												networkTopologyRepo.save(networkTopologyModel);
												loggerTypeB.info("nwdafIpWebclient: Updating Niral Agent that Niral 5G Core NRF is Active");
											} else {
												networkTopologyRepo.updateAmfStatus(networkFunctionName, nfTypeofPcscf,
														"1", networkFunctionName, device1);
												loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Data count>0. NRF is Active");
											}
										} catch (Exception e) {
											loggerTypeB.info("nwdafIpWebclien: liveness data of NRF in progress to sync to Agent" + e);
										}
									} else {
//								Mark it as Inactive
										networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofPcscf, "0",
												networkFunctionName, device1);
										loggerTypeB.info("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
									}
								} catch (Exception e) {
//								Mark it as Inactive
									networkTopologyRepo.updateAmfStatus( networkFunctionName, nfTypeofPcscf, "0",
											networkFunctionName, device1);
									loggerTypeB.error("nwdafIpWebclien: Updating Niral Agent that Niral 5G Core NRF is In-Active");
								}
							}
					return null;
				}
	
}
