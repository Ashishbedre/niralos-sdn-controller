package com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend;


import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpInfo;

public interface PfcpInfoRepository extends JpaRepository<PfcpInfo, Long> {
    PfcpInfo findByNfNameAndNfType(String nfName, String nfType);


}
