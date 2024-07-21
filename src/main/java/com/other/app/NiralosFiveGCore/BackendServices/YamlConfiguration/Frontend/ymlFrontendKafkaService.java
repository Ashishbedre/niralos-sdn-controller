//package com.other.app.Service.BackendServices.YamlConfiguration.Frontend;
//
//
//
//import com.other.app.YamlConfiguration.YmlModel.ListofNf;
//import com.other.app.YamlConfiguration.YmlModel.ReadDataReturn;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.AmfYmlModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.AmfYmlModelForMogoDb;
//import com.other.app.YamlConfiguration.YmlModel.AusfModel.AusfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.BsfModel.BsfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.NrfModel.NrfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.NssfModel.NssfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.ScpDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.SmfModel.SmfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.UdmModel.UdmDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.UdrModel.UdrDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.pcfModel.PcfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.upfModel.UpfDataMongoModel;
//
//public interface ymlFrontendKafkaService {
//	public ReadDataReturn ListofNfNameDataRead1(String nfName);
//	public ListofNf ListofNfName();
//
//	public void ListofNfNameDataRead(String nfName);
//
//	public String postDataofYamlFile(
//			String nfName,
//			AmfYmlModelForMogoDb amfYmlModelForMogoDb);
//
//	
//	public String postDataofYamlFileInScp(String nfName,
//			ScpDataMongoModel scpDataMongoModel);
//
//	public String postDataofYamlFileInpcf(String nfName,
//			PcfDataMongoModel pcfDataMongoModel);
//
//	public String postDataofYamlFileInAusf(String nfName,
//			AusfDataMongoModel ausfDataMongoModel);
//
//	public String postDataofYamlFileInBsf(String nfName,
//			BsfDataMongoModel bsfDataMongoModel);
//
//	public String postDataofYamlFileInUdr(String nfName,
//			UdrDataMongoModel udrDataMongoModel);
//
//	public String postDataofYamlFileInUpf(String nfName,
//			UpfDataMongoModel upfDataMongoModel);
//
//	public String postDataofYamlFileInSmf( String nfName,
//			SmfDataMongoModel smfDataMongoModel);
//
//	public String postDataofYamlFileInUdm( String nfName,
//			UdmDataMongoModel udmDataMongoModel);
//
//	public String postDataofYamlFileInNssf( String nfName,
//			NssfDataMongoModel nssfDataMongoModel);
//
//	public String postDataofYamlFileInNrf( String nfName,
//			NrfDataMongoModel nrfDataMongoModel);
//}
