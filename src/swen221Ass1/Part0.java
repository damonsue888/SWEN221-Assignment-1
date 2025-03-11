package swen221Ass1;

import static org.junit.jupiter.api.Assertions.*;
import static swen221Ass1.Part1.illegal;
import static swen221Ass1.Part3.city;
import static swen221Ass1.Part4.multiB;
import static swen221Ass1.Part2.box;
import static swen221Ass1.Part3.address1;


import org.junit.jupiter.api.Test;

import java.util.Set;


class Part0 {
  //This just tests that JUnit is working properly
  @Test
  void junitWorking() {
    assertTrue(true);
  }

  //This tests that (at least while running under JUnits)
  //assertions are enabled. We will see details of assertions soon.
  //This test should simply pass. If it fails on your system ask me or a tutor for support.
  @Test
  void assertionsWorking() {
    assertThrows(AssertionError.class, () -> {assert false;});
  }

//  void checkOverlap(boolean ok, GeoBox box1, GeoBox box2){
//    City c= city();
//    Street s1= new Street(box1, "a", 1);
//    Street s2= new Street(box2, "a", 1);
//    c.addStreet(s1);
//    c.addStreet(s2);
//    Address a1= new Address(s1,c,"12a");
//    Address a2= new Address(s2,c,"23r");
//    Building.of(box1,a1);//ok no failure here ever
//    if(ok) {
//      Building.of(box1,a1,Set.of(a2));//may fail
//      Building.of(box1,a2);//We test both as primary and as secondary
//    }
//    else {
//      illegal(()->Building.of(box1,a1,Set.of(a2)));
//      illegal(()->Building.of(box1,a2));
//    }
//  }
//  void overlapKo(GeoBox box){ checkOverlap(false, box(1, 1, 0, 0), box); }
//
//  @Test void aboveWithAGap(){ overlapKo(box(2.1, 1, 1.1, 0)); }

  @Test
  void averageWithNegativeFirst() {
    GeoPoint p1 = new GeoPoint(0, -162);
    GeoPoint p2 = new GeoPoint(0, 170);
    GeoPoint mid = p1.average(p2);
    assertEquals(0, mid.latitude());
    assertEquals(-176, mid.longitude()); // Correct wrap-around
  }
  @Test
  void averageWithNegativeSecond() {
    GeoPoint p1 = new GeoPoint(0, 162);
    GeoPoint p2 = new GeoPoint(0, -170);
    GeoPoint mid = p1.average(p2);
    assertEquals(0, mid.latitude());
    assertEquals(176, mid.longitude()); // Correct wrap-around
  }

  @Test void sameMagNegFirst() {
    var a= new GeoPoint(0d, -175d);
    var b= new GeoPoint(0d, +175d);
    assertEquals(new GeoPoint(0d,-180d), a.average(b));
  }

  @Test void sameMagNegSecond() {
    var a= new GeoPoint(0d, +175d);
    var b= new GeoPoint(0d, -175d);
    assertEquals(new GeoPoint(0d,-180d), a.average(b));
  }

  @Test
  void averageWithNegativeFirstBig() {
    GeoPoint p1 = new GeoPoint(0, -170);
    GeoPoint p2 = new GeoPoint(0, 162);
    GeoPoint mid = p1.average(p2);
    assertEquals(0, mid.latitude());
    assertEquals(176, mid.longitude()); // Correct wrap-around
  }

  @Test
  void averageWithNegativeSecondSmall() {
    GeoPoint p1 = new GeoPoint(0, 170);
    GeoPoint p2 = new GeoPoint(0, -162);
    GeoPoint mid = p1.average(p2);
    assertEquals(0, mid.latitude());
    assertEquals(-176, mid.longitude()); // Correct wrap-around
  }

}

