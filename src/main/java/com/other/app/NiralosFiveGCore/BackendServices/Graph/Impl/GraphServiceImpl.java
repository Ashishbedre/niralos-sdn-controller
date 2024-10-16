package com.other.app.NiralosFiveGCore.BackendServices.Graph.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.attoparser.config.ParseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl.GnbRegistrationDeregistrationFailureCountServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.Graph.GraphService;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.GnbGraphDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UeGraphDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend.ThroughputGraphFrontendDto;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpfErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpgErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgInterfaceDataRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.GnbGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.UeGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend.LiveDataFrontendRepo;
import com.other.app.NiralosFiveGCore.Repository.Throughput.ThroughputRepository;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.ThroughputModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorDeltaModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorDeltaModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgInterface;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;
import com.other.app.NiralosFiveGCore.model.Graph.CombineGraphData;
import com.other.app.NiralosFiveGCore.model.Graph.GnbGraphModel;
import com.other.app.NiralosFiveGCore.model.Graph.UeGraphModel;

@Service
@EnableScheduling
public class GraphServiceImpl implements GraphService {
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:GraphServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(GnbRegistrationDeregistrationFailureCountServiceImpl.class);

	@Autowired
	UeGraphRepository ueGraphRepository;

	@Autowired
	GnbGraphRepository gnbGraphRepository;

	@Autowired
	LiveDataFrontendRepo liveDataRepository;

	@Autowired
	ThroughputRepository throughputRepository;

	@Autowired
	UpgFrontendRepository upgRepository;

	@Autowired
	UpgInterfaceDataRepository upgInterfaceDataRepository;

	@Autowired
	UpgErrorGraphModelRepository upgErrorGraphModelRepository;

	@Autowired
	UpgErrorRepository upgErrorRepository;

	@Autowired
	UpgErrorDeltaModelRepository upgErrorDeltaModelRepository;
	@Autowired
	UpfErrorRepository upfErrorModelRepository;
	@Autowired
	UpfErrorDeltaModelRepository upfErrorDeltaModelRepository;
	@Autowired
	UpfErrorGraphModelRepository upfErrorGraphModelRepository;

	@Override
	@Scheduled(fixedRate = 60000)
	public void saveGnbGraph() {
		List<LiveDataModel> liveDataModels = liveDataRepository.getliveData();
		for (LiveDataModel liveDataModel : liveDataModels) {
			gnbGraphRepository.save(new GnbGraphModel(2L, LocalDateTime.now(),
					Integer.parseInt(liveDataModel.getActiveGnb())
					));
		}
	}

	@Override
	@Scheduled(fixedRate = 60000)
	public void saveUeGraph() {
		List<LiveDataModel> liveDataModels = liveDataRepository.getliveData();
		for (LiveDataModel liveDataModel : liveDataModels) {
			ueGraphRepository.save(new UeGraphModel(2L, LocalDateTime.now(),
					Integer.parseInt(liveDataModel.getActiveUe())));
		}

	}

	@Override
	public ArrayList<UeGraphDTO> getUegraph(String range)
	{    loggerTypeB.info("getUegraph: Requested range for UE graph data");
		if (range.equals("hour")) {
			ArrayList<UeGraphModel> ueGraphModels = ueGraphRepository.returnUeData(2L, 60);
			ArrayList<UeGraphDTO> ueGraphDTOs = new ArrayList<>();

			for (UeGraphModel ueGraphModel : ueGraphModels) {
				ueGraphDTOs.add(new UeGraphDTO(ueGraphModel.getLocalDateTime(), ueGraphModel.getUeCount()));
			}
			return ueGraphDTOs;

		} else if (range.equals("day")) {

			ArrayList<UeGraphModel> ueGraphModelsRawData = ueGraphRepository.returnUeData(2L, 1440);

			Integer counter = 0;
			Integer avgSumOfUe = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<UeGraphDTO> ueGraphDTOs = new ArrayList<>();
			for (UeGraphModel ueGraphModel : ueGraphModelsRawData) {
				if (counter < 60) {
					if (counter == 30) {
						localDateTime = ueGraphModel.getLocalDateTime();
					}
					avgSumOfUe = avgSumOfUe + ueGraphModel.getUeCount();
					counter++;
				} else {
					avgSumOfUe = avgSumOfUe / 60;
					ueGraphDTOs.add(new UeGraphDTO(localDateTime, avgSumOfUe));
					counter = 0;
				}
			}

			return ueGraphDTOs;
		} else if (range.equals("week")) {
			ArrayList<UeGraphModel> ueGraphModelsRawData = ueGraphRepository.returnUeData(2L, 10080);

			Integer counter = 0;
			Integer avgSumOfUe = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<UeGraphDTO> ueGraphDTOs = new ArrayList<>();
			for (UeGraphModel ueGraphModel : ueGraphModelsRawData) {
				if (counter < 1440) {
					if (counter == 720) {
						localDateTime = ueGraphModel.getLocalDateTime();
					}
					avgSumOfUe = avgSumOfUe + ueGraphModel.getUeCount();
					counter++;
				} else {
					avgSumOfUe = avgSumOfUe / 1440;
					ueGraphDTOs.add(new UeGraphDTO(localDateTime, avgSumOfUe));
					counter = 0;
				}
			}

			return ueGraphDTOs;
		} else {
			loggerTypeB.warn("getGnbGraph: Unrecognized range specified");
			return null;
		}

	}

