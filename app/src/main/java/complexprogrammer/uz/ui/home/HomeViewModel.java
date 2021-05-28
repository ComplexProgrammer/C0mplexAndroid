package complexprogrammer.uz.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public HomeViewModel() {
        final String Url="http://complexprogrammer.uz:4444/Api/C0mplexApi/GetNews";
        mText = new MutableLiveData<>();
        mText.setValue(Url);
    }

    public LiveData<String> getText() {
        return mText;
    }
}