package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.domain.exception.IncompatibleMedicamentException
import com.pada.medmaster.domain.exception.IngredientProhibitedInPatientCountryException
import com.pada.medmaster.domain.exception.PharmacyNotFoundException
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

}
