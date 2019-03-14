// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.poc.multitenancy.web.configuration;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.poc.multitenancy.web.filters.TenantNameFilter;
import com.poc.multitenancy.web.resources.CustomerResource;

/**
 * Jersey Configuration (Resources, Modules, Filters, ...)
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // Register the Filters:
        register(TenantNameFilter.class);

        // Register the Resources:
        register(CustomerResource.class);

        // Uncomment to disable WADL Generation:
        //property("jersey.config.server.wadl.disableWadl", true);

        // Uncomment to add Request Tracing:
        //property("jersey.config.server.tracing.type", "ALL");
        //property("jersey.config.server.tracing.threshold", "TRACE");
    }
}
