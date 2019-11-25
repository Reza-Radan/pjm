package com.progetto.projectmanagement.service.project;

import com.progetto.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.project.*;

import java.util.UUID;

public interface IProjectService {

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectAll(UUID pId ,String lang);

	/**
	 *
	 * @param lang
	 * @return
	 */
	ResponseModel getAllProject(String lang ,int page);

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectDetails(UUID pId,String lang);

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectFeature(UUID pId, String lang);

	/**
	 *
	 * @param pId
	 * @param lang
	 * @param page
	 * @return
	 */
	ResponseModel getProjectFeatures(UUID pId, String lang, int page);

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectMission(UUID pId,String lang);

	/**
	 *
	 * @param pId
	 * @param page
	 * @param lang
	 * @return
	 */
	ResponseModel getProjectMissions(UUID pId,int page ,String lang);

	/**
	 * 
	 * @param id
	 */
	ResponseModel getProjectRelease(UUID id,String lang);
	/**
	 *
	 * @param pId
	 */
	ResponseModel getProjectReleases(UUID pId,String lang , int fetchsize);

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectSubFeature(UUID pId,String lang);

	/**
	 *
	 * @param pId
	 * @param page
	 * @param lang
	 * @return
	 */
	ResponseModel getProjectSubFeatures(UUID pId, int page, String lang);

	/**
	 * 
	 * @param id
	 */
	ResponseModel getProjectVision(UUID id,String lang);

	/**
	 *
	 * @param pId
	 * @param lang
	 * @return
	 */
	ResponseModel getProjectVisions(UUID pId, int page ,String lang);

	/**
	 * 
	 * @param pId
	 */
	ResponseModel getProjectRole(UUID pId,String lang);

	/**
	 * 
	 * @param project
	 */
	ResultModel addProject(Project project);

	/**
	 * 
	 * @param projectAll
	 */
	ResultModel addProjectAll(ProjectAllDetails projectAll);

	/**
	 * 
	 * @param feature
	 */
	ResultModel addProjectFeature(FeatureByProject feature);

	/**
	 * 
	 * @param mission
	 */
	ResultModel addProjectMission(MissionByProject mission);

	/**
	 * 
	 * @param release
	 */
	ResultModel addProjectRelease(ReleaseByProject release);

	/**
	 * 
	 * @param role
	 */
	ResultModel addProjectRole(RoleByProject role);

	/**
	 * 
	 * @param subfeature
	 */
	ResultModel addProjectSubfeature(SubFeatureByProject subfeature);

	/**
	 * 
	 * @param vision
	 */
	ResultModel addProjectVision(VisionByProject vision);


	/*
	* delete methods
	* */


	public ResultModel deleteMissionTable(String missionID,String projectID,String lang);

	public ResultModel deleteVisionTable(String visionID,String projectID,String lang) ;

	public ResultModel deleteReleaseTable(String releaseID,String projectID,String lang);

	public ResultModel deleteRoleTable(String roleID,String projectID,String lang);

	public ResultModel deleteFeatureTable(String featureID,String projectID,String lang);

	public ResultModel deleteSubFeatureTable(String subFeatureID,String projectID,String lang);


   /*--------------------------------------------------------------------------------------------

    Update table separately for different route

    *---------------------------------------------------------------------------------------------*/

	public ResultModel updateMission(MissionByProject missionByProject);

	public ResultModel updateVision(VisionByProject visionByProject) ;

	public ResultModel updateRelease(ReleaseByProject releaseByProject);

	public ResultModel updateRole(RoleByProject roleByProject);

	public ResultModel updateFeature(FeatureByProject featureByProject);

	public ResultModel updateSubFeature(SubFeatureByProject subFeatureByProject);

}