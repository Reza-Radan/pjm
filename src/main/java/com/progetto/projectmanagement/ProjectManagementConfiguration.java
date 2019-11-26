package com.progetto.projectmanagement;

import com.progetto.projectmanagement.domain.issue.IssueRepository;
import com.progetto.projectmanagement.domain.meeting.MeetingRepository;
import com.progetto.projectmanagement.domain.member.MemberRepository;
import com.progetto.projectmanagement.domain.project.AddProjectRepository;
import com.progetto.projectmanagement.domain.report.SprintReport;
import com.progetto.projectmanagement.domain.role.RoleRepository;
import com.progetto.projectmanagement.domain.task.TaskRepository;
import com.progetto.projectmanagement.service.attachment.AttachmentValidator;
import com.progetto.projectmanagement.service.filemanager.FileManagerValidator;
import com.progetto.projectmanagement.service.issue.IssueService;
import com.progetto.projectmanagement.service.issue.IssueValidator;
import com.progetto.projectmanagement.service.meeting.MeetingService;
import com.progetto.projectmanagement.service.meeting.MeetingValidator;
import com.progetto.projectmanagement.service.member.MemberValidator;
import com.progetto.projectmanagement.service.notification.NotificationService;
import com.progetto.projectmanagement.service.permission.PermissionService;
import com.progetto.projectmanagement.service.permission.PermissionValidator;
import com.progetto.projectmanagement.service.project.ProjectService;
import com.progetto.projectmanagement.service.project.ProjectValidator;
import com.progetto.projectmanagement.service.report.ReportService;
import com.progetto.projectmanagement.service.report.ReportValidator;
import com.progetto.projectmanagement.service.role.RoleService;
import com.progetto.projectmanagement.service.role.RoleValidator;
import com.progetto.projectmanagement.service.security.SecurityService;
import com.progetto.projectmanagement.service.task.TaskService;
import com.progetto.projectmanagement.service.task.TaskValidator;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.attachment.AttachmentRepository;
import com.progetto.projectmanagement.domain.activity.NotificationRepository;
import com.progetto.projectmanagement.domain.permission.PermissionRepository;
import com.progetto.projectmanagement.domain.project.GetProjectRepository;
import com.progetto.projectmanagement.service.attachment.AttachmentService;
import com.progetto.projectmanagement.service.filemanager.FileManagerService;
import com.progetto.projectmanagement.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import FileManagerService;

@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:language_fa.properties",encoding="UTF-8")
public class ProjectManagementConfiguration {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String tag = "ProjectManagementConfiguration";


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ir.dabacenter.projectmanagement.controller"))
                .paths(PathSelectors.any())
                .build();
    }

//    private SecurityScheme securityScheme() {
//        GrantType grantType = new AuthorizationCodeGrantBuilder()
//                .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
//                .tokenRequestEndpoint(
//                        new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
//                .build();
//
//        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//                .grantTypes(Arrays.asList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//        return oauth;
//    }
//
//    @Bean
//    public SecurityConfiguration security() {
//
//
//        return new SecurityConfiguration(null, null, null,
//                null,
//                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZXphcmFkYW4iLCJhdWQiOiIxNTIyIiwiZXhwIjoxNTI2MDQ0NDY2LCJpYXQiOjE1MjU5NTgwNjZ9.eR0wFuywaw5KOHZJkD3BfeNk5H6chkPCBrrpp9PX76_ymO2_qpHGpeK5ZufJ1gErT2js7GJ56MuTN8htyDmDYg",
//                 ApiKeyVehicle.HEADER,
//                "Authorization", ",");
////        SecurityConfiguration
////        return SecurityConfigurationBuilder.builder()
////                .clientId(CLIENT_ID)
////                .clientSecret(CLIENT_SECRET)
////                .scopeSeparator(" ")
////                .useBasicAuthenticationWithAccessCodeGrant(true)
////                .build();
//    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    public ProjectManagementConfiguration() {

        /**
         * Code to create keyspace but not working
         */
