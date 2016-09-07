package com.chyld.controllers;

import com.chyld.entities.Device;
import com.chyld.entities.Profile;
import com.chyld.entities.User;
import com.chyld.security.JwtToken;
import com.chyld.services.DeviceService;
import com.chyld.services.ProfileService;
import com.chyld.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class DeviceController {

    private final DeviceService deviceService;
    private final UserService userService;

    public DeviceController(final UserService userService, final DeviceService device) {
        this.userService = userService;
        this.deviceService = device;
    }

    @RequestMapping( value = "/devices", method = RequestMethod.POST)
    public ResponseEntity<?> saveDevice(@RequestBody Device device, Principal prin) throws JsonProcessingException {

        int uid = ((JwtToken) prin).getUserId();
        User u = userService.findUserById(uid);

//        if (u.getDevices() != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//        }

        u.getDevices().add(device);
        device.setUser(u);
        userService.saveUser(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }
}
