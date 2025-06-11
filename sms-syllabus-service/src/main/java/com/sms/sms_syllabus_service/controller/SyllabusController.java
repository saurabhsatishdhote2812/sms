package com.sms.sms_syllabus_service.controller;

import com.sms.sms_syllabus_service.exception.SyllabusNotFoundException;
import com.sms.sms_syllabus_service.model.Syllabus;
import com.sms.sms_syllabus_service.service.SyllabusService;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling syllabus-related requests.
 */
@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*")
@Validated
public class SyllabusController {

    private static final Logger logger = LoggerFactory.getLogger(SyllabusController.class);

    private final SyllabusService syllabusService;

    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    /**
     * Retrieves the syllabus for a given subject.
     *
     * @param subjectName the name of the subject
     * @return list of syllabus items
     */
    @GetMapping("/getSyllabus/{subjectName}")
    public ResponseEntity<List<Syllabus>> getSyllabusBySubject(
            @PathVariable @NotBlank(message = "Subject name must not be blank") String subjectName)
            throws SyllabusNotFoundException {
        logger.info("Fetching syllabus for subject: {}", subjectName);
        List<Syllabus> result = syllabusService.getSyllabusBySubject(subjectName);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(SyllabusNotFoundException.class)
    public ResponseEntity<String> handleSyllabusNotFound(SyllabusNotFoundException ex) {
        logger.warn("Syllabus not found: {}", ex.getMessage());
        return ResponseEntity.status(404).body("Syllabus not found: " + ex.getMessage());
    }
}