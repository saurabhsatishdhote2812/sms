package com.sms.sms_syllabus_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.sms_syllabus_service.dao.SyllabusRepository;
import com.sms.sms_syllabus_service.exception.SyllabusNotFoundException;
import com.sms.sms_syllabus_service.model.Syllabus;
import com.sms.sms_syllabus_service.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Syllabus> getSyllabusBySubject(String subjectName) throws SyllabusNotFoundException {
        List<String> syllabusJsonList = syllabusRepository.getSyllabusBySubject(subjectName);
        if (syllabusJsonList.isEmpty()) {
            throw new SyllabusNotFoundException("No syllabus found for subject: " + subjectName);
        }

        List<Syllabus> result = new ArrayList<>();

        for (String json : syllabusJsonList) {
            try {
                JsonNode root = objectMapper.readTree(json);
                String grade = root.path("grade").asText();
                String subject = root.path("subject").asText();
                JsonNode syllabusNode = root.path("syllabus");

                List<Unit> units = new ArrayList<>();
                Iterator<String> fieldNames = syllabusNode.fieldNames();
                while (fieldNames.hasNext()) {
                    String unitKey = fieldNames.next();
                    JsonNode unitJson = syllabusNode.get(unitKey);
                    String title = unitJson.path("title").asText();
                    List<String> topics = new ArrayList<>();
                    unitJson.path("topics").forEach(topicNode -> topics.add(topicNode.asText()));
                    units.add(new Unit(title, topics));
                }

                result.add(new Syllabus(grade, subject, units));

            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse syllabus JSON", e);
            }
        }

        return result;
    }
}
