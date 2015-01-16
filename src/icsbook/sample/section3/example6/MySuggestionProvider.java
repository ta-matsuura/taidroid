package icsbook.sample.section3.example6;

import android.content.SearchRecentSuggestionsProvider;

public class MySuggestionProvider extends SearchRecentSuggestionsProvider {

    public MySuggestionProvider(){  
        setupSuggestions("icsbook.sample", MySuggestionProvider.DATABASE_MODE_QUERIES);  
    } 
}
