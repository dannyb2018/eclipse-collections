/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationTestCase;

public class ImmutableBagMultimapSerializationTest extends ImmutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return HashBagMultimap.newMultimap();
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAGlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5JbW11dGFi\n"
                + "bGVCYWdNdWx0aW1hcEltcGwkSW1tdXRhYmxlQmFnTXVsdGltYXBTZXJpYWxpemF0aW9uUHJveHkA\n"
                + "AAAAAAAAAQwAAHhwdwQAAAACdAABQXcEAAAAAnEAfgACdwQAAAABdAABQncEAAAAAnEAfgADdwQA\n"
                + "AAABcQB+AAJ3BAAAAAF4";
    }
}
