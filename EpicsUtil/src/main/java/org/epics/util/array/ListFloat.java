/*
 * Copyright 2011 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.array;

/**
 * An ordered collection of {@code float}s.
 *
 * @author Gabriele Carcassi
 */
public abstract class ListFloat implements ListNumber, CollectionFloat {

    @Override
    public IteratorFloat iterator() {
        return new IteratorFloat() {
            
            private int index;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public float nextFloat() {
                return getFloat(index++);
            }
        };
    }

    @Override
    public double getDouble(int index) {
        return (double) getFloat(index);
    }

    @Override
    public long getLong(int index) {
        return (long) getFloat(index);
    }

    @Override
    public int getInt(int index) {
        return (int) getFloat(index);
    }

    @Override
    public short getShort(int index) {
        return (short) getFloat(index);
    }

    @Override
    public byte getByte(int index) {
        return (byte) getFloat(index);
    }
    
}
