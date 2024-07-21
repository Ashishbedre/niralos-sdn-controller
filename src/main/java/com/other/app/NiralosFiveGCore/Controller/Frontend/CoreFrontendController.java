package com.other.app.NiralosFiveGCore.Controller.Frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgErrorService;
import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgService;
import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.UpgErrorFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.UpgServiceFontend;
import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbFailureListService;
import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbInfoService;
import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountService;
import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Frontend.GnbHistoryFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.GnbStatistics.Backend.GnBStatsCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.Graph.GraphService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LiveDataCollector;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LivenessCheckerService;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Frontend.LiveDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.CommonServices;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.PfcpInfoService;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.PfcpSessioEstablishmentModificationDelationFailReasonListService;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Frontend.PfcpFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.Throughput.Backend.ThroughputService;
import com.other.app.NiralosFiveGCore.BackendServices.Throughput.Frontend.ThroughputFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.Topology.TopologyService;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UeHistoryService;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UePduCauseInfoService;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UeRegistraionDeregistrationFailureCountService;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Frontend.UeHistoryFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Backend.UeStatsCollector;
import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.TopUserService;
import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.UeStatsFrontendService;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.FailureListFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.OverviewPageDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.RootUpgUpfErrorDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpfErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoRootFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend.ThroughputGraphFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.Topology.MainDTO;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeEntityFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeHistorDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UestatsPagesCount;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.UeStatsDistinct;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgUpfRoot;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;
import com.other.app.NiralosFiveGCore.model.UeStats.Frontend.TopUserModel;

import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/5gcoredata")
@CrossOrigin
public class CoreFrontendController {
	@Autowired
	GnbHistoryFrontendService gnbHistoryFrontendService;
	@Autowired
	UeHistoryFrontendService ueHistoryFrontendService;
	@Autowired
	PfcpFrontendService pfcpFrontendService;
	@Autowired
	ThroughputFrontendService throughputFrontendService;
	@Autowired
	UeStatsFrontendService ueStatsService;
	@Autowired
	GraphService graphService;
	@Autowired
	LiveDataFrontendService liveDataService;
	@Autowired
	TopUserService topUserService;
	@Autowired
	DeploymentRepository deploymentRepository;
	@Autowired
	TopologyService topologyService;
	@Autowired
	UpgErrorFrontendService upgErrorFrontendService;
	@Autowired
	UpgServiceFontend upgServiceFontend;

	
	//Internal Services
	@Autowired
	LiveDataCollector liveDataCollector;
	@Autowired
	LivenessCheckerService livenessCheckerService; 
	@Autowired
	UeStatsCollector ueStatsCollector ;
	@Autowired
	ThroughputService throughputService ;
	@Autowired
	UpgService upgService;
	@Autowired
	GnBStatsCollectorService statsCollectorService;
	@Autowired
	CommonServices commonServices;
 	@Autowired
	UeHistoryService ueHistoryService;
 	@Autowired
 	UePduCauseInfoService uePduCauseInfoService;
 	@Autowired
 	UeRegistraionDeregistrationFailureCountService ueRegistraionDeregistrationFailureCountService;
	@Autowired
	GnbFailureListService gnbFailureListService;
	@Autowired
	GnbInfoService gnbInfoService;
	@Autowired
	GnbRegistrationDeregistrationFailureCountService gnbRegistrationDeregistrationFailureCountService;
	@Autowired
	PfcpInfoService pfcpInfoService;
	@Autowired
	PfcpSessioEstablishmentModificationDelationFailReasonListService sessioEstablishmentModificationDelationFailReasonListService;
	@Autowired
	UpgErrorService upgErrorService;
	
	
// 		Overview Page
		@GetMapping("/overview/range={range}")
		public OverviewPageDTO overPageDetails(@PathVariable("range") String range) {
		liveDataCollector.liveDataFetcher();
		livenessCheckerService.basicInformation();
		livenessCheckerService.gngAndUeLivenessChecker();
//		ueStatsCollector.ueStatsParsing();
		return new OverviewPageDTO(liveDataService.getLiveData(),
				graphService.getGnbGraph(range), graphService.getUegraph(range));
 
		}
	
