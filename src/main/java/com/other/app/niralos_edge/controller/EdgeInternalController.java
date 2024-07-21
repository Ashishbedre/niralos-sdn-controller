package com.other.app.niralos_edge.controller;

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.SavingInternalData.InternalService;
import com.other.app.niralos_edge.dto.InternalDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeInternalController {

    @Autowired
    InternalService internalService;

    @Autowired
    InternalDataRepositorys internalDataRepository;


    //Post api for saving internal data
    @PostMapping("/node/save_internal_data")
    public String savingInternalData(@RequestBody InternalDataDto dto)
    {
        return internalService.saveInternalData(dto);
    }

    //Updating the data in the database
    @PutMapping("/node/update_data/edge_client_id={edge_client_id}")
    public String updatingInternalData(@RequestBody InternalDataDto dto,@PathVariable("edge_client_id") String edgeClientId)
    {
        return internalService.updateInternalData(dto,edgeClientId);
    }

    //Deleting Internal Data from the database
    @DeleteMapping("/node/delete_data/edge_client_id={edge_client_id}")
    public String deleteInternalData(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return internalService.deleteInternalData(edgeClientId);
    }

    //List of internal data in the database
    @GetMapping("/node/get_internal_data")
    public List<InternalDataModels> getInternalData()
    {
        return internalDataRepository.findAll();
    }

    @GetMapping("/node/get_specific_internal_data/edge_client_id={edge_client_id}")
    public InternalDataModels getSpecificInternalData(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return internalDataRepository.getData(edgeClientId);
    }

}
