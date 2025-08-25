package com.tharuna.splitwisepractice.controller;

import com.tharuna.splitwisepractice.dto.request.GroupCreationRequestDto;
import com.tharuna.splitwisepractice.model.Group;
import com.tharuna.splitwisepractice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupCreationRequestDto groupCreationRequestDto) {
        return ResponseEntity.ok(groupService.createGroup(groupCreationRequestDto));
    }
}
