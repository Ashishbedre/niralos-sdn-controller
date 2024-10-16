package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;


public interface UpgFrontendRepository extends JpaRepository<UpgModel, Long> {
	

		
//		Used to fetch Tx Packets on basis of Interface Name
		@Query(value="SELECT upg.tx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getTxPackets(String InterfaceName);
		
		@Query(value="SELECT upg.tx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getTxPacketsImpl(String InterfaceName);
		
//		Used to fetch Tx Packets on basis of Interface Name
		@Query(value="SELECT upg.previous_tx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getPreviousTxPackets(String InterfaceName);
		
		
//		Used to fetch Rx Packets on basis of Interface Name
		@Query(value="SELECT upg.rx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getRxPackets(String InterfaceName);
		
		@Query(value="SELECT upg.rx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getRxPacketsImpl(String InterfaceName);
		
		@Query(value="SELECT upg.rx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 ",nativeQuery = true)
	    public Integer getrxBytesImpl(String InterfaceName);
		
		@Query(value="SELECT upg.tx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 ",nativeQuery = true)
	    public Integer gettxBytesImpl(String InterfaceName);
		
//		Used to fetch Tx Packets on basis of Interface Name
		@Query(value="SELECT upg.previous_tx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery = true)
	    public Integer getPreviousTxbytes(String InterfaceName);
		
//		Used to fetch Tx Packets on basis of Interface Name
		@Query(value="SELECT upg.previous_rx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 ",nativeQuery = true)
	    public Integer getPreviousrxbytes(String InterfaceName);
		
		
//		Used to fetch Rx Packets on basis of Interface Name
		@Query(value="SELECT upg.previous_rx_packets FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 ",nativeQuery = true)
	    public Integer getPreviousRxPackets(String InterfaceName);
		
	// Used to fetch specific UPG data using interfaceName
		@Query(value="SELECT * FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery=true)
		public UpgModel getSpecificData(String interfaceName); 
		
//		Used to Update Stats for a exisiting UPG
//		@Modifying(clearAutomatically = true)
//		@Transactional
//		@Query(value = "UPDATE upg_stats_data_frontend m SET m.drops=?1,m.index_data=?2,m.ip4=?3,m.ip6=?4,m.punt=?5,m.rx_bytes=?6,m.rx_packets=?7,m.thread_number=?8,"
//				+ "m.tx_bytes=?9,m.tx_packets=?10,m.previous_rx_packets=?11,m.previous_tx_packets=?15, m.previous_rx_bytes=?16, m.previous_tx_bytes=?20  WHERE m.interface_name=?13 AND nf_name=?17 AND nf_type=?18", nativeQuery = true)
//		public void updateData(Integer drops,Integer index,Integer ip4,Integer ip6,Integer punt,Integer rx_bytes,
//				Integer rx_packets,Integer threadNumber,Integer tx_bytes,Integer tx_packets,
//				Integer previous_rx_packets,Integer previous_tx_packets,String interfaceName,String nfName, String nfType,
//				Integer previousrxbytes, Integer previoustxbytes);
		
//		Used to Update Stats for a exisiting UPG
		@Modifying(clearAutomatically = true)
		@Transactional
		@Query(value = "UPDATE upg_stats_data_frontend m SET m.drops=?1,m.index_data=?2,m.ip4=?3,m.ip6=?4,m.punt=?5,m.rx_bytes=?6,m.rx_packets=?7,m.thread_number=?8,"
				+ "m.tx_bytes=?9,m.tx_packets=?10,m.previous_rx_packets=?11,m.previous_tx_packets=?12, m.previous_rx_bytes=?16, m.previous_tx_bytes=?17  WHERE m.interface_name=?13 AND nf_name=?14 AND nf_type=?15", nativeQuery = true)
		public void updateData(Integer drops,Integer index,Integer ip4,Integer ip6,Integer punt,Integer rx_bytes,
				Integer rx_packets,Integer threadNumber,Integer tx_bytes,Integer tx_packets,
				Integer previous_rx_packets,Integer previous_tx_packets,String interfaceName,
				String nfName, String nfType,
				Integer previousrxbytes, Integer previoustxbytes);
		
		
		

		@Query(value="SELECT rx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 ",nativeQuery=true)
		public Integer getrxBytes(String interfaceName);

		@Query(value="SELECT tx_bytes FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery=true)
		public Integer gettxBytes(String interfaceName);
		
		@Query(value="SELECT drops FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1",nativeQuery=true)
		public Integer getdrops(String interfaceName);

		@Query(value="SELECT * FROM upg_stats_data_frontend upg",nativeQuery = true)
		public List<UpgModel> findAllData();

		@Query(value="SELECT * FROM upg_stats_data_frontend",nativeQuery = true)
	    public List<UpgModel> returnAllData();

		
		
		@Query(value="SELECT COUNT(*) FROM upg_stats_data_frontend upg WHERE upg.interface_name = ?1 AND nf_name=?2 AND nf_type=?3",nativeQuery = true)
		public Integer countInterfaceName(String interfaceName, String nfName, String nfType);

	


}
