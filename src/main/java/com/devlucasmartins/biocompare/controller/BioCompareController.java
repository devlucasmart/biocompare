package com.devlucasmartins.biocompare.controller;

@RestController
@RequestMapping("/blast")
public class BioCompareController {
    @Autowired
    private BlastService blastService;

    @PostMapping("/upload")
    public String uploadGenome(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        return blastService.runBlastFromFile(file);
    }
}
