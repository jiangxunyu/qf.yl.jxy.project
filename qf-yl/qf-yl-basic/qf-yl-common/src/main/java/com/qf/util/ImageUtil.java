package com.qf.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

    /**
     * 获得文件完整路径的方法
     * protocol 文件协议
     */
    public static String getFilePath(String protocol,String imageServer,String filePath){

        return protocol+"://"+imageServer+"/"+filePath;
    }

    /**
     * 图片上传工具类
     */
    public static String uploadFileToServer(FastFileStorageClient client,
                                            String imageServer,
                                            MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        String imagePath = "";

        try{
            StorePath storePath = client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extName, null);
            imagePath = ImageUtil.getFilePath("http", imageServer, storePath.getFullPath());

        }catch(Exception e){
            e.printStackTrace();
        }

        return imagePath;
    }

}
