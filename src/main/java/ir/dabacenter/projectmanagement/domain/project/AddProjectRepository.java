package ir.dabacenter.projectmanagement.domain.project;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.*;
import com.datastax.driver.core.utils.Bytes;
import ir.dabacenter.projectmanagement.ConfigSession;
import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.MemberType;
import ir.dabacenter.projectmanagement.domain.role.RoleRepository;
import ir.dabacenter.projectmanagement.domain.uuidClass;
import ir.dabacenter.projectmanagement.service.project.IProjectService;
import org.apache.cassandra.utils.UUIDGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;
import static org.hibernate.criterion.Restrictions.eq;

public class AddProjectRepository  {


    private String idStg = "id";
    private String local_idStg = "local_id";
    private String crteateDate = "crteateDate";
    private String name = "name";
    private String projectDescription = "projectDescription";
    private String status = "status";
    private String percentage = "percentage";
    private String startDate = "startDate";
    private String endDate = "endDate";
    private String creator = "creator";
    private String lang = "lang";
    private String creatorID = "creatorID";
    private String modifyTime = "modifyTime";
    private String timeString = "time";

    private String feature = "feature";
    private String mission = "mission";
    private String subfeature = "subfeature";
    private String release = "release";
    private String vision = "vision";
    private String members = "members";
    private String createDate = "createDate";

    //releaseByPRoject
    private String title = "title";
    private String releaseDesc = "releaseDesc";
    private String projectIdString = "projectId";
    private String missionId = "missionId";
    private String meetingId = "meetingId";
    private String issueId = "issueId";
    private String missionName = "missionName";
    private String family = "family";
    private String userName = "userName";
    private String email = "email";
    private String phoneNumber = "phoneNumber";
    private String imagePath = "imagePath";


    //FeatureByProject
    private String missionDesc = "missionDesc";
    private String attachment = "attachment";

    //MissionByProject
    private String featureDesc = "featuredesc";
    private String hasSubfeature = "hasSubfeature";
    private String undefinedSub = "undefinedSub";

    //subfeture
    private String subFeatureDesc = "subFeatureDesc";
    private String featureId = "featureId";
    private String featureTitle = "featureTitle";
    private String date = "date";

    //vision
    private String visionDesc = "visionDesc";

    //role
    private String roleId = "roleId";
    private String memberID = "memberID";
    private String member = "member";
    private String isStakeholder = "isStakeholder";
    private String roleName = "roleName";
    private String permissionKey = "permissionKey";
    private String permissionName = "permissionName";

    private String phoneNumberMemberType = "phonenumber";
    private String projectName = "projectName";


    ResultSet resultset, resultSetBatch, resultsetMemberRole;
    Logger logger = LoggerFactory.getLogger(AddProjectRepository.class);
    Batch batch = null;
    QueryBuilder queryBuilder;
    Select.Where select, selectMemberRole;
    Select selectallow ,selectProjectNotification ,selectProject,selectallowMemberRole;
    Update.Where update, updateNotificaiton, updateMission, updateVision, updateRelease, updateFeature, updateSubFeature,updateRole;
    Insert insertMemberRole, insertMember;


    @Autowired
    RequirementsProperties requirementsProperties;

    static ConfigSession sConfigSession;
    static ResultModel sResultModel;
    static RequirementsProperties sRequirementsProperties;

    @Autowired
    ConfigSession configSession;

    @Autowired
    ResultModel resultModel;

    @Autowired
    RoleRepository roleRepository;
    private String tag = "AddProjectRepository";
    private UUID fetchId = null, uuid = null ,timeALong = null;

    @Autowired
    ResponseModel responseModel;

    @PostConstruct
    public void init() {
        sConfigSession = configSession;
        sResultModel= resultModel;
        sRequirementsProperties = requirementsProperties;

    }

    //depend on other table mission vission ,...
    public ResponseModel getProjectAll(UUID pId,String lang) {
//        responseModel.setResult("");
//        logger.info("uuid: " +uuid);
//        try {
//            ResultSet resultsetMembers = configSession.getSession().execute(QueryBuilder.select()
//                    .all()
//                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getProjectAllDetails())
//                    .where(eq(id, uuid)));
//
//            ProjectAllDetails ProjectAllDetails = null;
//            for (Row row : resultsetMembers) {
//                logger.warn("uuid: " + row.getString("name"));
//                ProjectAllDetails = new ProjectAllDetails();
//                ProjectAllDetails = setMember(row, ProjectAllDetails);
//                responseModel.setContent(ProjectAllDetails);
//                responseModel.setResult(requirementsProperties.getSuccessful());
//            }
//            responseModel.setRecordCount(1);
//
//            if (ProjectAllDetails == null) {
//                responseModel.setResult(requirementsProperties.getFail());
//                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "MEMBER_NOT_EXIST_wITH_SPECIAL_ID"));
//                responseModel.setRecordCount(0);
//            }
//        }catch (Exception e){
//            responseModel.setResult(requirementsProperties.getFail());
//            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
//            responseModel.setSystemError(e.toString());
//            responseModel.setRecordCount(0);
//        }
        return responseModel;
    }

    //just project table
    public ResponseModel getProjectDetails(UUID id,String lang) {

        responseModel.setResult("");
        logger.info("uuid: " +id + " lang: " +lang);
        try {
            ResultSet result = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getProject())
                    .where(eq(idStg, id)));

            Project project = null;
            for (Row row : result) {
                project = new Project();
                project = setProject(row, project);
                responseModel.setContent(project);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (project == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "PROJECT_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    /**
     * @author masoomeh
     * @param lang
     * @return
     */
    public ResponseModel getAllProject(String lang,int page){

        List<UUID> ids = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<>();

        responseModel.setResult("");
        try {

            ResultSet resultSetIds = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getProject()));

            for (Row row : resultSetIds) {
                ids.add(AllProjectIds(row));
            }

            //get all projects
            if(ids.size()>0) {
                int index =  10*(page-1) ;
                Select query = QueryBuilder.select()
                        .all()
                        .from(requirementsProperties.getKeyspace(), requirementsProperties.getProject())
                        .where(in(idStg, ids)).orderBy(QueryBuilder.desc(timeString));
                Statement statement = new SimpleStatement(query.toString());
                statement.setFetchSize(Integer.MAX_VALUE);
                ResultSet resultSetProjects = configSession.getSession().execute(statement);

                logger.info("tag ids: "  +ids  + " resultSetProjects: " +resultSetProjects.toString());
                List<Row> dataAll = resultSetProjects.all();

                //notification



                //roleBy project

                if(resultSetProjects.wasApplied() && dataAll.size()>0) {
                    Project project = null;
                    int maxpage =page * 10;
                    for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                        Row row = dataAll.get(i);
                        project = new Project();
                        project = setProjectAll(row, project);
                        projects.add(project);
                    }
                }

                responseModel.setContent(projects);
                responseModel.setResult(requirementsProperties.getSuccessful());

                responseModel.setRecordCount(projects.size());
            }

            if (projects.size()<=0) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "PROJECT_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }

        return responseModel;
    }




