package swen221Ass1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

public class OwnTetss {
        // ======================= GeoPoint Tests =======================

        @Test
        void testGeoPointValidCreation() {
            GeoPoint point = new GeoPoint(-41.290, 174.767);
            assertEquals(-41.290, point.latitude());
            assertEquals(174.767, point.longitude());
        }

        @Test
        void testGeoPointInvalidLatitude() {
            assertThrows(IllegalArgumentException.class, () -> new GeoPoint(91, 0));
            assertThrows(IllegalArgumentException.class, () -> new GeoPoint(-91, 0));
        }

        @Test
        void testGeoPointInvalidLongitude() {
            assertThrows(IllegalArgumentException.class, () -> new GeoPoint(0, 181));
            assertThrows(IllegalArgumentException.class, () -> new GeoPoint(0, -181));
        }

        @Test
        void testGeoPointStringFormatting() {
            GeoPoint point = new GeoPoint(-41.290123456, 174.767123456);
            assertEquals("[latitude: -41.29012346, longitude: 174.76712346]", point.toString());  // Default precision 8
            assertEquals("[latitude: -41.29, longitude: 174.77]", point.toString(2));  // Precision 2
        }

        @Test
        void testGeoPointAverageSimple() {
            GeoPoint p1 = new GeoPoint(0, 0);
            GeoPoint p2 = new GeoPoint(10, 10);
            GeoPoint mid = p1.average(p2);
            assertEquals(5, mid.latitude());
            assertEquals(5, mid.longitude());
        }

        @Test
        void testGeoPointAverageAntimeridian() {
            GeoPoint p1 = new GeoPoint(0, 170);
            GeoPoint p2 = new GeoPoint(0, -170);
            GeoPoint mid = p1.average(p2);
            assertEquals(0, mid.latitude());
            assertEquals(-180, mid.longitude()); // Correct wrap-around
        }

        // ======================= GeoBox Tests =======================

        @Test
        void testGeoBoxValidCreation() {
            GeoBox box = new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0));
            assertEquals(10, box.ne().latitude());
            assertEquals(0, box.sw().latitude());
        }

        @Test
        void testGeoBoxInvalidLatRange() {
            assertThrows(IllegalArgumentException.class, () -> new GeoBox(new GeoPoint(90, 10), new GeoPoint(0, 0)));
        }

        @Test
        void testGeoBoxInvalidLongRange() {
            assertThrows(IllegalArgumentException.class, () -> new GeoBox(new GeoPoint(10, 190), new GeoPoint(0, 0)));
        }

        @Test
        void testGeoBoxCenterComputation() {
            GeoBox box = new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0));
            GeoPoint center = box.center();
            assertEquals(5, center.latitude());
            assertEquals(5, center.longitude());
        }

        @Test
        void testGeoBoxCrossesAntimeridian() {
            GeoBox box = new GeoBox(new GeoPoint(10, -170), new GeoPoint(0, 170));
            assertTrue(box.ne().longitude() < box.sw().longitude());
        }

        // ======================= Street Tests =======================

        @Test
        void testStreetValidCreation() {
            Street street = new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Main St", 100);
            assertEquals("Main St", street.name());
            assertEquals(100, street.length());
        }

        @Test
        void testStreetInvalidLength() {
            assertThrows(IllegalArgumentException.class, () -> new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Main St", -1));
        }

        @Test
        void testStreetToString() {
            Street street = new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Main St", 100);
            assertEquals("Main St", street.toString());
        }

        // ======================= City Tests =======================

        @Test
        void testCityCreation() {
            City city = new City("Wellington", 200000);
            assertEquals("Wellington", city.name());
            assertEquals(200000, city.population());
        }

        @Test
        void testCityPopulationUpdate() {
            City city = new City("Wellington", 200000);
            city.population(210000);
            assertEquals(210000, city.population());
        }

        @Test
        void testCityAddingStreets() {
            City city = new City("Wellington", 200000);
            Street street = new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Lambton Quay", 500);
            city.addStreet(street);
            assertTrue(city.streets().contains(street));
        }

        // ======================= Address Tests =======================

        @Test
        void testAddressValidCreation() {
            City city = new City("Wellington", 200000);
            Street street = new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Lambton Quay", 500);
            Address address = new Address(street, city, "42A");
            assertEquals("42A", address.number());
        }

        // ======================= Building Tests =======================

        @Test
        void testSingleAddressBuildingValidCreation() {
            Address address = new Address(new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Lambton Quay", 500), new City("Wellington", 200000), "42A");
            SingleAddressBuilding building = new SingleAddressBuilding(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), address);
            assertEquals(address, building.primaryAddress());
        }

        @Test
        void testMultiAddressBuildingValidCreation() {
            Address addr1 = new Address(new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Lambton Quay", 500), new City("Wellington", 200000), "42A");
            Address addr2 = new Address(new Street(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), "Featherston St", 500), new City("Wellington", 200000), "43B");
            MultiAddressBuilding building = new MultiAddressBuilding(new GeoBox(new GeoPoint(10, 10), new GeoPoint(0, 0)), addr1, Set.of(addr2));
            assertEquals(1, building.secondaryAddresses().size());
        }


}
