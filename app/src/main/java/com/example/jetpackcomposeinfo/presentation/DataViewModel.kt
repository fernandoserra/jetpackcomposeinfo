package com.example.jetpackcomposeinfo.presentation

import androidx.lifecycle.*
import com.example.jetpackcomposeinfo.data.model.ReqNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.domain.Repository
import com.example.jetpackcomposeinfo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val repo:Repository):ViewModel() {

    val getTeams = liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTeams())
            } catch (e: Exception) {
                emit(Resource.Failure<List<Team>>(e))
            }
        }
}