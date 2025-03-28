package com.devlucasmartins.biocompare.controller;

import com.devlucasmartins.biocompare.service.BioCompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/blast")
public class BioCompareController {
    @Autowired
    private BioCompareService compareService;

    @PostMapping("/upload")
    public String uploadGenome(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        return compareService.runBlastFromFile(file);
    }
}
