package API;

import io.restassured.response.Response;
import java.io.File;
import java.util.List;

public class CarsDetails {

    public CarsDetails() {
    }

    private Response apiResponse;
    private static List<String> SubcategoryList;
    private static File file;

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        CarsDetails.file = file;
    }

    public Response getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(Response apiResponse) {
        this.apiResponse = apiResponse;
    }

    public static List<String> getSubcategoryList() {
        return SubcategoryList;
    }

    public void setSubcategoryList(List<String> subcategoryList) {
        CarsDetails.SubcategoryList = subcategoryList;
    }

    public void resetAll(){
        setApiResponse(null);
        setSubcategoryList(null);
        setFile(null);
    }

}
