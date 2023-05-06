//package com.airline.models;
//
//package com.airline.models;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class AirplaneTest {
//
//    @Test
//    public void testGetModel() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Assertions.assertEquals("Boeing 747", airplane.getModel());
//    }
//
//    @Test
//    public void testGetCapacity() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Assertions.assertEquals(400, airplane.getCapacity());
//    }
//
//    @Test
//    public void testGetRange() {
//        Airplane airplane = new Airplane("Boeing 747", 400, 8000, "12345");
//        Assertions.assertEquals(8000, airplane.getRange());
//    }
//
//    @Test
//    public void testGetSerialNumber() {
//        Airplane airplane = new Airplane("Boeing 747", 400, 8000, "12345");
//        Assertions.assertEquals("12345", airplane.getSerialNumber());
//    }
//
//    @Test
//    public void testGetId() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        airplane.setId(1);
//        Assertions.assertEquals(1, airplane.getId());
//    }
//
//    @Test
//    public void testConstructor() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Assertions.assertEquals("", airplane.getSerialNumber());
//        Assertions.assertEquals(0, airplane.getRange());
//    }
//}