	@Override
	public ArrayList<CombineGraphData> getGnbGraph(String range){
		loggerTypeB.info("getThroughputGraph: Requested range for throughput graph data");
		if (range.equals("hour")) {
			ArrayList<LiveDataModel> gnbGraphModels = liveDataRepository.returnGnbData(60);
			ArrayList<CombineGraphData> graphDatas = new ArrayList<>();
			for (LiveDataModel liveDataModel : gnbGraphModels) {
				graphDatas.add(new CombineGraphData(liveDataModel.getActiveamf(), liveDataModel.getActiveGnb(),
						liveDataModel.getActiveUe(),liveDataModel.getActiveUeSession(),liveDataModel.getLocalDateTime()));
			}
			return graphDatas;
		} else if (range.equals("day")) {
			
		
			ArrayList<LiveDataModel> gnbGraphModels = liveDataRepository.returnGnbData(1440);
			Integer counter = 0;
			Integer avgSumOfGnb = 0;
			Integer avgSumOfUe = 0;
			Integer avgSumOfCore = 0;
			Integer avgSumOfUeSession = 0;
			
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<CombineGraphData> graphDatas = new ArrayList<>();
			for (LiveDataModel liveDataModel : gnbGraphModels) {
				if (counter < 60) {
					if (counter == 30) {
						localDateTime = liveDataModel.getLocalDateTime();
					}
					avgSumOfGnb = avgSumOfGnb +  Integer.parseInt(liveDataModel.getActiveGnb());
					avgSumOfUe = avgSumOfUe +  Integer.parseInt(liveDataModel.getActiveUe());
					avgSumOfCore = avgSumOfCore +  Integer.parseInt(liveDataModel.getActiveamf());
					avgSumOfUeSession = avgSumOfUeSession + Integer.parseInt(liveDataModel.getActiveAmfSession());
					
					counter++;
				} else {
					avgSumOfGnb = avgSumOfGnb / 60;
					avgSumOfUe  =  avgSumOfUe / 60;
					avgSumOfCore = avgSumOfCore / 60;
					avgSumOfUeSession = avgSumOfUeSession / 60; 
					graphDatas.add(new CombineGraphData(avgSumOfCore.toString(), avgSumOfGnb.toString(),
							avgSumOfUe.toString(),avgSumOfUeSession.toString(),localDateTime));
					counter = 0;
				}
			}

			return graphDatas;
			
		} else if (range.equals("week")) {
			ArrayList<LiveDataModel> gnbGraphModels = liveDataRepository.returnGnbData(10080);
			Integer counter = 0;
			Integer avgSumOfGnb = 0;
			Integer avgSumOfUe = 0;
			Integer avgSumOfCore = 0;
			Integer avgSumOfUeSession = 0;
			
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<CombineGraphData> graphDatas = new ArrayList<>();
			for (LiveDataModel liveDataModel : gnbGraphModels) {
				if (counter < 1440) {
					if (counter == 720) {
						localDateTime = liveDataModel.getLocalDateTime();
					}
					avgSumOfGnb = avgSumOfGnb +  Integer.parseInt(liveDataModel.getActiveGnb());
					avgSumOfUe = avgSumOfUe +  Integer.parseInt(liveDataModel.getActiveUe());
					avgSumOfCore = avgSumOfCore +  Integer.parseInt(liveDataModel.getActiveamf());
					avgSumOfUeSession = avgSumOfUeSession + Integer.parseInt(liveDataModel.getActiveAmfSession());
					counter++;
				} else {
					avgSumOfGnb = avgSumOfGnb / 1440;
					avgSumOfUe  =  avgSumOfUe /1440;
					avgSumOfCore = avgSumOfCore / 1440;
					avgSumOfUeSession = avgSumOfUeSession / 1440; 
					graphDatas.add(new CombineGraphData(avgSumOfCore.toString(), avgSumOfGnb.toString(),
							avgSumOfUe.toString(),avgSumOfUeSession.toString(),localDateTime));
					counter = 0;
				}
			}

			return graphDatas;
		} else {
			return null;}
		}


