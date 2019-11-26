package com.progetto.projectmanagement.service.project;

import com.progetto.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.project.*;
import org.apache.cassandra.utils.UUIDGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ProjectService implements IProjectService {


	@Autowired
	ProjectValidator projectValidator;

	@Autowired
	ResultModel resultModel;

	@Autowired
	AddProjectRepository addProjectRepository;

	@Autowired
	ResponseModel responseModel;

	@Autowired
	RequirementsProperties requirementsProperties;

	String tag = "ProjectService";

	Logger logger = LoggerFactory.getLogger(ProjectService.class);

	@Override
	public ResponseModel getProjectAll(UUID pId,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectAll(pId,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getAllProject(String lang ,int page) {
		resultModel.setResult(null);
		resultModel.setError(0);

		responseModel  = addProjectRepository.getAllProject(lang ,page);

		return responseModel;
	}

	@Override
	public ResponseModel getProjectDetails(UUID pId,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectDetails(pId,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectFeature(UUID id,String lang) {

		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(id,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectFeature(id,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectFeatures(UUID pId, String lang, int page) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectFeatures(pId,page,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}


	@Override
	public ResponseModel getProjectMission(UUID id,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdValidate(id,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectMission(id,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}


	@Override
	public ResponseModel getProjectMissions(UUID pId,int page ,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId ,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectMissions(pId,page,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectRelease(UUID id,String lang) {

		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdValidate(id,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectRelease(id,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectReleases(UUID pId, String lang , int page) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectReleases(pId,page,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectSubFeature(UUID id,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdValidate(id,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectSubFeature(id,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectSubFeatures(UUID pId, int page, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectSubFeatures(pId,page,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectVision(UUID id,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdValidate(id,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectVision(id,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectVisions(UUID pId,int page, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectVisions(pId,page ,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResponseModel getProjectRole(UUID pId,String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getProjectIdValidate(pId,lang);
		if (resultModel.getError() == 0) {
			responseModel  = addProjectRepository.getProjectRole(pId,lang);
		}else{
			responseModel.setError(resultModel.getResult());
			responseModel.setResult(requirementsProperties.getFail());
		}
		return responseModel;
	}

	@Override
	public ResultModel addProject(Project project) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.projectValidate(project);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProject(project);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectAll(ProjectAllDetails projectAll) {
		resultModel  = projectValidator.projectValidate(projectAll);
        logger.info(tag+" resultModel "+resultModel.getError());
		  if (resultModel.getError() == 0) {
			  resultModel  = addProjectRepository.addProjectAll(projectAll);
		  }
		return resultModel;
	}

	@Override
	public ResultModel addProjectFeature(FeatureByProject feature) {
		resultModel.setResult(null);
		resultModel.setError(0);
		 resultModel = projectValidator.addProjectFeatureValidate(feature);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectFeature(feature);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectMission(MissionByProject mission) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.addProjectMissionValidate(mission);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectMission(mission);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectRelease(ProjectStockholder release) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.addProjectReleaseValidate(release);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectRelease(release);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectRole(RoleByProject role) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.addProjectRoleValidate(role);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectRole(role);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectSubfeature(SubFeatureByProject subfeature) {
		logger.error("addProjectSubfeature");
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.addProjectSubfeatureValidate(subfeature);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectSubfeature(subfeature);
		}
		return resultModel;
	}

	@Override
	public ResultModel addProjectVision(VisionByProject vision) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.addProjectVisionValidate(vision);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.addProjectVision(vision);
		}
		return resultModel;
	}




	/*
	 Delete methods
	 */
	@Override
	public ResultModel deleteMissionTable(String missionID, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(missionID , projectID,lang);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteMissionTable(UUID.fromString(missionID),UUID.fromString(projectID),lang);
		}
		return resultModel;
	}

	@Override
	public ResultModel deleteVisionTable(String visionID, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(visionID , projectID,lang);
		logger.warn("visionID: " +visionID + " projectID: " +projectID + " resultModel: " +resultModel);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteVisionTable(UUID.fromString(visionID)
					,UUID.fromString(projectID)
					,lang);
		}
		return resultModel;
	}

	@Override
	public ResultModel deleteReleaseTable(String time, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(time , projectID,lang);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteReleaseTable(UUID.fromString(time),UUID.fromString(projectID),lang);
		}
		return resultModel;
	}

	@Override
	public ResultModel deleteRoleTable(String roleID, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(roleID , projectID,lang);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteRoleTable(UUID.fromString(roleID),UUID.fromString(projectID),lang);
		}
		return resultModel;
	}

	@Override
	public ResultModel deleteFeatureTable(String featureID, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(featureID , projectID,lang);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteSubFeatureTable(UUID.fromString(featureID),UUID.fromString(projectID), lang);
		}
		return resultModel;
	}

	@Override
	public ResultModel deleteSubFeatureTable(String subFeatureID, String projectID, String lang) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.getIdsValidate(subFeatureID , projectID,lang);
		if(resultModel.getError()==0) {
			resultModel = addProjectRepository.deleteSubFeatureTable(UUID.fromString(subFeatureID),UUID.fromString(projectID),lang);
		}
		return resultModel;
	}

	/*
	  Update methods
	 */

	@Override
	public ResultModel updateMission(MissionByProject missionByProject) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectMissionValidate(missionByProject);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateMission(missionByProject);
		}
		return resultModel;
	}

	@Override
	public ResultModel updateVision(VisionByProject visionByProject) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectVisionValidate(visionByProject);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateVision(visionByProject);
		}
		return resultModel;
	}

	@Override
	public ResultModel updateRelease(ProjectStockholder projectStockholder) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectReleaseValidate(projectStockholder);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateRelease(projectStockholder);
		}
		return resultModel;
	}

	@Override
	public ResultModel updateRole(RoleByProject roleByProject) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectRoleValidate(roleByProject);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateRole(roleByProject);
		}
		return resultModel;
	}

	@Override
	public ResultModel updateFeature(FeatureByProject featureByProject) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectFeatureValidate(featureByProject);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateFeature(featureByProject);
		}
		return resultModel;
	}

	@Override
	public ResultModel updateSubFeature(SubFeatureByProject subFeatureByProject) {
		resultModel.setResult(null);
		resultModel.setError(0);
		resultModel = projectValidator.updateProjectSubFeatureValidate(subFeatureByProject);
		if (resultModel.getError() == 0) {
			resultModel  = addProjectRepository.updateSubFeature(subFeatureByProject);
		}
		return resultModel;
	}
}