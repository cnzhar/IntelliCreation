package com.intellicreation.api.controller;


import com.intellicreation.api.annotation.SystemLog;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.constant.SystemConstants;
import com.intellicreation.common.enumtype.AppHttpCodeEnums;
import com.intellicreation.common.exception.SystemException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * 文件上传
 * @author za
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Value("${spring.tencent.secretId}")
    private String secretId;

    @Value("${spring.tencent.secretKey}")
    private String secretKey;

    @Value("${spring.tencent.region}")
    private String bucketRegion;

    @Value("${spring.tencent.bucketName}")
    private String bucketName;

    @Value("${spring.tencent.path}")
    private String path;

    @Value("${spring.tencent.prefix}")
    private String prefix;

//    @SystemLog(businessName = "上传图片", operationType = SystemConstants.USER_TYPE)
    @PostMapping("/uploadImage")
    public Object uploadImage(@RequestParam(value = "image") MultipartFile multipartFile) throws IOException {
        // 判空，抛出异常
        if (multipartFile == null) {
            throw new SystemException(AppHttpCodeEnums.IMAGE_NOT_NULL);
        }

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域
        Region region = new Region(bucketRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        File localFile = null;
        // 获取上传文件的原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        // 通过原始文件名的最后一个"."字符来确定文件扩展名
        String eName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用UUID.randomUUID()生成一个唯一的文件名，避免重复
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        // 获取当前的日期信息，包括年份、月份和日期
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        // 指定要上传的文件
        localFile = File.createTempFile("temp", null);
        multipartFile.transferTo(localFile);
        // 指定文件将要存放的存储桶
        String bucketName = this.bucketName;
        // 指定要上传到 COS 上的路径
        String key = "/" + this.prefix + "/" + year + "/" + month + "/" + day + "/" + newFileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        // PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        cosclient.putObject(putObjectRequest);
        // 关闭客户端(关闭后台线程)
        cosclient.shutdown();
        return ResponseResult.okResult(this.path + putObjectRequest.getKey());
    }
}