//        KeyspaceActionSpecificationFactoryBean keyspaceActionSpecificationFactoryBean = new KeyspaceActionSpecificationFactoryBean();
//        keyspaceActionSpecificationFactoryBean.setReplicationStrategy(KeyspaceOption.ReplicationStrategy.SIMPLE_STRATEGY);
//        keyspaceActionSpecificationFactoryBean.setReplicationFactor(3);
//        keyspaceActionSpecificationFactoryBean.setName("projectmanagement");
//        keyspaceActionSpecificationFactoryBean.setAction(KeyspaceAction.CREATE);
//        try {
//            logger.error(tag + " creating key space: ");
//            keyspaceActionSpecificationFactoryBean.afterPropertiesSet();
//            logger.error(tag + " Action "+keyspaceActionSpecificationFactoryBean.getAction()+" name "+keyspaceActionSpecificationFactoryBean.getName()+
//                          " replication "+keyspaceActionSpecificationFactoryBean.getReplicationFactor()+" strategy "+keyspaceActionSpecificationFactoryBean.getReplicationStrategy());
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(tag + " error in creating key space : "+e);
//        }
    }




    /***********
     * Add services to Bean
     ***********/

    @Bean
    AttachmentService attachment(){return new AttachmentService();}

    @Bean
    IssueService issue(){return new IssueService();}

    @Bean
    MemberService member(){return new MemberService();}

    @Bean
    NotificationService notification(){return new NotificationService();}

    @Bean
    PermissionService permission(){return new PermissionService();}

    @Bean
    ProjectService project(){return new ProjectService();}

    @Bean
    ReportService report(){return new ReportService();}

    @Bean
    RoleService role(){return  new RoleService();}

    @Bean
    TaskService task(){return new TaskService();}

    @Bean
    FileManagerService fileManager(){return new FileManagerService();}

    @Bean
    SecurityService _security(){return new SecurityService();}

    @Bean
    MeetingService meeting(){return new MeetingService();}

    /********************************************************/



    /***********
     * Add Repository to Bean
     ***********/

    @Bean
    AttachmentRepository attachmentRepo(){return new AttachmentRepository();}

    @Bean
    IssueRepository issueRepo(){return new IssueRepository();}

    @Bean
    MemberRepository memberRepo(){return new MemberRepository();}

    @Bean
    NotificationRepository notificationRepo(){return new NotificationRepository();}

    @Bean
    PermissionRepository permissionRepo(){return new PermissionRepository();}

    @Bean
    AddProjectRepository projectAddRepo(){return new AddProjectRepository();}

    @Bean
    GetProjectRepository projectGetRepo(){return new GetProjectRepository();}

    @Bean
    SprintReport reportRepo(){return new SprintReport();}

    @Bean
    RoleRepository roleRepo(){return  new RoleRepository();}

    @Bean
    TaskRepository taskRepo(){return new TaskRepository();}

    @Bean
    MeetingRepository meetingRepo(){return new MeetingRepository();}

    /********************************************************/


    /***********
     * Add Validator to Bean
     ***********/

    @Bean
    RoleValidator roleValidator(){return new RoleValidator();}

    @Bean
    AttachmentValidator attachmentValidator(){return new AttachmentValidator();}

    @Bean
    IssueValidator issueValidator(){return new IssueValidator();}

    @Bean
    MeetingValidator meetingValidator(){return new MeetingValidator();}

    @Bean
    MemberValidator memberValidator(){return new MemberValidator();}

    @Bean
    ProjectValidator projectValidator(){return new ProjectValidator();}

    @Bean
    ReportValidator reportValidator(){return new ReportValidator();}

    @Bean
    TaskValidator taskValidator(){return new TaskValidator();}

    @Bean
    FileManagerValidator fileManagerValidator(){return new FileManagerValidator();}

    @Bean
    PermissionValidator permissionValidator(){return new PermissionValidator();}


    //@Bean
//    SecurityValidator securityValidator(){return new SecurityValidator();}

    //@Bean
//    NotificationValidator notificationValidator(){return new NotificationValidator();}

//    @Bean
//    PermissionValidator permissionValidator(){return new PermissionValidator();}

    /********************************************************/


    @Bean
    ResultModel resultmodel(){return new ResultModel();}

    @Bean
    ResponseModel responsemodel(){return new ResponseModel();}

    @Bean
    AbstractApplicationContext getAppContext(){ return  new AnnotationConfigApplicationContext(RequirementsProperties.class);}

    @Bean
    RequirementsProperties getProperties(){ return getAppContext().getBean(RequirementsProperties.class); }

    @Bean
    ConfigSession getSession(){return new ConfigSession(); }


}
