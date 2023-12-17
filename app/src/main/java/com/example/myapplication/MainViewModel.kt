import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UIState
import com.example.myapplication.repository.CountriesRepository
import com.example.myapplication.repository.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val countryRepository = CountriesRepository()

    private val mutableCountriesData = MutableLiveData<UIState<List<Country>>>()
    val immutableCountriesData: LiveData<UIState<List<Country>>> = mutableCountriesData

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = countryRepository.getCountriesResponse()
                if (request.isSuccessful) {
                    val countries = request.body()
                    mutableCountriesData.postValue(UIState(data = countries))
                } else {
                    mutableCountriesData.postValue(UIState(error = "${request.code()}"))
                    Log.e("MainViewModel", "Exception: 02395623-d01f-45b7-a613-5609d7e5f509")
                }


            } catch (e: Exception) {
                mutableCountriesData.postValue(UIState(error = "Exception ${e.message}"))
                Log.e("MainViewModel", "Exception: 5ce3daf4-bee0-4f72-b12f-c483147991ad", e)
            }
        }
    }
}