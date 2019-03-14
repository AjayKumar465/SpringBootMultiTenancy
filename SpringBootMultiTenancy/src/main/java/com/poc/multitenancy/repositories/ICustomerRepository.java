// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.poc.multitenancy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.poc.multitenancy.model.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
