// 代码生成时间: 2025-10-06 22:31:39
package com.example.hrm

import grails.transaction.Transactional

// 定义Person实体，模拟员工信息
class Person {
    String firstName
    String lastName
    Date birthDate
    String gender
    String role

    static constraints = {
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        birthDate(nullable: false)
        gender(inList: ['male', 'female', 'other'], nullable: false)
        role(blank: false, nullable: false)
    }
}

// 定义PersonService服务，处理与Person相关的业务逻辑
@Transactional
class PersonService {
    // 保存员工信息
    Person savePerson(Person person) {
        if (person.hasErrors()) {
            throw new IllegalArgumentException('Person information is invalid.')
        }
        person.save(flush: true)
    }

    // 获取所有员工信息
    List<Person> getAllPersons() {
        Person.list()
    }

    // 根据ID获取员工信息
    Person getPersonById(Long id) {
        Person.findById(id)
    }

    // 更新员工信息
    Person updatePerson(Person person) {
        if (!person.id) {
            throw new IllegalArgumentException('Person ID is required to update.')
        }
        person.save(flush: true)
    }

    // 删除员工信息
    void deletePerson(Long id) {
        Person person = Person.findById(id)
        if (person) {
            person.delete(flush: true)
        } else {
            throw new IllegalArgumentException('Person not found with ID: ' + id)
        }
    }
}

// 定义PersonController，处理HTTP请求和响应
class PersonController {
    static scaffold = Person
    PersonService personService

    def index() {
        render(view: "index", model: [personList: personService.getAllPersons()])
    }

    def show(Long id) {
        Person person = personService.getPersonById(id)
        if (!person) {
            flash.message = 'Person not found with ID: ' + id
            redirect(action: "index")
            return
        }
        [person: person]
    }

    def create() {
        [person: new Person(params)]
    }

    def save() {
        Person person = new Person(params)
        if (!personService.savePerson(person)) {
            render(view: "create", model: [person: person])
            return
        }
        flash.message = 'Person created successfully'
        redirect(action: "show", id: person.id)
    }

    def edit(Long id) {
        Person person = personService.getPersonById(id)
        if (!person) {
            flash.message = 'Person not found with ID: ' + id
            redirect(action: "index")
            return
        }
        [person: person]
    }

    def update() {
        Person person = personService.getPersonById(params.id)
        if (!person) {
            flash.message = 'Person not found with ID: ' + params.id
            redirect(action: "index")
            return
        }

        if (!personService.updatePerson(person)) {
            render(view: "edit", model: [person: person])
            return
        }
        flash.message = 'Person updated successfully'
        redirect(action: "show", id: person.id)
    }

    def delete(Long id) {
        try {
            personService.deletePerson(id)
            flash.message = 'Person deleted successfully'
            redirect(action: "index")
        } catch (IllegalArgumentException e) {
            flash.message = e.message
            redirect(action: "show", id: id)
        }
    }
}