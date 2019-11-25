package ir.dabacenter.projectmanagement.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class RequirementsProperties {


    @Autowired
    Environment environment;


    String fail;
    String successful;
    String unknownDatabaseError;
    String userExistence;
    String keyspace;
    String contactPoint;
    String port;
    String requiredField ;



    public String getUserExistence() { return environment.getProperty("USEREXIST");  }

    public String unknownError() { return environment.getProperty("UNKNOWN_TRANSACTION_ERROR");  }

    public String getRequiredField() {   return environment.getProperty("REQUIREDFIELD");  }

    public String getContactPoint() { return environment.getProperty("CONTACTPOINT");  }

    public String getPort() { return environment.getProperty("PORT"); }

    public String getKeyspace() {return environment.getProperty("KEYSPACE"); }

    public String getFail() {  return environment.getProperty("FAIL"); }

    public void setFail(String fail) {   this.fail = fail; }

    public String getSuccessful() { return environment.getProperty("SUCCESSFUL"); }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    public String getUnknownDatabaseError() { return environment.getProperty("UNKNOWN_DATABASE_ERROR"); }

    public void setUnknownDatabaseError(String error) {
        this.unknownDatabaseError = error;
    }

    public String noAvailableData() { return environment.getProperty("NOAVAILABLEDATA"); }

    public String fieldsNameMisake() { return environment.getProperty("CHECKFIELD"); }

    public String tokenNotValid() { return environment.getProperty("TOKEN_NOTVALID"); }

    public String roleIdNotValid() { return environment.getProperty("ROLE_ID_REQUIRED"); }


    /*****************************************
      add tables name to avoid the spelling mistake
     *****************************************/

    String   meeting
            ,meetingByProject
            ,meetingByFeature
            ,meetingByRole
            ,meetingByRelease
            ,reportByMeeting
            ,notificationByMeeting
            ,member
            ,permission
            ,role
            ,issue
            ,issueByRelease
            ,issueByTask
            ,project
            ,roleByProject
            ,featureByProject
            ,releaseByProject
            ,missionByProject
            ,subfeatureByProject
            ,visionByProject
            ,projectAllDetails
            ,projectReport
            ,projectNotification
            ,task
            ,taskByProject
            ,subfeatureByTask
            ,notificationByTask
            ,notificationByUser
            ,reportByUser
            ,reportByTask
            ,attachment;

    public String getMeeting() {return environment.getProperty("meeting"); }

    public String getMeetingByProject() { return environment.getProperty("meeting_by_project"); }

    public String getMeetingByFeature() { return environment.getProperty("meeting_by_feature");  }

    public String getMemberByRole() {  return environment.getProperty("member_by_role");  }

    public String membertable() {  return environment.getProperty("member");  }

    public String getMeetingByRelease() {  return environment.getProperty("meeting_by_release");  }

    public String getReportByMeeting() {  return environment.getProperty("report_by_meeting");  }

    public String getNotificationByMeeting() {  return environment.getProperty("notification_by_meeting");  }

    public String getPermission() {  return environment.getProperty("permission"); }

    public String getRole() { return environment.getProperty("role");  }

    public String getIssue() { return environment.getProperty("issue"); }

    public String getIssueByRelease() {  return environment.getProperty("issue_by_release"); }

    public String getIssueByTask() {  return environment.getProperty("issue_by_task"); }

    public String getProject() {  return environment.getProperty("project");  }

    public String getRoleByProject() { return environment.getProperty("role_by_project"); }

    public String getFeatureByProject() { return environment.getProperty("feature_by_project"); }

    public String getReleaseByProject() {  return environment.getProperty("release_by_project"); }

    public String getMissionByProject() {   return environment.getProperty("mission_by_project");  }

    public String getSubfeatureByProject() {    return environment.getProperty("subfeature_by_project");  }

    public String getVisionByProject() {   return environment.getProperty("vision_by_project");    }

    public String getProjectAllDetails() {   return environment.getProperty("project_all_details");   }

    public String getProjectReport() {   return environment.getProperty("project_report");    }

    public String getProjectNotification() {   return environment.getProperty("project_notification");  }

    public String getTask() {    return environment.getProperty("task");   }

    public String getTaskByUserImplementer() {    return environment.getProperty("task_by_user_implementer");   }

    public String getTaskByUserResp() {    return environment.getProperty("task_by_user_responsible");   }

    public String getTaskByProject() { return environment.getProperty("task_by_project");   }

    public String getSubfeatureByTask() {   return environment.getProperty("subfeature_by_task");    }

    public String getNotificationByTask() {     return environment.getProperty("notification_by_task");   }

    public String getNotificationByUser() {   return environment.getProperty("notification_by_user");  }

    public String getReportByUser() {    return environment.getProperty("report_by_user");   }

    public String getReportByTask() {    return environment.getProperty("report_by_task");   }

    public String getAttachment() {  return environment.getProperty("attachment");    }

    public String accessDenied() {  return environment.getProperty("ACCESS_DENIED"); }

    /******************************************/

    /*Clear the value of response model for each request*/



}
