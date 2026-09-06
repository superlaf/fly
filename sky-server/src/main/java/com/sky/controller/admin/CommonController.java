package com.sky.controller.admin;

import com.sky.constant.FileUploadConstant;
import com.sky.result.Result;
import com.sky.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags="通用接口")
public class CommonController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(@RequestBody MultipartFile file){

        log.info("文件上传:{}",file);
        //本地存储
         //获取原始文件名
        try {
            String originalFilename = file.getOriginalFilename();
            //获取文件后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //判断文件是否合法
            if(!extension.equals(".png")&&!extension.equals(".jpg")&&!extension.equals(".jpeg")){
                log.info("文件不合法，文件名为：{}",originalFilename);
                return Result.error("文件不合法，请重新上传");
            }
            //生成新的文件名
            String newFileName = UUID.randomUUID()
                    .toString() + extension;
            //保存文档
            String path = FileUploadConstant.FILE_UPLOAD_PATH + newFileName;
            file.transferTo(new File(path));
            //返回文件访问路径
            return Result.success(FileUploadConstant.FILE_VIVST_PATH+newFileName);
        } catch (IOException e) {
            log.info("文件上传失败:{}",e);
            throw new RuntimeException(e);
        }

    }

}
