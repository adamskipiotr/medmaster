package com.pada.medmaster.application.ports.out.medicament

import com.pada.medmaster.domain.model.medicament.Ingredient


interface GetIngredientsPort {
    fun get(ids: List<Long>) : List<Ingredient>

}