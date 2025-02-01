package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.domain.exception.IncompatibleMedicamentException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class BusinessExceptionHandler {

    @ExceptionHandler(IncompatibleMedicamentException::class)
    fun handleIncompatibleMedicament(
        exception: IncompatibleMedicamentException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medicament can't be prescripted due to incomplatiblibty with medicaments already in use",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}
