package swen221Ass1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;


class Part1 {

  //This two tests check that creating those GeoPoints
  //do not trigger exceptions.
  @Test void validPoint1(){ new GeoPoint(0,0); }

  @Test void validPoint2(){ new GeoPoint(50,-60); }

  //This test checks that attempting to create an invalid point
  //causes an IllegalArgumentException.
  //This test relies on an explicit try-catch
  //We will soon see try-catch in details, but you should start
  //getting used to see code looking like this.
  @Test void invalidPointTrCatch(){
    try { new GeoPoint(-400,-60); }
    catch(IllegalArgumentException iae){ return; }
    fail();//fail if we did not return
    }

  //This test checks that attempting to create an invalid point
  //causes an IllegalArgumentException
  //This test relies on the Junit method assertThrows
  //This is better then the former test manually using try-catch
  //This code uses lambdas. We will see lambdas later in this course, but you should start
  //getting used to see code looking like this.
  ///Try to understand what this code may do and what "()->" may mean.
  @Test void invalidPointAssertThrows(){
    assertThrows(IllegalArgumentException.class,()->new GeoPoint(500,2));
    }

  public static void illegal(Executable e){
    assertThrows(IllegalArgumentException.class,e);
  }
  //This test checks that attempting to create an invalid point
  //causes an IllegalArgumentException
  //This test relies on the user defined 'illegal' method,
  //internally using the Junit method assertThrows
  //Abstracting junit methods with your own testing functions is
  //one of the best ways to write a lot of tests without using too much code.
  //As you can see the test can now fit in just one or two lines!
  @Test void invalidPoint(){ illegal(()->new GeoPoint(50,-600)); }

  //below a few more invalid point tests
  @Test void invalidLowLat(){ illegal(()->new GeoPoint(-500,0)); }

  @Test void invalidLowLon(){ illegal(()->new GeoPoint(0,-500)); }

  @Test void invalidHighLat(){ illegal(()->new GeoPoint(500,0)); }

  //testing validity at the exact boundary
  @Test void boundaryNotOKLowLat(){ illegal(()->new GeoPoint(-90.000001d,0)); }

  @Test void boundaryOKLowLat(){ new GeoPoint(-90d,0); }

  @Test void boundaryNotOKLowLon(){ illegal(()->new GeoPoint(0,-180.000001d)); }

  @Test void boundaryOKLowLon(){ new GeoPoint(0,-18d); }

  @Test void boundaryNotOKHighLon(){ illegal(()->new GeoPoint(0,180d)); }

  @Test void boundaryOKHighLon(){ new GeoPoint(0,179.999999d); }

  //Here we test that averaging with zero to the left or to the right divides the value by 2.
  //Note the kind of thinking behind the design of those tests.
  //This test uses 'assertEquals' from JUnit.
  //It is a great method and it has good integration with the IDE.
  //on one of those failed methods, try to double click on the first line of the failure trace.
  //You will get a readable 'diff' on the string representation of the involved objects.
  //The first parameter to 'assertEquals' is the 'expected result'
  @Test void average00Left(){
    assertEquals(new GeoPoint(5d,10d),
      new GeoPoint(0d,0d).average(new GeoPoint(10d,20d)));
    }
  @Test void average00Right(){
    assertEquals(new GeoPoint(25d,-23d),
      new GeoPoint(50d,-46d).average(new GeoPoint(0d,0d)));
    }

  @Test void oddAverage00Left(){
    assertEquals(new GeoPoint(24.5d,-22.5d),
      new GeoPoint(49d,-45d).average(new GeoPoint(0d,0d)));
    }
  @Test void oddAverage00Right(){
    assertEquals(new GeoPoint(24.5d,-22.5d),
      new GeoPoint(0d,0d).average(new GeoPoint(49d,-45d)));
    }
  @Test void average00(){
    var zero= new GeoPoint(0,0);
    assertEquals(zero,zero.average(zero));
  }
  @Test void averageLarge(){
    assertEquals(new GeoPoint(85d,-100d),
        new GeoPoint(90d,-50d).average(new GeoPoint(80d,-150d)));
  }
  @Test void averageHard() {
    var a= new GeoPoint(0d, -175d);
    var b= new GeoPoint(0d, +175d);
    assertEquals(new GeoPoint(0d,-180d), a.average(b));
  }
  @Test void averageHard2() {
    var a= new GeoPoint(0d, -175d);
    var b= new GeoPoint(0d, +176d);
    assertEquals(new GeoPoint(0d,-179.5d), a.average(b));
  }

  //Note ""+ is just a fast way to call .toString()
  @Test void testToString(){
    assertEquals("[latitude: 1.23456789, longitude: 3.45678901]",
        ""+new GeoPoint(1.23456789012d,3.4567890123456d));
  }
  @Test void testToString1(){
    assertEquals("[latitude: 1.2, longitude: 3.5]",
        new GeoPoint(1.23456789012d,3.4567890123456d).toString(1));
  }
  @Test void testToString2(){
    assertEquals("[latitude: 1.23, longitude: 3.46]",
        new GeoPoint(1.23456789012d,3.4567890123456d).toString(2));
  }
  @Test void testToString0(){
    assertEquals("[latitude: 1, longitude: 3]",
        new GeoPoint(1.23456789012d,3.4567890123456d).toString(0));
  }
  @Test void testToStringErr5(){ illegal(()->new GeoPoint(0d,0d).toString(-5)); }

  @Test void testToStringErr1(){ illegal(()->new GeoPoint(1d,1d).toString(-1)); }

  @Test void geoPointIsRecord(){
    assertTrue(new GeoPoint(0,0) instanceof Record);
  }

  // Own Tests
  @Test void tests() {
    List<List<Double>> list = List.of(List.of(30d, -120d, -45d),
            List.of(60d, -150d, 135d),
            List.of(-60d, 150d, -135d),
            List.of(50.2d, -171.0d, 119.6d),
            List.of(-81.0d, -99.6d, -90.3d),
            List.of(85.1d, 63.6d, 74.35d),
            List.of(141.2d, -148.7d, 176.25d),
            List.of(-28.1d, -169.3d, -98.7d),
            List.of(-170.4d, -108.4d, -139.4d),
            List.of(54.0d, 16.2d, 35.1d),
            //List.of(-101.3d, 1.9d, -49.7d),
            //List.of(111.4d, -177.7d, 146.85d),
            List.of(-100.6d, 32.1d, -34.25d));

    for (List<Double> l : list) {
      var a = new GeoPoint(0d, l.get(0));
      var b = new GeoPoint(0d, l.get(1));
      assertEquals(new GeoPoint(0d, l.get(2)), a.average(b));
    }
  }


}