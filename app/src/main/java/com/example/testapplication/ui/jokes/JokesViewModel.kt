package com.example.testapplication.ui.jokes

import android.util.Log
import androidx.lifecycle.*
import com.example.testapplication.data.RetrofitRequestRepository
import com.example.testapplication.data.retrofitrequest.RequestModel
import com.example.testapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import java.util.concurrent.Flow
import javax.inject.Inject

class JokesViewModel(private val retrofitRepository: RetrofitRequestRepository) : ViewModel() {
    val actualData = MutableLiveData<List<RequestModel>>(emptyList())
    val currentNumber = MutableLiveData(0)

    fun setCurrentNumber(int: Int) {
        Log.i("setCurrentNumber","setCurrentNumber")
        currentNumber.value = int
    }
    fun getResponse() = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = retrofitRepository.getUsers()))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }
@Suppress("UNCHECKED_CAST")
class JokesViewModelFactory @Inject constructor(
    private val retrofitRepository: RetrofitRequestRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokesViewModel(retrofitRepository) as T
    }
}