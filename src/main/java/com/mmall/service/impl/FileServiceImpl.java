package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.FileService;
import com.mmall.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-05 下午12:16
 **/
@Service(value = "FileService")
public class FileServiceImpl implements FileService{
    private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String newUpload(MultipartFile file, String path){
        //获取名称
        String fileName = file.getOriginalFilename();
        String uploadFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        //上传到web服务器上
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
        File fileDir = new File(path);
        if (fileDir.exists() == false) {
            fileDir.mkdirs();
            fileDir.setWritable(true);
            fileDir.setReadable(true);
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(fileDir);
            //上传到ftp服务器
            FTPUtil.uploadFile(Lists.<File>newArrayList(targetFile));
            targetFile.delete();
            logger.info("上传文件成功");
        } catch (IOException e) {
            logger.error("上传文件失败");
        }
        return fileName;
    }
}
