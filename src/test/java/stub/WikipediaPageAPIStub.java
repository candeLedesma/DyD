package stub;

import model.API.WikipediaPageAPI;
import retrofit2.Call;
import retrofit2.mock.Calls;

public class WikipediaPageAPIStub implements WikipediaPageAPI {

    @Override
    public Call<String> getExtractByPageID(String term) {
        String response = """
            {
                "query": {
                    "pages": {
                        "12345": {
                            "pageid": 12345,
                            "title": "Breaking Bad",
                            "extract": "This is a sample extract for Breaking Bad."
                        }
                    }
                }
            }
            """;

        return Calls.response(response);
    }
}
