// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.poc.multitenancy.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.poc.multitenancy.core.ThreadLocalStorage;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantName();
    }

}
