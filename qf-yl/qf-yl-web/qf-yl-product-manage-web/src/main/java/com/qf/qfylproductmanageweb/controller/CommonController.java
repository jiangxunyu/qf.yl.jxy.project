package com.qf.qfylproductmanageweb.controller;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.dto.ResultBean;
import com.qf.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private FastFileStorageClient client;

    @Value("${image.server}")
    private String imageServer;

    @RequestMapping(value = "uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadImage(MultipartFile file) {

        String imagePath = ImageUtil.uploadFileToServer(client, imageServer, file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("imagePath",imagePath);
        return map;
    }

    @RequestMapping(value = "uploadImageByEditor",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean uploadImageByEditor(MultipartFile file){

        String imagePath = ImageUtil.uploadFileToServer(client, imageServer, file);

        //使用数据传输对象将结果传给前端
        ResultBean<Object> resultBean = new ResultBean<>();
        resultBean.setErrno(0);
        resultBean.setData(new String[]{imagePath});

        return resultBean;
    }
}
