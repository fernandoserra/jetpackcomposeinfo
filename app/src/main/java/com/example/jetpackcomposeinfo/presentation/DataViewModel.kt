package com.example.jetpackcomposeinfo.presentation

import androidx.lifecycle.*
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal
import com.example.jetpackcomposeinfo.data.model.ReqGamesNBA
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

    private val _games = MutableLiveData<Resource<ReqGamesNBA>>()
    fun getGamesTeam(seasons: Int, id: Int): LiveData<Resource<ReqGamesNBA>> {
        viewModelScope.launch(Dispatchers.IO) {
            val game = repo.getGamesTeam(seasons,id)
            _games.postValue(game)
        }
        return _games
    }

    fun inserTeamRoom(teamLocal:TeamLocal){
        viewModelScope.launch {
            repo.inserTeamRoom(teamLocal)
        }
    }

    private val _favorites = MutableLiveData<Resource<List<TeamLocal>>>()
    fun getTeamFavoritos(): LiveData<Resource<List<TeamLocal>>> {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repo.getTeamFavoritos()
            _favorites.postValue(favorites)
        }
        return _favorites
    }

}