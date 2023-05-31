package mapper;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import custumer.Customers;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapperMock {

     public static List<Customers> getCustomers() throws IOException {
        InputStream inputStream = Resources.getResource("dumydata/data.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type type = new TypeToken<ArrayList<Customers>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

}
