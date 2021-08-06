package com.dab1e.retrofitrx.model

import com.dab1e.retrofitrx.Dog
import com.dab1e.retrofitrx.Tivi
import com.dab1e.retrofitrx.User

data class DataZip(val user: List<User>,val tv:List<Tivi>, val dog: Dog) {
}