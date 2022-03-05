package com.example.controller;

import com.example.entity.UserInfo;
import com.example.service.UserInfoService;
import com.example.util.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @Value("${file.uploadPath}")
    private String uploadPath;

    public void setService(UserInfoService service) {
        this.service = service;
    }

    public UserInfoService getService() {
        return service;
    }

    @RequestMapping("/find.action")
    public String findUser(String id){
        UserInfo ui = service.getUser(id);
        Map map = new LinkedHashMap();
        map.put("ui",ui);
        return JsonTools.querySuccess(map);
    }

@RequestMapping("/upload")
public String upLoad(MultipartFile file, UserInfo user, HttpServletRequest request) throws IOException {

  System.out.println(user.getUserId() + user.getUserName() + user.getPhone() + user.getUserPhotoLink());
    String orName = file.getOriginalFilename();
    int index = orName.lastIndexOf(".");
    String newName;
    if(user.getUserPhotoLink()==null || user.getUserPhotoLink().equals("")){
        newName = "avatar/" + UUID.randomUUID()+orName.substring(index);
    }
    else{
        newName = user.getUserPhotoLink();
    }
    file.transferTo(new File(uploadPath + newName));
    user.setUserPhotoLink(newName);
    this.service.uploadUserInfo(user);

    return JsonTools.executeSuccess();
}

}
