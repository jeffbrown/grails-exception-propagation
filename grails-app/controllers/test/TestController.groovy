package test

import test.exceptions.BusinessException
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

class TestController {

    def service1Service

    @Transactional
    @Secured('permitAll')
    def test1() {
        try {
            service1Service.error()
            render "No Exception  Was Thrown"
        } catch(BusinessException be) {
            render "CustomException was thrown, and caught by controller"
        }
    }

    @Transactional
    @Secured('permitAll')
    def test2() {
        try {
            service1Service.successPropagation()
            render "No Exception  Was Thrown"
        } catch(BusinessException be) {
            render "CustomException was thrown, and caught by controller"
        }
    }
}
