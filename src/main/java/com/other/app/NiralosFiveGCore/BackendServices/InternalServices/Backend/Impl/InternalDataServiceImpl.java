package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.NiralosFiveGCore.model.InternalDataModel;

@Service
public class InternalDataServiceImpl implements InternalDataService {

	@Autowired
	InternalDataRepository internalDataRepository;
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:InternalDataServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(InternalDataServiceImpl.class);

	@Override
	public InternalDataModel getInternalDetails() {
		// Assuming you want to retrieve the first entry in the repository
		List<InternalDataModel> dataList = internalDataRepository.findAll();
		if (!dataList.isEmpty()) {
			// If data is found, return the first entry
			return dataList.get(0);
		} else {
			// If no data is found, return null
			return null;
		}
	}

	@Override
	public void saveInternalDataModel(InternalDataModel internalData) {
	    InternalDataModel internalData1 = internalDataRepository.findAll().stream().findFirst().orElse(null);
	    if (internalData1 != null) {
			loggerTypeA.warn("InternalDataModel object is null. Cannot save or update.");
			internalDataRepository.updateInternalData(internalData1.getId(), internalData.getNiralControllerIp(), internalData.getAmfIp(),
	                internalData.getSmfIp(), internalData.getNrfIp(), internalData.getUpfIp(), internalData.getUdrIp(),
	                internalData.getNssfIp(), internalData.getUpgIp(), internalData.getAmfPort(), internalData.getSmfPort(),
	                internalData.getNrfPort(), internalData.getUpfPort(), internalData.getUdrPort(),
	                internalData.getNssfPort(), internalData.getUpgPort(),

	                internalData.getAusfIp(), internalData.getAusfPort(), internalData.getPcfIp(),
	                internalData.getPcfPort(), internalData.getUdmIp(), internalData.getUdmPort(), internalData.getScpIp(),
	                internalData.getScpPort(), internalData.getBsfIp(), internalData.getBsfPort(),internalData.getNiralGlobalControllerport()
	        );
	    }
	}



	@Override
	public String getNiralControllerIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			for (InternalDataModel internalDataModel : internalDataList) {
				if (internalDataModel != null) {
					return internalDataModel.getNiralControllerIp();
				}
			}
			loggerTypeA.info("getNiralControllerIp: No InternalDataModel found");
			return null;
		} catch (Exception e) {
			loggerTypeA.error("getNiralControllerIp: An unexpected error occurred while fetching Niral Controller IP.", e);
			e.printStackTrace();
			return null;
		}
	}


//	@Override
//	public String getNiralControllerClientId() {
//	    List<InternalDataModel> internalDataList = internalDataRepository.findAll();
//	    if (!internalDataList.isEmpty()) {
//	        return internalDataList.get(0).getNiralControllerClientId();
//	    } else {
//	        // Handle the case where no InternalDataModel entities are found
//	        // You can throw an exception, return a default value, or take appropriate action
//			loggerTypeA.warn("getNiralControllerClientId: No InternalDataModel entities found in the repository.");
//			throw new IllegalStateException("No InternalDataModel entities found");
//	    }
//	}


	@Override
	public String getAmfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getAmfIp() : null;
			} else {
				loggerTypeA.info("getAmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfIp: An unexpected error occurred while fetching AMF IP.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAmfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getAmfPort() : null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSmfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getSmfIp() : null;
			} else {
				loggerTypeA.info("getSmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getSmfIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSmfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getSmfPort() : null;
			} else {
				loggerTypeA.info("getSmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getSmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnrfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNrfIp() : null;
			} else {
				loggerTypeA.info("getnrfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getnrfIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnrfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNrfPort() : null;
			} else {
				loggerTypeA.info("getnrfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getnrfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getupfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getUpfIp() : null;
			} else {
				loggerTypeA.info("getupfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getupfIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getupfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getUpfPort() : null;
			} else {
				loggerTypeA.info("getupfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getupfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getudrIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getUdrIp() : null;
			} else {
				loggerTypeA.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getudrIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getudrPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getUdrPort() : null;
			} else {
				loggerTypeA.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getudrPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnssfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNssfIp() : null;
			} else {
				loggerTypeA.info("getnssfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getnssfIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnssfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNssfPort() : null;
			} else {
				loggerTypeA.info("getnssfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getnssfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getupgIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getUpgIp() : null;
			} else {
				loggerTypeA.info("getupgIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getupgIp: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getupgPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getUpgPort() : null;
	}

	
	

	@Override
	public String getpcfIp() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getPcfIp() : null;
	}

	@Override
	public String getpcfPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getPcfPort() : null;
	}

	@Override
	public String getbsfIp() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getBsfIp() : null;
	}

	@Override
	public String getbsfPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getBsfPort() : null;
	}

	@Override
	public String getudmIp() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getUdmIp() : null;
	}

	@Override
	public String getudmPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getUdmPort() : null;
	}

	@Override
	public String getscpIp() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getScpIp() : null;
	}

	@Override
	public String getscpPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getScpPort() : null;
	}

	@Override
	public String getausfIp() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getAusfIp() : null;
	}

	@Override
	public String getausfPort() {
		InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		return internalData != null ? internalData.getAusfPort() : null;
	}

	


	
	@Override
	public long getId() {
	    InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
	    return internalData != null ? internalData.getId() : 1L;
	}

	@Override
	public String getNiralGlobalControllerPort() {
		 InternalDataModel internalData = internalDataRepository.findAll().stream().findFirst().orElse(null);
		    return internalData != null ? internalData.getNiralGlobalControllerport() : null;
	}

	@Override
	public String getnefIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNefIp() : null;
			} else {
				loggerTypeA.info("getAmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfIp: An unexpected error occurred while fetching AMF IP.", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getnefPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNefPort() : null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getnwdafIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNwdafIp() : null;
			} else {
				loggerTypeA.info("getAmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfIp: An unexpected error occurred while fetching AMF IP.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getnwdafPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getNwdafPort() : null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getn3iwfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getN3iwfIp() : null;
			} else {
				loggerTypeA.info("getAmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfIp: An unexpected error occurred while fetching AMF IP.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getn3iwfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getN3iwfPort() : null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public String getpcscfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getPcscfIp() : null;
			} else {
				loggerTypeA.info("getAmfIp: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfIp: An unexpected error occurred while fetching AMF IP.", e);
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public String getpcscfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getPcscfPort(): null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String geticscfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getIcscfIp(): null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String geticscfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getIcscfPort(): null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getscscfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getScscfIp(): null;
			} else {
				loggerTypeA.info("getAmfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String getscscfPort() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getScscfPort(): null;
			} else {
				loggerTypeA.info("getscscfPort: No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
			loggerTypeA.error("getAmfPort: An unexpected error occurred while fetching AMF Port.", e);
			e.printStackTrace();
			return null;
		}
	}

}
