package com.progetto.projectmanagement.controller;


import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/")
@PropertySource(value = "classpath:language.properties",encoding="UTF-8")
public class testController {



    Logger logger = LoggerFactory.getLogger(this.getClass());
    //RequirementsProperties requirementsProperties = new RequirementsProperties();

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    ResponseModel responseModel;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void saveUploadedFile(@RequestParam("testParam") String testParam) {

//        System.out.println(" properties : fail  "+requirementsProperties.getFail()+" success "+requirementsProperties.getSuccessful());
//        System.out.println(" properties : fail  "+requirementsProperties.getIssueByRelease()+" key space "+requirementsProperties.getKeyspace());
//            //return new ResponseModel(isCompleted.getStatus(),FAIL,"","");
//
//        Locale nonexistentLocale = new Locale("fa", "persian");
//        ResourceBundle bundle4 = ResourceBundle.getBundle("language", nonexistentLocale);
//        System.out.println("error test :" + bundle4.getString("UNKNOWN_DATABASE_ERROR"));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseModel test(String testParam) {

        Locale locale = new Locale("fa", "persian");
        ResourceBundle bundle4 = ResourceBundle.getBundle("language", locale);
        logger.info("tag : "+ bundle4.getString("UNKNOWN_DATABASE_ERROR"));
        responseModel.setResult(bundle4.getString("UNKNOWN_DATABASE_ERROR"));

        System.out.println("error test :" + bundle4.getString("UNKNOWN_DATABASE_ERROR"));
        return responseModel;
    }

}
