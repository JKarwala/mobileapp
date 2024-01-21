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

class DetailsViewModel : ViewModel() {

    private val countryRepository = CountriesRepository()

    private val mutableCountriesData = MutableLiveData<UIState<List<Country>>>()
    val immutableCountriesData: LiveData<UIState<List<Country>>> = mutableCountriesData

    fun getData(ccn3: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = countryRepository.getCountriesDetailsResponse(ccn3)
                if (request.isSuccessful) {
                    val countries = request.body()
                    mutableCountriesData.postValue(UIState(data = countries))
                } else {
                    mutableCountriesData.postValue(UIState(error = "${request.code()}"))
                    Log.e("MainViewModel", "Exception: ffb0f33e-5c5c-4af2-94d8-a9b48c6ec61c")
                }


            } catch (e: Exception) {
                mutableCountriesData.postValue(UIState(error = "Exception ${e.message}"))
                Log.e("MainViewModel", "Exception: bd120371-d6cf-4140-93ef-b6335095b04d", e)
            }
        }
    }
}