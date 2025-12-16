package com.example.universityschedule.data.repository

import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.repository.DictRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DictRepositoryImpl @Inject constructor(
    private val api: DictApiService
) : DictRepository {


}
