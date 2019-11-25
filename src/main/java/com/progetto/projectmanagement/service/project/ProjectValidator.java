package com.progetto.projectmanagement.service.project;

import com.progetto.projectmanagement.domain.project.*;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.project.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectValidator {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;


    public ResultModel projectValidate(ProjectAllDetails project){

        if(!validateField(project.getCreateDate())) {
            resultModel.setError(1);
            resultModel.setResult(" crteateDate " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getName())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getStatus())){
            resultModel.setError(1);
            resultModel.setResult(" status " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getPercentage())){
            resultModel.setError(1);
            resultModel.setResult(" percentage " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getStart_date())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getEnd_date())) {
            resultModel.setError(1);
            resultModel.setResult(" endDate " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())) {
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }if(!validateField(project.getFeature())) {
            resultModel.setError(1);
            resultModel.setResult(" feature " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }if(!validateField(project.getMission())) {
            resultModel.setError(1);
            resultModel.setResult(" mission " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }if(!validateField(project.getVision())) {
            resultModel.setError(1);
            resultModel.setResult(" vision " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        } if(!validateField(project.getSubfeature())) {
            resultModel.setError(1);
            resultModel.setResult(" subFeature " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        } if(!validateField(project.getRelease())) {
            resultModel.setError(1);
            resultModel.setResult(" release " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        } else{
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel projectValidate(Project project){

        if(!validateField(project.getName())) {
            resultModel.setError(1);
            resultModel.setResult(" name " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMembers())) {
            resultModel.setError(1);
            resultModel.setResult(" members " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getName())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getStatus())){
            resultModel.setError(1);
            resultModel.setResult(" status " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getPercentage())){
            resultModel.setError(1);
            resultModel.setResult(" percentage " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getStartDate())){
            resultModel.setError(1);
            resultModel.setResult(" startDate " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getEndDate())) {
            resultModel.setError(1);
            resultModel.setResult(" endDate " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())) {
            resultModel.setError(1);
            resultModel.setResult(" creatorId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getProjectIdValidate(UUID pId,String lang){

        if(!validateField(pId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getIdValidate(UUID id,String lang){

        if(!validateField(id)) {
            resultModel.setError(1);
            resultModel.setResult(" id " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel getIdsValidate(String Id ,String pId,String lang){
        try {
            String regularUUid = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
            Pattern pUUid = Pattern.compile(regularUUid);
            Pattern pUUidTime = Pattern.compile(regularUUid);
            Matcher idReg = pUUidTime.matcher(Id);
            Matcher pidReg = pUUid.matcher(pId);
            System.out.println(" getIdsValidate: " + validateField(Id) + " id: " + Id);


            if (!(validateField(pId)) || !pidReg.matches()) {
                resultModel.setError(1);
                resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang, "REQUIREDFIELD"));

            } else if (!(validateField(Id)) || !idReg.matches()) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(lang, "REQUIREDFIELD"));

            } else {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        }catch (Exception e){
            resultModel.setError(1);
            resultModel.setResult(" Id or pId " + resultModel.getErrorTextByLanguage(lang, "REQUIREDFIELD"));
        }

        return resultModel;
    }


    public ResultModel addProjectFeatureValidate(FeatureByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getTitle())){
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getFeatureDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMeetingId())){
            resultModel.setError(1);
            resultModel.setResult(" meetingId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getHasSubfeature())) {
            resultModel.setError(1);
            resultModel.setResult(" hasSubfeature " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())) {
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }
        return resultModel;
    }



//    @IdColumn(name = "projectId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
//    @CassandraType(type = DataType.Name.UUID)
//    private UUID projectId;
//
//    private String title;
//    private String desc;
//    private MemberType author;
//    private List<UUID> attachment;
//    private MemberType creator;

    public ResultModel addProjectMissionValidate(MissionByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getTitle())){
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMissionDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getAttachment())) {
            resultModel.setError(1);
            resultModel.setResult(" attachment " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())) {
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }
        return resultModel;
    }

    public ResultModel addProjectReleaseValidate(ReleaseByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getTitle())){
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getReleaseDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getReleaseDesc())) {
            resultModel.setError(1);
            resultModel.setResult(" meetingId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        } else if(!validateField(project.getIssueId())) {
            resultModel.setError(1);
            resultModel.setResult(" issueId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())) {
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel addProjectRoleValidate(RoleByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }/*else if(!validateField(project.getMember())){
            resultModel.setError(1);
            resultModel.setResult(" member " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }*/else if(!validateField(project.getProject_name())){
            resultModel.setError(1);
            resultModel.setResult(" projectName " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMember_name())){
            resultModel.setError(1);
            resultModel.setResult(" memberName " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getIs_stackholder())) {
            resultModel.setError(1);
            resultModel.setResult(" isStackholder " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        } else if(!validateField(project.getRole_name())) {
            resultModel.setError(1);
            resultModel.setResult(" roleName " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getPermission_key())) {
            resultModel.setError(1);
            resultModel.setResult(" permissionKey " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
        }else if(!validateField(project.getPermission_name())) {
            resultModel.setError(1);
            resultModel.setResult(" permissionName " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMemberID())) {
            resultModel.setError(1);
            resultModel.setResult(" memberID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public ResultModel addProjectSubfeatureValidate(SubFeatureByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getTitle())){
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getSubFeatureDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())){
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getFeature_title())){
            resultModel.setError(1);
            resultModel.setResult(" featureTitle " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getFeature_id())) {
            resultModel.setError(1);
            resultModel.setResult(" featureId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getDate())) {
            resultModel.setError(1);
            resultModel.setResult(" date " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMembers())) {
            resultModel.setError(1);
            resultModel.setResult(" members " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }
        return resultModel;
    }


    public ResultModel addProjectVisionValidate(VisionByProject project){

        if(!validateField(project.getprojectId())) {
            resultModel.setError(1);
            resultModel.setResult(" projectId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getTitle())){
            resultModel.setError(1);
            resultModel.setResult(" title " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getVisionDesc())){
            resultModel.setError(1);
            resultModel.setResult(" desc " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getCreatorID())){
            resultModel.setError(1);
            resultModel.setResult(" creatorID " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(project.getMembers())) {
            resultModel.setError(1);
            resultModel.setResult(" members " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }
        return resultModel;
    }


    /*
     check update value
     */
    public ResultModel updateProjectFeatureValidate(FeatureByProject project){

        resultModel =  addProjectFeatureValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getId())) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }


    public ResultModel updateProjectSubFeatureValidate(SubFeatureByProject project){

        resultModel =  addProjectSubfeatureValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getId())) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }

    public ResultModel updateProjectVisionValidate(VisionByProject project){

        resultModel =  addProjectVisionValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getId())) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }
    public ResultModel updateProjectMissionValidate(MissionByProject project){

        resultModel =  addProjectMissionValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getId())) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }

    public ResultModel updateProjectReleaseValidate(ReleaseByProject project){

        resultModel =  addProjectReleaseValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getId())) {
                resultModel.setError(1);
                resultModel.setResult(" Id " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }

    public ResultModel updateProjectRoleValidate(RoleByProject project){

        resultModel =  addProjectRoleValidate(project);
        if(resultModel.getError() == 0)
            if(!validateField(project.getroleId())) {
                resultModel.setError(1);
                resultModel.setResult(" roleId " + resultModel.getErrorTextByLanguage(project.getLang(),"REQUIREDFIELD"));
            }else  {
                resultModel.setResult(requirementsProperties.getSuccessful());
                resultModel.setError(0);
            }
        return resultModel;
    }

    public <T> boolean validateField(T field){
        if(field!=null)
            if (field=="")
                return false;
            else
                return true;
        else
            return false;

    }


}
