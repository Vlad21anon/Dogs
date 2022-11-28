package com.ukadovlad21.dogapplication

import android.view.View
import android.widget.TextView
import com.ukadovlad21.dogapplication.model.Dog


const val BASEURL: String = "https://dog.ceo/"

object DogData {
    var dogList: List<Dog> = listOf()
    var dogAllNamesList: List<String>? = null
}

object ApplicationData {
    var hintCount = 30 //Кол-во подсказок при первом запуске
    var recordLVL = 0 //рекордный уровень до которого доходил пользователь
    var isVolumeOn:Boolean = true // Звук вкл/выкл
    var themeMode:String = "dark" // тема ночь/день
    var internetEconomy = false //экономная загрузка Да/нет


}





fun TextView.setCorrectText() {//Если строка слишком длинная, то уменьшаю размер шрифта
    if(ApplicationData.hintCount.toString().length==5){
        this.textSize = 16f
    }
    this.text = ApplicationData.hintCount.toString()

}

fun View.volume(){
    this.isSoundEffectsEnabled = ApplicationData.isVolumeOn
}

