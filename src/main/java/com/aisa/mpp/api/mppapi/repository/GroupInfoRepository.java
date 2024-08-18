package com.aisa.mpp.api.mppapi.repository;


import com.aisa.mpp.api.mppapi.model.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroupInfoRepository extends JpaRepository<GroupInfo, Long> {
    Optional<GroupInfo> findByGrpName(String grpName);

    Optional<GroupInfo> findById(Long id);
}