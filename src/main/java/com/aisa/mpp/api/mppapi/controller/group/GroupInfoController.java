package com.aisa.mpp.api.mppapi.controller.group;

import com.aisa.mpp.api.mppapi.model.group.GroupInfo;
import com.aisa.mpp.api.mppapi.service.group.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupInfoController {

    @Autowired(required = false)
    private GroupInfoService groupInfoService;

    // Add a Group
    @PostMapping
    public ResponseEntity<GroupInfo> addGroup(@RequestBody GroupInfo groupInfo) {
        GroupInfo savedGroup = groupInfoService.saveGroup(groupInfo);
        return ResponseEntity.ok(savedGroup);
    }

    // Find All Groups
    @GetMapping
    public ResponseEntity<List<GroupInfo>> getAllGroups() {
        List<GroupInfo> groups = groupInfoService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    // Find Group by Name
    @GetMapping("/findByGrpName")
    public ResponseEntity<GroupInfo> getGroupByName(@RequestParam String grpName) {
        Optional<GroupInfo> groupInfo = groupInfoService.getGroupByName(grpName);

        if (groupInfo.isPresent()) {
            return ResponseEntity.ok(groupInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Find Group by Name
    @GetMapping("/findByGrpId")
    public ResponseEntity<GroupInfo> getGroupById(@RequestParam Long Id) {
        Optional<GroupInfo> groupInfo = groupInfoService.getGroupById(Id);

        if (groupInfo.isPresent()) {
            return ResponseEntity.ok(groupInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}