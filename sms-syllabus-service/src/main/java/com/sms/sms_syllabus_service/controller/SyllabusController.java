package com.sms.sms_syllabus_service.controller;


import com.sms.sms_syllabus_service.exception.SyllabusNotFoundException;
import com.sms.sms_syllabus_service.model.Syllabus;
import com.sms.sms_syllabus_service.service.SyllabusService;
import com.sms.sms_syllabus_service.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*")
public class SyllabusController {

    @Autowired
    SyllabusService syllabusService;

    @GetMapping("/getSyllabus/{subjectName}")
    public ResponseEntity<List<Syllabus>> getSyllabusBySubject(@PathVariable String subjectName) throws SyllabusNotFoundException {
        List<Syllabus> result = syllabusService.getSyllabusBySubject(subjectName);
        return ResponseEntity.ok(result);
    }
}
