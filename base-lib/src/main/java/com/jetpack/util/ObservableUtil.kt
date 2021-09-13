package com.jetpack.util


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.util.*



class ObservableUtil {
  fun  addObserver(observable: Observable,observer: Observer,lifecycleOwner: LifecycleOwner){
      observable.addObserver(observer)

      if (lifecycleOwner.lifecycle.currentState == Lifecycle.Event.ON_DESTROY){
          observable.deleteObserver(observer)
      }
  }
}