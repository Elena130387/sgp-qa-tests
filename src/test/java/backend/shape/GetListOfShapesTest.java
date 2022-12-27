package backend.shape;

import api.client.CalcManagement;
import api.dto.shape.Shape;
import api.helper.JsonHelper;
import backend.interfaces.GetObjectsListParamLimitTest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.jupiter.api.Assertions.*;
import static util.Constants.CALC_MAN_SHAPES_EP;

public class GetListOfShapesTest implements GetObjectsListParamLimitTest {
    private final int DEFAULT_LIMIT_VALUE = 1000;
    @Test
    void getLimitNumberOfShapes() {
        int limit = 3;
        ValidatableResponse responseShapesList = CalcManagement.getLimitNumberOfShapes(limit);
        List<Shape> shapesList = asList(JsonHelper.getObjectsListFromJson(responseShapesList, Shape[].class));
        assertEquals(limit,
                shapesList.size(), "Количество областей в ответе не совпадает с заданным");
    }

    @Test
    void getListOfAllShapes() {
        ValidatableResponse responseShapesList = CalcManagement.getListOfAllShapes();
        responseShapesList.statusCode(200);
        List<Shape> shapesList = asList(JsonHelper.getObjectsListFromJson(responseShapesList, Shape[].class));
        assertAll(
                () -> assertTrue(shapesList.size() > 0, "Количество областей в ответе равно " + shapesList.size()),
                () -> assertTrue(shapesList.size() <= DEFAULT_LIMIT_VALUE, "Количество областей в ответе превышает " + DEFAULT_LIMIT_VALUE
                        + " и равно " + shapesList.size())
        );
    }

    @Test
    void checkDescSortByIdInShapesList() {
        List<Integer> actualListShapeIds = new ArrayList<>();
        ValidatableResponse responseShapesList = CalcManagement.getListOfAllShapes();
        List<Shape> shapesList = asList(JsonHelper.getObjectsListFromJson(responseShapesList, Shape[].class));
        shapesList.forEach(shape -> {
            actualListShapeIds.add(shape.getId());
        });
        List<Integer> sortDescListShapeIds = new ArrayList<>(actualListShapeIds);

        // sort the list in descending order
        Collections.sort(sortDescListShapeIds, Collections.reverseOrder());

        System.out.println(actualListShapeIds);
        System.out.println(sortDescListShapeIds);

        assertEquals(sortDescListShapeIds,
                actualListShapeIds, "Сортировка областей не соответствует ожидаемой");
    }

    @Test
    void checkAscSortByIdInShapesList() {
        List<Integer> actualListShapeIds = new ArrayList<>();
        ValidatableResponse responseShapesList = CalcManagement.getSortedListOfShapes("id,asc");
        List<Shape> shapesList = asList(JsonHelper.getObjectsListFromJson(responseShapesList, Shape[].class));
        shapesList.forEach(shape -> {
            actualListShapeIds.add(shape.getId());
        });
        List<Integer> sortAscListShapeIds = new ArrayList<>(actualListShapeIds);

        // sort the list in ascending order
        Collections.sort(sortAscListShapeIds);

        System.out.println(actualListShapeIds);
        System.out.println(sortAscListShapeIds);

        assertEquals(sortAscListShapeIds,
                actualListShapeIds, "Сортировка областей не соответствует ожидаемой");
    }

    @Override
    public String getUrl() {
        return CALC_MAN_SHAPES_EP;
    }

    @Override
    public int getCorrectLimit() {
        return 9;
    }
}