	@Override
	public ArrayList<ThroughputGraphFrontendDto> getThroughputGraph(String range, String tenentId, String siteId)
	{
		if (range.equals("hour")) {
			ArrayList<ThroughputModel> throughputGraphModels = throughputRepository.returnUlandDlData(60);
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputGraphModels) {

				throughputGraphDtos.add(new ThroughputGraphFrontendDto(throughputModel.getLocalDateTime(),
						throughputModel.getUplinkBytes(), throughputModel.getDownlinkBytes()));

			}
			return throughputGraphDtos;

		} else if (range.equals("day")) {

			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(1440);
			Integer counter = 0;
			Integer avgSumOfUl = 0;
			Integer avgSumOfDl = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputModels) {
				if (counter < 60) {
					if (counter == 30) {
						localDateTime = throughputModel.getLocalDateTime();
					}
					avgSumOfUl = avgSumOfUl + throughputModel.getUplinkBytes();
					avgSumOfDl = avgSumOfDl + throughputModel.getDownlinkBytes();
					counter++;
				} else {
					avgSumOfDl = avgSumOfDl / 60;
					avgSumOfUl = avgSumOfUl / 60;
					throughputGraphDtos.add(new ThroughputGraphFrontendDto(localDateTime, avgSumOfUl, avgSumOfDl));
					counter = 0;

				}
			}
			return throughputGraphDtos;
		} else if (range.equals("week")) {
			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(10080);
			Integer counter = 0;
			Integer avgSumOfUl = 0;
			Integer avgSumOfDl = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputModels) {
				if (counter < 1440) {
					if (counter == 720) {
						localDateTime = throughputModel.getLocalDateTime();
					}
					avgSumOfUl = avgSumOfUl + throughputModel.getUplinkBytes();
					avgSumOfDl = avgSumOfDl + throughputModel.getDownlinkBytes();
					counter++;
				} else {
					avgSumOfDl = avgSumOfDl / 1440;
					avgSumOfUl = avgSumOfUl / 1440;
					loggerTypeB.info("getThroughputGraph: Added averaged throughput data point for time");
					throughputGraphDtos.add(new ThroughputGraphFrontendDto(localDateTime, avgSumOfUl, avgSumOfDl));
					counter = 0;

				}
			}

			return throughputGraphDtos;

		}
		return null;

	}

	@Override
	public void upgErrorLastDataForDeltaValue() {
		loggerTypeB.info("upgErrorLastDataForDeltaValue: Starting processing of UPG errors for delta value calculation.");
		List<UpgErrorModel> arrayList = upgErrorRepository.findAll();
		for (UpgErrorModel upgErrorModel : arrayList) {

			if (upgErrorDeltaModelRepository.countDatainDelta() == 0) {
				loggerTypeB.info("upgErrorLastDataForDeltaValue: No delta data found. Creating new UPG error delta model.");
				UpgErrorDeltaModel errorDeltaModel = new UpgErrorDeltaModel();
				// Setting values from the error model to the delta model
				errorDeltaModel.setThread(upgErrorModel.getThread());
				errorDeltaModel.setNat44_ed_out2in_slowpath_no_translation(
						upgErrorModel.getNat44_ed_out2in_slowpath_no_translation());
				errorDeltaModel.setDpdk_input_no_error(upgErrorModel.getDpdk_input_no_error());
				errorDeltaModel.setIp4_input_Multicast_RPF_check_failed(
						upgErrorModel.getIp4_input_Multicast_RPF_check_failed());
				errorDeltaModel
						.setIp4_local_ip4_source_lookup_miss(upgErrorModel.getIp4_local_ip4_source_lookup_miss());
				errorDeltaModel.setIp4_icmp_input_unknown_type(upgErrorModel.getIp4_icmp_input_unknown_type());
				errorDeltaModel.setIgmp_input_IGMP_not_enabled_on_this_interface(
						upgErrorModel.getIgmp_input_IGMP_not_enabled_on_this_interface());
				errorDeltaModel.setArp_reply_ARP_replies_sent(upgErrorModel.getArp_reply_ARP_replies_sent());
				errorDeltaModel.setArp_input_IP4_destination_address_is_unset(
						upgErrorModel.getArp_input_IP4_destination_address_is_unset());
				errorDeltaModel.setUpf_ip4_flow_process_packets_with_an_existing_flow(
						upgErrorModel.getUpf_ip4_flow_process_packets_with_an_existing_flow());
				errorDeltaModel.setUpf_ip4_flow_process_packets_gone_through(
						upgErrorModel.getUpf_ip4_flow_process_packets_gone_through());
				errorDeltaModel.setUpf_ip4_flow_process_packets_which_created_a_new_flow(
						upgErrorModel.getUpf_ip4_flow_process_packets_which_created_a_new_flow());
				errorDeltaModel.setUpf_gtpu4_input_good_packets_decapsulated(
						upgErrorModel.getUpf_gtpu4_input_good_packets_decapsulated());
				errorDeltaModel.setUpf4_encap_good_packets_encapsulated(
						upgErrorModel.getUpf4_encap_good_packets_encapsulated());
				errorDeltaModel
						.setSession_queue_no_buffer(upgErrorModel.getSession_queue_no_buffer());
				errorDeltaModel.setUdp4_input_enqueued(upgErrorModel.getUdp4_input_enqueued());

				errorDeltaModel.setLocal0_output_interface_is_down(upgErrorModel.getLocal0_output_interface_is_down());
				errorDeltaModel
						.setLocal0_output_interface_is_deleted(upgErrorModel.getLocal0_output_interface_is_deleted());
				errorDeltaModel.setLocal0_output_no_tx_queue_available(upgErrorModel.getLocal0_output_no_tx_queue_available());
				errorDeltaModel.setN3_GNB_output_interface_is_down(upgErrorModel.getN3_GNB_output_interface_is_down());
				errorDeltaModel
						.setN3_GNB_output_interface_is_deleted(upgErrorModel.getN3_GNB_output_interface_is_deleted());
				errorDeltaModel.setN3_GNB_output_no_buffers_to_segment_GSO(
						upgErrorModel.getN3_GNB_output_no_buffers_to_segment_GSO());
				errorDeltaModel.setN3_GNB_tx_DPDK_tx_function_returned_an_error(
						upgErrorModel.getN3_GNB_tx_DPDK_tx_function_returned_an_error());
				errorDeltaModel.setN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(
						upgErrorModel.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure());
				errorDeltaModel.setN4_SMF_output_interface_is_down(upgErrorModel.getN4_SMF_output_interface_is_down());
				errorDeltaModel
						.setN4_SMF_output_interface_is_deleted(upgErrorModel.getN4_SMF_output_interface_is_deleted());
				errorDeltaModel.setN4_SMF_output_no_buffers_to_segment_GSO(
						upgErrorModel.getN4_SMF_output_no_buffers_to_segment_GSO());
				errorDeltaModel.setN4_SMF_tx_DPDK_tx_function_returned_an_error(
						upgErrorModel.getN4_SMF_tx_DPDK_tx_function_returned_an_error());
				errorDeltaModel.setN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(
						upgErrorModel.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure());
				errorDeltaModel.setN6_DN_output_interface_is_down(upgErrorModel.getN6_DN_output_interface_is_down());
				errorDeltaModel
						.setN6_DN_output_interface_is_deleted(upgErrorModel.getN6_DN_output_interface_is_deleted());
				errorDeltaModel.setN6_DN_output_no_buffers_to_segment_GSO(
						upgErrorModel.getN6_DN_output_no_buffers_to_segment_GSO());
				errorDeltaModel.setN6_DN_tx_DPDK_tx_function_returned_an_error(
						upgErrorModel.getN6_DN_tx_DPDK_tx_function_returned_an_error());
				errorDeltaModel.setN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(
						upgErrorModel.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure());
				errorDeltaModel.setMEC_output_interface_is_down(upgErrorModel.getMEC_output_interface_is_down());
				errorDeltaModel.setMEC_output_interface_is_deleted(upgErrorModel.getMEC_output_interface_is_deleted());
				errorDeltaModel.setMEC_output_no_buffers_to_segment_GSO(
						upgErrorModel.getMEC_output_no_buffers_to_segment_GSO());
				errorDeltaModel.setMEC_tx_DPDK_tx_function_returned_an_error(
						upgErrorModel.getMEC_tx_DPDK_tx_function_returned_an_error());
				errorDeltaModel.setMEC_tx_Tx_packet_drops__dpdk_tx_failure(
						upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure());
				errorDeltaModel.setNfName(upgErrorModel.getNfName());
				errorDeltaModel.setNfType(upgErrorModel.getNfType());

				upgErrorDeltaModelRepository.save(errorDeltaModel);

			} else {
				// Update the existing delta model with new values
				upgErrorDeltaModelRepository.updateError(upgErrorModel.getNat44_ed_out2in_slowpath_no_translation(),
						upgErrorModel.getDpdk_input_no_error(), upgErrorModel.getIp4_input_Multicast_RPF_check_failed(),
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
						upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure(),
						upgErrorModel.getThread(),
						upgErrorModel.getNfName(),upgErrorModel.getNfType());

			}

		}

	}

	@Override
	public void upfErrorLastDataForDeltaValue() {
		// Retrieve all UPF error models from the repository
		List<UpfErrorModel> arrayList = upfErrorModelRepository.findAll();
		loggerTypeB.info("Retrieved UPF error models from the repository.");
		for (UpfErrorModel upfErrorModel : arrayList) {

			if (upfErrorDeltaModelRepository.countDatainDelta() == 0) {
				UpfErrorDeltaModel errorDeltaModel = new UpfErrorDeltaModel();

				errorDeltaModel.setFar_not_found_upf(upfErrorModel.getFar_not_found_upf());
				errorDeltaModel.setGtpu_packet_decode_failure_upf(upfErrorModel.getGtpu_packet_decode_failure_upf());
				errorDeltaModel.setInvalid_eth_type_upf(upfErrorModel.getInvalid_eth_type_upf());
				errorDeltaModel.setTun_write_failure_upf(upfErrorModel.getTun_write_failure_upf());
				errorDeltaModel.setInvalid_gtpu_version_upf(upfErrorModel.getInvalid_gtpu_version_upf());
				errorDeltaModel.setNo_outer_header_creation_upf(upfErrorModel.getNo_outer_header_creation_upf());
				errorDeltaModel.setSmall_gtpu_packet_upf(upfErrorModel.getSmall_gtpu_packet_upf());
				errorDeltaModel.setTun_open_failure_upf(upfErrorModel.getTun_open_failure_upf());
				errorDeltaModel.setNfName(upfErrorModel.getNfName());
				errorDeltaModel.setNfType(upfErrorModel.getNfType());

				upfErrorDeltaModelRepository.save(errorDeltaModel);

			} else {
				// Fetch the last updated UPF error model by nfType and nfName
				UpfErrorModel lastUpdatedModel =upfErrorModelRepository.findByNfTypeAndNfName(upfErrorModel.getNfType(),upfErrorModel.getNfName());

	            if (lastUpdatedModel != null) {
					loggerTypeB.info("Updating existing UPF error delta model for nfNameandnfType");
					// Update the corresponding UpfErrorDeltaModel with the values from the last updated UpfErrorModel
	                upfErrorDeltaModelRepository.updateUpfError(
	                    lastUpdatedModel.getTun_write_failure_upf(),
	                    lastUpdatedModel.getInvalid_eth_type_upf(),
	                    lastUpdatedModel.getInvalid_gtpu_version_upf(),
	                    lastUpdatedModel.getSmall_gtpu_packet_upf(),
	                    lastUpdatedModel.getNo_outer_header_creation_upf(),
	                    lastUpdatedModel.getTun_open_failure_upf(),
	                    lastUpdatedModel.getFar_not_found_upf(),
	                    lastUpdatedModel.getGtpu_packet_decode_failure_upf(),
	                    upfErrorModel.getNfName(),
	                    upfErrorModel.getNfType());
	            }}
		}
	}

	@Override
	public synchronized void saveUpgInterfaceData() {
		// Retrieve all UPG models from the repository
		List<UpgModel> models = upgRepository.returnAllData();
		for (UpgModel upgModel : models) {
			
			try {
				// Save N3 interface data to the repository
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N3", LocalDateTime.now(),
					upgRepository.getRxPackets(" N3 ")
							- upgRepository.getPreviousRxPackets(" N3 "),
					upgRepository.getrxBytes(" N3 ")
					- upgRepository.getPreviousrxbytes(" N3 "),
					
					upgRepository.getTxPackets(" N3 ")
							- upgRepository.getPreviousTxPackets(" N3 "),
							
					upgRepository.gettxBytes(" N3 ")
					- upgRepository.getPreviousTxbytes(" N3 "),
					upgRepository.getdrops(" N3 ")));
			}catch (Exception e) {
				loggerTypeB.info("saveUpgInterfaceData: N3 Data is not there in Database");
			}
			try {
				// Save N4 interface data to the repository
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N4", LocalDateTime.now(),
					upgRepository.getRxPackets(" N4 ")
							- upgRepository.getPreviousRxPackets(" N4 "),
					upgRepository.getrxBytes(" N4 ")
					- upgRepository.getPreviousrxbytes(" N4 "),
					upgRepository.getTxPackets(" N4 ")
							- upgRepository.getPreviousTxPackets(" N4 "),
					upgRepository.gettxBytes(" N4 ")
					- upgRepository.getPreviousTxbytes(" N4 "),
					upgRepository.getdrops(" N4 ")));
			}
			catch (Exception e) {
				loggerTypeB.error("saveUpgInterfaceData: N4 Data is not there in Database");
			}
			try {
				// Save N6 interface data to the repository
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N6", LocalDateTime.now(),
					upgRepository.getRxPackets(" N6 ")
							- upgRepository.getPreviousRxPackets(" N6 "),
					upgRepository.getrxBytes(" N6 ")
					- upgRepository.getPreviousrxbytes(" N6 "),
					upgRepository.getTxPackets(" N6 ")
							- upgRepository.getPreviousTxPackets(" N6 "),
					upgRepository.gettxBytes(" N6 ")
					- upgRepository.getPreviousTxbytes(" N6 "),
					upgRepository.getdrops(" N6 ")));
			}catch (Exception e) {
				loggerTypeB.error("saveUpgInterfaceData: N6 Data is not there in Database");
			}
