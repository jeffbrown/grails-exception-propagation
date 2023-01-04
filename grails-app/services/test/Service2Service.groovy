package test

import test.exceptions.CustomException

class Service2Service {

    def error() {
        throw new CustomException("Error")
    }

}