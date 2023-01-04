package test

import test.exceptions.CustomException
import grails.transaction.Transactional

@Transactional
class Service1Service {

    def service2Service

    def error() {
        try {
            service2Service.error()
        } catch(CustomException exception){
            throw exception
        }
    }

    def successPropagation() {
        throw new CustomException("Success")
    }
}
