package ir.dabacenter.projectmanagement.controller.project;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.project.*;
import ir.dabacenter.projectmanagement.service.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@RestController
@RequestMapping("/project")
public class ProjectController implements IProjectController {

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel  responseModel;

    @Autowired
    ProjectService projectService;

    @Autowired
    ResultModel resultModel;
    Logger logger = LoggerFactory.getLogger(ProjectController.class);


    @RequestMapping(value = "/getProjectDetails", method = RequestMethod.POST)
    public ResponseModel getProjectDetails(@RequestParam UUID pId,String lang, HttpServletResponse httpServletResponse) {
        logger.info("getProject details pid "+pId);

        makeResponseClear();
        responseModel = projectService.getProjectDetails(pId,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    //get all project list with order by time
    @RequestMapping(value = "/getAllProject", method = RequestMethod.POST)
    public ResponseModel getAllProject(@RequestParam int page ,String lang, HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getAllProject(lang ,page);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectMission", method = RequestMethod.POST)
    public ResponseModel getProjectMission(@RequestParam UUID id,String lang , HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectMission(id ,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectMissions", method = RequestMethod.POST)
    public ResponseModel getProjectMissions(@RequestParam UUID pId,@RequestParam int page ,String lang , HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectMissions(pId ,page, lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectVision", method = RequestMethod.POST)
    public ResponseModel getProjectVision(@RequestParam UUID id, String lang ,HttpServletResponse httpServletResponse){

        makeResponseClear();
        responseModel = projectService.getProjectVision(id,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }


    @RequestMapping(value = "/getProjectVisions", method = RequestMethod.POST)
    public ResponseModel getProjectVisions(@RequestParam UUID pId,@RequestParam int page ,String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = projectService.getProjectVisions(pId,page,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectFeature", method = RequestMethod.POST)
    public ResponseModel getProjectFeature(@RequestParam UUID id, String lang ,HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectFeature(id,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectFeatures", method = RequestMethod.POST)
    public ResponseModel getProjectFeatures(@RequestParam UUID pId, String lang ,
                                            @RequestParam int page,HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectFeatures(pId,lang ,page);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectSubFeature", method = RequestMethod.POST)
    public ResponseModel getProjectSubFeature(@RequestParam UUID id, String lang ,HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectSubFeature(id,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }


    @RequestMapping(value = "/getProjectSubFeatures", method = RequestMethod.POST)
    public ResponseModel getProjectSubFeatures(@RequestParam UUID pId, String lang ,
                                               @RequestParam int page,HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectSubFeatures(pId,page, lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectRelease", method = RequestMethod.POST)
    public ResponseModel getProjectRelease(@RequestParam UUID id, String lang , HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectRelease(id ,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectReleases", method = RequestMethod.POST)
    public ResponseModel getProjectReleases(@RequestParam  UUID pId, String lang,
                                            @RequestParam int page, HttpServletResponse httpServletResponse) {

        logger.info("getProjectReleases");
        makeResponseClear();
        responseModel = projectService.getProjectReleases(pId,lang ,page);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @RequestMapping(value = "/getProjectAll", method = RequestMethod.POST)
    public ResponseModel getProjectAll(UUID pId, String lang ,HttpServletResponse httpServletResponse) {

        makeResponseClear();
        responseModel = projectService.getProjectAll(pId,lang);
        responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public ResponseModel addProject(@RequestBody Project project, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.addProject(project);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }




    @Override
    @RequestMapping(value = "/addMission", method = RequestMethod.POST)
    public ResponseModel addProjectMission(@RequestBody MissionByProject mission, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.addProjectMission(mission);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addVision", method = RequestMethod.POST)
    public ResponseModel addProjectVision(@RequestBody VisionByProject vision, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.addProjectVision(vision);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addRelease", method = RequestMethod.POST)
    public ResponseModel addProjectRelease(@RequestBody ReleaseByProject release, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.addProjectRelease(release);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addFeature", method = RequestMethod.POST)
    public ResponseModel addProjectFeature(@RequestBody FeatureByProject feature, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.addProjectFeature(feature);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addSubfeature", method = RequestMethod.POST)
    public ResponseModel addProjectSubfeature(@RequestBody SubFeatureByProject subfeature, HttpServletResponse httpServletResponse) {

        logger.info("addProjectSubfeature");
        makeResponseClear();
        resultModel = projectService.addProjectSubfeature(subfeature);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public ResponseModel addProjectAll(@RequestBody ProjectAllDetails projectAllDetails , HttpServletResponse httpServletResponse) {

        makeResponseClear();
        resultModel =  projectService.addProjectAll(projectAllDetails);
          if(resultModel.getError() == 0) {
              responseModel.setResult(resultModel.getResult());
              responseModel.setStatus(httpServletResponse.getStatus());
          }else{
              responseModel.setError(resultModel.getResult());
              responseModel.setSystemError(resultModel.getResult());
              responseModel.setStatus(httpServletResponse.getStatus());
              responseModel.setResult(requirementsProperties.getFail());
          }
        return responseModel;
    }



    @Override
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseModel addProjectRole(@RequestBody RoleByProject role, HttpServletResponse httpServletResponse) {

        makeResponseClear();
        resultModel = projectService.addProjectRole(role);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @RequestMapping(value = {"/deleteMission/{time}/{projectID}/{lang}", "/deleteMission/{time}/{projectID}"}, method = RequestMethod.DELETE)
    public ResponseModel deleteMissionTable(@PathVariable Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) {
        makeResponseClear();

       String [] returnValue = sepratedData(pathVariablesMap);
        resultModel = projectService.deleteMissionTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @RequestMapping(value = {"/deleteVision/{time}/{projectID}/{lang}","/deleteVision/{time}/{projectID}"} , method = RequestMethod.DELETE)
    public ResponseModel deleteVisionTable(@PathVariable Map<String, String> pathVariablesMap , HttpServletResponse httpServletResponse) {

        logger.error("pathVariablesMap: " + pathVariablesMap );
        makeResponseClear();
        String [] returnValue = sepratedData(pathVariablesMap);
        logger.warn("returnValue: " + returnValue[0]+returnValue[1]+returnValue[2] );
        resultModel = projectService.deleteVisionTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = {"/deleteRelease/{time}/{projectID}/{lang}","/deleteRelease/{time}/{projectID}"}, method = RequestMethod.DELETE)
    public ResponseModel deleteReleaseTable(@PathVariable Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        String [] returnValue = sepratedData(pathVariablesMap);
        resultModel = projectService.deleteReleaseTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = {"/deleteRole/{roleID}/{projectID}/{lang}","/deleteRole/{roleID}/{projectID}"}, method = RequestMethod.DELETE)
    public ResponseModel deleteRoleTable(@PathVariable Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        String [] returnValue = sepratedData(pathVariablesMap);
        resultModel = projectService.deleteRoleTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @RequestMapping(value = {"/deleteFeature/{time}/{projectID}/{lang}","/deleteFeature/{time}/{projectID}"}, method = RequestMethod.DELETE)
    public ResponseModel deleteFeatureTable(@PathVariable Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) {
        makeResponseClear();
//        logger.error("deleteFeatureTable featureId" +featureId);
        String [] returnValue = sepratedData(pathVariablesMap);
        resultModel = projectService.deleteFeatureTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = {"/deleteSubfeature/{subFeatureID}/{projectID}/{lang}","/deleteSubfeature/{subFeatureID}/{projectID}"}, method = RequestMethod.DELETE)
    public ResponseModel deleteSubFeatureTable(@PathVariable Map<String, String> pathVariablesMap, HttpServletResponse httpServletResponse) {
        logger.info("deleteSubFeatureTable");
        makeResponseClear();
        String [] returnValue = sepratedData(pathVariablesMap);
        resultModel = projectService.deleteSubFeatureTable(returnValue[0],returnValue[1],returnValue[2]);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateMission", method = RequestMethod.POST)
    public ResponseModel updateMission(@RequestBody MissionByProject missionByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateMission(missionByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateVision", method = RequestMethod.POST)
    public ResponseModel updateVision(@RequestBody VisionByProject visionByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateVision(visionByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateRelease", method = RequestMethod.POST)
    public ResponseModel updateRelease(@RequestBody ReleaseByProject releaseByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateRelease(releaseByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResponseModel updateRole(@RequestBody RoleByProject roleByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateRole(roleByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateFeature", method = RequestMethod.POST)
    public ResponseModel updateFeature(@RequestBody FeatureByProject featureByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateFeature(featureByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/updateSubfeature", method = RequestMethod.POST)
    public ResponseModel updateSubFeature(@RequestBody SubFeatureByProject subFeatureByProject, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = projectService.updateSubFeature(subFeatureByProject);
        if (resultModel.getError() == 0){
            responseModel.setResult(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
        }else{
            responseModel.setError(resultModel.getResult());
            responseModel.setSystemError(resultModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(requirementsProperties.getFail());
        }

        return responseModel;
    }


    public void makeResponseClear(){
        responseModel.setContents(null);
        responseModel.setContent(null);
        responseModel.setSystemError("");
        responseModel.setError("");
        responseModel.setRecordCount(0);
    }


    private String[] sepratedData(Map<String, String> pathVariablesMap ) {
        String [] returnData = new String[3];
        if (!pathVariablesMap.isEmpty()&& pathVariablesMap.containsKey("time")) {
            try{
                returnData[0] = pathVariablesMap.get("time");
            }catch (Exception e){
                returnData[0] = null;
            }
        } if(pathVariablesMap.containsKey("projectID")){
            try{
                returnData[1] = pathVariablesMap.get("projectID");
            }catch (Exception e){
                returnData[1]  = null;
            }
        } if(pathVariablesMap.containsKey("lang")){
            try {

                returnData[2]  = pathVariablesMap.get("lang");
            }catch (Exception e){
                returnData[2]  = "en";
            }
        }

        return returnData;
    }
}
