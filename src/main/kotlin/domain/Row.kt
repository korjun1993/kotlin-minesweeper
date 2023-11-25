package domain

import java.util.SortedSet

class Row(private val columns: SortedSet<Coordinate>) : SortedSet<Coordinate> by columns {
    init {
        require(columns.size >= MIN_SIZE) { "행에는 최소 ${MIN_SIZE}개 이상의 좌표가 포함되어야 합니다." }
        require(columns.first().x == START_COLUMN) { "행의 첫번째 좌표의 x 값은 ${START_COLUMN}이어야 합니다." }
        require(columns.all { columns.first().y == it.y }) { "같은 행에 속한 좌표들의 y 값은 모두 동일해야 합니다." }
        require(checkColumDiffIsOne()) { "좌표들의 x 값은 1씩 커져야합니다" }
    }

    constructor(vararg coordinates: Coordinate) : this(coordinates.toSortedSet())

    companion object {
        private const val MIN_SIZE = 1
        private const val START_COLUMN = 0
    }

    private fun checkColumDiffIsOne(): Boolean {
        return columns.map { it.x } == (columns.first().x..columns.last().x).toList()
    }
}
