/*
 *  Copyright (c) 2023 Mastercard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mastercard.locationsatmsreferenceapplication.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationsAtmsServiceException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(LocationsAtmsServiceException.class);

    public LocationsAtmsServiceException(Exception e) {
        super(e);
        log.error("Client side error has occurred.", e);
    }
}
