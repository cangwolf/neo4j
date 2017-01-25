/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.schema_new.index;

import org.neo4j.kernel.api.TokenNameLookup;
import org.neo4j.kernel.api.schema_new.LabelSchemaDescriptor;

import static java.lang.String.format;

class NewIndexDescriptorImpl implements NewIndexDescriptor
{
    private final LabelSchemaDescriptor schema;
    private final NewIndexDescriptor.Type type;

    NewIndexDescriptorImpl( LabelSchemaDescriptor schema, Type type )
    {
        this.schema = schema;
        this.type = type;
    }

    @Override
    public Type type()
    {
        return type;
    }

    @Override
    public LabelSchemaDescriptor schema()
    {
        return schema;
    }

    @Override
    public String userDescription( TokenNameLookup tokenNameLookup )
    {
        return format( "Index( %s, %s )", type.name(), schema.userDescription( tokenNameLookup ) );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o != null && o instanceof NewIndexDescriptor )
        {
            NewIndexDescriptor that = (NewIndexDescriptor)o;
            return this.type() == that.type() && this.schema().equals( that.schema() );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return type.hashCode() & schema.hashCode();
    }
}
