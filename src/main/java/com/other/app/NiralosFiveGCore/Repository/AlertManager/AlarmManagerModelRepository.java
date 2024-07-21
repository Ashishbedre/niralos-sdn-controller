package com.other.app.NiralosFiveGCore.Repository.AlertManager;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;



public interface AlarmManagerModelRepository extends JpaRepository<AlarmModel, Long> {
	 List<AlarmModel> findAllByStatus(Boolean status);

	//Getting the list of alarm from the database
	@Query(value="SELECT * FROM alarm_model WHERE tenent_id=?1",nativeQuery = true)
	public List<AlarmModel> getListofAlerts(String tenentId);
	//Getting the list of alarm for a particular site
	@Query(value="SELECT * FROM alarm_model WHERE tenent_id=?1 AND site_id=?2",nativeQuery = true)
	public List<AlarmModel> getListofAlertsSite(String tenentId,String siteId);
	//	Select specific Data from database
	@Query("SELECT COUNT(m) FROM AlarmModel m WHERE m.alarmId = ?1 AND m.subAlarmId = ?2 ")
	public Integer checkIfAlarmExists(String alarmId, String subAlarmId);// CHECKED FINE
	
//	Select specific Data from database
	@Query("SELECT COUNT(m) FROM AlarmModel m WHERE m.alarmId = ?1 AND m.dateTime = ?2")
	public Integer checkIfaAlarmExists(String alarmId, LocalDateTime dateTime);// CHECKED FINE

	// Update Alerts in DB when previous entry exists.
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query("UPDATE AlarmModel a SET  a.sourceDescription = ?1, a.sourceType = ?2, a.sourceIp =?3, a.destinationDescription = ?4,"
//			+ "a.destinationType = ?5, a.destinationIp = ?6, a.dateTime = ?7, a.alarmLevel=?8, a.reason =?9,a.solution=?10  WHERE a.alarmId = ?11 AND a.subAlarmId= ?12")
//	public void updateExistingSession(String sourceDescription, String sourceType, String sourceIp,String destinationDescription
//			,String destinationType, String destinationIp,String dateTime, String alarmLevel, String reason, String solution, String alarmId, String subAlarmId);
	

	
//	Select List of Alarms from database
	@Query(value = "SELECT * FROM alarm_model WHERE alarm_level='critical' ORDER BY id DESC LIMIT 20", nativeQuery = true)
	public List<AlarmModel> listOfCriticalAlerts();

//	Select List of Alarms from database
	@Query(value = "SELECT * FROM alarm_model WHERE alarm_level='Major' ORDER BY alarm_level  LIMIT 20", nativeQuery = true)
	public List<AlarmModel> listOfMajorAlerts();
	
//	Select List of Alarms from database
	@Query(value = "SELECT * FROM alarm_model WHERE alarm_level='Minor' ORDER BY alarm_level  LIMIT 20", nativeQuery = true)
	public List<AlarmModel> listOfMinorAlerts();
	
	@Query(value = "SELECT * FROM alarm_model WHERE alarm_level='Not Alarmed' ORDER BY alarm_level  LIMIT 20", nativeQuery = true)
	public ArrayList<AlarmModel> listofNotAlarmed();

	@Query(value = "SELECT * FROM alarm_model WHERE alarm_level='Not Reported' ORDER BY alarm_level  LIMIT 20", nativeQuery = true)
	public ArrayList<AlarmModel> listofNotReported();
	
//	Select List of Latest 10 Alarms from database
	@Query(value = "SELECT * FROM alarm_model l WHERE l.id ORDER BY l.id DESC LIMIT 20", nativeQuery = true)
	public List<AlarmModel> listOfLatestAlerts();
	
//	Select List of Latest 10 Alarms from database
	@Query(value = "SELECT * FROM alarm_model l WHERE l.id = ?1", nativeQuery = true)
	public List<AlarmModel> DetailsofAlarmsById(Integer id);

	@Query(value = "SELECT COUNT(*) FROM alarm_model", nativeQuery = true)
	public Integer countofAll();
	
	@Query(value = "SELECT COUNT(d.alarm_level) FROM alarm_model d WHERE d.alarm_level = 'critical'", nativeQuery = true)
	public Integer countofCrticalAlarms();

	@Query(value = "SELECT COUNT(d.alarm_level) FROM alarm_model d WHERE d.alarm_level = 'major'", nativeQuery = true)
	public Integer countofMajorAlarms();

	@Query(value = "SELECT COUNT(d.alarm_level) FROM alarm_model d WHERE d.alarm_level = 'minor'", nativeQuery = true)
	public Integer countofMinorAlarms();

	@Query(value = "SELECT COUNT(d.alarm_level) FROM alarm_model d WHERE d.alarm_level = 'notAlarmed'", nativeQuery = true)
	public Integer countofNotAlarmed();


	@Query(value = "SELECT COUNT(d.alarm_level) FROM alarm_model d WHERE d.alarm_level = 'notReported'", nativeQuery = true)
	public Integer countofNotReported();

	
	
	//	Select List of Latest 10 Alarms from database
	@Query(value = "SELECT * FROM alarm_model l WHERE l.alarm_level = ?1", nativeQuery = true)
	public List<AlarmModel> filterLevelsofAlarms(String level);

	@Query(value = "SELECT * FROM alarm_model  WHERE date_time BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<AlarmModel> alarmsListBydateTime(String startdate, String enddate);

	@Query(value = "SELECT * FROM alarm_model WHERE date_time BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<AlarmModel> alarmsListBydateTimeAndSite(String startdate, String enddate);


	@Query(value = "SELECT status FROM alarm_model WHERE id = ?1", nativeQuery = true)
	public Boolean checkStatusOfAlarm(String id);

	
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE AlarmModel SET status = ?2 WHERE id = ?1")
	public void alarmstatusUpdate(Long id, Boolean status);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE AlarmModel SET status = ?2  WHERE id = ?1 ")
	public void alarmstatus2Update(Long id, Boolean status);

	
	@Query(value ="SELECT * FROM alarm_model WHERE alarm_level = ?1 AND status = ?2", nativeQuery = true)
	public List<AlarmModel> alertFilter(String level, Integer status);

	
	@Query(value="SELECT * FROM alarm_model WHERE alarm_level=?1 AND status=?2", nativeQuery = true)
	public List<AlarmModel> alertFilterForSite(String level, Integer status);

	
	@Query(value="SELECT DISTINCT(site_id) FROM alarm_model", nativeQuery = true)
	public List<String> getListOfSite();


//	UPDATE AlarmModel a SET  a.sourceDescription 
	


}
