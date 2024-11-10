package lk.ijse.agriproject.finalproject2ndsem.util;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;


public class AppUtill {
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static String toBase64Profilepic(MultipartFile profilepic){
        String profilepicBase64=null;
        try{
            byte [] imagebyteCollection= profilepic.getBytes();
            profilepicBase64= Base64.getEncoder().encodeToString(imagebyteCollection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return profilepicBase64;
    }
}
