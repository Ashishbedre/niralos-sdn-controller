package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgInterface;


public interface UpgInterfaceDataRepository extends JpaRepository<UpgInterface, Long> {

	@Query(value = "SELECT * FROM upg_interface u WHERE u.interface_name = ?1 ORDER BY u.date_time DESC LIMIT ?2", nativeQuery = true)
	public List<UpgInterface> returnUpg(String interfaceName, Integer limit);

	@Query(value ="SELECT COUNT(*) FROM upg_interface", nativeQuery = true)
	public Integer checkUpgStacDataLinesCount();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value ="DELETE FROM upg_interface  WHERE serial_id ORDER BY serial_id ASC LIMIT ?1", nativeQuery = true)
	public Integer deleteUpgStacData(Integer limit);
}
