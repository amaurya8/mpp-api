package com.aisa.mpp.api.mppapi.service;

import com.aisa.mpp.api.mppapi.model.GroupInfo;
import com.aisa.mpp.api.mppapi.repository.GroupInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupInfoService {

    @Autowired
    private GroupInfoRepository groupInfoRepository;

    // Save a Group
    public GroupInfo saveGroup(GroupInfo groupInfo) {
        return groupInfoRepository.save(groupInfo);
    }

    // Get All Groups
    public List<GroupInfo> getAllGroups() {
        return groupInfoRepository.findAll();
    }

    // Get Group by Name
    public Optional<GroupInfo> getGroupByName(String grpName) {
        return groupInfoRepository.findByGrpName(grpName);
    }

    // Get Group by Name
    public Optional<GroupInfo> getGroupById(Long Id) {
        return groupInfoRepository.findById(Id);
    }
}