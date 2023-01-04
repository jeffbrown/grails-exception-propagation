package test

import test.exceptions.CustomException

class Service2Service {

    def error() throws CustomException {
        throw new CustomException("Error")
    }

}