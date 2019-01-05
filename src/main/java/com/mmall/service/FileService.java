package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2019-01-05 下午12:16
 **/
public interface FileService {
    /**
     * 上传文件
     * @param file 上传文件
     * @param path 上传到服务器路径
     * @return
     */
    String newUpload(MultipartFile file, String path);

}
