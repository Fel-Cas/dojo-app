package com.app.dojo.controllers;

import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.services.Interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_ID;
import static com.app.dojo.constants.EndPointsConstants.ENDPOINT_ROOMS;

@RestController
@RequestMapping(ENDPOINT_ROOMS)
public class RoomController {

    @Autowired
    private RoomService roomService;
    @PostMapping()
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO)throws Exception{
        RoomDTO roomDTOCreated= this.roomService.create(roomDTO);
        return ResponseEntity.status(201).body(roomDTOCreated);
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) throws Exception{
        RoomDTO roomFound = this.roomService.findById(id);
        return ResponseEntity.ok(roomFound);
    }
}
