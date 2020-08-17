object Day11 {
    private val input = IsolatedArea(
        Floor(
            true,
            InterestingObject("thulium", ObjectType.GENERATOR),
            InterestingObject("thulium", ObjectType.MICROCHIP),
            InterestingObject("plutonium", ObjectType.GENERATOR),
            InterestingObject("strontium", ObjectType.GENERATOR)
        ),
        Floor(
            false,
            InterestingObject("plutonium", ObjectType.MICROCHIP),
            InterestingObject("strontium", ObjectType.MICROCHIP)
        ),
        Floor(
            false,
            InterestingObject("promethium", ObjectType.GENERATOR),
            InterestingObject("promethium", ObjectType.MICROCHIP),
            InterestingObject("ruthenium", ObjectType.GENERATOR),
            InterestingObject("ruthenium", ObjectType.MICROCHIP)
        ),
        Floor(false)
    )

    fun part1(): Int {
        var steps = 0
        var states = listOf(input)
        var seenStates = emptyList<IsolatedArea>()
        while (states.none { it.finished }) {
            states = states.asSequence().flatMap { nextSteps(it) }.filterNot { it in seenStates }.distinct().toList()
            seenStates = (seenStates + states).asSequence().distinct().toList()
            require(states.isNotEmpty()) { "No way to get to the final state" }
            steps++
        }
        return steps
    }

    private fun nextSteps(state: IsolatedArea): Sequence<IsolatedArea> = sequence {
        val curFloorIdx = state.floors.indexOfFirst { it.elevator }
        when (curFloorIdx) {
            state.floors.indices.first -> listOf(state.floors.indices.first + 1)
            state.floors.indices.last -> listOf(state.floors.indices.last - 1)
            else -> listOf(curFloorIdx - 1, curFloorIdx + 1)
        }.forEach { destFloorIdx ->
            state.floors[curFloorIdx].objects.forEach { obj ->
                val newState = state.clone()
                newState.floors[curFloorIdx].objects.remove(obj)
                newState.floors[curFloorIdx].elevator = false
                newState.floors[destFloorIdx].objects.add(obj)
                newState.floors[destFloorIdx].elevator = true
                if (newState.valid) {
                    yield(newState)
                }
            }
            state.floors[curFloorIdx].objects.forEach { obj1 ->
                state.floors[curFloorIdx].objects.filterNot { it == obj1 }.forEach { obj2 ->
                    val newState = state.clone()
                    newState.floors[curFloorIdx].objects.removeAll(listOf(obj1, obj2))
                    newState.floors[curFloorIdx].elevator = false
                    newState.floors[destFloorIdx].objects.addAll(listOf(obj1, obj2))
                    newState.floors[destFloorIdx].elevator = true
                    if (newState.valid) {
                        yield(newState)
                    }
                }
            }
        }
    }

    private enum class ObjectType {
        GENERATOR, MICROCHIP,
    }

    private data class InterestingObject(val element: String, val type: ObjectType)

    private class Floor(var elevator: Boolean, vararg initialObjects: InterestingObject) {
        val objects = initialObjects.toMutableList()

        init {
            require(valid) { "Invalid floor configuration" }
        }

        val valid: Boolean
            get() = !(objects.any { it.type == ObjectType.GENERATOR } && objects.any { microchip ->
                microchip.type == ObjectType.MICROCHIP && objects.none { generator ->
                    generator.type == ObjectType.GENERATOR && generator.element == microchip.element
                }
            })

        override fun equals(other: Any?) = other is Floor && other.objects == objects
        override fun hashCode() = objects.hashCode()

        fun clone() = Floor(elevator, *objects.toTypedArray())
    }

    private class IsolatedArea(vararg initialFloors: Floor) {
        val floors = initialFloors.toMutableList().apply {
            while (size < 4) {
                add(Floor(elevator = false))
            }
            require(size == 4) { "Invalid floor configuration" }
        }

        init {
            require(valid) { "Invalid isolated area layout" }
        }

        val valid: Boolean
            get() = floors.all { it.valid } && floors.count { it.elevator } == 1

        val finished: Boolean
            get() = floors.dropLast(1).all { it.objects.isEmpty() && !it.elevator }

        override fun equals(other: Any?) = other is IsolatedArea && other.floors == floors
        override fun hashCode() = floors.hashCode()

        fun clone() = IsolatedArea(*floors.map { it.clone() }.toTypedArray())
    }
}