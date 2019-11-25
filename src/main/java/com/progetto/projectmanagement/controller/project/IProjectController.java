package com.progetto.projectmanagement.controller.project;

import com.progetto.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.project.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public interface IProjectController {
    
    ResponseModel getProjectDetails(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectMission(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectVision(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectVisions(UUID pId, int page ,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectFeature(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectSubFeature(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectSubFeatures(UUID pId, String lang ,int page, HttpServletResponse httpServletResponse);
    ResponseModel getProjectRelease(UUID id,String lang , HttpServletResponse httpServletResponse);
    ResponseModel getProjectReleases(UUID pId,String lang  , int fetchsize, HttpServletResponse httpServletResponse);
    ResponseModel getProjectAll(UUID pId, String lang ,HttpServletResponse httpServletResponse);

    ResponseModel addProject(Project project, HttpServletResponse httpServletResponse);
    ResponseModel addProjectMission(MissionByProject mission, HttpServletResponse httpServletResponse);
    ResponseModel addProjectVision(VisionByProject vision, HttpServletResponse httpServletResponse);
    ResponseModel addProjectRelease(ReleaseByProject release, HttpServletResponse httpServletResponse);
    ResponseModel addProjectFeature(FeatureByProject feature, HttpServletResponse httpServletResponse);
    ResponseModel addProjectSubfeature(SubFeatureByProject subfeature, HttpServletResponse httpServletResponse);
    ResponseModel addProjectAll(ProjectAllDetails projectAll, HttpServletResponse httpServletResponse);
    ResponseModel addProjectRole(RoleByProject role, HttpServletResponse httpServletResponse);


    ResponseModel deleteMissionTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse);
    ResponseModel deleteVisionTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) ;
    ResponseModel deleteReleaseTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse);
    ResponseModel deleteRoleTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse);
    ResponseModel deleteFeatureTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse);
    ResponseModel deleteSubFeatureTable( Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse);


   /*--------------------------------------------------------------------------------------------

    Update table separately for different route

    *---------------------------------------------------------------------------------------------*/

    ResponseModel updateMission(MissionByProject missionByProject, HttpServletResponse httpServletResponse);

    ResponseModel updateVision(VisionByProject visionByProject, HttpServletResponse httpServletResponse) ;

    ResponseModel updateRelease(ReleaseByProject releaseByProject, HttpServletResponse httpServletResponse);

    ResponseModel updateRole(RoleByProject roleByProject, HttpServletResponse httpServletResponse);

    ResponseModel updateFeature(FeatureByProject featureByProject, HttpServletResponse httpServletResponse);

    ResponseModel updateSubFeature(SubFeatureByProject subFeatureByProject, HttpServletResponse httpServletResponse);
}

