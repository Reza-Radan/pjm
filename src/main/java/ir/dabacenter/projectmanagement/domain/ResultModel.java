package ir.dabacenter.projectmanagement.domain;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResultModel{

    String result ;
    int error ;


    public ResultModel() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public long getInsertDate(){
        //   SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
       //    f.format(new Date());
      //     f.getDateFormatSymbols();
        return new Date().getTime();
    }

    public String getErrorTextByLanguage(String lang,String errorkey) {

        Locale locale;
        String errorValue = "";
        try {
            if(lang != null && lang.equalsIgnoreCase("en")) {
                ResourceBundle bundle4 = ResourceBundle.getBundle("language", Locale.US);
                errorValue = new String(bundle4.getString(errorkey).getBytes("UTF-8"), "UTF-8");

            }else {
                locale = new Locale("fa", "persian");
                ResourceBundle bundle4 = ResourceBundle.getBundle("language", locale);
                errorValue = new String(bundle4.getString(errorkey).getBytes("UTF-8"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return errorValue;
        }
        return errorValue;
    }

}
