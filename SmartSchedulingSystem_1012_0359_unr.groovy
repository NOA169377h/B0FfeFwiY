// 代码生成时间: 2025-10-12 03:59:26
package com.example.schedule

import groovy.transform.builder.Builder

// SmartSchedulingSystem class
// This class is responsible for managing the scheduling logic
class SmartSchedulingSystem {

    // Define the scheduling data structure
    static class Schedule {
        String courseName
        String teacherName
        String roomName
        String startTime
        String endTime
    }

    // List to store schedules
    List<Schedule> schedules = new ArrayList<>()

    // Adds a schedule to the system
    void addSchedule(Schedule schedule) {
        schedules.add(schedule)
    }

    // Finds schedules that do not conflict with each other
    List<Schedule> findNonConflictingSchedules() {
        List<Schedule> nonConflictingSchedules = new ArrayList<>()
        schedules.each {
            if (!isScheduleConflicting(it, nonConflictingSchedules)) {
                nonConflictingSchedules.add(it)
            }
        }
        return nonConflictingSchedules
    }

    // Checks if a schedule conflicts with others
    private boolean isScheduleConflicting(Schedule schedule, List<Schedule> nonConflictingSchedules) {
        return nonConflictingSchedules.any {
            it != schedule && 
            it.courseName == schedule.courseName && 
            it.teacherName == schedule.teacherName &&
            it.roomName == schedule.roomName &&
            (it.startTime <= schedule.endTime && it.endTime >= schedule.startTime)
        }
    }

    // Prints the schedules
    void printSchedules() {
        schedules.each { schedule ->
            println "Course: ${schedule.courseName}, Teacher: ${schedule.teacherName}, Room: ${schedule.roomName}, Start: ${schedule.startTime}, End: ${schedule.endTime}"
        }
    }
}

// Example usage of the SmartSchedulingSystem
class MainClass {
    static void main(String[] args) {
        SmartSchedulingSystem scheduler = new SmartSchedulingSystem()

        // Create schedules
        Schedule schedule1 = new SmartSchedulingSystem.Schedule(courseName: 'Math', teacherName: 'John Doe', roomName: 'Room 101', startTime: '08:00', endTime: '09:00')
        Schedule schedule2 = new SmartSchedulingSystem.Schedule(courseName: 'Science', teacherName: 'Jane Doe', roomName: 'Room 102', startTime: '09:00', endTime: '10:00')
        Schedule schedule3 = new SmartSchedulingSystem.Schedule(courseName: 'Math', teacherName: 'John Doe', roomName: 'Room 101', startTime: '09:00', endTime: '10:00')

        // Add schedules to the system
        scheduler.addSchedule(schedule1)
        scheduler.addSchedule(schedule2)
        scheduler.addSchedule(schedule3)

        // Find and print non-conflicting schedules
        List<Schedule> nonConflicting = scheduler.findNonConflictingSchedules()
        nonConflicting.each {
            println "Non-conflicting schedule: ${it.courseName}, ${it.teacherName}, ${it.roomName}, ${it.startTime}, ${it.endTime}"
        }
    }
}