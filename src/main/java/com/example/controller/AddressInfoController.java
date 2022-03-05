package com.example.controller;

import com.example.service.AddressInfoService;
import com.example.service.UserInfoService;
import com.example.util.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adds")
public class AddressInfoController {
    @Autowired
    private AddressInfoService addService;
    @Autowired
    private UserInfoService userService;

    public void setAddService(AddressInfoService addService) {
        this.addService = addService;
    }

    public AddressInfoService getAddService() {
        return addService;
    }

    public void setUserService(UserInfoService userService) {
        this.userService = userService;
    }

    public UserInfoService getUserService() {
        return userService;
    }

    @RequestMapping("/add")
    public String addAdds(String userId, String adds){
        if(this.userService.getUser(userId)!=null){
            this.addService.addAdd(userId,adds);
            return JsonTools.executeSuccess();
        }
        else return JsonTools.error(JsonTools.ADD_ERROR);
    }

    @RequestMapping("/find")
    public String findAdds(String userId){
        return JsonTools.querySuccess("result",this.addService.findAdds(userId));
    }

    @RequestMapping("/del")
    public String delAdds(String addressId){
        if(this.addService.delAdds(addressId) != 0){
            return JsonTools.executeSuccess();
        }
        else return JsonTools.error(JsonTools.DEL_ERROR);
    }
    @RequestMapping("/update")
    public String updateAdds(String addressId){
        this.addService.updateDefault(addressId);
        return JsonTools.executeSuccess();
    }
}
