// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.poc.multitenancy.web.converter;

import com.poc.multitenancy.model.Customer;
import com.poc.multitenancy.web.model.CustomerDto;

public class Converters {

    public static CustomerDto convert(Customer source) {
        if(source == null) {
            return null;
        }

        return new CustomerDto(source.getId(), source.getFirstName(), source.getLastName());
    }

    public static Customer convert(CustomerDto source) {
        if(source == null) {
            return null;
        }

        return new Customer(source.getId(), source.getFirstName(), source.getLastName());
    }

}
