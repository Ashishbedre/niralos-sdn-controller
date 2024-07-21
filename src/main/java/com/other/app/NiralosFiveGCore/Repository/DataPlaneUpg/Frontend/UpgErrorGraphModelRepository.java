package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorGraphModel;

public interface UpgErrorGraphModelRepository extends JpaRepository<UpgErrorGraphModel, Long>{
	
	@Query(value="SELECT SUM(value) FROM upg_errorgraph_model WHERE name = 'nat44_ed_out2in_slowpath' AND reason = 'no_translation'",nativeQuery=true)
	public Integer getnat44_ed_out2in_slowpath();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE upg_errorgraph_model SET value=?1 WHERE name='nat44_ed_out2in_slowpath' AND reason='no_translation'",nativeQuery=true)
	public Integer updatenat44_ed_out2in_slowpath_no_translation(Integer value);
	
	@Query(value = "SELECT * FROM upg_errorgraph_model u WHERE u.error_type= ?1 AND u.name= ?2 AND u.reason = ?3 ORDER BY u.date_time DESC LIMIT 20", nativeQuery = true)
	public List<UpgErrorGraphModel> getUpgErrorbyfilter(String type, String name, String reason);
	
	@Query(value ="SELECT COUNT(*) FROM upg_errorgraph_model", nativeQuery = true)
	public Integer checkUpgErrorDataLinesCount();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value ="DELETE FROM upg_errorgraph_model  WHERE serial_id ORDER BY serial_id ASC LIMIT ?1", nativeQuery = true)
	public Integer deleteUpgErrorData(Integer limit);

	

}
