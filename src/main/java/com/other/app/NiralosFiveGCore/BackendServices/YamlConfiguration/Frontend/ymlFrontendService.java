package com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Frontend;
 
 
 
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData.ListofNf;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData.ReadDataReturn;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AusfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.BsfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NrfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.PcfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.ScpDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdmDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdrDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;
 
public interface ymlFrontendService {
 
	public ListofNf ListofNfName();
 
	public ReadDataReturn ListofNfNameDataRead(String nfName);
 
	public String postDataofYamlFile(
			String nfName,
			AmfYmlModelForMogoDb amfYmlModelForMogoDb);
 
	
	public void postDataofYamlFileInScp(String nfName,
			ScpDataMongoModel scpDataMongoModel);
 
	public void postDataofYamlFileInpcf(String nfName,
			PcfDataMongoModel pcfDataMongoModel);
 
	public void postDataofYamlFileInAusf(String nfName,
			AusfDataMongoModel ausfDataMongoModel);
 
	public void postDataofYamlFileInBsf(String nfName,
			BsfDataMongoModel bsfDataMongoModel);
 
	public void postDataofYamlFileInUdr(String nfName,
			UdrDataMongoModel udrDataMongoModel);
 
	public void postDataofYamlFileInUpf(String nfName,
			UpfDataMongoModel upfDataMongoModel);
 
	public void postDataofYamlFileInSmf( String nfName,
			SmfDataMongoModel smfDataMongoModel);
 
	public void postDataofYamlFileInUdm( String nfName,
			UdmDataMongoModel udmDataMongoModel);
 
	public void postDataofYamlFileInNssf( String nfName,
			NssfDataMongoModel nssfDataMongoModel);
 
	public void postDataofYamlFileInNrf( String nfName,
			NrfDataMongoModel nrfDataMongoModel);
}