//
		}

	}

	@Override
	public void saveUpgErrorGraphData() {
		// Set the current date and time without nanoseconds for consistency
		LocalDateTime dateTime= LocalDateTime.now().withNano(0);
		loggerTypeB.info("saveUpgErrorGraphData: Starting the process to save UPG error graph data");
		List<UpgErrorModel> upgErrorModels = upgErrorRepository.tenentSiteData();
		// Iterate over each UPG error model
		for (UpgErrorModel upgErrorModel : upgErrorModels) {
			loggerTypeB.debug("saveUpgErrorGraphData: Processing UPG error model");
			if (upgErrorDeltaModelRepository.countUpgErrorDeltaData() == 0) {
				List<UpgErrorModel> upgErrorModelsrepo = upgErrorRepository.subtractFromZero(
						 upgErrorModel.getNfName(),
						upgErrorModel.getNfType());
				for (UpgErrorModel upgErrorModel2 : upgErrorModelsrepo) {
					
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"nat44_ed_out2in_slowpath", "no_translation",
							upgErrorModel2.getNat44_ed_out2in_slowpath_no_translation()));
					
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"dpdk_input", "no_error", upgErrorModel2.getDpdk_input_no_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_input_Multicast", "RPF_check_failed",
							upgErrorModel2.getIp4_input_Multicast_RPF_check_failed()));
					
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_local", "ip4_source_lookup_miss", upgErrorModel2.getIp4_local_ip4_source_lookup_miss()));
					
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_icmp_input", "unknown_type", upgErrorModel2.getIp4_icmp_input_unknown_type()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"igmp_input", "IGMP_not_enabled_on_this_interface",
							upgErrorModel2.getIgmp_input_IGMP_not_enabled_on_this_interface()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"arp_reply", "ARP_replies_sent", upgErrorModel2.getArp_reply_ARP_replies_sent()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"arp_input", "IP4_destination_address_is_unset",
							upgErrorModel2.getArp_input_IP4_destination_address_is_unset()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_with_an_existing_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_with_an_existing_flow()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_gone_through",
							upgErrorModel2.getUpf_ip4_flow_process_packets_gone_through()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_which_created_a_new_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_which_created_a_new_flow()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_gtpu4_input", "good_packets_decapsulated",
							upgErrorModel2.getUpf_gtpu4_input_good_packets_decapsulated()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf4_encap", "good_packets_encapsulated",
							upgErrorModel2.getUpf4_encap_good_packets_encapsulated()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"session_queue", "Packets_transmitted",
							upgErrorModel2.getSession_queue_no_buffer()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"udp4_input", "Packets_enqueued", upgErrorModel2.getUdp4_input_enqueued()));

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_interface_is_down", upgErrorModel2.getLocal0_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_interface_is_deleted",
							upgErrorModel2.getLocal0_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getLocal0_output_no_tx_queue_available()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_interface_is_down", upgErrorModel2.getN3_GNB_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_interface_is_deleted",
							upgErrorModel2.getN3_GNB_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN3_GNB_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN3_GNB_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_interface_is_down", upgErrorModel2.getN4_SMF_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_interface_is_deleted",
							upgErrorModel2.getN4_SMF_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN4_SMF_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN4_SMF_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_interface_is_down", upgErrorModel2.getN6_DN_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_interface_is_deleted",
							upgErrorModel2.getN6_DN_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN6_DN_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN6_DN_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_interface_is_down", upgErrorModel2.getMEC_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_interface_is_deleted", upgErrorModel2.getMEC_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getMEC_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getMEC_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getMEC_tx_Tx_packet_drops__dpdk_tx_failure()));
				}
			} else {
				loggerTypeB.info("saveUpgErrorGraphData: UPG error delta data exists. Performing data subtraction.");
				List<UpgErrorDeltaModel> upgErrorModelsrepo = upgErrorDeltaModelRepository.subtractDaata(
					 upgErrorModel.getNfName(),
						upgErrorModel.getNfType());
				for (UpgErrorDeltaModel upgErrorModel2 : upgErrorModelsrepo) {

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"nat44_ed_out2in_slowpath", "no_translation",
							upgErrorModel2.getNat44_ed_out2in_slowpath_no_translation()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"dpdk_input", "no_error", upgErrorModel2.getDpdk_input_no_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_input_Multicast", "RPF_check_failed",
							upgErrorModel2.getIp4_input_Multicast_RPF_check_failed()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_local", "ip4_source_lookup_miss", upgErrorModel2.getIp4_local_ip4_source_lookup_miss()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_icmp_input", "unknown_type", upgErrorModel2.getIp4_icmp_input_unknown_type()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"igmp_input", "IGMP_not_enabled_on_this_interface",
							upgErrorModel2.getIgmp_input_IGMP_not_enabled_on_this_interface()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"arp_reply", "ARP_replies_sent", upgErrorModel2.getArp_reply_ARP_replies_sent()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"arp_input", "IP4_destination_address_is_unset",
							upgErrorModel2.getArp_input_IP4_destination_address_is_unset()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_with_an_existing_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_with_an_existing_flow()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_gone_through",
							upgErrorModel2.getUpf_ip4_flow_process_packets_gone_through()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_which_created_a_new_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_which_created_a_new_flow()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,dateTime, "Node",
							"upf_gtpu4_input", "good_packets_decapsulated",
							upgErrorModel2.getUpf_gtpu4_input_good_packets_decapsulated()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf4_encap", "good_packets_encapsulated",
							upgErrorModel2.getUpf4_encap_good_packets_encapsulated()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"session_queue", "Packets_transmitted",
							upgErrorModel2.getSession_queue_no_buffer()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"udp4_input", "Packets_enqueued", upgErrorModel2.getUdp4_input_enqueued()));

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_interface_is_down", upgErrorModel2.getLocal0_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_interface_is_deleted",
							upgErrorModel2.getLocal0_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getLocal0_output_no_tx_queue_available()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_interface_is_down", upgErrorModel2.getN3_GNB_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_interface_is_deleted",
							upgErrorModel2.getN3_GNB_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN3_GNB_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN3_GNB_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_interface_is_down", upgErrorModel2.getN4_SMF_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_interface_is_deleted",
							upgErrorModel2.getN4_SMF_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN4_SMF_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN4_SMF_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_interface_is_down", upgErrorModel2.getN6_DN_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_interface_is_deleted",
							upgErrorModel2.getN6_DN_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN6_DN_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN6_DN_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_interface_is_down", upgErrorModel2.getMEC_output_interface_is_down()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_interface_is_deleted", upgErrorModel2.getMEC_output_interface_is_deleted()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getMEC_output_no_buffers_to_segment_GSO()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getMEC_tx_DPDK_tx_function_returned_an_error()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getMEC_tx_Tx_packet_drops__dpdk_tx_failure()));

				}
			}
		}
	}

	@Override
	public void saveUpfErrorGraphData() {
		LocalDateTime dateTime= LocalDateTime.now().withNano(0);
		loggerTypeB.info("saveUpfErrorGraphData: Starting the process to save UPF error graph data at {}", dateTime);
		List<UpfErrorModel> upfErrorModels = upfErrorModelRepository.tenentSiteData();
		

		for (UpfErrorModel upfErrorModel : upfErrorModels) {
	        Integer count = upfErrorDeltaModelRepository.countUpfErrorDeltaData();
		
			if (count == 0) {
				loggerTypeB.info("saveUpfErrorGraphData: No UPF error delta data found. Performing initial data save from zero.");
				System.out.println("222222222222222222222222222222//////////////////////////////////////////////");
				List<UpfErrorModel> upfErrorModelsrepo = upfErrorModelRepository.subtractFromZero(
						 upfErrorModel.getNfName(),
						upfErrorModel.getNfType());
				System.out.println("11111111111111111111111111111111111//////////////////////////////////////////////");
				for (UpfErrorModel upfErrorModel2 : upfErrorModelsrepo) {
					// Adjust the model and field names for UpfErrorModel
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L,dateTime,
							"tun_write_failure_upf", upfErrorModel2.getTun_write_failure_upf()));
					
					
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_eth_type_upf", upfErrorModel2.getInvalid_eth_type_upf()));
					
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_gtpu_version_upf", upfErrorModel2.getInvalid_gtpu_version_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"small_gtpu_packet_upf", upfErrorModel2.getSmall_gtpu_packet_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"no_outer_header_creation_upf", upfErrorModel2.getNo_outer_header_creation_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_open_failure_upf", upfErrorModel2.getTun_open_failure_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"far_not_found_upf", upfErrorModel2.getFar_not_found_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"gtpu_packet_decode_failure_upf", upfErrorModel2.getGtpu_packet_decode_failure_upf()));
//System.out.println("11111111111111111111111111111111111//////////////////////////////////////////////");
				}
			} else {
				loggerTypeB.info("saveUpfErrorGraphData: UPF error delta data exists. Performing data subtraction.");
				List<UpfErrorDeltaModel> upfErrorModelsrepo = upfErrorDeltaModelRepository.subtractData(
						 upfErrorModel.getNfName(),
						upfErrorModel.getNfType());
				for (UpfErrorDeltaModel upfErrorModel2 : upfErrorModelsrepo) {
					loggerTypeB.debug("saveUpfErrorGraphData: Saving UPF error graph data for delta model.");
					// Adjust the model and field names for UpfErrorDeltaModel

					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_write_failure_upf", upfErrorModel2.getTun_write_failure_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_eth_type_upf", upfErrorModel2.getInvalid_eth_type_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_gtpu_version_upf", upfErrorModel2.getInvalid_gtpu_version_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"small_gtpu_packet_upf", upfErrorModel2.getSmall_gtpu_packet_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"no_outer_header_creation_upf", upfErrorModel2.getNo_outer_header_creation_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_open_failure_upf", upfErrorModel2.getTun_open_failure_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"far_not_found_upf", upfErrorModel2.getFar_not_found_upf()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"gtpu_packet_decode_failure_upf", upfErrorModel2.getGtpu_packet_decode_failure_upf()));
					
					
					
					System.out.println("555555555555555555555555555555");
				}
			}
		}
	}

	@Override
	public ArrayList<UpgFrontendDto> getN3N4N6Graphdata(String interfaceType, Integer limit) {
		loggerTypeB.info("getN3N4N6Graphdata: Fetching UPF interface graph data for interface type");
		List<UpgInterface> list = upgInterfaceDataRepository.returnUpg(interfaceType, limit);
		ArrayList<UpgFrontendDto> arrayList = new ArrayList<>();
		for (UpgInterface upgInterface : list) {
			UpgFrontendDto model = new UpgFrontendDto();
			model.setLocalDateTime(upgInterface.getLocalDateTime());
			model.setInterfaceName(upgInterface.getInterfaceName());
			model.setRx_packets(upgInterface.getRxPackets());
			model.setRxBytes(upgInterface.getRxBytes());
			model.setTx_packets(upgInterface.getTxPackets());
			model.setTxBytes(upgInterface.getTxBytes());
			model.setDrops(upgInterface.getDrops());
			arrayList.add(model);
		}

		return arrayList;
	}

}
