package com.shc.shc_server.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.shc.shc_server.model.Activity;
import com.shc.shc_server.model.Student;
import com.shc.shc_server.repository.ActivityRepository;
import com.shc.shc_server.repository.StudentRepository;

@Service
public class ActivityService {

    @Autowired
    @Lazy
    private ActivityRepository activityRepository;

    @Autowired
    private StudentRepository studentRepository;

    // get all activities
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // get activity by id
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    // get activitys by name coordinator
    public List<Activity> getActivitiesByNameCoordinator(String name) {
        return activityRepository.findByCoordinatorContainingIgnoreCase(name);
    }

    // save new activity
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    // update existing activity
    public Activity updateActivity(Long id, Activity updatedActivity) {
        Activity existingActivity = getActivityById(id);

        existingActivity.setName(updatedActivity.getName());
        existingActivity.setStartTime(updatedActivity.getStartTime());
        existingActivity.setEndTime(updatedActivity.getEndTime());
        existingActivity.setMultiplier(updatedActivity.getMultiplier());
        existingActivity.setScholarshipHoursOffered(updatedActivity.getScholarshipHoursOffered());
        existingActivity.setCoordinator(updatedActivity.getCoordinator());
        existingActivity.setLocation(updatedActivity.getLocation());
        existingActivity.setMaxCapacity(updatedActivity.getMaxCapacity());
        existingActivity.setDepartment(updatedActivity.getDepartment());
        existingActivity.setDescription(updatedActivity.getDescription());
        existingActivity.setDate(updatedActivity.getDate());

        return activityRepository.save(existingActivity);
    }

    // delete activity by id
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new RuntimeException("Activity not found for id: " + id);
        }
        activityRepository.deleteById(id);
    }


    // generate qr code image
    public BufferedImage generateQRCodeImage(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200, hintMap);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    // get qr code as bytes
    public byte[] getQRCodeImageBytes(String text) throws Exception {
        BufferedImage qrImage = generateQRCodeImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        return baos.toByteArray();
    }

    // get students by activity id
    @Transactional(readOnly = true)
    public List<Student> getStudentsByActivityId(Long activityId) {
        Activity activity = getActivityById(activityId);
        Hibernate.initialize(activity.getStudents());
        return activity.getStudents();
    }

    // add student to activity
    @Transactional
    public Activity addStudent(Long activityId, Long studentId) {
        Activity activity = getActivityById(activityId);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (activity.getStudents().size() >= activity.getMaxCapacity()) {
            throw new RuntimeException("Maximum capacity reached for activity: " + activityId);
        }

        activity.getStudents().add(student);
        student.setActivity(activity);

        activityRepository.save(activity);
        studentRepository.save(student);

        return activity;
    }

    // remove student from activity
    @Transactional
    public Activity removeStudent(Long activityId, Long studentId) {
        Activity activity = getActivityById(activityId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found for id: " + studentId));

        activity.getStudents().remove(student);
        student.setActivity(null);

        activityRepository.save(activity);
        studentRepository.save(student);

        return activity;
    }

    // disable joining to an activity
    public Activity disableJoining(Long id) {
        Activity activity = getActivityById(id);
        activity.setMaxCapacity(0);
        return activityRepository.save(activity);
    }
}