	  	@GetMapping("/throughput/range={range}")
		public ArrayList<ThroughputGraphFrontendDto> getThroughput(@PathVariable("range") String range) {
		  throughputService.ThroughputData();
			return throughputFrontendService.getThroughputGraph(range);
		}
	
	  	
		@GetMapping("/upgstats/type={type}/limit={limit}")
		public ArrayList<UpgFrontendDto> getSpecificUpgStatsData(@PathVariable("type") String type,
				@PathVariable("limit") Integer limit) {
			upgService.saveUpgData();
			graphService.saveUpgInterfaceData();
			
			
			
			return graphService.getN3N4N6Graphdata(type, limit);
		}
		
// 		Top user data to front end
		@GetMapping("/get_top_user")
		public ArrayList<TopUserModel> getTopUser() {
//			ueStatsCollector.ueStatsParsing();
			return topUserService.getTopUser();
				}
				
//		Topology Page #Need to change the uri
	 	@GetMapping("/data")
	    public MainDTO getNewTopologyData() {
//			Liveness of ue/Gnb
	 		liveDataCollector.liveDataFetcher();
			livenessCheckerService.basicInformation();
			livenessCheckerService.gngAndUeLivenessChecker();
			livenessCheckerService.coreLivenessChecker();
//          ue Live Data
//			ueStatsCollector.ueStatsParsing();
// 			Gnb Live Data
			statsCollectorService.liveDataParse();
//			All Network Functions
			commonServices.amfIpWebclient();
          	commonServices.smfIpWebclient();
          	commonServices.nrfIpWebclient();
          	commonServices.nssfIpWebclient();
          	commonServices.udmIpWebclient();//remain
          	commonServices.udrIpWebclient();
          	commonServices.upfIpWebclient();
          	commonServices.ausfIpWebclient();//remain
          	commonServices.bsfIpWebclient();//remain
          	commonServices.pcfIpWebclient();
          	commonServices.scpIpWebclient();//remain
	        return topologyService.getNewTopologyData();
	    }
	 	
//		Ue Session Page Api
		@GetMapping("/ue_stats/pageNumber={pageNumber}")
		public ArrayList<UeStatsDistinct> UeStats(@PathVariable("pageNumber") int pageNumber) {
//			ueStatsCollector.ueStatsParsing();
			int pageSize = 20; 
			return ueStatsService.getAllUeStatsData(pageNumber,pageSize);
		}
		
		 @GetMapping("/ue_stats/totalPages")
		    public UestatsPagesCount getTotalPages() {
		        int pageSize = 20; // Set the desired page size here
		     return   ueStatsService.getAllUeStatsPages(pageSize, pageSize);
		    }
		
//		Search UE Statistics by Using Imsi 
		@GetMapping("/ue_stats/imsi={imsi}")
		public ArrayList<UeStatsDistinct> SearchUeStats(@PathVariable("imsi") String imsi) {
			return ueStatsService.searchUeStatsData(imsi);
		}
		
		@GetMapping("/ue_stats_details/imsi={imsi}")
		public ArrayList<UeStatsModel1> ItemizedUeDetails(@PathVariable("imsi") String imsi) {
//			ueStatsCollector.ueStatsParsing();
			return ueStatsService.getItemizedUeDetails(imsi);
		}
		
		@GetMapping("/ue_stats_per_gnb/gnbid={gnbid}")
		public ArrayList<UeStatsDistinct> UeStatsPerGnb(@PathVariable("gnbid") String gnbId) {
			return ueStatsService.getUeStatsDataPerGnb(gnbId);
		}

	 	
//		Device Statistics or history	
		@GetMapping("/uehistory/count")
	    public UeEntityFrontendDto getUeEntityData() {
			
			ueHistoryService.fetchDataAndSaveToDatabase();
			uePduCauseInfoService.fetchDataAndSaveToDatabase();
			uePduCauseInfoService.fetchDataAndSaveToDatabase1();
			ueRegistraionDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
			
	        // Call a service method to retrieve data based on tenantId and siteId
	        return ueHistoryFrontendService.getUeEntityData();
	    }
		
		@GetMapping("/uehistoryunique/pageNumber={pageNumber}")
		public List<UeHistorDistinctFrontendDto> getUniqueImsiData(@PathVariable("pageNumber") int pageNumber) {
//			ueHistoryService.fetchDataAndSaveToDatabase();
//			uePduCauseInfoService.fetchDataAndSaveToDatabase();
//			uePduCauseInfoService.fetchDataAndSaveToDatabase1();
//			ueRegistraionDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
			int pageSize = 9; 
	        return ueHistoryFrontendService. getUniqueImsiData(pageNumber,pageSize);
	    }
		
		@GetMapping("/uehistoryunique/totalPages")
	    public UestatsPagesCount getTotalPagesofUeHistory() {
	        int pageSize = 20; // Set the desired page size here
	     return   ueHistoryFrontendService.getAllUeStatsPages(pageSize, pageSize);
	    }
		
		@GetMapping("/uehistory/imsi={imsi}")
	    public List<UeHistoryModel> getUeStatsByImsiAndTenentIdAndSiteId1(
	            @PathVariable String imsi) {
		   	
			ueHistoryService.fetchDataAndSaveToDatabase();
			uePduCauseInfoService.fetchDataAndSaveToDatabase();
			uePduCauseInfoService.fetchDataAndSaveToDatabase1();
			ueRegistraionDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
	        // Use the updated service method to fetch the latest 20 data entries
	        return ueHistoryFrontendService.getLatest20UeStatsByImsi(imsi);
	    }

