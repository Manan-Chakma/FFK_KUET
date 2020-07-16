package com.chakmakuet.kuetfantasy.ui.group.model

class GroupData {
    companion object {
        private var tOne: List<Result> = emptyList()
        private var tTwo: List<Result> = emptyList()
        private var tThree: List<Result> = emptyList()
        private var tFour: List<Result> = emptyList()
        private var tFive: List<Result> = emptyList()
        private var tSix: List<Result> = emptyList()
        fun setGroupData(data: List<Result>, i: Int) {
            when (i) {
                1 -> {
                    tOne = data
                }
                2 -> {
                    tTwo = data
                }
                3 -> {
                    tThree = data
                }
                4 -> {
                    tFour = data
                }
                5 -> {
                    tFive = data
                }
                else -> {
                    tSix = data
                }
            }
        }

        fun getGroupData(id: Int): List<Result> {
            return when (id) {
                1 -> {
                    tOne
                }
                2 -> {
                    tTwo
                }
                3 -> {
                    tThree
                }
                4 -> {
                    tFour
                }
                5 -> {
                    tFive
                }
                else -> {
                    tSix
                }
            }
        }
    }
}