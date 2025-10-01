// 代码生成时间: 2025-10-01 22:44:39
package com.rehab.training

import grails.transaction.Transactional

@Transactional
class RehabilitationTrainingService {

    /**
     * 存储康复训练计划
     */
    def rehabilitationPlans = []

    /**
     * 添加一个新的康复训练计划
     * @param planId 计划ID
     * @param details 计划详细信息
     * @return 成功与否的状态
     */
    def addRehabilitationPlan(String planId, Map details) {
        try {
            rehabilitationPlans << [id: planId, details: details]
            return [success: true, message: "Rehabilitation plan added successfully."]
        } catch (Exception e) {
            return [success: false, message: "Failed to add rehabilitation plan: ${e.message}"]
        }
    }

    /**
     * 更新康复训练计划
     * @param planId 计划ID
     * @param newDetails 新的计划详细信息
     * @return 成功与否的状态
     */
    def updateRehabilitationPlan(String planId, Map newDetails) {
        try {
            def plan = rehabilitationPlans.find { it.id == planId }
            if (!plan) {
                return [success: false, message: "Rehabilitation plan not found."]
            }
            plan.details = newDetails
            return [success: true, message: "Rehabilitation plan updated successfully."]
        } catch (Exception e) {
            return [success: false, message: "Failed to update rehabilitation plan: ${e.message}"]
        }
    }

    /**
     * 删除康复训练计划
     * @param planId 计划ID
     * @return 成功与否的状态
     */
    def deleteRehabilitationPlan(String planId) {
        try {
            rehabilitationPlans.removeAll { it.id == planId }
            return [success: true, message: "Rehabilitation plan deleted successfully."]
        } catch (Exception e) {
            return [success: false, message: "Failed to delete rehabilitation plan: ${e.message}"]
        }
    }

    /**
     * 获取所有康复训练计划
     * @return 所有计划的列表
     */
    def getAllRehabilitationPlans() {
        return rehabilitationPlans
    }

    /**
     * 获取单个康复训练计划
     * @param planId 计划ID
     * @return 单个计划的详细信息
     */
    def getRehabilitationPlan(String planId) {
        def plan = rehabilitationPlans.find { it.id == planId }
        if (!plan) {
            return [success: false, message: "Rehabilitation plan not found."]
        }
        return [success: true, plan: plan]
    }
}

// Usage example
// rehabilitationTrainingService.addRehabilitationPlan("Plan1", [exercise: "Walking", duration: "30 minutes"])
// rehabilitationTrainingService.updateRehabilitationPlan("Plan1", [exercise: "Swimming", duration: "45 minutes"])
// rehabilitationTrainingService.deleteRehabilitationPlan("Plan1")
// rehabilitationTrainingService.getAllRehabilitationPlans()
// rehabilitationTrainingService.getRehabilitationPlan("Plan1")