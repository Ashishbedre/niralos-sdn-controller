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
	final Logger logger = LoggerFactory.getLogger(InternalDataServiceImpl.class);

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
			logger.info("No InternalDataModel found");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public String getNiralControllerClientId() {
	    List<InternalDataModel> internalDataList = internalDataRepository.findAll();
	    if (!internalDataList.isEmpty()) {
	        return internalDataList.get(0).getNiralControllerClientId();
	    } else {
	        // Handle the case where no InternalDataModel entities are found
	        // You can throw an exception, return a default value, or take appropriate action
	        throw new IllegalStateException("No InternalDataModel entities found");
	    }
	}


	@Override
	public String getAmfIp() {
		try {
			List<InternalDataModel> internalDataList = internalDataRepository.findAll();
			if (!internalDataList.isEmpty()) {
				InternalDataModel internalData = internalDataList.get(0);
				return internalData != null ? internalData.getAmfIp() : null;
			} else {
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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
				logger.info("No InternalDataModel found");
				return null;
			}
		} catch (Exception e) {
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

}