	//	Gnb History		
		@GetMapping("/gnbInfo")
		public List<GnbRegistrationDeregistrationFailureCount> getGnbInfo() {
			gnbFailureListService.fetchDataAndSaveToDatabase();
			gnbInfoService.fetchDataAndSaveToDatabase();
			gnbRegistrationDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
        return gnbHistoryFrontendService.getGnbInfo();
		}
		@GetMapping("/UniquegnbList")
		public List<GnbListDistinctFrontendDto> getUniqueGnbList() {
			gnbFailureListService.fetchDataAndSaveToDatabase();
			gnbInfoService.fetchDataAndSaveToDatabase();
			gnbRegistrationDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
        return gnbHistoryFrontendService.getUniqueGnbList();
		}
		@GetMapping("/gnbListInfo/gnbId={gnbId}")
	    public List<GnbListFrontendDto> getGnbList(
	            @PathVariable String gnbId,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "20") int size){
			gnbFailureListService.fetchDataAndSaveToDatabase();
			gnbInfoService.fetchDataAndSaveToDatabase();
			gnbRegistrationDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
	        return gnbHistoryFrontendService.getGnbList(gnbId, page, size);
	    }
		@GetMapping("/gnbFailureList/gnbId={gnbId}")
		public List<FailureListFrontendDto> getGnbFailureListt(   
            @PathVariable String gnbId){
			gnbFailureListService.fetchDataAndSaveToDatabase();
			gnbInfoService.fetchDataAndSaveToDatabase();
			gnbRegistrationDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
        return gnbHistoryFrontendService.getGnbfailureList(gnbId);
		}

		//pfcp Data(N4)
		@GetMapping("/pfcpData")
		public PfcpInfoRootFrontendDto getPfcpData() {
			pfcpInfoService.fetchDataAndSaveToDatabase();
			sessioEstablishmentModificationDelationFailReasonListService.fetchDataAndSaveToDatabase();
        return pfcpFrontendService.getPfcpInfoRootDtoByTenantAndSite();
		}

		
		@GetMapping("/pfcp1")
		public PfcpInfoFrontendDto getFailReasonList1() {
			pfcpInfoService.fetchDataAndSaveToDatabase();
			sessioEstablishmentModificationDelationFailReasonListService.fetchDataAndSaveToDatabase();
        return pfcpFrontendService.getFailReasonList();
		}
  

		//Data plane upg/upf stats and Errors
		@GetMapping("/upgerror/type={type}/name={name}/reason={reason}")
		public ArrayList<UpgErrorGraphDto> getN3GraphData(@PathVariable("type") String type,
				@PathVariable("name") String name, @PathVariable("reason") String reason) {
			upgErrorService.saveUpgErrorData();
			upgService.saveUpgData();
			return upgErrorFrontendService.getUpgErrorbyfilter(type, name, reason);
		}
		 @GetMapping("/upgupfgraphdata/type={type}/name={name}/reason={reason}")
		    public RootUpgUpfErrorDto getUpgUpfErrorgraphData(
		            @PathVariable("type") String type,
		            @PathVariable("name") String name,
		            @PathVariable("reason") String reason) {
			  	upgErrorService.saveUpgErrorData();
				upgService.saveUpgData();
				 List<UpgErrorGraphDto> upgErrors = upgErrorFrontendService.getUpgErrorbyfilter(type, name, reason);
			        List<UpfErrorGraphDto> upfErrors = upgErrorFrontendService.getUpfErrorByFilter(type, reason);
			      return  new RootUpgUpfErrorDto(upfErrors, upgErrors);
			      
		    }
		
		 @GetMapping("/combinedUpgUpfdata/type={type}")
		 public UpgUpfRoot getUpgUpfErrorData(
		            @PathVariable("type") String type) {
			 upgErrorService.saveUpgErrorData();
				upgService.saveUpgData();
		        return upgErrorFrontendService.getCombinedErrorByFilter(type);
		    }
		// Fetching data from database and sending it to frontEnd
		@GetMapping("/get_upg_data")//working
		public List<UpgModel> getUpgData() {
			upgErrorService.saveUpgErrorData();
			upgService.saveUpgData();
			return upgServiceFontend.getUpgData();
		}

		
		// Fetching data from database and showing it at frontEnd
		@GetMapping("/get_upg_error_data")
		public List<UpgErrorModel> getUpgErrorData() {
			upgErrorService.saveUpgErrorData();
			upgService.saveUpgData();
			return upgErrorFrontendService.getUpgErrorData();
		}
		@Autowired
		InternalDataFrontendService internalDataFrontendService;
		@GetMapping("/site-info")
	    public Mono<SiteInformationDto> getSiteInformation() {
	        return internalDataFrontendService.fetchDeployedItemInformation();
	    }		
		
	}
