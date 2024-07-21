package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.UpgErrorFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.Graph.GraphService;
import com.other.app.NiralosFiveGCore.Dto.Frontend.ReturnSiteList;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpfErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgUpfInterfaceDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgUpfNodeDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgUpfRoot;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpfErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpgErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;
@Service
public class UpgErrorFrontendServiceImpl implements UpgErrorFrontendService{
	final Logger logger = LoggerFactory.getLogger(UpgErrorFrontendServiceImpl.class);

	
	@Autowired
	UpgErrorRepository upgErrorRepository;

	@Autowired
	GraphService graphService;
	
	@Autowired
	UpgErrorGraphModelRepository upgErrorGraphModelRepository;
	@Autowired
	UpfErrorGraphModelRepository upfErrorGraphModelRepository;
	@Autowired
	UpfErrorRepository upfErrorModelRepository;
    //Fetching the data from database and sending it to frontend
	@Override
	public List<UpgErrorModel> getUpgErrorData() {
		return upgErrorRepository.findAllData();
	}

	@Override
	public ArrayList<UpgErrorGraphDto> getUpgErrorbyfilter(String type, String name,String reason) {
	List<UpgErrorGraphModel> upgErrorGraphModels = upgErrorGraphModelRepository.getUpgErrorbyfilter(type,name,reason);
	ArrayList<UpgErrorGraphDto>arrayList = new ArrayList<>();
	for (UpgErrorGraphModel upgErrorGraphModel : upgErrorGraphModels) {
		UpgErrorGraphDto upgErrorGraphDto = new UpgErrorGraphDto();
		upgErrorGraphDto.setErrorType(upgErrorGraphModel.getErrorType());
		upgErrorGraphDto.setLocalDateTime(upgErrorGraphModel.getLocalDateTime());
		upgErrorGraphDto.setName(upgErrorGraphModel.getName());
		upgErrorGraphDto.setReason(upgErrorGraphModel.getReason());
		upgErrorGraphDto.setValue(upgErrorGraphModel.getValue());
		arrayList.add(upgErrorGraphDto);
	}
	return arrayList;	
	}
	@Override
	public List<UpfErrorGraphDto> getUpfErrorByFilter(String type, String reason) {
        List<UpfErrorGraphModel> upfErrorGraphModels = upfErrorGraphModelRepository.getfindByReason(reason);
        List<UpfErrorGraphDto> upfErrorGraphDtos = new ArrayList<>();

        if ("interface".equals(type)) {
            for (UpfErrorGraphModel upfErrorGraphModel : upfErrorGraphModels) {
                UpfErrorGraphDto upfErrorGraphDto = new UpfErrorGraphDto();
                upfErrorGraphDto.setLocalDateTime(upfErrorGraphModel.getLocalDateTime());
                upfErrorGraphDto.setReason(upfErrorGraphModel.getReason());
                upfErrorGraphDto.setValue(upfErrorGraphModel.getValue());
                upfErrorGraphDtos.add(upfErrorGraphDto);
            }
        }

        return upfErrorGraphDtos;
    }

