package ir.dabacenter.projectmanagement;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    RequirementsProperties requirementsProperties;


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String errorMsg = ex.getMessage();

        return new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),errorMsg.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(ex.getContentType());
//        builder.append(" media type is not supported. Supported media types are ");
//        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        String errorMsg = ex.getMessage();
//        String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        return new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),errorMsg.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
//        ApiError apiError = new ApiError(BAD_REQUEST);
////        apiError.setMessage("Validation error");
//        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
//        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        String errorMsg = ex.getMessage();
      //  String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        return new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),errorMsg.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        String errorMsg = ex.getMessage();
       // String err= errorMsg.substring(0,errorMsg.indexOf(";"));

      //  System.out.println("Error in handleConstraintViolation "+err.replace("\"",""));

        return  new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),errorMsg.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        String errorMsg = ex.getMessage();
        String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        System.out.println("Error in handleEntityNotFound "+err.replace("\"",""));

        return  new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),err.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
       // log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String errorMsg = ex.getMessage();
        String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        System.out.println("Error in handleHttpMessageNotReadable "+err.replace("\"",""));

        return  new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),err.replace("\"","")), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        String errorMsg = ex.getMessage();
        String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        System.out.println("Error in handleDataIntegrityViolation "+err.replace("\"",""));

        return  new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),err.replace("\"","")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        String errorMsg = ex.getMessage();
        String err= errorMsg.substring(0,errorMsg.indexOf(";"));
        System.out.println("Error in handleDataIntegrityViolation "+err.replace("\"",""));
        return  new ResponseEntity<>(getErrorModel(HttpStatus.BAD_REQUEST.value(),err.replace("\"","")), HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    public String getErrorModel(int statusRec,String errorMsg){
        /*
         Here we make string in json wise because we can not pass the object of model to getOutputStream().println
         */
        logger.info(" received value : "+errorMsg);
        String status = "status";
        String recordCount = "recordCount";
        String content = "content";
        String contents = "contents";
        String error = "error";
        String result = "result";
        String systemError = "systemError";
        String strParam = "";

        strParam += "{\"" + status + "\":\"" + statusRec+ "\"";
        strParam += ",\"" + recordCount + "\":\"" +0+ "\"";
        strParam += ",\"" + contents + "\":\"" + "null"+"\"";
        strParam += ",\"" + content + "\":\"" + "null"+"\"";
        strParam += ",\"" + error + "\":\"" + errorMsg+ "\"";
        strParam += ",\"" + result + "\":\""+"fail"+"\"";
        strParam += ",\"" + systemError + "\":\""+errorMsg+"\""+"}";

        return strParam;
    }

}