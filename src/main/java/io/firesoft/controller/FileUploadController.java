package io.firesoft.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);
 
    private static String FILE_SEPARATOR = "/";
 
    @RequestMapping(value = {"upload", "uploadClassic"})
    public void upload() {
 
    }
    
    @RequestMapping(value = "/upload-single", method = RequestMethod.POST,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadFileHandler(
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String url;
        String storedFolderLocation = createStoredFolder(request);
        String fileName = file.getOriginalFilename();
        String uploadedFileName = Charset.forName("UTF-8").encode(fileName).toString();
        System.out.println(uploadedFileName);
     
        try {
            byte[] bytes = file.getBytes();
            String storedFileLocation = storedFolderLocation + FILE_SEPARATOR + uploadedFileName;
            File serverFile = new File(storedFileLocation);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            url = getDomainName(request)
                    + getRelativePath() + FILE_SEPARATOR + uploadedFileName;
            
            System.out.println("url " +url);
            if (isFileTypeImage(uploadedFileName)) {
                url= "<img src=\"" + url + "\" />";
            } else {
                url= "<a href=\"" + url + "\">" + url + "</a>";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        System.out.println("Loaded File:"+url);
        return "Loaded File:"+url;
    }
    
    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "upload-classic", method = RequestMethod.POST,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadMultipleFileHandler(@RequestParam("CKEditor") String ckEditor,
            @RequestParam("file[]") MultipartFile[] files, HttpServletRequest request) {
 
        String storedFolderLocation = createStoredFolder(request);
 
        String urls = "";
 
        for (MultipartFile file : files) {
            String uploadedFileName = file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
 
                String storedFileLocation = storedFolderLocation + FILE_SEPARATOR + uploadedFileName;
                // Create the file on server
                File serverFile = new File(storedFileLocation);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                String url = getDomainName(request)
                        + getRelativePath() + FILE_SEPARATOR + uploadedFileName;
                System.out.println(url);
                if (isFileTypeImage(uploadedFileName)) {
                    urls += "<img src=\"" + url + "\" />";
                } else {
                    urls += "<a href=\"" + url + "\">" + url + "</a>";
                }
 
            } catch (Exception e) {
                return "You failed to upload " + uploadedFileName + " => " + e.getMessage();
            }
        }
        if(!ckEditor.equals("undefined")){
            urls = "<script type=\"text/javascript\">window.opener.CKEDITOR.instances."+ckEditor+".insertHtml('"
                    + urls + "');window.opener.CKEDITOR.dialog.getCurrent().hide();" +
                    "window.close();</script>";
        }else{
            urls="Uploaded files:"+urls;
        }
 
        return urls;
    }
   
    @RequestMapping(value = "/upload-ajax", method = RequestMethod.POST)
    @ResponseBody
    public String uploadMultipleFiles(//@RequestParam("CKEditor") String ckEditor,
                                      MultipartHttpServletRequest request) {
    	System.out.println("Vasya");
        String filePaths=uploadedFiles(request);
        System.out.println(getDomainName(request));
        System.out.println(filePaths);
        String ckEditor = request.getParameter("CKEditor");
        if(!ckEditor.equals("undefined")){
            filePaths = "<script type=\"text/javascript\">window.opener.CKEDITOR.instances."+ckEditor+".insertHtml('"
                    + filePaths + "');window.opener.CKEDITOR.dialog.getCurrent().hide();" +
                    "window.close();</script>";
           
        }else{
            filePaths="Uploaded files:"+filePaths;
        }
      
        return filePaths;
    }
    private String uploadedFiles(MultipartHttpServletRequest request){
        CommonsMultipartFile multipartFile = null;
        Iterator<String> iterator = request.getFileNames();
        String filePaths = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            // create multipartFile array if you upload multiple files
            multipartFile = (CommonsMultipartFile) request.getFile(key);
            String uploadedFileName = multipartFile.getOriginalFilename();
            try {
                byte[] bytes = multipartFile.getBytes();
 
                String storedFileLocation = createStoredFolder(request) + FILE_SEPARATOR + uploadedFileName;
                // Create the file on server
                File serverFile = new File(storedFileLocation);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                String url = getDomainName(request)
                        + getRelativePath() + FILE_SEPARATOR + uploadedFileName;
                if (isFileTypeImage(uploadedFileName)) {
                    filePaths += "<img src=\"" + url + "\" />";
                } else {
                    filePaths += "<a href=\"" + url + "\">" + url + "</a>";
                }
            } catch (Exception e) {
                return "You failed to upload " + uploadedFileName + " => " + e.getMessage();
            }
        }
        System.out.println(filePaths);
        return filePaths;
    }
 
    private boolean isFileTypeImage(String fileName) {
        String imagePattern =
                "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
        return Pattern.compile(imagePattern).matcher(fileName).matches();
 
    }
 
    private String getDomainName(HttpServletRequest request) {
        return request.getProtocol().toLowerCase().replaceAll("[0-9./]", "") + "://" +
                request.getServerName() + ":" + request.getServerPort()+FILE_SEPARATOR+"Firesoftblog";
    }
 
    private String createStoredFolder(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String relativePath = getRelativePath();
        String storedFolderLocation = realPath + relativePath;
        File dir = new File(storedFolderLocation);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return storedFolderLocation;
    }
 
    private String getRelativePath() {
        String fileSeparator = "/";
       
        int[] yearMonthDay = getDayMonthYear();
        return "/resources/uploads/" + yearMonthDay[0] + fileSeparator
                + yearMonthDay[1] + fileSeparator + yearMonthDay[2];
    }
    public int[] getDayMonthYear() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH); // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int[] date = new int[3];
        date[0] = year;
        date[1] = month;
        date[2] = day;
        return date;
    }
}
