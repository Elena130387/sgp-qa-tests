package api.helper;

import api.dto.polygon.PointDto;
import api.dto.polygon.PolygonInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolygonHelper {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //this method compares polygon number and tries to find for each expected polygon actual polygon
     public static void verifyPolygonNumberAndCoordinates(List<PolygonInput> expected, List<PolygonInput> actual) {
        assertEquals(expected.size(), actual.size(), "Ожидаемое количество полигонов в области не совпадает с актуальным");
        expected.forEach(polygon -> {
            List<PointDto> expectedCoordinates = polygon.getCoordinates();
            List<PolygonInput> possiblePolygons = actual.stream()
                    .filter(actPol -> actPol.getCoordinates().size() == expectedCoordinates.size()).collect(Collectors.toList());
            assertTrue(possiblePolygons.size() > 0, String.format("В актуальных полигонах не найдено ни одного похожего на %s", polygon));
            expectedCoordinates.forEach(point -> {
                List<PolygonInput> correspondingPolygons = new ArrayList<>(possiblePolygons);
                for (PolygonInput possiblePolygon : possiblePolygons) {
                    boolean pointFound = false;
                    List<PointDto> currentPolygonCoordinates = possiblePolygon.getCoordinates();
                    for (PointDto currentPolygonCoordinate : currentPolygonCoordinates) {
                        if (currentPolygonCoordinate.equals(point)) {
                            pointFound = true;
                            break;
                        }
                    }
                    if (!pointFound) {
                        correspondingPolygons.remove(possiblePolygon);
                    }
                }
                assertEquals(1, correspondingPolygons.size(), String.format("В области отсутствует полигон %s", polygon));
            });
        });
    }
}
