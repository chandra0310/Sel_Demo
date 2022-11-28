package Library;

import API.CarsDetails;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static Utility.SelWebDriver.prop;
import static io.restassured.RestAssured.given;

public class HelperService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelperService.class);

    public static Response getJsonResponse(String baseURI, String endpoint, String subCategoryItem, String file_format) {
        return given().
                pathParam("subCategoryItem", subCategoryItem).pathParam("file_format", file_format).
                when().get(baseURI + endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    public static void writeToResultsFile(String text){
        String date = DateTimeFormat.forPattern("ddMMYY").print(LocalDateTime.now());
        createResultsFile(date);
        File file = CarsDetails.getFile();
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + text);
            LOGGER.info(text);
            writer.close();
        }catch (IOException | NullPointerException e){
            LOGGER.info("file " +file + " not found");
        }
    }

    private static void createResultsFile(String date){
        String fileName=prop.getProperty("resultFile") + date;
        final String baseDir = new File("").getAbsolutePath();
        final String filePath = baseDir + "\\src\\test\\Results\\" + fileName;
        CarsDetails.setFile(new File(filePath));
    }


}
