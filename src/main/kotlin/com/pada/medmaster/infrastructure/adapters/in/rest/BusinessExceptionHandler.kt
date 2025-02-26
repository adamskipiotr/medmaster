package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.domain.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
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
            message = exception.message ?: "Medicament can't be prescribed due to incompatibility with medicaments already in use",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PharmacyNotFoundException::class)
    fun handlePharmacyNotFound(
        exception: PharmacyNotFoundException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medicament can't be prescribed - no pharmacy in patient's voivodeship has it in stock",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IngredientProhibitedInPatientCountryException::class)
    fun handleIngredientProhibitedInPatientCountry(
        exception: IngredientProhibitedInPatientCountryException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medicament can't be prescribed - at least one of it's ingredients is prohibited in patient's country",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MedicalProcedureScheduledOnExpectedRecoveryTimeException::class)
    fun handleMedicalProcedureScheduledOnExpectedRecoveryTimeException(
        exception: MedicalProcedureScheduledOnExpectedRecoveryTimeException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medical procedure can't be scheduled if patient doesn't recover from it before next procedure",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(MedicalProcedureScheduledOnRecoveryTimeException::class)
    fun handleMedicalProcedureScheduledOnRecoveryTimeException(
        exception: MedicalProcedureScheduledOnRecoveryTimeException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medical procedure can't be scheduled on recovery time of other procedure",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MedicalProcedureScheduledOnTheSameDayException::class)
    fun handleMedicalProcedureScheduledOnTheSameDayException(
        exception: MedicalProcedureScheduledOnTheSameDayException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Medical Procedure can't be scheduled on day of other procedure date",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        exception: IllegalArgumentException,
        request: WebRequest
    ) : ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message ?: "Invalid input data - illegal argument value",
            path = (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
