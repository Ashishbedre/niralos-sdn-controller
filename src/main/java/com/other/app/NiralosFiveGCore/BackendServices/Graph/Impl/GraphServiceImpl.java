package com.other.app.NiralosFiveGCore.BackendServices.Graph.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.other.app.NiralosFiveGCore.model.Graph.GnbGraphModel;
import com.other.app.NiralosFiveGCore.model.Graph.UeGraphModel;

@Service
@EnableScheduling
public class GraphServiceImpl implements GraphService {
	final Logger logger = LoggerFactory.getLogger(GnbRegistrationDeregistrationFailureCountServiceImpl.class);

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
					Integer.parseInt(liveDataModel.getActiveGnb()), liveDataModel.getControllerClientId(),
					liveDataModel.getSiteId(), liveDataModel.getTenentId()));
		}
	}

	@Override
	@Scheduled(fixedRate = 60000)
	public void saveUeGraph() {
		List<LiveDataModel> liveDataModels = liveDataRepository.getliveData();
		for (LiveDataModel liveDataModel : liveDataModels) {
			ueGraphRepository.save(new UeGraphModel(2L, LocalDateTime.now(),
					Integer.parseInt(liveDataModel.getActiveUe()), liveDataModel.getControllerClientId(),
					liveDataModel.getSiteId(), liveDataModel.getTenentId()));
		}

	}

	@Override
	public ArrayList<UeGraphDTO> getUegraph(String range)
	{
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
			return null;
		}

	}

	@Override
	public ArrayList<GnbGraphDTO> getGnbGraph(String range)
	{
		if (range.equals("hour")) {
			ArrayList<GnbGraphModel> gnbGraphModels = gnbGraphRepository.returnGnbData(2L, 60);

			ArrayList<GnbGraphDTO> gnbGraphDTOs = new ArrayList<>();
			for (GnbGraphModel gnbGraphModel : gnbGraphModels) {
				gnbGraphDTOs.add(new GnbGraphDTO(gnbGraphModel.getLocalDateTime(), gnbGraphModel.getGnbCount()));
			}
			return gnbGraphDTOs;

		} else if (range.equals("day")) {

			ArrayList<GnbGraphModel> gnbGraphModels = gnbGraphRepository.returnGnbData(2L, 1440);
			Integer counter = 0;
			Integer avgSumOfGnb = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<GnbGraphDTO> gnbGraphDTOs = new ArrayList<>();
			for (GnbGraphModel gnbGraphModel : gnbGraphModels) {
				if (counter < 60) {
					if (counter == 30) {
						localDateTime = gnbGraphModel.getLocalDateTime();
					}
					avgSumOfGnb = avgSumOfGnb + gnbGraphModel.getGnbCount();
					counter++;
				} else {
					avgSumOfGnb = avgSumOfGnb / 60;
					gnbGraphDTOs.add(new GnbGraphDTO(localDateTime, avgSumOfGnb));
					counter = 0;
				}
			}

			return gnbGraphDTOs;
		} else if (range.equals("week")) {
			ArrayList<GnbGraphModel> gnbGraphModels = gnbGraphRepository.returnGnbData(2L, 10080);
			Integer counter = 0;
			Integer avgSumOfGnb = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<GnbGraphDTO> gnbGraphDTOs = new ArrayList<>();
			for (GnbGraphModel gnbGraphModel : gnbGraphModels) {
				if (counter < 1440) {
					if (counter == 720) {
						localDateTime = gnbGraphModel.getLocalDateTime();
					}
					avgSumOfGnb = avgSumOfGnb + gnbGraphModel.getGnbCount();
					counter++;
				} else {
					avgSumOfGnb = avgSumOfGnb / 1440;
					gnbGraphDTOs.add(new GnbGraphDTO(localDateTime, avgSumOfGnb));
					counter = 0;
				}
			}

			return gnbGraphDTOs;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<ThroughputGraphFrontendDto> getThroughputGraph(String range, String tenentId, String siteId)
	{
		if (range.equals("hour")) {
			ArrayList<ThroughputModel> throughputGraphModels = throughputRepository.returnUlandDlData(60, tenentId,
					siteId);
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputGraphModels) {

				throughputGraphDtos.add(new ThroughputGraphFrontendDto(throughputModel.getLocalDateTime(),
						throughputModel.getUplinkBytes(), throughputModel.getDownlinkBytes()));

			}
			return throughputGraphDtos;

		} else if (range.equals("day")) {

			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(1440, tenentId,
					siteId);
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
			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(10080, tenentId,
					siteId);
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

		List<UpgErrorModel> arrayList = upgErrorRepository.findAll();
		for (UpgErrorModel upgErrorModel : arrayList) {

			if (upgErrorDeltaModelRepository.countDatainDelta(upgErrorModel.getTenentId(), upgErrorModel.getSiteId(),
					upgErrorModel.getAgentId()) == 0) {
				UpgErrorDeltaModel errorDeltaModel = new UpgErrorDeltaModel();
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
				errorDeltaModel.setSiteId(upgErrorModel.getSiteId());
				errorDeltaModel.setTenentId(upgErrorModel.getTenentId());
				errorDeltaModel.setAgentId(upgErrorModel.getAgentId());
				errorDeltaModel.setNfName(upgErrorModel.getNfName());
				errorDeltaModel.setNfType(upgErrorModel.getNfType());

				upgErrorDeltaModelRepository.save(errorDeltaModel);

			} else {
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
						upgErrorModel.getMEC_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getSiteId(),
						upgErrorModel.getTenentId(), upgErrorModel.getAgentId(), upgErrorModel.getThread(),
						upgErrorModel.getNfName(),upgErrorModel.getNfType());

			}

		}

	}

	@Override
	public void upfErrorLastDataForDeltaValue() {
		List<UpfErrorModel> arrayList = upfErrorModelRepository.findAll();
		for (UpfErrorModel upfErrorModel : arrayList) {

			if (upfErrorDeltaModelRepository.countDatainDelta(upfErrorModel.getTenentId(), upfErrorModel.getSiteId(),
					upfErrorModel.getAgentId()) == 0) {
				UpfErrorDeltaModel errorDeltaModel = new UpfErrorDeltaModel();

				errorDeltaModel.setFar_not_found_upf(upfErrorModel.getFar_not_found_upf());
				errorDeltaModel.setGtpu_packet_decode_failure_upf(upfErrorModel.getGtpu_packet_decode_failure_upf());
				errorDeltaModel.setInvalid_eth_type_upf(upfErrorModel.getInvalid_eth_type_upf());
				errorDeltaModel.setTun_write_failure_upf(upfErrorModel.getTun_write_failure_upf());
				errorDeltaModel.setInvalid_gtpu_version_upf(upfErrorModel.getInvalid_gtpu_version_upf());
				errorDeltaModel.setNo_outer_header_creation_upf(upfErrorModel.getNo_outer_header_creation_upf());
				errorDeltaModel.setSmall_gtpu_packet_upf(upfErrorModel.getSmall_gtpu_packet_upf());
				errorDeltaModel.setTun_open_failure_upf(upfErrorModel.getTun_open_failure_upf());
				errorDeltaModel.setSiteId(upfErrorModel.getSiteId());
				errorDeltaModel.setNfName(upfErrorModel.getNfName());
				errorDeltaModel.setNfType(upfErrorModel.getNfType());
				errorDeltaModel.setTenentId(upfErrorModel.getTenentId());
				errorDeltaModel.setAgentId(upfErrorModel.getAgentId());

				upfErrorDeltaModelRepository.save(errorDeltaModel);

			} else {
				UpfErrorModel lastUpdatedModel =upfErrorModelRepository.findByTenentIdAndSiteIdAndAgentIdAndNfTypeAndNfName(upfErrorModel.getTenentId(), upfErrorModel.getSiteId(), upfErrorModel.getAgentId(),upfErrorModel.getNfType(),upfErrorModel.getNfType());

	            if (lastUpdatedModel != null) {
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
	                    upfErrorModel.getSiteId(),
	                    upfErrorModel.getTenentId(),
	                    upfErrorModel.getAgentId(),
	                    upfErrorModel.getNfName(),
	                    upfErrorModel.getNfType());
	            }}
		}
	}

	@Override
	public synchronized void saveUpgInterfaceData() {

		List<UpgModel> models = upgRepository.returnAllData();
		for (UpgModel upgModel : models) {
			
			try {
				
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N3", LocalDateTime.now(),
					upgRepository.getRxPackets(" N3 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousRxPackets(" N3 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getrxBytes(" N3 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousrxbytes(" N3 ", upgModel.getTenentId(), upgModel.getSiteId()),
					
					upgRepository.getTxPackets(" N3 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousTxPackets(" N3 ", upgModel.getTenentId(), upgModel.getSiteId()),
							
					upgRepository.gettxBytes(" N3 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousTxbytes(" N3 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getdrops(" N3 ", upgModel.getTenentId(), upgModel.getSiteId()), upgModel.getAgentId(),
					upgModel.getSiteId(), upgModel.getTenentId()));
			}catch (Exception e) {
				logger.info("N3 Data is not there in Database");
			}
			try {
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N4", LocalDateTime.now(),
					upgRepository.getRxPackets(" N4 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousRxPackets(" N4 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getrxBytes(" N4 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousrxbytes(" N4 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getTxPackets(" N4 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousTxPackets(" N4 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.gettxBytes(" N4 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousTxbytes(" N4 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getdrops(" N4 ", upgModel.getTenentId(), upgModel.getSiteId()), upgModel.getAgentId(),
					upgModel.getSiteId(), upgModel.getTenentId()));
			}
			catch (Exception e) {
				logger.info("N4 Data is not there in Database");
			}
			try {
			upgInterfaceDataRepository.save(new UpgInterface(2L, "N6", LocalDateTime.now(),
					upgRepository.getRxPackets(" N6 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousRxPackets(" N6 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getrxBytes(" N6 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousrxbytes(" N6 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getTxPackets(" N6 ", upgModel.getTenentId(), upgModel.getSiteId())
							- upgRepository.getPreviousTxPackets(" N6 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.gettxBytes(" N6 ", upgModel.getTenentId(), upgModel.getSiteId())
					- upgRepository.getPreviousTxbytes(" N6 ", upgModel.getTenentId(), upgModel.getSiteId()),
					upgRepository.getdrops(" N6 ", upgModel.getTenentId(), upgModel.getSiteId()), upgModel.getAgentId(),
					upgModel.getSiteId(), upgModel.getTenentId()));
			}catch (Exception e) {
				logger.info("N6 Data is not there in Database");
			}
//
		}

	}

	@Override
	public void saveUpgErrorGraphData() {
		LocalDateTime dateTime= LocalDateTime.now().withNano(0);
		List<UpgErrorModel> upgErrorModels = upgErrorRepository.tenentSiteData();
		for (UpgErrorModel upgErrorModel : upgErrorModels) {
			if (upgErrorDeltaModelRepository.countUpgErrorDeltaData() == 0) {
				List<UpgErrorModel> upgErrorModelsrepo = upgErrorRepository.subtractFromZero(
						upgErrorModel.getTenentId(), upgErrorModel.getSiteId(), upgErrorModel.getNfName(),
						upgErrorModel.getNfType());
				for (UpgErrorModel upgErrorModel2 : upgErrorModelsrepo) {
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"nat44_ed_out2in_slowpath", "no_translation",
							upgErrorModel2.getNat44_ed_out2in_slowpath_no_translation(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"dpdk_input", "no_error", upgErrorModel2.getDpdk_input_no_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_input_Multicast", "RPF_check_failed",
							upgErrorModel2.getIp4_input_Multicast_RPF_check_failed(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_local", "ip4_source_lookup_miss", upgErrorModel2.getIp4_local_ip4_source_lookup_miss(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"ip4_icmp_input", "unknown_type", upgErrorModel2.getIp4_icmp_input_unknown_type(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"igmp_input", "IGMP_not_enabled_on_this_interface",
							upgErrorModel2.getIgmp_input_IGMP_not_enabled_on_this_interface(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"arp_reply", "ARP_replies_sent", upgErrorModel2.getArp_reply_ARP_replies_sent(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"arp_input", "IP4_destination_address_is_unset",
							upgErrorModel2.getArp_input_IP4_destination_address_is_unset(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_with_an_existing_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_with_an_existing_flow(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_gone_through",
							upgErrorModel2.getUpf_ip4_flow_process_packets_gone_through(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_ip4_flow_process", "packets_which_created_a_new_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_which_created_a_new_flow(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf_gtpu4_input", "good_packets_decapsulated",
							upgErrorModel2.getUpf_gtpu4_input_good_packets_decapsulated(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"upf4_encap", "good_packets_encapsulated",
							upgErrorModel2.getUpf4_encap_good_packets_encapsulated(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"session_queue", "Packets_transmitted",
							upgErrorModel2.getSession_queue_no_buffer(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Node",
							"udp4_input", "Packets_enqueued", upgErrorModel2.getUdp4_input_enqueued(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_interface_is_down", upgErrorModel2.getLocal0_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_interface_is_deleted",
							upgErrorModel2.getLocal0_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"local0", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getLocal0_output_no_tx_queue_available(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_interface_is_down", upgErrorModel2.getN3_GNB_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_interface_is_deleted",
							upgErrorModel2.getN3_GNB_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN3_GNB_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN3_GNB_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N3_GNB", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_interface_is_down", upgErrorModel2.getN4_SMF_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_interface_is_deleted",
							upgErrorModel2.getN4_SMF_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN4_SMF_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N4_SMF", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN4_SMF_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_interface_is_down", upgErrorModel2.getN6_DN_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_interface_is_deleted",
							upgErrorModel2.getN6_DN_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN6_DN_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN6_DN_tx_DPDK_tx_function_returned_an_error(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"N6_DN", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_interface_is_down", upgErrorModel2.getMEC_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_interface_is_deleted", upgErrorModel2.getMEC_output_interface_is_deleted(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getMEC_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getMEC_tx_DPDK_tx_function_returned_an_error(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,  dateTime, "Interface",
							"MEC", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getMEC_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
				}
			} else {
				List<UpgErrorDeltaModel> upgErrorModelsrepo = upgErrorDeltaModelRepository.subtractDaata(
						upgErrorModel.getTenentId(), upgErrorModel.getSiteId(), upgErrorModel.getNfName(),
						upgErrorModel.getNfType());
				for (UpgErrorDeltaModel upgErrorModel2 : upgErrorModelsrepo) {

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"nat44_ed_out2in_slowpath", "no_translation",
							upgErrorModel2.getNat44_ed_out2in_slowpath_no_translation(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"dpdk_input", "no_error", upgErrorModel2.getDpdk_input_no_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_input_Multicast", "RPF_check_failed",
							upgErrorModel2.getIp4_input_Multicast_RPF_check_failed(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_local", "ip4_source_lookup_miss", upgErrorModel2.getIp4_local_ip4_source_lookup_miss(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"ip4_icmp_input", "unknown_type", upgErrorModel2.getIp4_icmp_input_unknown_type(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"igmp_input", "IGMP_not_enabled_on_this_interface",
							upgErrorModel2.getIgmp_input_IGMP_not_enabled_on_this_interface(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"arp_reply", "ARP_replies_sent", upgErrorModel2.getArp_reply_ARP_replies_sent(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"arp_input", "IP4_destination_address_is_unset",
							upgErrorModel2.getArp_input_IP4_destination_address_is_unset(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_with_an_existing_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_with_an_existing_flow(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_gone_through",
							upgErrorModel2.getUpf_ip4_flow_process_packets_gone_through(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf_ip4_flow_process", "packets_which_created_a_new_flow",
							upgErrorModel2.getUpf_ip4_flow_process_packets_which_created_a_new_flow(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L,dateTime, "Node",
							"upf_gtpu4_input", "good_packets_decapsulated",
							upgErrorModel2.getUpf_gtpu4_input_good_packets_decapsulated(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"upf4_encap", "good_packets_encapsulated",
							upgErrorModel2.getUpf4_encap_good_packets_encapsulated(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"session_queue", "Packets_transmitted",
							upgErrorModel2.getSession_queue_no_buffer(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Node",
							"udp4_input", "Packets_enqueued", upgErrorModel2.getUdp4_input_enqueued(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));

					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_interface_is_down", upgErrorModel2.getLocal0_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_interface_is_deleted",
							upgErrorModel2.getLocal0_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"local0", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getLocal0_output_no_tx_queue_available(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_interface_is_down", upgErrorModel2.getN3_GNB_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_interface_is_deleted",
							upgErrorModel2.getN3_GNB_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN3_GNB_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN3_GNB_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N3_GNB", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_interface_is_down", upgErrorModel2.getN4_SMF_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_interface_is_deleted",
							upgErrorModel2.getN4_SMF_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN4_SMF_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN4_SMF_tx_DPDK_tx_function_returned_an_error(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N4_SMF", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_interface_is_down", upgErrorModel2.getN6_DN_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_interface_is_deleted",
							upgErrorModel2.getN6_DN_output_interface_is_deleted(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getN6_DN_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getN6_DN_tx_DPDK_tx_function_returned_an_error(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"N6_DN", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_interface_is_down", upgErrorModel2.getMEC_output_interface_is_down(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_interface_is_deleted", upgErrorModel2.getMEC_output_interface_is_deleted(),
							upgErrorModel.getAgentId(), upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "output_no_buffers_to_segment_GSO",
							upgErrorModel2.getMEC_output_no_buffers_to_segment_GSO(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "tx_DPDK_tx_function_returned_an_error",
							upgErrorModel2.getMEC_tx_DPDK_tx_function_returned_an_error(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));
					upgErrorGraphModelRepository.save(new UpgErrorGraphModel(2L, dateTime, "Interface",
							"MEC", "tx_Tx_packet_drops__dpdk_tx_failure",
							upgErrorModel2.getMEC_tx_Tx_packet_drops__dpdk_tx_failure(), upgErrorModel.getAgentId(),
							upgErrorModel.getSiteId(), upgErrorModel.getTenentId()));

				}
			}
		}
	}

	@Override
	public void saveUpfErrorGraphData() {
		LocalDateTime dateTime= LocalDateTime.now().withNano(0);
		List<UpfErrorModel> upfErrorModels = upfErrorModelRepository.tenentSiteData();
		for (UpfErrorModel upfErrorModel : upfErrorModels) {
	        Integer count = upfErrorDeltaModelRepository.countUpfErrorDeltaData();
			if (count == 0) {
				List<UpfErrorModel> upfErrorModelsrepo = upfErrorModelRepository.subtractFromZero(
						upfErrorModel.getTenentId(), upfErrorModel.getSiteId(), upfErrorModel.getNfName(),
						upfErrorModel.getNfType());

				for (UpfErrorModel upfErrorModel2 : upfErrorModelsrepo) {
					// Adjust the model and field names for UpfErrorModel
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L,dateTime,
							"tun_write_failure_upf", upfErrorModel2.getTun_write_failure_upf(),
							
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_eth_type_upf", upfErrorModel2.getInvalid_eth_type_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_gtpu_version_upf", upfErrorModel2.getInvalid_gtpu_version_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"small_gtpu_packet_upf", upfErrorModel2.getSmall_gtpu_packet_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"no_outer_header_creation_upf", upfErrorModel2.getNo_outer_header_creation_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_open_failure_upf", upfErrorModel2.getTun_open_failure_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"far_not_found_upf", upfErrorModel2.getFar_not_found_upf(), upfErrorModel.getAgentId(),
							upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"gtpu_packet_decode_failure_upf", upfErrorModel2.getGtpu_packet_decode_failure_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));

				}
			} else {
				List<UpfErrorDeltaModel> upfErrorModelsrepo = upfErrorDeltaModelRepository.subtractData(
						upfErrorModel.getTenentId(), upfErrorModel.getSiteId(), upfErrorModel.getNfName(),
						upfErrorModel.getNfType());
				for (UpfErrorDeltaModel upfErrorModel2 : upfErrorModelsrepo) {
					
					// Adjust the model and field names for UpfErrorDeltaModel

					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_write_failure_upf", upfErrorModel2.getTun_write_failure_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_eth_type_upf", upfErrorModel2.getInvalid_eth_type_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"invalid_gtpu_version_upf", upfErrorModel2.getInvalid_gtpu_version_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"small_gtpu_packet_upf", upfErrorModel2.getSmall_gtpu_packet_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"no_outer_header_creation_upf", upfErrorModel2.getNo_outer_header_creation_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"tun_open_failure_upf", upfErrorModel2.getTun_open_failure_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"far_not_found_upf", upfErrorModel2.getFar_not_found_upf(), upfErrorModel.getAgentId(),
							upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
					upfErrorGraphModelRepository.save(new UpfErrorGraphModel(2L, dateTime,
							"gtpu_packet_decode_failure_upf", upfErrorModel2.getGtpu_packet_decode_failure_upf(),
							upfErrorModel.getAgentId(), upfErrorModel.getSiteId(), upfErrorModel.getTenentId()));
				}
			}
		}
	}

	@Override
	public ArrayList<UpgFrontendDto> getN3N4N6Graphdata(String interfaceType, Integer limit) {

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
