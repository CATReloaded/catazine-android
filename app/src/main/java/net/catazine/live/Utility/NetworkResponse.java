package net.catazine.live.Utility;

import java.io.IOException;
import java.util.Collection;

public interface NetworkResponse {

    void onFailure(IOException error, int code);

    void onResponse(Collection<?> resultList);

    void onResponse(Object resultObj);

}