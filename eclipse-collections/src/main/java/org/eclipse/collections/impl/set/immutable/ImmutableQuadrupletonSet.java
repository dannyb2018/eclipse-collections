/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.ImmutableSet;

final class ImmutableQuadrupletonSet<T>
        extends AbstractImmutableSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final T element1;
    private final T element2;
    private final T element3;
    private final T element4;

    ImmutableQuadrupletonSet(T obj1, T obj2, T obj3, T obj4)
    {
        this.element1 = obj1;
        this.element2 = obj2;
        this.element3 = obj3;
        this.element4 = obj4;
    }

    @Override
    public int size()
    {
        return 4;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if (!(other instanceof Set))
        {
            return false;
        }
        Set<?> set = (Set<?>) other;
        return set.size() == this.size()
                && set.contains(this.element1)
                && set.contains(this.element2)
                && set.contains(this.element3)
                && set.contains(this.element4);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1)
                + this.nullSafeHashCode(this.element2)
                + this.nullSafeHashCode(this.element3)
                + this.nullSafeHashCode(this.element4);
    }

    @Override
    public ImmutableSet<T> newWith(T element)
    {
        if (!this.contains(element))
        {
            return Sets.immutable.with(this.element1, this.element2, this.element3, this.element4, element);
        }
        return this;
    }

    @Override
    public ImmutableSet<T> newWithout(T element)
    {
        if (this.contains(element))
        {
            if (Objects.equals(element, this.element1))
            {
                return Sets.immutable.with(this.element2, this.element3, this.element4);
            }
            if (Objects.equals(element, this.element2))
            {
                return Sets.immutable.with(this.element1, this.element3, this.element4);
            }
            if (Objects.equals(element, this.element3))
            {
                return Sets.immutable.with(this.element1, this.element2, this.element4);
            }
            return Sets.immutable.with(this.element1, this.element2, this.element3);
        }
        return this;
    }

    @Override
    public boolean contains(Object obj)
    {
        return Objects.equals(obj, this.element1)
                || Objects.equals(obj, this.element2)
                || Objects.equals(obj, this.element3)
                || Objects.equals(obj, this.element4);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new QuadrupletonSetIterator();
    }

    @Override
    public T getFirst()
    {
        return this.element1;
    }

    @Override
    public T getLast()
    {
        return this.element4;
    }

    @Override
    public T getOnly()
    {
        throw new IllegalStateException("Size must be 1 but was " + this.size());
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.element1);
        procedure.value(this.element2);
        procedure.value(this.element3);
        procedure.value(this.element4);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.element1, 0);
        objectIntProcedure.value(this.element2, 1);
        objectIntProcedure.value(this.element3, 2);
        objectIntProcedure.value(this.element4, 3);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.element1, parameter);
        procedure.value(this.element2, parameter);
        procedure.value(this.element3, parameter);
        procedure.value(this.element4, parameter);
    }

    protected class QuadrupletonSetIterator
            extends ImmutableSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return ImmutableQuadrupletonSet.this.element1;
            }
            if (i == 1)
            {
                return ImmutableQuadrupletonSet.this.element2;
            }
            if (i == 2)
            {
                return ImmutableQuadrupletonSet.this.element3;
            }
            if (i == 3)
            {
                return ImmutableQuadrupletonSet.this.element4;
            }
            throw new NoSuchElementException("i=" + i);
        }
    }

    private Object writeReplace()
    {
        return new ImmutableSetSerializationProxy<>(this);
    }
}
