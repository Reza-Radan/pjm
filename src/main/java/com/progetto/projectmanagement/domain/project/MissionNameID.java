package com.progetto.projectmanagement.domain.project;


import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.UUID;

@UserDefinedType("MissionNameID")
public class MissionNameID {

    private UUID missionId ;
    private String missionName;

    public UUID getMissionId() {
        return missionId;
    }

    public void setMissionId(UUID missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }
}
