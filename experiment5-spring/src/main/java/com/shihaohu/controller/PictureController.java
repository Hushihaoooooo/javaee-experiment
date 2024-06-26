package com.shihaohu.controller;

import com.shihaohu.model.Picture;
import com.shihaohu.model.Result;
import com.shihaohu.service.PictureService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/picture")
public class PictureController {

    private static final String UPLOAD_DIR = "E:/ProjectFile/";

    @Resource
    private PictureService pictureService;

    @PostMapping("/upload")
    public Result uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy,
            @RequestParam("customFileName") String customFileName) {


        if (file.isEmpty()) {
            return Result.error("Please upload a file");
        }

        // Check file size
        if (file.getSize() > 500 * 1024) {
            return Result.error("File size exceeds limit");
        }

        // Check file type
        String fileType = file.getContentType();
        if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
            return Result.error("Only jpg/png files are supported");
        }


        try {
            // Generate unique file name
            String url = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String filePath = Paths.get(UPLOAD_DIR, url).toString();

            // Create directory if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            System.out.println(filePath);
            System.out.println(customFileName);

            // Save file
            file.transferTo(new File(filePath));

            // Log metadata
            System.out.println("Uploaded by: " + uploadedBy);

            url = "/api/static/" + url;

            pictureService.upload(uploadedBy, url, customFileName);

            return Result.success(url);

        } catch (IOException e) {
            return Result.error("操作失败");
        }
    }


    @GetMapping("/getAll")
    public Result getAll() {
        List<Picture> pictures =  pictureService.getAll();
        return Result.success(pictures);
    }

    @GetMapping("/getList")
    public Result getList(@RequestParam("userId") Integer userId) {
        List<Picture> pictures =  pictureService.getList(userId);
        System.out.println(userId);
        System.out.println("getList" + pictures);
        return Result.success(pictures);
    }




}
