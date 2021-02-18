package ru.orlovvv

/*
    СОРТИРОВКА СЛИЯНИЕМ

    Сложность в общем случае O(n*log(n))
    Сложность в лучшем случае O(n*log(n))
    Сложность в худшем случае O(n*log(n))

    Суть алгоритма заключается в сравнении данных:
        1) Набор данных делится на две группы примерно одного размера
        2) Пары значений сравниваются, а наименьшее перемещается налево
        3) После сортировки внутри всех пар сравниваются левые значения двух левых пар

    Массив разбивается на примерно равные части, затем каждая из них сортируется. После
    два отсортированных массива сливаются в один. Для этого сравнива/тся два первых
    элемента в каждом из них и ставятся в нужной последовательности. Если один подмассив
    закончился, то оставшиеся элементы второго записываются после последнего элемента первого.

    Как и в случае с быстрой сортировкой используется принцип разделяй и властвуй.

 */

fun mergeSort(list: List<Int>): List<Int> {
    if (list.size <= 1) return list                         // базовый случай

    val mid = list.size / 2                                 // середина
    val left = list.subList(0, mid)                         // левый массив
    val right = list.subList(mid, list.size)                // правый массив

    return merge(mergeSort(left), mergeSort(right))         // вызывается функция слияния
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    var leftIndex = 0                                       // хранение индексов
    var rightIndex = 0
    val newList = mutableListOf<Int>()

    while (leftIndex < left.size && rightIndex < right.size) {
        if (left[leftIndex] <= right[rightIndex]) {
            newList.add(left[leftIndex])
            leftIndex++
        } else {
            newList.add(right[rightIndex])
            rightIndex++
        }
    }

    while (leftIndex < left.size) {
        newList.add(left[leftIndex])
        leftIndex++
    }
    while (rightIndex < right.size) {
        newList.add(right[rightIndex])
        rightIndex++
    }

    return newList

}

fun main() {
    val list = List(100) { it + (0..555).random()}
    println(mergeSort(list))
}