    public ResponseModel getProjectFeature(UUID id ,String lang) {

        responseModel.setResult("");
        logger.info("uuid: " +id + " lang: " +lang);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).allowFiltering()
                    .where(eq(idStg, id)));

            logger.warn("resultset: " + resultset);
            FeatureByProject featureByProject = null;
            for (Row row : resultset) {
                featureByProject = new FeatureByProject();
                featureByProject = setFeature(row, featureByProject);
                responseModel.setContent(featureByProject);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (featureByProject == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "PROJECT_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    public ResponseModel getProjectFeatures(UUID pId,int page,String lang) {
        responseModel.setResult("");
        ArrayList<FeatureByProject> featureByProjects = new ArrayList<>();
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).allowFiltering()
                    .where(eq(projectIdString, pId)));

            List<Row> dataAll = rs.all();
                logger.info(" dataAll.size(): " +dataAll.size()
                +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    FeatureByProject featureByProject = new FeatureByProject();
                    featureByProjects.add(setFeature(row, featureByProject));
                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (featureByProjects.size() > 0 ) {
                      responseModel.setContent(featureByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(featureByProjects.size());

            }else {
                responseModel.setContent(featureByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(featureByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    public ResponseModel getProjectMission(UUID time,String lang) {

        responseModel.setResult("");
        logger.info("uuid: " +time + " lang: " +lang);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).allowFiltering()
                    .where(eq(idStg, time)));

            logger.warn("resultset: " + resultset);
            MissionByProject missionByProject = null;
            for (Row row : resultset) {
                missionByProject = new MissionByProject();
                missionByProject = setMission(row, missionByProject);
                responseModel.setContent(missionByProject);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (missionByProject == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "PROJECT_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    public ResponseModel getProjectMissions(UUID pId, int page, String lang) {
        responseModel.setResult("");
        ArrayList<MissionByProject> missionByProjects = new ArrayList<>();
        logger.info("getProjectMissions");
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).allowFiltering()
                    .where(eq(projectIdString, pId)));

            List<Row> dataAll = rs.all();
            logger.info(" dataAll.size(): " +dataAll.size()
                    +" \n rs: " +rs);
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    MissionByProject missionByProject = new MissionByProject();
                    missionByProjects.add(setMission(row, missionByProject));
                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (missionByProjects.size() > 0 ) {
                responseModel.setContent(missionByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(missionByProjects.size());

            }else {
                responseModel.setContent(missionByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(missionByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    /**
     * @author Masoomeh
     * @param Id
     * @param lang
     * @return
     */
    public ResponseModel getProjectRelease(UUID Id,String lang) {
        responseModel.setResult("");
        logger.info("getProjectRelease uuid: " +Id);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject())
                    .allowFiltering()
                    .where(eq(idStg, Id)));

            ReleaseByProject releaseByProject = null;
            for (Row row : resultset) {
                releaseByProject = new ReleaseByProject();
                releaseByProject = setRelease(row, releaseByProject);
                responseModel.setContent(releaseByProject);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (releaseByProject == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    /**
     * @author Masoomeh
     * @param pId
     * @param lang
     * @return
     */
    public ResponseModel getProjectReleases(UUID pId, int page, String lang ) {
        responseModel.setResult("");
        ArrayList<ReleaseByProject> releaseByProjects = new ArrayList<>();
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                .all()
                .from(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).allowFiltering()
                .where(eq(projectIdString, pId)));

            List<Row> dataAll = rs.all();
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                logger.info("maxpage: " +maxpage + " dataAll.size(): " +dataAll.size());
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    ReleaseByProject releaseByProject = new ReleaseByProject();
                    releaseByProjects.add(setRelease(row, releaseByProject));
                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (releaseByProjects.size() > 0 ) {
                responseModel.setContent(releaseByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(releaseByProjects.size());

            }else {
                responseModel.setContent(releaseByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(releaseByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    public ResponseModel getProjectSubFeatures(UUID pId, int page, String lang) {
        responseModel.setResult("");
        ArrayList<SubFeatureByProject> subFeatureByProjects = new ArrayList<>();
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject()).allowFiltering()
                    .where(eq(projectIdString, pId)));

            List<Row> dataAll = rs.all();
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                logger.info("maxpage: " +maxpage + " dataAll.size(): " +dataAll.size());
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    SubFeatureByProject subFeatureByProject = new SubFeatureByProject();
                    subFeatureByProjects.add(setsubFeature(row, subFeatureByProject));
                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (subFeatureByProjects.size() > 0 ) {
                responseModel.setContent(subFeatureByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(subFeatureByProjects.size());

            }else {
                responseModel.setContent(subFeatureByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "RELEASE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(subFeatureByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    public ResponseModel getProjectSubFeature(UUID id,String lang) {
        responseModel.setResult("");
        logger.info("getProjectSubFeature uuid: " +id);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject())
                    .allowFiltering()
                    .where(eq(idStg, id)));

            SubFeatureByProject subFeatureByProject = null;
            for (Row row : resultset) {
                subFeatureByProject = new SubFeatureByProject();
                subFeatureByProject = setsubFeature(row, subFeatureByProject);
                responseModel.setContent(subFeatureByProject);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (subFeatureByProject == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "SUBFEATURE_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }



    public ResponseModel getProjectVision(UUID id,String lang) {
        responseModel.setResult("");
        logger.info("getProjectSubFeature uuid: " +id);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject())
                    .allowFiltering()
                    .where(eq(idStg, id)));

            VisionByProject visionByProject = null;
            for (Row row : resultset) {
                visionByProject = new VisionByProject();
                visionByProject = setVision(row, visionByProject);
                responseModel.setContent(visionByProject);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (visionByProject == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "VISION_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    public ResponseModel getProjectVisions(UUID pId, int page, String lang) {
        responseModel.setResult("");
        ArrayList<VisionByProject> visionByProjects = new ArrayList<>();
        try {
            int index =  10*(page-1) ;

            ResultSet rs = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).allowFiltering()
                    .where(eq(projectIdString, pId)));

            List<Row> dataAll = rs.all();
            if(rs.wasApplied() && dataAll.size()>0) {

                int maxpage =page * 10;
                logger.info("maxpage: " +maxpage + " dataAll.size(): " +dataAll.size());
                for (int i = index; ((i < maxpage) && (i<dataAll.size())); i++) {
                    Row row = dataAll.get(i);
                    VisionByProject visionByProject = new VisionByProject();
                    visionByProjects.add(setVision(row, visionByProject));
                    System.out.println("uuiddddd: " + row.getUUID(idStg));
                }
            }

            if (visionByProjects.size() > 0 ) {
                responseModel.setContent(visionByProjects);
                responseModel.setResult(requirementsProperties.getSuccessful());
                responseModel.setRecordCount(visionByProjects.size());

            }else {
                responseModel.setContent(visionByProjects);
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "VISION_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setContent(visionByProjects);
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }


    public ResponseModel getProjectRole(UUID id,String lang) {

        responseModel.setResult("");
        logger.info("getProjectRole uuid: " +id);
        try {
            ResultSet resultset = configSession.getSession().execute(QueryBuilder.select()
                    .all()
                    .from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject())
                    .where(eq(idStg, id)));

            ProjectAllDetails ProjectAllDetails = null;
            for (Row row : resultset) {
                logger.warn("uuid: " + row.getString("name"));
                ProjectAllDetails = new ProjectAllDetails();
                ProjectAllDetails = setProjectAllDetail(row, ProjectAllDetails);
                responseModel.setContent(ProjectAllDetails);
                responseModel.setResult(requirementsProperties.getSuccessful());
            }
            responseModel.setRecordCount(1);

            if (ProjectAllDetails == null) {
                responseModel.setResult(requirementsProperties.getFail());
                responseModel.setError(resultModel.getErrorTextByLanguage(lang, "MEMBER_NOT_EXIST_wITH_SPECIAL_ID"));
                responseModel.setRecordCount(0);
            }
        }catch (Exception e){
            responseModel.setResult(requirementsProperties.getFail());
            responseModel.setError(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_DATABASE_ERROR"));
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(0);
        }
        return responseModel;
    }

    //Add project again
    public ResultModel addProject(Project project) {
        logger.info("project : ");
        try {
            batch = QueryBuilder.batch();
            selectProject = QueryBuilder.select(idStg).from(requirementsProperties.getKeyspace(), requirementsProperties.getProject()).allowFiltering();
            select = selectProject.where(eq(name, project.getName()));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset) {
                fetchId = row.getUUID(idStg);
                timeALong = row.getUUID(timeString);
            }

            System.out.println(tag + " fetchId " + fetchId);
            if (fetchId == null) {
                uuid = UUID.randomUUID();
            } else {
                uuid = fetchId;
            }

            if (timeALong == null) {
                timeALong = UUIDGen.getTimeUUID();
            }
        /*
         Insert into project table, basic details of project
         */
            System.out.println(tag + " uuid " + uuid + " project.getId() " + project.getId() + " fetchId : " + fetchId);
            update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getProject()).
                    with()
                    .and(QueryBuilder.set(name, project.getName()))
                    .and(QueryBuilder.set(projectDescription, project.getDesc()))
                    .and(QueryBuilder.set(status, project.getStatus()))
                    .and(QueryBuilder.set(percentage, project.getPercentage()))
                    .and(QueryBuilder.set(startDate, project.getStartDate()))
                    .and(QueryBuilder.set(endDate, project.getEndDate()))
                    .and(QueryBuilder.set(creatorID, project.getCreatorID()))
                    .and(QueryBuilder.set(creator, getMemberTypeById(project.getCreatorID())))
//                .and(QueryBuilder.set(lang,getRoleByProjectUDTList(project,uuid,project.getName())))
                    .and(QueryBuilder.set(lang, project.getLang()))
                    .and(QueryBuilder.set(modifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(members, getRoleByProjectUDTList(project, uuid, project.getName())))
//                .and(QueryBuilder.set(idStg, UUIDGen.getTimeUUID()))

                    .where(QueryBuilder.eq(idStg, uuid)).and(QueryBuilder.eq(timeString, timeALong));

            selectProjectNotification = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getProjectNotification()).allowFiltering();
            select = selectProjectNotification.where(eq(projectIdString, uuid));
            resultset = configSession.getSession().execute(select);
            UUID notifUUID = null;

            for (Row row : resultset)
                fetchId = row.getUUID("notificationId");

            System.out.println(tag + " fetchId " + fetchId);

            if (fetchId == null)
                notifUUID = UUID.randomUUID();
            else
                notifUUID = fetchId;


            updateNotificaiton = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getProjectNotification()).
                    with()
                    .and(QueryBuilder.set("notificationTitle", resultModel.getErrorTextByLanguage(project.getLang(), "PROJECT_ADDED")))
                    .and(QueryBuilder.set("creatorMember", getMemberTypeById(project.getCreatorID())))
                    .and(QueryBuilder.set(modifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set("projectName", project.getName()))
                    .and(QueryBuilder.set("notificationDesc", project.getName() + "  " + resultModel.getErrorTextByLanguage(project.getLang(), "CREATED_BY") +
                            getMemberTypeById(project.getCreatorID()).getString("name")))
                    .where(QueryBuilder.eq("notificationId", notifUUID)).and(eq(projectIdString, uuid));


            batch.add(update);
            batch.add(updateNotificaiton);
            batch.add(insertIntoRoleTableStatement(getRoleByProjectUDTList(project, uuid, project.getName())));

            resultSetBatch = configSession.getSession().execute(batch);
            System.out.println(tag + " resultSetBatch wasApplied : " + resultSetBatch.wasApplied());


            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(project.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }

        }catch (Exception e){
            logger.error(tag + " Error in add AllProjectDetails : " + e);
            e.printStackTrace();
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(project.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            return resultModel;

        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
//
        return resultModel;
    }

    /**
     *
     * add project with all detail
     * Mr Radan
     * @param project
     * @return
     */
    public ResultModel addProjectAll(ProjectAllDetails project) {

        logger.info("ProjectAllDetails Add project : " + project.getName());

        batch = QueryBuilder.batch();

        try {
            selectProject = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getProjectAllDetails()).allowFiltering();
            select = selectProject.where(eq(name, project.getName()));
            resultset = configSession.getSession().execute(select);

            for (Row row : resultset)
                fetchId = row.getUUID(idStg);

            System.out.println(tag + " fetchId " + fetchId);

            if (fetchId == null)
                uuid = UUID.randomUUID();
            else
                uuid = fetchId;


            System.out.println(tag + " uuid " + uuid + " ProjectAllDetails project.getId() " + project.getId() + " fetchId : " + fetchId);
            update = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getProjectAllDetails()).
                    with()
                    .and(QueryBuilder.set(name, project.getName()))
                    .and(QueryBuilder.set(crteateDate, resultModel.getInsertDate()))
                    .and(QueryBuilder.set(projectDescription, project.getProjectDescription()))
                    .and(QueryBuilder.set(status, project.getStatus()))
                    .and(QueryBuilder.set(percentage, project.getPercentage()))
                    .and(QueryBuilder.set(startDate, project.getStart_date()))
                    .and(QueryBuilder.set(endDate, project.getEnd_date()))
                    .and(QueryBuilder.set(creatorID, project.getCreatorID()))
                    .and(QueryBuilder.set(creator, getMemberTypeById(project.getCreatorID())))
                    .and(QueryBuilder.set(lang, project.getLang()))

                    .and(QueryBuilder.set(mission, getMissionUDT(project, uuid)))
                    .and(QueryBuilder.set(feature, getFeatureUDT(project, uuid)))
                    .and(QueryBuilder.set(subfeature, getSubFeatureUDT(project, uuid)))
                    .and(QueryBuilder.set(release, getReleaseUDT(project, uuid)))
                    .and(QueryBuilder.set(vision, getVisionUDT(project, uuid)))
                    .and(QueryBuilder.set(members, getRoleByProjectUDT(project,uuid,project.getName())))

                    .where(QueryBuilder.eq(idStg, uuid));

           /*
             When insert into project table we have to insert in role_by_project and notification_by_user simultaneously
           */


            List<UUID> receivedRoleIds = new ArrayList<>();
            List<UUID> tableRoleIds = new ArrayList<>();
            UUID existedUUID = null, existedProjectID = null;


        /*it is possible to add project details without having the members details so we check if members of project added then we update other table */
//            if (project.getMembers() != null) {
//                for (RoleByProjectType roleByProject : project.getMembers())
//                    receivedRoleIds.add(roleByProject.getRoelId());
//
//                logger.info(tag+"receivedRoleIds : "+receivedRoleIds);
//                selectallowMemberRole = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).allowFiltering();
////            selectMemberRole = selectallowMemberRole.where(eq("roleId",project.getMembers().get(0).getRoelId() ));
//                resultsetMemberRole = configSession.getSession().execute(selectallowMemberRole);
//                for (Row row : resultsetMemberRole)
//                    tableRoleIds.add(row.getUUID("roleId"));
//
//                logger.info(tag + " receivedRoleIds size : " + receivedRoleIds.size() + " tableRoleId size : " + tableRoleIds.size());
//
//                for (UUID tableRoleId : tableRoleIds)
//                    for (UUID recRoleId : receivedRoleIds)
//                        if (tableRoleId == recRoleId)
//                            existedUUID = tableRoleId;
//
//                logger.info(tag + " existedUUID : " + existedUUID);
//
//                if (existedUUID != null) {
//                    selectallowMemberRole = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).allowFiltering();
//                    selectMemberRole = selectallowMemberRole.where(eq("roleId", existedUUID));
//                    resultsetMemberRole = configSession.getSession().execute(selectallowMemberRole);
//                    for (Row row : resultsetMemberRole)
//                        existedProjectID = row.getUUID(projectIdString);
//
//                    Delete.Where delete = QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject())
//                            .where(eq("roleId", existedUUID)).and(eq(projectIdString, existedProjectID));
//                    configSession.getSession().execute(delete);
//
//                } else {
//                    for (RoleByProjectType roleByProjectType : project.getMembers()) {
//                        insertMemberRole = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject())
//                                .value("roleId", UUID.randomUUID())
//                                .value(projectIdString, uuid)
//                                .value("member", getUdtvalue(roleByProjectType.getMember()))
//                                .value("isStackholder", roleByProjectType.isStackholder())
//                                .value("roleName", roleByProjectType.getRoleName());
//                        configSession.getSession().execute(insertMemberRole);
//                    }
//                }
//            }

       /*
       -----------------------------------------------------------------------end of role_by_project operation ---------------------------------------
        */


       /*---------------------------------------------------------------------add notification by project operation ------------------------------------*/


//            insertMemberRole = QueryBuilder.insertInto(requirementsProperties.getKeyspace(), requirementsProperties.getProjectNotification())
//                    .value("notificationId", UUID.randomUUID())
//                    .value("notificationTitle", resultModel.getErrorTextByLanguage(project.getLang(), "PROJECT_ADDED"))
//                    .value("creatorMember", getMemberTypeById(project.getCreatorID()))
//                    .value(projectIdString, uuid)
//                    .value("projectName", project.getName())
//                    .value("notificationDesc", project.getName() +"  "+ resultModel.getErrorTextByLanguage(project.getLang(), "CREATED_BY") +
//                            getMemberTypeById(project.getCreatorID()).getString("name"));
//            //     configSession.getSession().execute(insertMemberRole);


            selectProjectNotification = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getProjectNotification()).allowFiltering();
            select = selectProjectNotification.where(eq(projectIdString, uuid));
            resultset = configSession.getSession().execute(select);
            UUID notifUUID = null;

            for (Row row : resultset)
                fetchId = row.getUUID("notificationId");

            System.out.println(tag + " fetchId " + fetchId);

            if (fetchId == null)
                notifUUID = UUID.randomUUID();
            else
                notifUUID = fetchId;

            updateNotificaiton = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getProjectNotification()).
                    with()
                    //.and(QueryBuilder.set("notificationId", UUID.randomUUID()))
                    .and(QueryBuilder.set("notificationTitle", resultModel.getErrorTextByLanguage(project.getLang(), "PROJECT_ADDED")))
                    .and(QueryBuilder.set("creatorMember", getMemberTypeById(project.getCreatorID())))
                    .and(QueryBuilder.set(modifyTime, UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set("projectName", project.getName()))
                    .and(QueryBuilder.set("notificationDesc", project.getName() + "  " + resultModel.getErrorTextByLanguage(project.getLang(), "CREATED_BY") +
                            getMemberTypeById(project.getCreatorID()).getString("name")))
                    .where(QueryBuilder.eq("notificationId", notifUUID)).and(eq(projectIdString, uuid));



       /*---------------------------------------------------------------------end of add notification by project operation ------------------------------------*/


       /*
        Insert into other table
        */
            insertIntoMissionTable(getMissionUDT(project, uuid));
            insertIntoVisionTable(getVisionUDT(project, uuid));
            insertIntoFeatureTable(getFeatureUDT(project, uuid));
            insertIntoSubFeatureTable(getSubFeatureUDT(project,uuid));
            insertIntoReleaseTable(getReleaseUDT(project,uuid));
            insertIntoRoleTable(getRoleByProjectUDT(project,uuid,project.getName()));

            batch.add(updateNotificaiton);
            batch.add(update);

            resultSetBatch = configSession.getSession().execute(batch);
            System.out.println(tag + " resultSetBatch wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(project.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());

        } catch (Exception e) {
            logger.error(tag + " Error in add AllProjectDetails : " + e);
            e.printStackTrace();
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(project.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel addProjectFeature(FeatureByProject feature) {
        return insertIntoFeatureTable(feature);
    }

    public ResultModel addProjectMission(MissionByProject mission) {
        return insertIntoMissionTable(mission);
    }

    public ResultModel addProjectRelease(ReleaseByProject release) {
        return insertIntoReleaseTable(release);
    }

    public ResultModel addProjectRole(RoleByProject role) {
        return insertIntoRoleTable(role);
    }

    public ResultModel addProjectSubfeature(SubFeatureByProject subfeature) {
        return insertIntoSubFeatureTable(subfeature);
    }

    public ResultModel addProjectVision(VisionByProject vision) {
        return insertIntoVisionTable(vision);
    }

    public ResultModel deleteMissionTable(UUID missionID, UUID projectID, String lang) {
        return deleteFromMissionTable(missionID,projectID,lang);
    }

    public ResultModel deleteVisionTable(UUID visionID, UUID projectID, String lang) {
        return deleteFromVisionTable(visionID,projectID,lang);
    }

    public ResultModel deleteReleaseTable(UUID time, UUID projectID, String lang) {
        return deleteFromReleaseTable(time,projectID,lang);
    }

    public ResultModel deleteRoleTable(UUID roleID, UUID projectID, String lang) {
        return deleteFromRoleTable(roleID,projectID,lang);
    }

    public ResultModel deleteFeatureTable(UUID featureID, UUID projectID, String lang) {
        return deleteFromFeatureTable(featureID,projectID,lang);
    }

    public ResultModel deleteSubFeatureTable(UUID subFeatureID, UUID projectID, String lang) {
        return deleteFromSubFeatureTable(subFeatureID,projectID,lang);
    }

    public ResultModel updateMission(MissionByProject missionByProject) {
        return updateMissionTable(missionByProject);
    }

    public ResultModel updateVision(VisionByProject visionByProject) {
        return updateVisionTable(visionByProject);
    }

    public ResultModel updateRelease(ReleaseByProject releaseByProject) {
        return updateReleaseTable(releaseByProject);
    }

    public ResultModel updateRole(RoleByProject roleByProject) {
        return updateRoleTable(roleByProject);
    }

    public ResultModel updateFeature(FeatureByProject featureByProject) {
        return updateFeatureTable(featureByProject);
    }

    public ResultModel updateSubFeature(SubFeatureByProject subFeatureByProject) {
        return updateSubFeatureTable(subFeatureByProject);
    }

    /*
     we don't use this function right now
     */
    public UDTValue getUdtvalue(Project project) {

        UDTValue udtValue = null;
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MemberType");
            udtValue = coordsType.newValue()
                    .setString("name", project.getCreator().getName())
                    .setString("family", project.getCreator().getFamily())
                    .setString("imagePath", project.getCreator().getImagePath())
                    .setString("phoneNumber", project.getCreator().getPhoneNumber())
                    .setString("email", project.getCreator().getPhoneNumber())
                    .setString("userName", project.getCreator().getUserName());

            System.out.println("member type :  " + project.getCreator().getName());

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
        }
        return udtValue;
    }


     /*
     UDT Value for get role by project
     */

    public List<UDTValue> getRoleByProjectUDT(ProjectAllDetails project, UUID pid,String projectName) {

        List<UDTValue> udtValues = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("RoleByProjectType");

            for (RoleByProjectType roleByProjectType : project.getMembers()) {
                UDTValue udtValue = coordsType.newValue()
                        .setUUID("roleId", roleByProjectType.getRoleId())
                        .setUUID(projectIdString, pid)
                        .setUUID("memberID", roleByProjectType.getMemberID())
                        .setUDTValue("member", getMemberTypeById(roleByProjectType.getMemberID()))
                        .setString("projectName", projectName)
                        .setBool("isStakeholder", roleByProjectType.isStackholder())
                        .setString("roleName", roleByProjectType.getRoleName())
                        .setString("permissionName", roleByProjectType.getPermissionName());

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of mission udt list " + udtValues.size());
        return udtValues;

    }

    /*
     UDT Value for get role by project
     */

    public List<UDTValue> getRoleByProjectUDTList(Project project, UUID pid,String projectName) {

        List<UDTValue> udtValues = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("RoleByProjectType");

            for (RoleByProjectType roleByProjectType : project.getMembers()) {
                UDTValue udtValue = coordsType.newValue()
                        .setUUID("roleId", roleByProjectType.getRoleId())
                        .setUUID(projectIdString, pid)
                        .setUUID("memberID", roleByProjectType.getMemberID())
                        .setUDTValue("member", getMemberTypeById(roleByProjectType.getMemberID()))
                        .setString("projectName", projectName)
                        .setBool("isStakeholder", roleByProjectType.isStackholder())
                        .setString("roleName", roleByProjectType.getRoleName())
                        .setString("permissionName", roleByProjectType.getPermissionName());

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of mission udt list " + udtValues.size());
        return udtValues;

    }


    /*
     UDT Value for get subfeature
     */

    public List<UDTValue> getSubFeatureUDT(ProjectAllDetails project, UUID pid) {

        List<UDTValue> udtValues = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("SubFeatureByProjectType");

            for (SubFeatureByProjectType subFeatureByProjectType : project.getSubfeature()) {
                UDTValue udtValue = coordsType.newValue()
                        .setString("subFeatureDesc", subFeatureByProjectType.getSubFeatureDesc())
                        .setString("title", subFeatureByProjectType.getTitle())
                        .setString("featureTitle", subFeatureByProjectType.getFeatureTitle())
                        .setUUID("featureId", subFeatureByProjectType.getFeatureId())
                        .setUUID(projectIdString, pid)
                        .setUUID("creatorID", subFeatureByProjectType.getCreatorID())
                        .setList("members", getUUIDclassUDT(subFeatureByProjectType.getMembers()))
                        .setUDTValue("creator", getMemberTypeById(subFeatureByProjectType.getCreatorID()));

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of mission udt list " + udtValues.size());
        return udtValues;
    }


      /*
     UDT Value for get feature
     */

    public List<UDTValue> getFeatureUDT(ProjectAllDetails project, UUID pid) {

        List<UDTValue> udtValues = new ArrayList<>();
        logger.info(tag+" feature getFeatureUDT : "+project.getFeature());
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("FeatureByProjectType");

            for (FeatureByProjectType featureByProjectType : project.getFeature()) {
                UDTValue udtValue = coordsType.newValue()
                        .setString("featureDesc", featureByProjectType.getFeatureDesc())
                        .setString("title", featureByProjectType.getTitle())
                        .setList("meetingId", getUUIDclassUDT(featureByProjectType.getMeetingId()))
                        .setBool("hasSubfeature", featureByProjectType.isHasSubfeature())
                        .setBool("undefinedSub", featureByProjectType.isUndefinedSub())
                        .setUUID(projectIdString, pid)
                        .setUUID("creatorID", featureByProjectType.getCreatorID())
                        .setUDTValue("creator", getMemberTypeById(featureByProjectType.getCreatorID()));

                System.out.println("getFeatureUDT : " + featureByProjectType.getTitle());
                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error feature udt : " + e);
            return null;
        }
        logger.info(tag + " size of getFeatureUDT " + udtValues.size());
        return udtValues;
    }

    /*
     UDT Value for get vision
     */

    public List<UDTValue> getVisionUDT(ProjectAllDetails project, UUID pid) {

        List<UDTValue> udtValues = new ArrayList<>();

        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("VisionByProjectType");

            for (VisionByProjectType visionByProjectType : project.getVision()) {
                UDTValue udtValue = coordsType.newValue()
                        .setString("visionDesc", visionByProjectType.getVisionDesc())
                        .setUUID(projectIdString, pid)
                        .setString("title", visionByProjectType.getTitle())
                        .setUUID("creatorID", visionByProjectType.getCreatorID())
                        .setUDTValue("creator", getMemberTypeById(visionByProjectType.getCreatorID()))
                        .setList("members", getUdtvalueList(visionByProjectType.getMembers()));
                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of vision udt list " + udtValues.size());
        return udtValues;
    }

    /*
     UDT Value for get mission
     */

    public List<UDTValue> getMissionUDT(ProjectAllDetails project, UUID pid) {

        List<UDTValue> udtValues = new ArrayList<>();
//        logger.info(tag + " projectMission.... " + project.getMission().get(0));
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MissionByProjectType");

            for (MissionByProjectType missionByProjectType : project.getMission()) {
                System.out.println("missionByProjectType :  " + missionByProjectType.getAttachment() + " creator id " + missionByProjectType.getCreatorID());

                UDTValue udtValue = coordsType.newValue()
                        .setString("missionDesc", missionByProjectType.getMissionDesc())
                        .setString("title", missionByProjectType.getTitle())
                        .setList("attachment", getUUIDclassUDT(missionByProjectType.getAttachment()))
                        .setUUID(projectIdString, pid)
                        .setUUID("creatorID", missionByProjectType.getCreatorID())
                        .setUDTValue("creator", getMemberTypeById(missionByProjectType.getCreatorID()));

                System.out.println("missionByProjectType end :  " + missionByProjectType.getAttachment());
                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error in getMissionUDT getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of mission udt list " + udtValues.size());
        return udtValues;
    }



    /*
   UDT Value for uuid list of class
   */

    public List<UDTValue> getUUIDclassUDT(List<uuidClass> uuidClasses) {

        List<UDTValue> udtValues = new ArrayList<>();
        logger.info(tag + " getUUIDclassUDT.... " + uuidClasses);
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("uuidClass");

            for (uuidClass uuidclass : uuidClasses) {
                System.out.println("missionByProjectType :  " + uuidclass.getId());

                UDTValue udtValue = coordsType.newValue()
                        .setUUID("id", uuidclass.getId());

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error in getMissionUDT getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of mission udt list " + udtValues.size());
        return udtValues;
    }

    /**
     * @author Masoomeh
     * @param udtValues
     * @return
     */
    public static List<uuidClass> getudtValuesuuidClass(Object udtValues ,String idStg) {
//        udtValues
        List<uuidClass> uuidClasses = new ArrayList<>();
        for (int i = 0; i <((List)udtValues).size(); i++) {
//            logger.warn(tag+" in if");
            uuidClass uuidClass = new uuidClass();
            uuidClass.setId (((UDTValue)((List)udtValues).get(i)).getUUID(idStg));
            uuidClasses.add(uuidClass);
        }

//        logger.warn(tag+" uuidClasses "+uuidClasses + " size: " +((List)udtValues).size());

        return uuidClasses;
    }


    /*
     get mission name and id for Release part ...
     */
    public List<UDTValue> getReleaseMissionIDAndNameUDT(List<MissionNameID> uuidClasses) {

        List<UDTValue> udtValues = new ArrayList<>();
        logger.info(tag + " getUUIDclassUDT.... " + uuidClasses);
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MissionNameID");

            for (MissionNameID uuidclass : uuidClasses) {
                System.out.println("missionByProjectType :  " + uuidclass.getMissionId());

                UDTValue udtValue = coordsType.newValue()
                        .setUUID("missionId", uuidclass.getMissionId())
                        .setString("missionName", uuidclass.getMissionName());

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error in getMissionUDT getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of getReleaseMissionIDAndNameUDT udt list " + udtValues.size());
        return udtValues;
    }


    /*
     UDT Value for get release
     */

    public List<UDTValue> getReleaseUDT(ProjectAllDetails project, UUID pid) {

        List<UDTValue> udtValues = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("ReleaseByProjectType");

            for (ReleaseByProjectType ReleaseByProjectType : project.getRelease()) {
                UDTValue udtValue = coordsType.newValue()
                        .setString("releaseDesc", ReleaseByProjectType.getReleaseDesc())
                        .setString("title", ReleaseByProjectType.getTitle())
                        .setList("meetingId", getUUIDclassUDT(ReleaseByProjectType.getMeetingId()))
                        .setList("missionId", getReleaseMissionIDAndNameUDT(ReleaseByProjectType.getMissionId()))
                        .setList("issueId", getUUIDclassUDT(ReleaseByProjectType.getIssueId()))
                        .setUUID(projectIdString, pid)
                        .setUUID("creatorID", ReleaseByProjectType.getCreatorID())
                        .setUDTValue("creator", getMemberTypeById(ReleaseByProjectType.getCreatorID()));

                udtValues.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        logger.info(tag + " size of release udt list " + udtValues.size());
        return udtValues;
    }

    /*
    get udt value of member type
     */
    public UDTValue getUdtvalue(MemberType membertype) {

        UDTValue udtValue = null;
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MemberType");
            udtValue = coordsType.newValue()
                    .setUUID("id", UUID.randomUUID())
                    .setString("name", membertype.getName())
                    .setString("family", membertype.getFamily())
                    .setString("imagePath", membertype.getImagePath())
                    .setString("phoneNumber", membertype.getPhoneNumber())
                    .setString("email", membertype.getPhoneNumber())
                    .setString("userName", membertype.getUserName());

            System.out.println("member type :  " + membertype.getName());

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
        }
        return udtValue;
    }

    /*
* we get member id of project creator from client but, we have creator variable with type of
* MemberType so here we fetch the member data by member id and the add to UDTVALUE
*/
    public static UDTValue getMemberTypeById(UUID uuid) {

//        logger.info("uuid in add member : " + uuid);
        UDTValue udtValue = null;
        UserType coordsType;

        coordsType = sConfigSession.getCluster().getMetadata()
                .getKeyspace(sRequirementsProperties.getKeyspace())
                .getUserType("MemberType");

        Select selectallowMemberRole = QueryBuilder.select().from(sRequirementsProperties.getKeyspace(), sRequirementsProperties.membertable()).allowFiltering();
        Select.Where selectMemberRole = selectallowMemberRole.where(eq("id", uuid));
        ResultSet resultsetMemberRole = sConfigSession.getSession().execute(selectMemberRole);
        // logger.info(tag+" getMemberTypeById result size : "+resultsetMemberRole.all().size());
        for (Row row : resultsetMemberRole) {
//            logger.info(tag + " row........... " + row);
            udtValue = coordsType.newValue()
                    .setUUID("id", uuid)
                    .setString("name", row.getString("name"))
                    .setString("family", row.getString("family"))
                    .setString("imagePath", row.getString("imagePath"))
                    .setString("phoneNumber", row.getString("phoneNumber"))
                    .setString("email", row.getString("email"))
                    .setString("userName", row.getString("userName"));
        }
//        logger.info(tag + " udtvalue " + udtValue);
        return udtValue;
    }

    /*
     get list of UDT value of member type
    */
    public List<UDTValue> getUdtvalueList(List<MemberType> membertype) {

        List<UDTValue> udtValueList = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MemberType");

            for (MemberType memberType : membertype) {

                UDTValue udtValue = coordsType.newValue()
                        .setString("name", memberType.getName())
                        .setString("family", memberType.getFamily())
                        .setString("imagePath", memberType.getImagePath())
                        .setString("phoneNumber", memberType.getPhoneNumber())
                        .setString("email", memberType.getPhoneNumber())
                        .setString("userName", memberType.getUserName());

                System.out.println("getUdtvalueList member type :  " + memberType.getName());
                udtValueList.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalue : " + e);
            return null;
        }
        return udtValueList;
    }


    public void insertIntoMissionTable(List<UDTValue> missions) {

        UUID missionId = null;
        UUID projectId = null;
        String title = null;
        String desc = null;
        List<UDTValue> attachment = null;

//        MemberType creator = null;
        UDTValue creator = null;
        String lang = null;
        UUID creatorID = null;
        Long modifyTime = null;

        logger.info(" insertIntoMission by udtvalue list missions size : " + missions + " udtValue.getList  ");

        for (UDTValue udtValue : missions) {
            System.out.println(" list udt " + udtValue.getList("attachment", UDTValue.class) + " creator " + udtValue.getUUID("id"));
            // missionId = udtValue.getUUID("id");
            desc = udtValue.getString("missionDesc");
            title = udtValue.getString("title");
            attachment = udtValue.getList("attachment", UDTValue.class);
            projectId = udtValue.getUUID(projectIdString);
            creatorID = udtValue.getUUID("creatorID");
            creator = udtValue.getUDTValue("creator");
        }

        batch = QueryBuilder.batch();

        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).allowFiltering();
        select = selectallow.where(eq("title", title));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset)
            fetchId = row.getUUID(idStg);

        System.out.println(tag + " fetchId " + fetchId);

        if (fetchId == null)
            missionId = UUID.randomUUID();
        else
            missionId = fetchId;

        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " uuid " + uuid + " desc......" + desc + " title : " + title + " projectId " + projectId + " missionId " + missionId);

        updateMission = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).
                with()
                .and(QueryBuilder.set("title", title))
                .and(QueryBuilder.set("missionDesc", desc))
                .and(QueryBuilder.set("attachment", attachment))
                .and(QueryBuilder.set("creatorID", creatorID))
                .and(QueryBuilder.set("creator", creator))
                .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                .where(QueryBuilder.eq("id", missionId)).and(eq(projectIdString, projectId));

        resultSetBatch = configSession.getSession().execute(updateMission);
        System.out.println(tag + " insertIntoMission wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }


    //-----------------------------------------------------

    /*
      Insert vision
     */

    public void insertIntoVisionTable(List<UDTValue> visions) {


        UUID visionId;
        UUID projectId = null;
        String title = null;
        String visionDesc = null;
        Long modifyTime = null;
        List<MemberType> members = null;
        UDTValue creator = null;
        String lang = null;
        UUID creatorID = null;


        List<UDTValue> membersList = null;

        logger.info(" insertIntoVisionTable by udtvalue list vision size : " + visions + " udtValue.getList  ");

        for (UDTValue udtValue : visions) {
            visionDesc = udtValue.getString("visionDesc");
            title = udtValue.getString("title");
            membersList = udtValue.getList("members", UDTValue.class);
            projectId = udtValue.getUUID(projectIdString);
            creatorID = udtValue.getUUID("creatorID");
            creator = udtValue.getUDTValue("creator");
        }

        batch = QueryBuilder.batch();

        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).allowFiltering();
        select = selectallow.where(eq("title", title));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset)
            fetchId = row.getUUID(idStg);

        System.out.println(tag + " fetchId " + fetchId);

        if (fetchId == null)
            visionId = UUID.randomUUID();
        else
            visionId = fetchId;

        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " insertIntoVisionTable " + " visionDesc " + visionDesc + " title : " + title + " projectId " + projectId + " visionId " + visionId);

        updateVision = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).
                with()
                .and(QueryBuilder.set("title", title))
                .and(QueryBuilder.set("visionDesc", visionDesc))
                .and(QueryBuilder.set("members", membersList))
                .and(QueryBuilder.set("creatorID", creatorID))
                .and(QueryBuilder.set("creator", creator))
                .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                .where(QueryBuilder.eq("id", visionId)).and(eq(projectIdString, projectId));

        resultSetBatch = configSession.getSession().execute(updateVision);
        System.out.println(tag + " insertIntoVisionTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }

    //------------------------------------------------------------------------------


    //---------------------------Release--------------------------

    /*
      Insert into release
     */

    public void insertIntoReleaseTable(List<UDTValue> release) {


        UUID releaseId = null;
        UUID projectId = null;
        String title = null;
        String releaseDesc = null;
        List<UDTValue> missionId = null;
        List<UDTValue> meetingId = null;
        List<UDTValue> issueId = null;
        UDTValue creator = null;
        String lang = null;
        Long modifyTime = null;
        UUID creatorID = null;

        logger.info(" insertIntoReleaseTable by udtvalue list release size : " + release + " udtValue.getList  ");

        for (UDTValue udtValue : release) {
            releaseDesc = udtValue.getString("releaseDesc");
            title = udtValue.getString("title");
            projectId = udtValue.getUUID(projectIdString);
            creatorID = udtValue.getUUID("creatorID");
            creator = udtValue.getUDTValue("creator");

            missionId = udtValue.getList("missionId", UDTValue.class);
            meetingId = udtValue.getList("meetingId", UDTValue.class);
            issueId = udtValue.getList("issueId", UDTValue.class);

        }

        batch = QueryBuilder.batch();

        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).allowFiltering();
        select = selectallow.where(eq("title", title));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset)
            fetchId = row.getUUID("id");

        System.out.println(tag + " fetchId " + fetchId);

        if (fetchId == null)
            releaseId = UUID.randomUUID();
        else
            releaseId = fetchId;

        /*
         Insert into Release table, basic details of project
         */
        System.out.println(tag + " insertIntoReleaseTable " + " releaseDesc " + releaseDesc + " title : " + title + " projectId " + projectId + " releaseId " + releaseId);

        updateRelease = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).
                with()
                .and(QueryBuilder.set("title", title))
                .and(QueryBuilder.set("releaseDesc", releaseDesc))
                .and(QueryBuilder.set("missionId", missionId))
                .and(QueryBuilder.set("meetingId", meetingId))
                .and(QueryBuilder.set("issueId", issueId))
                .and(QueryBuilder.set("creator", creator))
                .and(QueryBuilder.set("creatorID", creatorID))
                .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                .where(QueryBuilder.eq("id", releaseId)).and(eq(projectIdString, projectId));


        resultSetBatch = configSession.getSession().execute(updateRelease);
        System.out.println(tag + " insertIntoVisionTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }


    //---------------------------Feature--------------------------

    /*
      Insert into Feature
     */


    public void insertIntoFeatureTable(List<UDTValue> feature) {


        UUID id = null;
        UUID projectId = null;
        UUID creatorID = null;
        String title = null;
        String featureDesc = null;
        List<UDTValue> meetingId = null;
        boolean hasSubfeature = false;
        UDTValue creator = null;
        boolean undefinedSub = false;


        logger.info(" insertIntoFeatureTable by udtvalue list feature size : " + feature + " udtValue.getList  ");

        for (UDTValue udtValue : feature) {
                featureDesc = udtValue.getString("featureDesc");
                title = udtValue.getString("title");
                projectId = udtValue.getUUID(projectIdString);
                creatorID = udtValue.getUUID("creatorID");
                creator   = udtValue.getUDTValue("creator");

                hasSubfeature = udtValue.getBool("hasSubfeature");
                undefinedSub  = udtValue.getBool("undefinedSub");

                meetingId = udtValue.getList("meetingId", UDTValue.class);

        }

        batch = QueryBuilder.batch();

        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).allowFiltering();
        select = selectallow.where(eq("title", title));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset)
            fetchId = row.getUUID("id");

        System.out.println(tag + " fetchId " + fetchId);

        if (fetchId == null)
            id = UUID.randomUUID();
        else
            id = fetchId;

        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " insertIntoFeatureTable " + " featureDesc " + featureDesc + " title : " + title + " projectId " + projectId + " feature id " + id);

        updateFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).
                with()
                .and(QueryBuilder.set("title", title))
                .and(QueryBuilder.set("featureDesc", featureDesc))
                .and(QueryBuilder.set("meetingId", meetingId))
                .and(QueryBuilder.set("creator", creator))
                .and(QueryBuilder.set("creatorID", creatorID))
                .and(QueryBuilder.set("hasSubfeature", hasSubfeature))
                .and(QueryBuilder.set("undefinedSub", undefinedSub))
                .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                .where(QueryBuilder.eq("id", id)).and(eq(projectIdString, projectId));


        resultSetBatch = configSession.getSession().execute(updateFeature);
        System.out.println(tag + " insertIntoFeatureTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }



    /*
      Insert into subFeature
     */


    public void insertIntoSubFeatureTable(List<UDTValue> feature) {


        UUID id = null;
        UUID projectId = null;
        String title = null;
        String subFeatureDesc = null;
        UUID featureId = null;
        String featureTitle = null;
        Long date = null;
        List<UDTValue> members = null;
        UDTValue creator = null;
        UUID creatorID = null;


        logger.info(" insertIntoSubFeatureTable by udtvalue list release size : " + feature + " udtValue.getList  ");

        for (UDTValue udtValue : feature) {
            subFeatureDesc = udtValue.getString("subFeatureDesc");
            title = udtValue.getString("title");
            projectId = udtValue.getUUID(projectIdString);
            creatorID = udtValue.getUUID("creatorID");
            creator = udtValue.getUDTValue("creator");
            featureId = udtValue.getUUID("featureId");
            featureTitle = udtValue.getString("featureTitle");
            members = udtValue.getList("members", UDTValue.class);

        }

        batch = QueryBuilder.batch();

        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject()).allowFiltering();
        select = selectallow.where(eq("title", title));
        resultset = configSession.getSession().execute(select);

        for (Row row : resultset)
            fetchId = row.getUUID("id");

        System.out.println(tag + " fetchId " + fetchId);

        if (fetchId == null)
            id = UUID.randomUUID();
        else
            id = fetchId;

        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " insertIntoSubFeatureTable " + " subFeatureDesc " + subFeatureDesc +
                " title : " + title + " projectId " + projectId + " subfeatureId : " + id);

        updateSubFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject()).
                with()
                .and(QueryBuilder.set("title", title))
                .and(QueryBuilder.set("subFeatureDesc", subFeatureDesc))
                .and(QueryBuilder.set("members", members))
                .and(QueryBuilder.set("featureTitle", featureTitle))
                .and(QueryBuilder.set("featureId", featureId))
                .and(QueryBuilder.set("creator", creator))
                .and(QueryBuilder.set("creatorID", creatorID))
                .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                .where(QueryBuilder.eq("id", id)).and(eq(projectIdString, projectId));


        resultSetBatch = configSession.getSession().execute(updateSubFeature);
        System.out.println(tag + " insertIntoSubFeatureTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }


    /*
     Add Role insert
     */
    public void insertIntoRoleTable(List<UDTValue> role) {


          UUID roleId = null;
          UUID projectId = null;
          UUID memberId = null;
          UDTValue member = null;
          String projectName = null;
          boolean isStackholder= false;
          String roleName = null;
          int permissionKey = 0;
          String permissionName = null;


        logger.info(" insertIntoRoleTable by udtvalue role by project size : " + role);

        for (UDTValue udtValue : role) {
                roleId = udtValue.getUUID("roleId");
                memberId = udtValue.getUUID("memberID");
                projectId = udtValue.getUUID(projectIdString);
                member = udtValue.getUDTValue("member");
                projectName = udtValue.getString("projectName");
                isStackholder =  udtValue.getBool("isStackholder");
                roleName = udtValue.getString("roleName");
                permissionName = udtValue.getString("permissionName");
         }

        batch = QueryBuilder.batch();

//        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).allowFiltering();
//        select = selectallow.where(eq("roleId", roleId)).and(eq(projectIdString, projectId));
//        resultset = configSession.getSession().execute(select);


        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " insertIntoRoleTable " + " projectName " + projectName +
                " roleId : " + roleId + " projectId " + projectId);

        updateRole = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).
                with()
                .and(QueryBuilder.set("projectName", projectName))
//                .and(QueryBuilder.set("memberID", memberID))
                .and(QueryBuilder.set("member", member))
                .and(QueryBuilder.set("isStackholder", isStackholder))
                .and(QueryBuilder.set("roleName", roleName))
                .and(QueryBuilder.set("permissionName", permissionName))
                .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                .where(QueryBuilder.eq("roleId", roleId)).and(eq(projectIdString, projectId)).and(eq(memberID,memberId));


        resultSetBatch = configSession.getSession().execute(updateRole);
        System.out.println(tag + " insertIntoRoleTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
    }



    /*
     Add Role insert
     */
    public Update.Where  insertIntoRoleTableStatement(List<UDTValue> role) {

        UUID roleId = null;
        UUID projectId = null;
        UUID memberId = null;
        UDTValue member = null;
        String projectName = null;
        boolean isStackholder= false;
        String roleName = null;
        int permissionKey = 0;
        String permissionName = null;

        logger.info(" insertIntoRoleTable by udtvalue role by project size : " + role);

        for (UDTValue udtValue : role) {
            roleId = udtValue.getUUID("roleId");
            memberId = udtValue.getUUID("memberID");
            projectId = udtValue.getUUID(projectIdString);
            member = udtValue.getUDTValue("member");
            projectName = udtValue.getString("projectName");
            isStackholder =  udtValue.getBool("isstakeholder");
            roleName = udtValue.getString("roleName");
            permissionName = udtValue.getString("permissionName");
        }

//        batch = QueryBuilder.batch();

//        selectallow = QueryBuilder.select().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).allowFiltering();
//        select = selectallow.where(eq("roleId", roleId)).and(eq(projectIdString, projectId));
//        resultset = configSession.getSession().execute(select);


        /*
         Insert into mission table, basic details of project
         */
        System.out.println(tag + " insertIntoRoleTable " + " projectName " + projectName +
                " roleId : " + roleId + " projectId " + projectId);

        updateRole = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).
                with()
                .and(QueryBuilder.set("projectName", projectName))
//                .and(QueryBuilder.set(memberID, memberId))
                .and(QueryBuilder.set("member", member))
                .and(QueryBuilder.set("isStackholder", isStackholder))
                .and(QueryBuilder.set("roleName", roleName))
                .and(QueryBuilder.set("permissionName", permissionName))
                .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                .where(QueryBuilder.eq("roleId", roleId)).and(eq(projectIdString, projectId)).and(eq(memberID,memberId));


        //resultSetBatch = configSession.getSession().execute(updateRole);
//        System.out.println(tag + " insertIntoRoleTable wasApplied : " + resultSetBatch.wasApplied());

//        if (resultSetBatch.wasApplied()) {
//            resultModel.setError(0);
//            resultModel.setResult(requirementsProperties.getSuccessful());
//        } else {
//            resultModel.setError(1);
//            resultModel.setResult(resultModel.getErrorTextByLanguage("fa", "UNKNOWN_TRANSACTION_ERROR"));
//        }
//        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());

        return updateRole;
    }

    //-------------------------------------------------

    /*
     get memberType from UDT value
     */
    public static MemberType getMemberTypeFromUdtValue(UDTValue udtValue) {

        MemberType memberType = new MemberType();
        memberType.setId(udtValue.getUUID("id"));
        memberType.setName(udtValue.getString("name"));
        memberType.setFamily(udtValue.getString("family"));
        memberType.setEmail(udtValue.getString("email"));
        memberType.setUserName(udtValue.getString("userName"));
        memberType.setImagePath(udtValue.getString("imagePath"));
        memberType.setPhoneNumber(udtValue.getString("phoneNumber"));

        return memberType;
    }

    public List<UDTValue> getUdtvalueListForMissionNameID(List<MissionNameID> missionNameIDS) {

        List<UDTValue> udtValueList = new ArrayList<>();
        try {
            UserType coordsType;
            coordsType = configSession.getCluster().getMetadata()
                    .getKeyspace(requirementsProperties.getKeyspace())
                    .getUserType("MissionNameID");

            for (MissionNameID missionNameID : missionNameIDS) {

                UDTValue udtValue = coordsType.newValue()
                        .setUUID("missionId", missionNameID.getMissionId())
                        .setString("missionName", missionNameID.getMissionName());

                udtValueList.add(udtValue);
            }

        } catch (Exception e) {
            logger.error("error getUdtvalueListForMissionNameID : " + e);
            return null;
        }
        return udtValueList;
    }



   /*--------------------------------------------------------------------------------------------

    Insert to each table separately for different add route

    *---------------------------------------------------------------------------------------------*/

    public ResultModel insertIntoMissionTable(MissionByProject missionByProject){
        /*
         Insert into mission table, basic details of project
         */
        try {

            logger.error(tag + " mission: " + missionByProject.getAttachment());

            resultModel.setError(0);
            resultModel.setResult(null);

            updateMission = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).
                    with()
                    .and(QueryBuilder.set("title", missionByProject.getTitle()))
                    .and(QueryBuilder.set("missionDesc", missionByProject.getMissionDesc()))
                    .and(QueryBuilder.set("attachment", getUUIDclassUDT(missionByProject.getAttachment())))
                    .and(QueryBuilder.set("creatorID", missionByProject.getCreatorID()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(missionByProject.getCreatorID())))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(local_idStg, UUID.randomUUID()))
                    .where(QueryBuilder.eq(idStg ,UUIDGen.getTimeUUID())).and(eq(projectIdString, missionByProject.getprojectId()));

            resultSetBatch = configSession.getSession().execute(updateMission);
            System.out.println(tag + " insertIntoMission wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(missionByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in insertIntoMissionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel insertIntoVisionTable(VisionByProject visionByProject) {

        /*
         Insert into mission table, basic details of project
         */
        try {
            System.out.println(tag + " insertIntoVisionTable " + " visionDesc " + visionByProject.getVisionDesc());
            resultModel.setError(0);
            resultModel.setResult(null);

            updateVision = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).
                    with()
                    .and(QueryBuilder.set("title", visionByProject.getTitle()))
                    .and(QueryBuilder.set("visionDesc", visionByProject.getTitle()))
                    .and(QueryBuilder.set("members", getUdtvalueList(visionByProject.getMembers())))
                    .and(QueryBuilder.set("creatorID", visionByProject.getCreatorID()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(visionByProject.getCreatorID())))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(local_idStg, UUID.randomUUID()))
                    .where(QueryBuilder.eq("id", UUIDGen.getTimeUUID())).and(eq(projectIdString, visionByProject.getprojectId()));

            resultSetBatch = configSession.getSession().execute(updateVision);
            System.out.println(tag + " insertIntoVisionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(visionByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());

        }catch (Exception e){
            logger.error(tag+" error in insertIntoVisionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel insertIntoReleaseTable(ReleaseByProject releaseByProject){

        try {
            System.out.println(tag + " insertIntoReleaseTable " + " visionDesc " + releaseByProject.getReleaseDesc());
            resultModel.setError(0);
            resultModel.setResult(null);

            updateRelease = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).
                    with()
                    .and(QueryBuilder.set("title", releaseByProject.getTitle()))
                    .and(QueryBuilder.set("releaseDesc", releaseByProject.getReleaseDesc()))
                    .and(QueryBuilder.set("missionId", getUdtvalueListForMissionNameID(releaseByProject.getMissionId())))
                    .and(QueryBuilder.set("meetingId", getUUIDclassUDT(releaseByProject.getMeetingId())))
                    .and(QueryBuilder.set("issueId", getUUIDclassUDT(releaseByProject.getIssueId())))
                    .and(QueryBuilder.set("creator", getMemberTypeById(releaseByProject.getCreatorID())))
                    .and(QueryBuilder.set("creatorID", releaseByProject.getCreatorID()))
//                    .and(QueryBuilder.set("id", UUID.randomUUID()))
                    .and(QueryBuilder.set(modifyTime, UUIDGen.getTimeUUID()))
                    .where(eq(projectIdString, releaseByProject.getprojectId()))
                    .and(QueryBuilder.eq(idStg ,UUIDGen.getTimeUUID()));


            resultSetBatch = configSession.getSession().execute(updateRelease);
            System.out.println(tag + " insertIntoVisionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(releaseByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in insertIntoReleaseTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel insertIntoRoleTable(RoleByProject roleByProject){

        try {
            resultModel.setError(0);
            resultModel.setResult(null);

            updateRole = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).
                    with()
                    .and(QueryBuilder.set("projectName", roleByProject.getProject_name()))
                    .and(QueryBuilder.set("memberID", roleByProject.getMemberID()))
                    .and(QueryBuilder.set("member", getMemberTypeById(roleByProject.getMemberID())))
                    .and(QueryBuilder.set("isStackholder", roleByProject.getIs_stackholder()))
                    .and(QueryBuilder.set("roleName", roleByProject.getRole_name()))
                    .and(QueryBuilder.set("permissionName", roleByProject.getProject_name()))
                    .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                    .where(QueryBuilder.eq("roleId", UUID.randomUUID())).and(eq(projectIdString, roleByProject.getprojectId()));


            resultSetBatch = configSession.getSession().execute(updateRole);
            System.out.println(tag + " insertIntoRoleTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(roleByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in insertIntoRoleTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel insertIntoFeatureTable(FeatureByProject featureByProject){

        try{
            resultModel.setError(0);
            resultModel.setResult(null);

        updateFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).
                with()
                .and(QueryBuilder.set("title", featureByProject.getTitle()))
                .and(QueryBuilder.set("featureDesc", featureByProject.getFeatureDesc()))
                .and(QueryBuilder.set("meetingId", getUUIDclassUDT(featureByProject.getMeetingId())))
                .and(QueryBuilder.set("creator", getMemberTypeById(featureByProject.getCreatorID())))
                .and(QueryBuilder.set("creatorID", featureByProject.getCreatorID()))
                .and(QueryBuilder.set("hasSubfeature", featureByProject.getHasSubfeature()))
                .and(QueryBuilder.set("undefinedSub", featureByProject.getUndefinedSub()))
                .and(QueryBuilder.set("modifyTime",  UUIDGen.getTimeUUID()))
                .and(QueryBuilder.set(local_idStg,  UUID.randomUUID()))
                .where(QueryBuilder.eq(idStg,  UUIDGen.getTimeUUID())).and(eq(projectIdString, featureByProject.getprojectId()));


        resultSetBatch = configSession.getSession().execute(updateFeature);
        System.out.println(tag + " insertIntoFeatureTable wasApplied : " + resultSetBatch.wasApplied());

        if (resultSetBatch.wasApplied()) {
            resultModel.setError(0);
            resultModel.setResult(requirementsProperties.getSuccessful());
        } else {
            resultModel.setError(1);
            resultModel.setResult(resultModel.getErrorTextByLanguage(featureByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
        }
        System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in insertIntoFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel insertIntoSubFeatureTable(SubFeatureByProject subFeatureByProject){

        /*
         Insert into mission table, basic details of project
         */

        try {
            resultModel.setError(0);
            resultModel.setResult(null);


            System.out.println(tag + " insertIntoSubFeatureTable " + " subFeatureDesc " + subFeatureByProject.getSubFeatureDesc());

            updateSubFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject()).
                    with()
                    .and(QueryBuilder.set("title", subFeatureByProject.getTitle()))
                    .and(QueryBuilder.set("subFeatureDesc", subFeatureByProject.getSubFeatureDesc()))
                    .and(QueryBuilder.set("members", getUUIDclassUDT(subFeatureByProject.getMembers())))
                    .and(QueryBuilder.set("featureTitle", subFeatureByProject.getFeature_title()))
                    .and(QueryBuilder.set("featureId", subFeatureByProject.getFeature_id()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(subFeatureByProject.getCreatorID())))
                    .and(QueryBuilder.set("creatorID", subFeatureByProject.getCreatorID()))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .and(QueryBuilder.set(local_idStg, UUID.randomUUID()))
                    .where(QueryBuilder.eq("id",  UUIDGen.getTimeUUID())).and(eq(projectIdString, subFeatureByProject.getprojectId()));


            resultSetBatch = configSession.getSession().execute(updateSubFeature);
            System.out.println(tag + " insertIntoSubFeatureTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(subFeatureByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in insertIntoSubFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }



    /*--------------------------------------------------------------------------------------------

     Delete from each table separately

    *---------------------------------------------------------------------------------------------*/

    public ResultModel deleteFromMissionTable(UUID missionID,UUID projectID,String lang){

        /*
         delete from mission table, basic details of project
         */
        try {
            System.out.println(tag + " delete missionID " + missionID);

            resultModel.setError(0);
            resultModel.setResult(null);


            resultSetBatch = configSession.getSession().execute(
                    QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).
                            where(QueryBuilder.eq("id",missionID)).and(eq(projectIdString, projectID)));

            System.out.println(tag + " deleteFromMissionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromMissionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel deleteFromVisionTable(UUID visionID,UUID projectID,String lang) {

       /*
         delete from vision table, basic details of project
         */
        try {
            System.out.println(tag + " delete missionID " + visionID);

            resultModel.setError(0);
            resultModel.setResult(null);


            resultSetBatch = configSession.getSession().execute(
                    QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).
                            where(QueryBuilder.eq("id",visionID)).and(eq(projectIdString, projectID)));

            System.out.println(tag + " deleteFromVisionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromVisionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel deleteFromReleaseTable(UUID time,UUID projectID,String lang){

        /*
         delete from release table, basic details of project
         */
        try {
            logger.warn(tag + " delete releaseID " + time + " projectID: " +projectID);

            resultModel.setError(0);
            resultModel.setResult(null);


            Delete.Where deleteSelection = QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).
                    where(QueryBuilder.eq(idStg, time)).and(eq(projectIdString, projectID));


//            Delete aa = deleteSelection.ifExists();
            resultSetBatch = configSession.getSession().execute(deleteSelection);

            logger.warn(tag + " deleteFromReleaseTable wasApplied : " + resultSetBatch.isExhausted() +
            " " +resultSetBatch.isFullyFetched() + " "+resultSetBatch + " deleteSelection: " +deleteSelection) ;

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromReleaseTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel deleteFromRoleTable(UUID roleID,UUID projectID,String lang){

        /*
         delete from Role table, basic details of project
         */
        try {
            System.out.println(tag + " delete roleID " + roleID);

            resultModel.setError(0);
            resultModel.setResult(null);


            resultSetBatch = configSession.getSession().execute(
                    QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).
                            where(QueryBuilder.eq("roleId",roleID)).and(eq(projectIdString, projectID)));

            System.out.println(tag + " deleteFromRoleTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromRoleTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel deleteFromFeatureTable(UUID time,UUID projectID,String lang){

     /*
         delete from feature table, basic details of project
         */
        try {
            System.out.println(tag + " delete roleID " + time);

            resultModel.setError(0);
            resultModel.setResult(null);


            resultSetBatch = configSession.getSession().execute(
                    QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).
                            where(QueryBuilder.eq(timeString,time)).and(eq(projectIdString, projectID)));

            System.out.println(tag + " deleteFromFeatureTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel deleteFromSubFeatureTable(UUID subFeatureID,UUID projectID,String lang){

        /*
         delete from subfeature table, basic details of project
         */
        try {
            System.out.println(tag + " delete subFeatureID " + subFeatureID);

            resultModel.setError(0);
            resultModel.setResult(null);


            resultSetBatch = configSession.getSession().execute(
                    QueryBuilder.delete().from(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).
                            where(QueryBuilder.eq("id",subFeatureID)).and(eq(projectIdString, projectID)));

            System.out.println(tag + " deleteFromSubFeatureTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(lang, "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in deleteFromSubFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }


   /*--------------------------------------------------------------------------------------------

    Update table separately for different route

    *---------------------------------------------------------------------------------------------*/

    public ResultModel updateMissionTable(MissionByProject missionByProject){
        /*
         Insert into mission table, basic details of project
         */
        try {
            System.out.println(tag + " updateMissionTable title " + missionByProject.getTitle());

            resultModel.setError(0);
            resultModel.setResult(null);

            updateMission = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getMissionByProject()).
                    with()
                    .and(QueryBuilder.set("title", missionByProject.getTitle()))
                    .and(QueryBuilder.set(local_idStg, missionByProject.getLocal_id()))
                    .and(QueryBuilder.set("missionDesc", missionByProject.getMissionDesc()))
                    .and(QueryBuilder.set("attachment", getUUIDclassUDT(missionByProject.getAttachment())))
                    .and(QueryBuilder.set("creatorID", missionByProject.getCreatorID()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(missionByProject.getCreatorID())))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq("id", missionByProject.getId())).and(eq(projectIdString, missionByProject.getprojectId()));

            resultSetBatch = configSession.getSession().execute(updateMission);
            System.out.println(tag + " updateMissionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(missionByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in updateMissionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }

        return resultModel;
    }

    public ResultModel updateVisionTable(VisionByProject visionByProject) {

        /*
         update mission table, basic details of project
         */
        try {
            System.out.println(tag + " updateVisionTable " + " visionDesc " + visionByProject.getVisionDesc());
            resultModel.setError(0);
            resultModel.setResult(null);

            updateVision = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getVisionByProject()).
                    with()
                    .and(QueryBuilder.set("title", visionByProject.getTitle()))
                    .and(QueryBuilder.set(local_idStg, visionByProject.getLocal_id()))
                    .and(QueryBuilder.set("visionDesc", visionByProject.getVisionDesc()))
                    .and(QueryBuilder.set("members", getUdtvalueList(visionByProject.getMembers())))
                    .and(QueryBuilder.set("creatorID", visionByProject.getCreatorID()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(visionByProject.getCreatorID())))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq("id", visionByProject.getId())).and(eq(projectIdString, visionByProject.getprojectId()));

            resultSetBatch = configSession.getSession().execute(updateVision);
            System.out.println(tag + " updateVisionTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(visionByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());

        }catch (Exception e){
            logger.error(tag+" error in updateVisionTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel updateReleaseTable(ReleaseByProject releaseByProject){

        try {
            System.out.println(tag + " updateReleaseTable " + " visionDesc " );
            resultModel.setError(0);
            resultModel.setResult(null);

            updateRelease = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getReleaseByProject()).
                    with()
                    .and(QueryBuilder.set("title", releaseByProject.getTitle()))
                    .and(QueryBuilder.set("releaseDesc", releaseByProject.getReleaseDesc()))
                    .and(QueryBuilder.set("missionId", getUdtvalueListForMissionNameID(releaseByProject.getMissionId())))
                    .and(QueryBuilder.set("meetingId", getUUIDclassUDT(releaseByProject.getMeetingId())))
                    .and(QueryBuilder.set("issueId", getUUIDclassUDT(releaseByProject.getIssueId())))
                    .and(QueryBuilder.set("creator", getMemberTypeById(releaseByProject.getCreatorID())))
                    .and(QueryBuilder.set("creatorID", releaseByProject.getCreatorID()))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq(idStg, releaseByProject.getId())).and(eq(projectIdString, releaseByProject.getprojectId()));


            System.out.println(tag + " updateReleaseTable wasApplied : " + updateRelease.toString());
            resultSetBatch = configSession.getSession().execute(updateRelease);

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(releaseByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in updateReleaseTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel updateRoleTable(RoleByProject roleByProject){

        try {

            resultModel.setError(0);
            resultModel.setResult(null);

            updateRole = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getRoleByProject()).
                    with()
                    .and(QueryBuilder.set("projectName", roleByProject.getProject_name()))
//                    .and(QueryBuilder.set("memberID", roleByProject.getMemberID()))
                    .and(QueryBuilder.set("member", getUdtvalue(roleByProject.getMember())))
                    .and(QueryBuilder.set("isStackholder", roleByProject.getIs_stackholder()))
                    .and(QueryBuilder.set("roleName", roleByProject.getRole_name()))
                    .and(QueryBuilder.set("permissionName", roleByProject.getPermission_name()))
                    .and(QueryBuilder.set("modifyTime", resultModel.getInsertDate()))
                    .where(QueryBuilder.eq("roleId", roleByProject.getroleId())).and(eq(projectIdString, roleByProject.getprojectId()));


            resultSetBatch = configSession.getSession().execute(updateRole);
            System.out.println(tag + " updateRoleTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(roleByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " updateRoleTable result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in updateRoleTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel updateFeatureTable(FeatureByProject featureByProject){

        try{
            resultModel.setError(0);
            resultModel.setResult(null);

            updateFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getFeatureByProject()).
                    with()
                    .and(QueryBuilder.set("title", featureByProject.getTitle()))
                    .and(QueryBuilder.set("featureDesc", featureByProject.getFeatureDesc()))
                    .and(QueryBuilder.set("meetingId", getUUIDclassUDT(featureByProject.getMeetingId())))
                    .and(QueryBuilder.set("creator", getMemberTypeById(featureByProject.getCreatorID())))
                    .and(QueryBuilder.set("creatorID", featureByProject.getCreatorID()))
                    .and(QueryBuilder.set("hasSubfeature", featureByProject.getHasSubfeature()))
                    .and(QueryBuilder.set("undefinedSub", featureByProject.getUndefinedSub()))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq("id", featureByProject.getId())).and(eq(projectIdString, featureByProject.getprojectId()));


            resultSetBatch = configSession.getSession().execute(updateFeature);
            System.out.println(tag + " updateFeatureTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(featureByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in updateFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    public ResultModel updateSubFeatureTable(SubFeatureByProject subFeatureByProject){

        /*
         update subfeature table, basic details of project
         */

        try {
            resultModel.setError(0);
            resultModel.setResult(null);


            System.out.println(tag + " updateSubFeatureTable " + " subFeatureDesc " + subFeatureByProject.getSubFeatureDesc());

            updateSubFeature = QueryBuilder.update(requirementsProperties.getKeyspace(), requirementsProperties.getSubfeatureByProject()).
                    with()
                    .and(QueryBuilder.set("title", subFeatureByProject.getTitle()))
                    .and(QueryBuilder.set("subFeatureDesc", subFeatureByProject.getSubFeatureDesc()))
                    .and(QueryBuilder.set("members", getUUIDclassUDT(subFeatureByProject.getMembers())))
                    .and(QueryBuilder.set("featureTitle", subFeatureByProject.getFeature_title()))
                    .and(QueryBuilder.set("featureId", subFeatureByProject.getFeature_id()))
                    .and(QueryBuilder.set("creator", getMemberTypeById(subFeatureByProject.getCreatorID())))
                    .and(QueryBuilder.set("creatorID", subFeatureByProject.getCreatorID()))
                    .and(QueryBuilder.set("modifyTime", UUIDGen.getTimeUUID()))
                    .where(QueryBuilder.eq("id", subFeatureByProject.getId())).and(eq(projectIdString, subFeatureByProject.getprojectId()));


            resultSetBatch = configSession.getSession().execute(updateSubFeature);
            System.out.println(tag + " updateSubFeatureTable wasApplied : " + resultSetBatch.wasApplied());

            if (resultSetBatch.wasApplied()) {
                resultModel.setError(0);
                resultModel.setResult(requirementsProperties.getSuccessful());
            } else {
                resultModel.setError(1);
                resultModel.setResult(resultModel.getErrorTextByLanguage(subFeatureByProject.getLang(), "UNKNOWN_TRANSACTION_ERROR"));
            }
            System.out.println(tag + " result set value " + resultSetBatch.all().size() + " , " + resultSetBatch.wasApplied());
        }catch (Exception e){
            logger.error(tag+" error in updateSubFeatureTable "+e);
            resultModel.setError(1);
            resultModel.setResult(e.toString());
            return resultModel;
        }
        return resultModel;
    }

    /**
     * @author Masoomeh
     * @param row
     * @param projectAllDetails
     * @return
     */

    private ProjectAllDetails setProjectAllDetail(Row row, ProjectAllDetails projectAllDetails) {
        projectAllDetails.setCreateDate(resultModel.getInsertDate());
//        projectAllDetails.setCreator(getMemberTypeById(projectAllDetails.getCreatorID()));
        projectAllDetails.setStatus(row.getBool(status));
        projectAllDetails.setStart_date(row.getLong(startDate));
        projectAllDetails.setProjectDescription(row.getString(projectDescription));
        projectAllDetails.setPercentage(row.getInt(percentage));
        projectAllDetails.setName(row.getString(name));
        projectAllDetails.setModifyTime(row.getLong(modifyTime));
//        projectAllDetails.setMission();
//        projectAllDetails.setVision();
//        projectAllDetails.setMembers();
//        projectAllDetails.setRelease();
//        projectAllDetails.setFeature(row.get);
//        projectAllDetails.setSubfeature(row.get);
        projectAllDetails.setEnd_date(row.getLong(endDate));
        projectAllDetails.setCreatorID(row.getUUID(creatorID));

        return projectAllDetails;
    }



    private Project setProject(Row row, Project project) {
        project.setId(row.getUUID(idStg));
//        project.setCreateDate(resultModel.getInsertDate());
        project.setName(row.getString(name));
        project.setDesc(row.getString(projectDescription));
        project.setStatus(row.getBool(status));
        project.setPercentage(row.getInt(percentage));
        project.setStartDate(row.getLong(startDate));
        project.setEndDate(row.getLong(endDate));
        project.setCreator(getMemberType(row.getObject(creator)));
        project.setModifyTime(row.getUUID(modifyTime));
        project.setModifyTime(row.getUUID(timeString));
        return project;
    }

    /**
     *
     * @author Masoomeh
     * @param row
     * @param releaseByProject
     * @return
     */
    private ReleaseByProject setRelease(Row row, ReleaseByProject releaseByProject) {

//        logger.info("row: " +row +" releaseByProject: " +releaseByProject);
        releaseByProject.setTitle(row.getString(title));
        releaseByProject.setReleaseDesc(row.getString(releaseDesc));
        releaseByProject.setprojectId(row.getUUID(projectIdString));
        releaseByProject.setModifyTime(row.getUUID(modifyTime));
        releaseByProject.setMissionId(getudtValuesMissionNameId(row.getObject(missionId)));
        releaseByProject.setMeetingId(getudtValuesuuidClass(row.getObject(meetingId),idStg));
        releaseByProject.setIssueId(getudtValuesuuidClass(row.getObject(issueId),idStg));
        releaseByProject.setCreatorID(row.getUUID(creatorID));
        releaseByProject.setCreator(getMemberType(row.getObject(creator)));
        releaseByProject.setId(row.getUUID(idStg));
        return releaseByProject;

    }


    /**
     * @author Masoomeh
     * @param row
     * @param featureByProject
     * @return
     */
    private FeatureByProject setFeature(Row row, FeatureByProject featureByProject) {
        logger.info(tag+" row: " +row +
        " \n featureByProject: " +featureByProject);
        featureByProject.setId(row.getUUID(idStg));
        featureByProject.setprojectId(row.getUUID(projectIdString));
        featureByProject.setCreatorID(row.getUUID(creatorID));
        featureByProject.setTitle(row.getString(title));
        featureByProject.setFeatureDesc(row.getString(featureDesc));
        featureByProject.setMeetingId(getudtValuesuuidClass(row.getObject(meetingId),idStg));
        featureByProject.setHasSubfeature(row.getBool(hasSubfeature));
        featureByProject.setCreator(getMemberType(row.getObject(creator)));
        featureByProject.setUndefinedSub(row.getBool(undefinedSub));
        featureByProject.setModifyTime(row.getUUID(modifyTime));
        featureByProject.setLocal_id(row.getUUID(local_idStg));

        return featureByProject;
    }


    /**
     * @author Masoomeh
     * @param row
     * @param missionByProject
     * @return
     */
    private MissionByProject setMission(Row row, MissionByProject missionByProject) {
        logger.info(tag+" row: " +row +
                " \n featureByProject: " +missionByProject);
        missionByProject.setId(row.getUUID(idStg));
        missionByProject.setprojectId(row.getUUID(projectIdString));
        missionByProject.setTitle(row.getString(title));
        missionByProject.setMissionDesc(row.getString(missionDesc));
        missionByProject.setAttachment(getudtValuesuuidClass(row.getObject(attachment),idStg));
        missionByProject.setCreatorID(row.getUUID(creatorID));
        missionByProject.setCreator(getMemberType(row.getObject(creator)));
        missionByProject.setModifyTime(row.getUUID(modifyTime));
        missionByProject.setLocal_id(row.getUUID(local_idStg));

        return missionByProject;
    }

    private Project setProjectAll(Row row, Project project) {

        ArrayList<RoleByProjectType> roleByProjectTypes = new ArrayList<>();
        project.setId(row.getUUID(idStg));
//        project.setCreateDate(row.getLong(createDate));
        project.setName(row.getString(name));
        project.setDesc(row.getString(projectDescription));
        project.setStatus(row.getBool(status));
        project.setPercentage(row.getInt(percentage));
        project.setStartDate(row.getLong(startDate));
        project.setEndDate(row.getLong(endDate));
        project.setCreator(getMemberType(row.getObject(creator)));
        project.setModifyTime(row.getUUID(modifyTime));
        project.setTime(row.getUUID(timeString));
        logger.warn(tag + " memberS: " +(ArrayList)row.getObject(members));
        for (Object role:(ArrayList)row.getObject(members)) {
            roleByProjectTypes.add(getRoleType(role));
        }
        project.setMembers(roleByProjectTypes);
        project.setCreatorID(row.getUUID(creatorID));

        return project;
    }

    private SubFeatureByProject setsubFeature(Row row, SubFeatureByProject subFeatureByProject) {
        subFeatureByProject.setLocal_id(row.getUUID(local_idStg));
        subFeatureByProject.setId(row.getUUID(idStg));
        subFeatureByProject.setprojectId(row.getUUID(projectIdString));
        subFeatureByProject.setTitle(row.getString(title));
        subFeatureByProject.setSubFeatureDesc(row.getString(subFeatureDesc));
        subFeatureByProject.setModifyTime(row.getUUID(modifyTime));
        subFeatureByProject.setFeature_id(row.getUUID(featureId));
        subFeatureByProject.setFeature_title(row.getString(featureTitle));
        subFeatureByProject.setDate(row.getUUID(date));
        subFeatureByProject.setMembers(getudtValuesuuidClass(row.getObject(members),idStg));
        subFeatureByProject.setCreator(getMemberType(row.getObject(creator)));
        subFeatureByProject.setCreatorID(row.getUUID(creatorID));

        return subFeatureByProject;

    }


    private VisionByProject setVision(Row row, VisionByProject visionByProject) {
        ArrayList<MemberType> memberTypes = new ArrayList<>();
        visionByProject.setLocal_id(row.getUUID(local_idStg));
        visionByProject.setId(row.getUUID(idStg));
        visionByProject.setprojectId(row.getUUID(projectIdString));
        visionByProject.setTitle(row.getString(title));
        visionByProject.setVisionDesc(row.getString(visionDesc));
        visionByProject.setModifyTime(row.getUUID(modifyTime));
        logger.warn(tag + " memberS: " +(ArrayList)row.getObject(members));
        for (Object member:(ArrayList)row.getObject(members)) {
            memberTypes.add(getMemberType(member));
        }
        visionByProject.setMembers(memberTypes);
        visionByProject.setCreator(getMemberType(row.getObject(creator)));
        visionByProject.setCreatorID(row.getUUID(creatorID));

        return visionByProject;
    }



    private UUID AllProjectIds(Row row) {

        logger.info("AllProjectIds: ");
        UUID uuids = null;
        uuids = row.getUUID(idStg);
        return uuids;
    }

    /**
     * @author Masoomeh
     * @param object
     * @return
     */

    public MemberType getMemberType(Object object) {

        UDTValue udtObj = ((UDTValue) object);
        logger.info(tag + " udtObj:member: " +udtObj);
        if(udtObj != null) {
            MemberType memberType = new MemberType();
            memberType.setName(udtObj.getString(name));
            memberType.setFamily(udtObj.getString(family));
            memberType.setUserName(udtObj.getString(userName));
            memberType.setEmail(udtObj.getString(email));
            memberType.setPhoneNumber(udtObj.getString(phoneNumberMemberType));
            memberType.setImagePath(udtObj.getString(imagePath));
            memberType.setId(udtObj.getUUID(idStg));
        return memberType;
        }else {
            return null;
        }
    }


    private RoleByProjectType getRoleType(Object object) {
        UDTValue udtObj = ((UDTValue) object);
        if(udtObj != null) {
            RoleByProjectType roleByProjectType = new RoleByProjectType();
            roleByProjectType.setRoleId(udtObj.getUUID(roleId));
            roleByProjectType.setProjectId(udtObj.getUUID(projectIdString));
            roleByProjectType.setMemberID(udtObj.getUUID(memberID));
            roleByProjectType.setMember(getMemberType(getMemberTypeById(udtObj.getUUID(memberID))));
            roleByProjectType.setProjectName(udtObj.getString(projectName));
            roleByProjectType.setStackholder(udtObj.getBool(isStakeholder));
            roleByProjectType.setRoleName(udtObj.getString(roleName));
            roleByProjectType.setPermissionKey(udtObj.getInt(permissionKey));
            roleByProjectType.setPermissionName(udtObj.getString(permissionName));
            return roleByProjectType;
        }else {
            return null;
        }
    }

    /**
     * @author Masoomeh
     * @param object
     * @return
     */
    private List<MissionNameID> getudtValuesMissionNameId(Object object) {
        List<MissionNameID> missionNameIDS = new ArrayList<>();
        for (int i = 0; i <((List)object).size(); i++) {
            MissionNameID missionNameID = new MissionNameID();
            missionNameID.setMissionId (((UDTValue)((List)object).get(i)).getUUID(missionId));
            missionNameID.setMissionName (((UDTValue)((List)object).get(i)).getString(missionName));
            missionNameIDS.add(missionNameID);
        }
        logger.warn(tag+" missionNameIDS "+missionNameIDS + " size: " + ((List)object).size());
        return missionNameIDS;
    }



}