	@Override
	public UpgUpfRoot getCombinedErrorByFilter(String type) {
		 List<UpgErrorModel> upgErrorModels = new ArrayList<>();
		 List<UpfErrorModel> upfErrorModels =  new  ArrayList<>();
		try {
			upgErrorModels = upgErrorRepository.findAll();
		}catch (Exception e) {
			System.out.println("Upg List is have null value");
		}
		try {
		upfErrorModels=    upfErrorModelRepository.findAll();
		}catch (Exception e) {
			System.out.println("upf List is have null value");
		}

	    UpgUpfRoot root = new UpgUpfRoot();
	    List<UpgUpfInterfaceDto> interfaceDtos = new ArrayList<>();
	    List<UpgUpfNodeDto> nodeDtos = new ArrayList<>();

//	    int minSize = Math.min(upgErrorModels.size(), upfErrorModels.size());
	    switch (type) {
	        case "interface":
	        	try {
	            for (int i = 0; i < 1; i++) {
	            	if(upgErrorModels.isEmpty()) {
	            	 UpgUpfInterfaceDto interfaceDto = new UpgUpfInterfaceDto();
	                UpgErrorModel upgErrorModel = null;;

	                interfaceDto.setThread(null);
	                interfaceDto.setIgmp_input_IGMP_not_enabled_on_this_interface(null);

	                interfaceDto.setLocal0_output_interface_is_deleted(null);
	                interfaceDto.setLocal0_output_interface_is_down(null);
	                interfaceDto.setLocal0_output_no_buffers_to_segment_GSO(null);
	                interfaceDto.setMEC_output_interface_is_deleted(null);
	                interfaceDto.setMEC_output_interface_is_down(null);
	                interfaceDto.setMEC_output_no_buffers_to_segment_GSO(null);
	                interfaceDto.setMEC_tx_DPDK_tx_function_returned_an_error(null);
	                interfaceDto.setMEC_tx_Tx_packet_drops__dpdk_tx_failure(null);
	                interfaceDto.setN3_GNB_output_interface_is_deleted(null);
	                interfaceDto.setN3_GNB_output_interface_is_down(null);
	                interfaceDto.setN3_GNB_output_no_buffers_to_segment_GSO(null);
	                interfaceDto.setN3_GNB_tx_DPDK_tx_function_returned_an_error(null);
	                interfaceDto.setN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(null);
	                interfaceDto.setN4_SMF_output_interface_is_deleted(null);
	                interfaceDto.setN4_SMF_output_interface_is_down(null);
	                interfaceDto.setN4_SMF_output_no_buffers_to_segment_GSO(null);
	                interfaceDto.setN4_SMF_tx_DPDK_tx_function_returned_an_error(null);
	                interfaceDto.setN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(null);
	                interfaceDto.setN6_DN_output_interface_is_deleted(null);
	                interfaceDto.setN6_DN_output_interface_is_down(null);
	                interfaceDto.setN6_DN_output_no_buffers_to_segment_GSO(null);
	                interfaceDto.setN6_DN_tx_DPDK_tx_function_returned_an_error(null);
	                interfaceDto.setN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(null);
	                UpfErrorModel upfErrorModel = upfErrorModels.get(i);
	                interfaceDto.setTun_write_failure_upf(upfErrorModel.getTun_write_failure_upf());
	                interfaceDto.setInvalid_eth_type_upf(upfErrorModel.getInvalid_eth_type_upf());
	                interfaceDto.setInvalid_gtpu_version_upf(upfErrorModel.getInvalid_gtpu_version_upf());
	                interfaceDto.setSmall_gtpu_packet_upf(upfErrorModel.getSmall_gtpu_packet_upf());
	                interfaceDto.setNo_outer_header_creation_upf(upfErrorModel.getNo_outer_header_creation_upf());
	                interfaceDto.setTun_open_failure_upf(upfErrorModel.getTun_open_failure_upf());
	                interfaceDto.setFar_not_found_upf(upfErrorModel.getFar_not_found_upf());
	                interfaceDto.setGtpu_packet_decode_failure_upf(upfErrorModel.getGtpu_packet_decode_failure_upf());
	                interfaceDtos.add(interfaceDto);
	            	}
	            	else{
	            		 UpgUpfInterfaceDto interfaceDto = new UpgUpfInterfaceDto();
	 	                UpgErrorModel upgErrorModel = upgErrorModels.get(i);

	 	                interfaceDto.setThread(upgErrorModel.getThread());
	 	                interfaceDto.setIgmp_input_IGMP_not_enabled_on_this_interface(upgErrorModel.getIgmp_input_IGMP_not_enabled_on_this_interface());

	 	                interfaceDto.setLocal0_output_interface_is_deleted(upgErrorModel.getLocal0_output_interface_is_deleted());
	 	                interfaceDto.setLocal0_output_interface_is_down(upgErrorModel.getLocal0_output_interface_is_down());
	 	                interfaceDto.setLocal0_output_no_buffers_to_segment_GSO(upgErrorModel.getLocal0_output_no_tx_queue_available());
	 	                interfaceDto.setMEC_output_interface_is_deleted(upgErrorModel.getMEC_output_interface_is_deleted());
	 	                interfaceDto.setMEC_output_interface_is_down(upgErrorModel.getMEC_output_interface_is_down());
	 	                interfaceDto.setMEC_output_no_buffers_to_segment_GSO(upgErrorModel.getMEC_output_no_buffers_to_segment_GSO());
	 	                interfaceDto.setMEC_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getMEC_tx_DPDK_tx_function_returned_an_error());
	 	                interfaceDto.setMEC_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure());
	 	                interfaceDto.setN3_GNB_output_interface_is_deleted(upgErrorModel.getN3_GNB_output_interface_is_deleted());
	 	                interfaceDto.setN3_GNB_output_interface_is_down(upgErrorModel.getN3_GNB_output_interface_is_down());
	 	                interfaceDto.setN3_GNB_output_no_buffers_to_segment_GSO(upgErrorModel.getN3_GNB_output_no_buffers_to_segment_GSO());
	 	                interfaceDto.setN3_GNB_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN3_GNB_tx_DPDK_tx_function_returned_an_error());
	 	                interfaceDto.setN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure());
	 	                interfaceDto.setN4_SMF_output_interface_is_deleted(upgErrorModel.getN4_SMF_output_interface_is_deleted());
	 	                interfaceDto.setN4_SMF_output_interface_is_down(upgErrorModel.getN4_SMF_output_interface_is_down());
	 	                interfaceDto.setN4_SMF_output_no_buffers_to_segment_GSO(upgErrorModel.getN4_SMF_output_no_buffers_to_segment_GSO());
	 	                interfaceDto.setN4_SMF_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN4_SMF_tx_DPDK_tx_function_returned_an_error());
	 	                interfaceDto.setN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure());
	 	                interfaceDto.setN6_DN_output_interface_is_deleted(upgErrorModel.getN6_DN_output_interface_is_deleted());
	 	                interfaceDto.setN6_DN_output_interface_is_down(upgErrorModel.getN6_DN_output_interface_is_down());
	 	                interfaceDto.setN6_DN_output_no_buffers_to_segment_GSO(upgErrorModel.getN6_DN_output_no_buffers_to_segment_GSO());
	 	                interfaceDto.setN6_DN_tx_DPDK_tx_function_returned_an_error(upgErrorModel.getN6_DN_tx_DPDK_tx_function_returned_an_error());
	 	                interfaceDto.setN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(upgErrorModel.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure());
	 	                UpfErrorModel upfErrorModel = upfErrorModels.get(i);
	 	                interfaceDto.setTun_write_failure_upf(upfErrorModel.getTun_write_failure_upf());
	 	                interfaceDto.setInvalid_eth_type_upf(upfErrorModel.getInvalid_eth_type_upf());
	 	                interfaceDto.setInvalid_gtpu_version_upf(upfErrorModel.getInvalid_gtpu_version_upf());
	 	                interfaceDto.setSmall_gtpu_packet_upf(upfErrorModel.getSmall_gtpu_packet_upf());
	 	                interfaceDto.setNo_outer_header_creation_upf(upfErrorModel.getNo_outer_header_creation_upf());
	 	                interfaceDto.setTun_open_failure_upf(upfErrorModel.getTun_open_failure_upf());
	 	                interfaceDto.setFar_not_found_upf(upfErrorModel.getFar_not_found_upf());
	 	                interfaceDto.setGtpu_packet_decode_failure_upf(upfErrorModel.getGtpu_packet_decode_failure_upf());
	 	                interfaceDtos.add(interfaceDto);
	            	
	            	}
	            	}
	            root.setIntrface(interfaceDtos);
	            break;}catch (Exception e) {
	            	logger.info("Interface : Not enough data to show!!");						}

	        case "node":
	        	try {
	            for (int i = 0; i < 1; i++) {
	                UpgUpfNodeDto nodeDto = new UpgUpfNodeDto();
	                UpgErrorModel upgErrorModel = upgErrorModels.get(i);

	                nodeDto.setNat44_ed_out2in_slowpath_no_translation(upgErrorModel.getNat44_ed_out2in_slowpath_no_translation());
	                nodeDto.setDpdk_input_no_error(upgErrorModel.getDpdk_input_no_error());
	                nodeDto.setIp4_icmp_input_unknown_type(upgErrorModel.getIp4_icmp_input_unknown_type());
	                nodeDto.setIp4_input_Multicast_RPF_check_failed(upgErrorModel.getIp4_input_Multicast_RPF_check_failed());
	                nodeDto.setIp4_local_ip4_source_lookup_miss(upgErrorModel.getIp4_local_ip4_source_lookup_miss());
	                nodeDto.setArp_input_IP4_destination_address_is_unset(upgErrorModel.getArp_input_IP4_destination_address_is_unset());
	                nodeDto.setArp_reply_ARP_replies_sent(upgErrorModel.getArp_reply_ARP_replies_sent());	
	                nodeDto.setIgmp_input_IGMP_not_enabled_on_this_interface(upgErrorModel.getIgmp_input_IGMP_not_enabled_on_this_interface());
	                nodeDto.setUpf4_encap_good_packets_encapsulated(upgErrorModel.getUpf4_encap_good_packets_encapsulated());
	                nodeDto.setUpf_gtpu4_input_good_packets_decapsulated(upgErrorModel.getUpf_gtpu4_input_good_packets_decapsulated());
	                nodeDto.setUpf_ip4_flow_process_packets_gone_through(upgErrorModel.getUpf_ip4_flow_process_packets_gone_through());
	                nodeDto.setUpf_ip4_flow_process_packets_which_created_a_new_flow(upgErrorModel.getUpf_ip4_flow_process_packets_which_created_a_new_flow());
	                nodeDto.setUpf_ip4_flow_process_packets_with_an_existing_flow(upgErrorModel.getUpf_ip4_flow_process_packets_with_an_existing_flow());
	                nodeDto.setSession_queue_Packets_transmitted(upgErrorModel.getSession_queue_no_buffer());
	                nodeDto.setUdp4_input_Packets_enqueued(upgErrorModel.getUdp4_input_enqueued());
	                nodeDtos.add(nodeDto);
	            }
	            root.setNode(nodeDtos);
	            break;
	        	}catch (Exception e) {
	        		logger.info("Node : Not enough data to show!!");				}

	        default:
	            // Handle an invalid "type" parameter
	            return null; // Return null or handle accordingly
	    }

	    return root;
	}


	@Override
	public List<ReturnSiteList> returnSitelistofUpg(String tenentId) {
		List<String> siteList = 	upgErrorRepository.returnSiteList(tenentId);
		
		List<ReturnSiteList> siteLists = new ArrayList<>();
		for (String string : siteList) {
			ReturnSiteList list = new ReturnSiteList();
			list.setSite(string);
			siteLists.add(list);
		}
			return siteLists;
	}
	
	

}
