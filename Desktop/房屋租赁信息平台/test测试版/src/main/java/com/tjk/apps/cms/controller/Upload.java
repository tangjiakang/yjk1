package com.tjk.apps.cms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tjk.apps.cms.bean.Basehouse;
import com.tjk.apps.cms.comment.Message;
import com.tjk.apps.cms.comment.MessageUtil;
import com.tjk.apps.cms.comment.ReadExcle;
import com.tjk.apps.cms.dao.BasehouseMapper;
@RestController
public class Upload {
	@Autowired
	BasehouseMapper bh;
	
	     @PostMapping(value="upload", produces = "application/json; charset=utf-8")
        public String upload(@RequestParam MultipartFile file) {
        	 if (file.isEmpty()) {
                 return "0";
             }
             Date d = new Date();
             long time = d.getTime();
             String fileName = time+file.getOriginalFilename();
             System.out.println(fileName);
             String filePath = "D:/vue/image/";
             String url = filePath + fileName;
             File dest = new File(url);
             try {
                 file.transferTo(dest);
              
                 return url;
             } catch (IOException e) {
 
             
             return "0";
        	
        }
      }
	    @PostMapping(value="uploadxls", produces = "application/json; charset=utf-8")
	    public Message uploadxls(@RequestParam MultipartFile file) {
	    	if (file.isEmpty()) {
                return MessageUtil.error("文件为空");
            }
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String filePath = "D:/tablesave/";
            String url = filePath + fileName;
            File dest = new File(url);
            try {
				file.transferTo(dest);
				List<Basehouse> readExcel = ReadExcle.readExcel(url);
				for (Basehouse basehouse : readExcel) {
					bh.insert(basehouse);
				}
				return MessageUtil.success("成功录入该表信息");
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
			System.out.println(e.getMessage());
			}
	    	return null;
	    }
	     

}
