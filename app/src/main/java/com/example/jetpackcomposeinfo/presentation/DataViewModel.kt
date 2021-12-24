package com.example.jetpackcomposeinfo.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.domain.Repository
import com.example.jetpackcomposeinfo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Author: Fernando Serra
 */

class DataViewModel @ViewModelInject constructor(private val repo:Repository):ViewModel() {

    val getTeams = liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTeams())
            } catch (e: Exception) {
                emit(Resource.Failure<List<Team>>(e))
            }
        }

   private val _games = MutableLiveData<Resource<ReqGamesNBA>>()
    fun getGamesTeam(seasons: Int, id: Int): LiveData<Resource<ReqGamesNBA>> {
        viewModelScope.launch(Dispatchers.IO) {
            val game = repo.getGamesTeam(seasons,id)
            _games.postValue(game)
        }
        return _games
    }

    fun insertTeamRoom(teamLocal:TeamLocal){
        viewModelScope.launch {
            repo.insertTeamRoom(teamLocal)
        }
    }

    private val _favorites = MutableLiveData<Resource<List<Team>>>()
    fun getTeamFavorites(): LiveData<Resource<List<Team>>> {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repo.getTeamFavorites()
            _favorites.postValue(favorites)
        }
        return _favorites
    }

}