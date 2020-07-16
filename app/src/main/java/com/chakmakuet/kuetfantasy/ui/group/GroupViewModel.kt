package com.chakmakuet.kuetfantasy.ui.group

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chakmakuet.kuetfantasy.constants.Constants.Companion.MY_TAG
import com.chakmakuet.kuetfantasy.ui.group.model.GroupData
import com.chakmakuet.kuetfantasy.ui.group.model.ResultResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.chakmakuet.kuetfantasy.ui.group.model.Result


class GroupViewModel(repository: GroupRepo) : ViewModel() {
    private var repo: GroupRepo = repository

    private val _leagueData = MutableLiveData<ResultResponse>()
    private val _err = MutableLiveData<String>()

    // val league: LiveData<League> = _leagueData
    val err: LiveData<String> = _err

    private val _tableOne = MutableLiveData<List<Result>>()
    val tableOne: LiveData<List<Result>> = _tableOne
    private var tOne: List<Result> = emptyList()
    private var tTwo: List<Result> = emptyList()
    private var tThree: List<Result> = emptyList()
    private var tFour: List<Result> = emptyList()
    private var tFive: List<Result> = emptyList()
    private var tSix: List<Result> = emptyList()
    private var finalList: MutableList<Result> = mutableListOf()


    private var hasFirstTable = false
    private var hasSecTable = false
    private var hasThirdTable = false
    private var hasFourthTable = false
    private var hasFifthTable = false
    private var hasSixthTable = false
    private var timeOut = false


    var finalData = MutableLiveData<MutableList<Result>>()

    var dataAvailable = MutableLiveData<Boolean>()

    private var _allPlayerData = MutableLiveData<List<Result>>()

    init {
        setToFalse()

        dataAvailable.value = false
        if (finalList.isNotEmpty()) {
            finalList.clear()
        }
        viewModelScope.launch {
            fetchData()
        }
    }

    private fun setToFalse() {
        hasFirstTable = false
        hasSecTable = false
        hasThirdTable = false
        hasFourthTable = false
        hasFifthTable = false
        hasSixthTable = false
        timeOut = false
    }

    private suspend fun fetchData() {
        coroutineScope {
            val job = launch {

                val jobOne = launch {
                    getLeagueData("1767663", 1)
                    delay(500)
                }
                jobOne.join()
                //Log.d(MY_TAG, "1st League done")
                val jobTwo = launch {
                    getLeagueData("1768575", 2)
                    delay(500)
                }
                jobTwo.join()
                //Log.d(MY_TAG, "2nd League done")
                val jobThree = launch {
                    getLeagueData("1767808", 3)
                    delay(500)
                }
                jobThree.join()
                //Log.d(MY_TAG, "3rd League done")
                val jobFour = launch {
                    getLeagueData("1767931", 4)
                    delay(500)
                }
                jobFour.join()
                //Log.d(MY_TAG, "4th League done")
                val jobFive = launch {
                    getLeagueData("1768001", 5)
                    delay(500)
                }
                jobFive.join()
                //Log.d(MY_TAG, "5th League done")
                val jobSix = launch {
                    getLeagueData("1768131", 6)
                    delay(500)
                }
                jobSix.join()
                //Log.d(MY_TAG, "6th League done")

            }
            delay(60000)
            //Log.d(MY_TAG, "AFTER DELAY")
            if (job.isActive) {
                timeOut = true
                //Log.d(MY_TAG, "Cancelling job")
                job.cancel()
            }

        }
    }

    private suspend fun getLeagueData(leagueNo: String, id: Int) = withContext(IO) {

        repo.getLeagueData(
                leagueNo,
                { leagueData ->

                    when (id) {
                        1 -> {
                            tOne = leagueData.resultList
                            finalList.addAll(tOne)
                            hasFirstTable = true
                            insertAllData()
                            GroupData.setGroupData(tOne, 1)
                            //Log.d("MY_TAG", "Got First Table")
                        }
                        2 -> {
                            tTwo = leagueData.resultList
                            finalList.addAll(tTwo)
                            hasSecTable = true
                            insertAllData()
                            GroupData.setGroupData(tTwo, 2)
                            //Log.d("MY_TAG", "Got Sec Table")
                        }
                        3 -> {
                            tThree = leagueData.resultList
                            finalList.addAll(tThree)
                            hasThirdTable = true
                            insertAllData()
                            GroupData.setGroupData(tThree, 3)
                            //Log.d("MY_TAG", "Got Third Table")
                        }
                        4 -> {
                            tFour = leagueData.resultList
                            finalList.addAll(tFour)
                            hasFourthTable = true
                            insertAllData()
                            GroupData.setGroupData(tFour, 4)
                            //Log.d("MY_TAG", "Got Fourth Table")
                        }
                        5 -> {
                            tFive = leagueData.resultList
                            finalList.addAll(tFive)
                            hasFifthTable = true
                            insertAllData()
                            GroupData.setGroupData(tFive, 5)
                            //Log.d("MY_TAG", "Got Fifth Table")
                        }
                        6 -> {
                            tSix = leagueData.resultList
                            finalList.addAll(tSix)
                            hasSixthTable = true
                            //Log.d("MY_TAG", "Got Sixth Table")
                            dataAvailable.value = true
                            insertAllData()
                            GroupData.setGroupData(tSix, 6)
                        }
                        else -> {
                            dataAvailable.value = false
                        }
                    }
                },
                { err ->
                    Log.d("MY_TAG", err)
                }

        )
    }

    private fun insertAllData() {
        if (isPermitted()) {
            finalData.value = finalList
        }
    }

    private fun isPermitted(): Boolean {
        return (hasFirstTable && hasSecTable && hasThirdTable && hasFourthTable && hasFifthTable && hasSixthTable && !timeOut)
    }

    fun getTable(id: String): List<Result> {
        when (id) {
            "1767663" -> {
                return tOne
            }
            "1768575" -> {
                return tTwo
            }
            "1767808" -> {
                return tThree
            }
            "1767931" -> {
                return tFour
            }
            "1768001" -> {
                return tFive
            }
            else -> {
                return tSix
                //1768131
            }
        }
    }

    fun isTablePresent(id: String): Boolean {
        when (id) {
            "1767663" -> {
                return hasFifthTable
            }
            "1768575" -> {
                return hasSecTable
            }
            "1767808" -> {
                return hasThirdTable
            }
            "1767931" -> {
                return hasFourthTable
            }
            "1768001" -> {
                return hasFifthTable
            }
            else -> {
                return hasSecTable
                //1768131
            }
        }

    }
}