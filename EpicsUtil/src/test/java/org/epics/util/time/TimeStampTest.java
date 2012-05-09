/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.time;

import java.util.Date;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author carcassi
 */
public class TimeStampTest {

    public TimeStampTest() {
    }
    
    @Test
    public void time1() {
        TimeStamp time = TimeStamp.of(100, 10000000);
        assertThat(time.getSec(), equalTo(100L));
        assertThat(time.getNanoSec(), equalTo(10000000));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void time2() {
        TimeStamp.of(100, 1000000000);
    }
    
    @Test
    public void ofDate1() {
        Date date = new Date(123456789);
        TimeStamp time = TimeStamp.of(date);
        assertThat(time.getSec(), equalTo(123456L));
        assertThat(time.getNanoSec(), equalTo(789000000));
    }
    
    @Test
    public void toDate1() {
        TimeStamp time = TimeStamp.of(123456, 789000000);
        assertThat(time.toDate(), equalTo(new Date(123456789)));
    }
    
    @Test
    public void plus1() {
        TimeStamp time = TimeStamp.of(0, 0);
        TimeStamp newTime = time.plus(TimeDuration.ofMillis(100));
        assertThat(newTime, equalTo(TimeStamp.of(0, 100000000)));
    }
    
    @Test
    public void plus2() {
        TimeStamp time = TimeStamp.of(100, 100000000);
        TimeStamp newTime = time.plus(TimeDuration.ofNanos(999000000));
        assertThat(newTime, equalTo(TimeStamp.of(101, 99000000)));
    }
    
    @Test
    public void plus3() {
        TimeStamp time = TimeStamp.of(100, 750000000);
        TimeStamp newTime = time.plus(TimeDuration.ofSeconds(5.750));
        assertThat(newTime, equalTo(TimeStamp.of(106, 500000000)));
    }
    
    @Test
    public void plus4() {
        TimeStamp time = TimeStamp.of(100, 750000000);
        TimeStamp newTime = time.plus(TimeDuration.ofSeconds(-5.750));
        assertThat(newTime, equalTo(TimeStamp.of(95, 000000000)));
    }
    
    @Test
    public void minus1() {
        TimeStamp time = TimeStamp.of(0, 0);
        TimeStamp newTime = time.minus(TimeDuration.ofMillis(100));
        assertThat(newTime, equalTo(TimeStamp.of(-1, 900000000)));
    }
    
    @Test
    public void minus2() {
        TimeStamp time = TimeStamp.of(0, 0);
        TimeStamp newTime = time.minus(TimeDuration.ofMillis(100));
        assertThat(newTime, equalTo(TimeStamp.of(-1, 900000000)));
    }
    
    @Test
    public void minus3() {
        TimeStamp time = TimeStamp.of(0, 0);
        TimeStamp newTime = time.minus(TimeDuration.ofMillis(100));
        assertThat(newTime, equalTo(TimeStamp.of(-1, 900000000)));
    }
    
    @Test
    public void durationFrom1() {
        TimeStamp reference = TimeStamp.now();
        assertThat(reference.plus(TimeDuration.ofNanos(10)).durationFrom(reference), equalTo(TimeDuration.ofNanos(10)));
    }
    
    @Test
    public void durationFrom2() {
        TimeStamp reference = TimeStamp.now();
        assertThat(reference.minus(TimeDuration.ofNanos(10)).durationFrom(reference), equalTo(TimeDuration.ofNanos(-10)));
    }
    
    @Test
    public void durationFrom3() {
        TimeStamp reference = TimeStamp.of(10, 500000000);
        assertThat(reference.plus(TimeDuration.ofMillis(600)).durationFrom(reference), equalTo(TimeDuration.ofMillis(600)));
    }
    
    @Test
    public void durationFrom4() {
        TimeStamp reference = TimeStamp.of(10, 500000000);
        assertThat(reference.minus(TimeDuration.ofMillis(600)).durationFrom(reference), equalTo(TimeDuration.ofMillis(-600)));
    }
    
    @Test
    public void durationBetween1() {
        TimeStamp reference = TimeStamp.now();
        assertThat(reference.durationBetween(reference.plus(TimeDuration.ofNanos(10))), equalTo(TimeDuration.ofNanos(10)));
    }
    
    @Test
    public void durationBetween2() {
        TimeStamp reference = TimeStamp.now();
        assertThat(reference.durationBetween(reference.minus(TimeDuration.ofNanos(10))), equalTo(TimeDuration.ofNanos(10)));
    }
    
    @Test
    public void durationBetween3() {
        TimeStamp reference = TimeStamp.of(10, 500000000);
        assertThat(reference.durationBetween(reference.plus(TimeDuration.ofMillis(600))), equalTo(TimeDuration.ofMillis(600)));
    }
    
    @Test
    public void durationBetween4() {
        TimeStamp reference = TimeStamp.of(10, 500000000);
        assertThat(reference.durationBetween(reference.minus(TimeDuration.ofMillis(600))), equalTo(TimeDuration.ofMillis(600)));
    }
    
}