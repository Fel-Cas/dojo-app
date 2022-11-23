package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassDTOResponse;
import com.app.dojo.mappers.MapperGroupClass;
import com.app.dojo.models.GroupClass;
import com.app.dojo.services.Interfaces.GroupClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(EndPointsConstants.ENDPOINT_GROUP_CLASSES)
public class GroupClassController {

    @Autowired
    private GroupClassService groupClassService;
    @Autowired
    private MapperGroupClass mapperGroupClass;

    @PostMapping()
    public ResponseEntity<GroupClassDTOResponse> create(@Valid @RequestBody()GroupClassDTO groupClassDTO) throws Exception {
        GroupClass groupSaved=this.groupClassService.create(groupClassDTO);
        return new ResponseEntity<>(mapperGroupClass.mapperGroupClassDTOResponse(groupSaved), HttpStatus.CREATED);
    }
}
