package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.other.app.niralos_edge.dto.HardwaraVM.VmDataDTOResponce;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class EdgeHelperService {

    public VmDataDTOResponce vmDataDTOResponce(Mono<Map<String, Object>> vmConfigDTOMono) throws JsonProcessingException {
        VmDataDTOResponce vmDataDTOResponce = new VmDataDTOResponce();

        Map<String, Object> hashMapResponce  = vmConfigDTOMono.block();

//        vmDataDTOResponce.setCores((int) hashMapResponce.get("cores"));
//        vmDataDTOResponce.setCpu((String) hashMapResponce.get("cpu"));
//        vmDataDTOResponce.setVmgenid((String) hashMapResponce.get("vmgenid"));
//        vmDataDTOResponce.setBoot((String) hashMapResponce.get("boot"));
//        vmDataDTOResponce.setArgs((String) hashMapResponce.get("args"));
//        vmDataDTOResponce.setAgent((String) hashMapResponce.get("agent"));
//        vmDataDTOResponce.setVga((String) hashMapResponce.get("vga"));
//        vmDataDTOResponce.setScsihw((String) hashMapResponce.get("scsihw"));
//        vmDataDTOResponce.setBalloon((int) hashMapResponce.get("balloon"));
//        vmDataDTOResponce.setMachine((String) hashMapResponce.get("machine"));
//        vmDataDTOResponce.setSockets((int) hashMapResponce.get("sockets"));
//        vmDataDTOResponce.setOstype((String) hashMapResponce.get("ostype"));
//        vmDataDTOResponce.setName((String) hashMapResponce.get("name"));
//        vmDataDTOResponce.setNuma((int) hashMapResponce.get("numa"));
//        vmDataDTOResponce.setBios((String) hashMapResponce.get("bios"));
//        vmDataDTOResponce.setOnboot((int) hashMapResponce.get("onboot"));
//        vmDataDTOResponce.setMemory((int) hashMapResponce.get("memory"));

        // Conditionally set values if they exist in the hashMapResponce
        if (hashMapResponce.get("cores") != null) {
            vmDataDTOResponce.setCores((int) hashMapResponce.get("cores"));
        }
        if (hashMapResponce.get("cpu") != null) {
            vmDataDTOResponce.setCpu((String) hashMapResponce.get("cpu"));
        }
        if (hashMapResponce.get("vmgenid") != null) {
            vmDataDTOResponce.setVmgenid((String) hashMapResponce.get("vmgenid"));
        }
        if (hashMapResponce.get("boot") != null) {
            vmDataDTOResponce.setBoot((String) hashMapResponce.get("boot"));
        }
        if (hashMapResponce.get("args") != null) {
            vmDataDTOResponce.setArgs((String) hashMapResponce.get("args"));
        }
        if (hashMapResponce.get("agent") != null) {
            vmDataDTOResponce.setAgent((String) hashMapResponce.get("agent"));
        }
        if (hashMapResponce.get("vga") != null) {
            vmDataDTOResponce.setVga((String) hashMapResponce.get("vga"));
        }
        if (hashMapResponce.get("scsihw") != null) {
            vmDataDTOResponce.setScsihw((String) hashMapResponce.get("scsihw"));
        }
        if (hashMapResponce.get("balloon") != null) {
            vmDataDTOResponce.setBalloon((int) hashMapResponce.get("balloon"));
        }
        if (hashMapResponce.get("machine") != null) {
            vmDataDTOResponce.setMachine((String) hashMapResponce.get("machine"));
        }
        if (hashMapResponce.get("sockets") != null) {
            vmDataDTOResponce.setSockets((int) hashMapResponce.get("sockets"));
        }
        if (hashMapResponce.get("ostype") != null) {
            vmDataDTOResponce.setOstype((String) hashMapResponce.get("ostype"));
        }
        if (hashMapResponce.get("name") != null) {
            vmDataDTOResponce.setName((String) hashMapResponce.get("name"));
        }
        if (hashMapResponce.get("numa") != null) {
            vmDataDTOResponce.setNuma((int) hashMapResponce.get("numa"));
        }
        if (hashMapResponce.get("bios") != null) {
            vmDataDTOResponce.setBios((String) hashMapResponce.get("bios"));
        }
        if (hashMapResponce.get("onboot") != null) {
            vmDataDTOResponce.setOnboot((int) hashMapResponce.get("onboot"));
        }
        if (hashMapResponce.get("memory") != null) {
            vmDataDTOResponce.setMemory((int) hashMapResponce.get("memory"));
        }


        // Initialize lists for network, cdAndDvd, and harddisk
        List<Map<String, String>> network = new ArrayList<>();
        List<Map<String, String>> cdAndDvd = new ArrayList<>();
        List<Map<String, String>> harddisk = new ArrayList<>();

        // Processing the map for additional fields
        for (Map.Entry<String, Object> entry : hashMapResponce.entrySet()) {
            String key = entry.getKey();
//            String value = (String) entry.getValue(); // Cast to String
            Object valueObj = entry.getValue(); // Use Object type for value
            String value = (valueObj != null) ? valueObj.toString() : null;

            if (value != null || value.equals(null)) {
                if (key.startsWith("net")) {
                    network.add(parseKeyValuePair(key, value));
                }
                if (value.contains("media=cdrom")) {
                    cdAndDvd.add(parseKeyValuePair(key, value));
                } else if (value.contains("local-lvm")) {
                    harddisk.add(parseKeyValuePair(key, value));
                }
            }
        }

        // Set the lists in the response DTO if they exist
        if (!network.isEmpty()) {
            vmDataDTOResponce.setNetwork(network);
        }
        if (!cdAndDvd.isEmpty()) {
            vmDataDTOResponce.setCdAndDvd(cdAndDvd);
        }
        if (!harddisk.isEmpty()) {
            vmDataDTOResponce.setHarddisk(harddisk);
        }

        return vmDataDTOResponce;
    }

    private Map<String, String> parseKeyValuePair(String key, String value) {
        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("key", key);

        // Split the value into key-value pairs and add to the map
        String[] keyValuePairs = value.split(",");
        for (String pair : keyValuePairs) {
            String[] splitPair = pair.split("=", 2);
            if (splitPair.length == 2) {
                keyValueMap.put(splitPair[0], splitPair[1]);
            } else {
                keyValueMap.put(splitPair[0], ""); // Handle cases where there's no '='
            }
        }
        return keyValueMap;
    }

    public String extractNextScsiNumber(VmDataDTOResponce vmConfigDTOMono) {
        // Extract 'cdAndDvd' and 'harddisk' fields
        List<Map<String, String>> cdAndDvdList = vmConfigDTOMono.getCdAndDvd();
        List<Map<String, String>> harddiskList = vmConfigDTOMono.getHarddisk();
        Set<Integer> usedScsiNumbers = new HashSet<>();

        // Process 'cdAndDvd' list for SCSI numbers
        if (cdAndDvdList != null) {
            for (Map<String, String> cdAndDvd : cdAndDvdList) {
                String key = cdAndDvd.get("key");
                if (key != null && key.startsWith("scsi")) {
                    try {
                        int number = Integer.parseInt(key.substring(4));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Process 'harddisk' list for SCSI numbers
        if (harddiskList != null) {
            for (Map<String, String> disk : harddiskList) {
                String key = disk.get("key");
                if (key != null && key.startsWith("scsi")) {
                    try {
                        int number = Integer.parseInt(key.substring(4));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Find the smallest unused scsiX number
        int nextScsiNumber = 0;
        while (usedScsiNumbers.contains(nextScsiNumber)) {
            nextScsiNumber++;
        }

        // Return the next available SCSI number
        return "scsi" + nextScsiNumber;
    }

    public String extractNextIdeNumber(VmDataDTOResponce vmConfigDTOMono) {
        // Extract 'cdAndDvd' and 'harddisk' fields
        List<Map<String, String>> cdAndDvdList = vmConfigDTOMono.getCdAndDvd();
        List<Map<String, String>> harddiskList = vmConfigDTOMono.getHarddisk();
        Set<Integer> usedScsiNumbers = new HashSet<>();

        // Process 'cdAndDvd' list for SCSI numbers
        if (cdAndDvdList != null) {
            for (Map<String, String> cdAndDvd : cdAndDvdList) {
                String key = cdAndDvd.get("key");
                if (key != null && key.startsWith("ide")) {
                    try {
                        int number = Integer.parseInt(key.substring(3));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Process 'harddisk' list for SCSI numbers
        if (harddiskList != null) {
            for (Map<String, String> disk : harddiskList) {
                String key = disk.get("key");
                if (key != null && key.startsWith("ide")) {
                    try {
                        int number = Integer.parseInt(key.substring(3));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Find the smallest unused scsiX number
        int nextScsiNumber = 0;
        while (usedScsiNumbers.contains(nextScsiNumber)) {
            nextScsiNumber++;
        }

        // Return the next available SCSI number
        return "ide" + nextScsiNumber;
    }

    public String extractNextnetNumber(VmDataDTOResponce vmConfigDTOMono) {
        // Extract 'cdAndDvd' and 'harddisk' fields
        List<Map<String, String>> cdAndDvdList = vmConfigDTOMono.getCdAndDvd();
        List<Map<String, String>> harddiskList = vmConfigDTOMono.getHarddisk();
        List<Map<String, String>> netList = vmConfigDTOMono.getNetwork();
        Set<Integer> usedScsiNumbers = new HashSet<>();

        // Process 'cdAndDvd' list for SCSI numbers
        if (cdAndDvdList != null) {
            for (Map<String, String> cdAndDvd : cdAndDvdList) {
                String key = cdAndDvd.get("key");
                if (key != null && key.startsWith("net")) {
                    try {
                        int number = Integer.parseInt(key.substring(3));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Process 'harddisk' list for SCSI numbers
        if (harddiskList != null) {
            for (Map<String, String> disk : harddiskList) {
                String key = disk.get("key");
                if (key != null && key.startsWith("net")) {
                    try {
                        int number = Integer.parseInt(key.substring(3));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (netList != null) {
            for (Map<String, String> disk : netList) {
                String key = disk.get("key");
                if (key != null && key.startsWith("net")) {
                    try {
                        int number = Integer.parseInt(key.substring(3));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Find the smallest unused scsiX number
        int nextScsiNumber = 0;
        while (usedScsiNumbers.contains(nextScsiNumber)) {
            nextScsiNumber++;
        }

        // Return the next available SCSI number
        return "net" + nextScsiNumber;
    }



}
