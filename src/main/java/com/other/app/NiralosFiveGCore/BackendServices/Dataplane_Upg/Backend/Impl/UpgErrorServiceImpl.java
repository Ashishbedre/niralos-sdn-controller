package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.Impl;


import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgErrorService;
import com.other.app.NiralosFiveGCore.BackendServices.Graph.GraphService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.UpgUpfDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.RootErrorModel;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpfErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpgErrorRepository;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;
@Service
@EnableScheduling
public class UpgErrorServiceImpl implements UpgErrorService{
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	
	@Autowired
	UpgErrorRepository upgErrorRepository;
	
	@Autowired
	GraphService graphService;
	
	@Autowired
	UpfErrorRepository upfErrorRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:UpgErrorServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(UpgErrorServiceImpl.class);

//	@Scheduled(fixedRate = 2000)
	@Override
	public void saveUpgErrorData() {
//		 internalDataFrontendService.fetchDeployedItemInformation().block();
		
		String upfIp = internalDataService.getupfIp();
		String upfPort = internalDataService.getupfPort();
		
//		WebClient webClient = WebClient.builder().baseUrl("http://" + "192.168.96.131" + ":" + "9097")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		loggerTypeA.info("saveUpgErrorData: Initiating error data retrieval for UPF instances.");

		for(int count = 1;count<=Integer.parseInt(upfIp);count++) {
			loggerTypeA.info("saveUpgErrorData: Processing UPF instance with IP count");
			String networkFunctionName=nfServiceImpl.upfName+count;
		try {
			String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + upfPort;
			RootErrorModel stats = webClient.get().uri(baseUrl+uriProtocol.getFivegcoreUpgError())
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(RootErrorModel.class) 
					.timeout(Duration.ofSeconds(1))
					.block();
			List<UpgUpfDto> data=stats.getStats();
			for (UpgUpfDto upgErrorModel : data) {
				
				if(upgErrorModel.getDpdk_input_no_error()!=null) {
				UpgErrorModel model=new UpgErrorModel();
				if(upgErrorRepository.countErrorData()==0)
				{
					model.setThread(upgErrorModel.getThread());
					model.setArp_input_IP4_destination_address_is_unset(upgErrorModel.getArp_input_IP4_destination_address_is_unset());
					model.setArp_reply_ARP_replies_sent(upgErrorModel.getArp_reply_ARP_replies_sent());
					model.setDpdk_input_no_error(upgErrorModel.getDpdk_input_no_error());
					model.setIgmp_input_IGMP_not_enabled_on_this_interface(upgErrorModel.getIgmp_input_IGMP_not_enabled_on_this_interface());
					model.setIp4_icmp_input_unknown_type(upgErrorModel.getIp4_icmp_input_unknown_type());
					model.setIp4_input_Multicast_RPF_check_failed(upgErrorModel.getIp4_input_Multicast_RPF_check_failed());
					model.setIp4_local_ip4_source_lookup_miss(upgErrorModel.getIp4_local_ip4_source_lookup_miss());
					model.setNat44_ed_out2in_slowpath_no_translation(upgErrorModel.getNat44_ed_out2in_slowpath_no_translation());
					model.setSession_queue_no_buffer(upgErrorModel.getSession_queue_no_buffer());
					model.setUdp4_input_enqueued(upgErrorModel.getUdp4_input_enqueued());
					model.setUpf4_encap_good_packets_encapsulated(upgErrorModel.getUpf4_encap_good_packets_encapsulated());
					model.setUpf_gtpu4_input_good_packets_decapsulated(upgErrorModel.getUpf_gtpu4_input_good_packets_decapsulated());
					model.setUpf_ip4_flow_process_packets_gone_through(upgErrorModel.getUpf_ip4_flow_process_packets_gone_through());
					model.setUpf_ip4_flow_process_packets_which_created_a_new_flow(upgErrorModel.getUpf_ip4_flow_process_packets_which_created_a_new_flow());
					model.setUpf_ip4_flow_process_packets_with_an_existing_flow(upgErrorModel.getUpf_ip4_flow_process_packets_with_an_existing_flow());
	                model.setLocal0_output_interface_is_down(upgErrorModel.getLocal0_output_interface_is_down());
	                model.setLocal0_output_interface_is_deleted(upgErrorModel.getLocal0_output_interface_is_deleted());
	                model.setLocal0_output_no_tx_queue_available(upgErrorModel.getLocal0_output_no_tx_queue_available());
	                model.setN3_GNB_output_interface_is_deleted(upgErrorModel.getN3_GNB_output_interface_is_deleted());
	                model.setN3_GNB_output_interface_is_down(upgErrorModel.getN3_GNB_output_interface_is_down());
	                model.setN3_GNB_output_no_buffers_to_segment_GSO(upgErrorModel.getN3_GNB_output_no_buffers_to_segment_GSO());
	                model.setN3_GNB_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN3_GNB_tx_DPDK_tx_function_returned_an_error());
	                model.setN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure());
	                model.setN4_SMF_output_interface_is_deleted(upgErrorModel.getN4_SMF_output_interface_is_deleted());
	                model.setN4_SMF_output_interface_is_down(upgErrorModel.getN4_SMF_output_interface_is_down());
	                model.setN4_SMF_output_no_buffers_to_segment_GSO(upgErrorModel.getN4_SMF_output_no_buffers_to_segment_GSO());
	                model.setN4_SMF_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN4_SMF_tx_DPDK_tx_function_returned_an_error());
	                model.setN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure());
	                model.setN6_DN_output_interface_is_deleted(upgErrorModel.getN6_DN_output_interface_is_deleted());
	                model.setN6_DN_output_interface_is_down(upgErrorModel.getN6_DN_output_interface_is_down());
	                model.setN6_DN_output_no_buffers_to_segment_GSO(upgErrorModel.getN6_DN_output_no_buffers_to_segment_GSO());
	                model.setN6_DN_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN6_DN_tx_DPDK_tx_function_returned_an_error());
	                model.setN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure());
	                model.setMEC_output_interface_is_deleted(upgErrorModel.getMEC_output_interface_is_deleted());
	                model.setMEC_output_interface_is_down(upgErrorModel.getMEC_output_interface_is_down());
	                model.setMEC_output_no_buffers_to_segment_GSO(upgErrorModel.getMEC_output_no_buffers_to_segment_GSO());
	                model.setMEC_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getMEC_tx_DPDK_tx_function_returned_an_error());
	                model.setMEC_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure());
	                model.setNfName(networkFunctionName);
	                model.setNfType(nfServiceImpl.nfTypeofUpf);
	               
					upgErrorRepository.save(model);
				}
				
				else {
					loggerTypeA.warn("SaveUpgErroData: In upgErrorRepository value of countErrorData is not Zero");
					upgErrorRepository.updateError(upgErrorModel.getNat44_ed_out2in_slowpath_no_translation(),
							upgErrorModel.getDpdk_input_no_error(),
							upgErrorModel.getIp4_input_Multicast_RPF_check_failed(),
							upgErrorModel.getIp4_local_ip4_source_lookup_miss(),
							upgErrorModel.getIp4_icmp_input_unknown_type(),
							upgErrorModel.getIgmp_input_IGMP_not_enabled_on_this_interface(),
							upgErrorModel.getArp_reply_ARP_replies_sent(),
							upgErrorModel.getArp_input_IP4_destination_address_is_unset(),
							upgErrorModel.getUpf_ip4_flow_process_packets_with_an_existing_flow(),
							upgErrorModel.getUpf_ip4_flow_process_packets_gone_through(),
							upgErrorModel.getUpf_ip4_flow_process_packets_which_created_a_new_flow(),
							upgErrorModel.getUpf_gtpu4_input_good_packets_decapsulated(),
							upgErrorModel.getUpf4_encap_good_packets_encapsulated(),
							upgErrorModel.getSession_queue_no_buffer(),
							upgErrorModel.getUdp4_input_enqueued(),
							upgErrorModel.getLocal0_output_interface_is_down(),
							upgErrorModel.getLocal0_output_interface_is_deleted(),
							upgErrorModel.getLocal0_output_no_tx_queue_available(),
							upgErrorModel.getN3_GNB_output_interface_is_down(),
							upgErrorModel.getN3_GNB_output_interface_is_deleted(),
							upgErrorModel.getN3_GNB_output_no_buffers_to_segment_GSO(),
							upgErrorModel.getN3_GNB_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(),
							upgErrorModel.getN4_SMF_output_interface_is_down(),
							upgErrorModel.getN4_SMF_output_interface_is_deleted(),
							upgErrorModel.getN4_SMF_output_no_buffers_to_segment_GSO(),
							upgErrorModel.getN4_SMF_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(),
							upgErrorModel.getN6_DN_output_interface_is_down(),
							upgErrorModel.getN6_DN_output_interface_is_deleted(),
							upgErrorModel.getN6_DN_output_no_buffers_to_segment_GSO(),
							upgErrorModel.getN6_DN_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(),
							upgErrorModel.getMEC_output_interface_is_down(),
							upgErrorModel.getMEC_output_interface_is_deleted(),
							upgErrorModel.getMEC_output_no_buffers_to_segment_GSO(),
							upgErrorModel.getMEC_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getThread(),
				
							networkFunctionName,
							nfServiceImpl.nfTypeofUpf);
					
					graphService.saveUpgErrorGraphData();
					graphService.upgErrorLastDataForDeltaValue();
				}
			}else{
					loggerTypeA.warn("saveUpgErrorData: DPDK input error detected in Null Value.");
					UpfErrorModel upfErrorModel = new UpfErrorModel();
				if(upfErrorRepository.countErrorData()==0) {
					upfErrorModel.setTun_open_failure_upf(upgErrorModel.getTun_open_failure_upf());
					upfErrorModel.setInvalid_eth_type_upf(upgErrorModel.getInvalid_eth_type_upf());
					upfErrorModel.setInvalid_gtpu_version_upf(upgErrorModel.getInvalid_gtpu_version_upf());
					upfErrorModel.setSmall_gtpu_packet_upf(upgErrorModel.getSmall_gtpu_packet_upf());
					upfErrorModel.setNo_outer_header_creation_upf(upgErrorModel.getNo_outer_header_creation_upf());
					upfErrorModel.setTun_write_failure_upf(upgErrorModel.getTun_write_failure_upf());
					upfErrorModel.setFar_not_found_upf(upgErrorModel.getFar_not_found_upf());
					upfErrorModel.setGtpu_packet_decode_failure_upf(upgErrorModel.getGtpu_packet_decode_failure_upf());
					upfErrorModel.setNfName(networkFunctionName);
					upfErrorModel.setNfType(nfServiceImpl.nfTypeofUpf);
					upfErrorRepository.save(upfErrorModel);
				}else {
					loggerTypeA.info("SaveUpgErroData: In upfErrorRepository value of countErrorData is not Zero");
					upfErrorRepository.updateError(upgErrorModel.getTun_open_failure_upf(),upgErrorModel.getInvalid_eth_type_upf(),
							upgErrorModel.getInvalid_gtpu_version_upf(),upgErrorModel.getSmall_gtpu_packet_upf(),
							upgErrorModel.getNo_outer_header_creation_upf(),upgErrorModel.getTun_write_failure_upf(),
							upgErrorModel.getFar_not_found_upf(),upgErrorModel.getGtpu_packet_decode_failure_upf()
							,networkFunctionName,nfServiceImpl.nfTypeofUpf);
				}
				graphService.saveUpfErrorGraphData();
				graphService.upfErrorLastDataForDeltaValue();
			}
			
			}
			
		} catch (Exception e) {
			loggerTypeA.error("Unable to fetch UpgError Data from Dataplane ");
		}

		}
	}
}
