package stub;

import model.API.WikipediaSearchAPI;
import retrofit2.Call;
import retrofit2.mock.Calls;

public class WikipediaSearchAPIStub implements WikipediaSearchAPI {

    @Override
    public Call<String> searchForTerm(String term) {
        String response = """
            {
                "query": {
                    "search": [
                        {
                            "title": "Breaking Bad",
                            "pageid": "12345",
                            "snippet": "Snippet for Breaking Bad"
                        },
                        {
                            "title": "Better Call Saul",
                            "pageid": "67890",
                            "snippet": "Snippet for Better Call Saul"
                        }
                    ]
                }
            }
            """;

        return Calls.response(response);
    }
}
