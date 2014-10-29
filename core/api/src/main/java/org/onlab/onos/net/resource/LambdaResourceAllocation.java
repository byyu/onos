/*
 * Copyright 2014 Open Networking Laboratory
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
package org.onlab.onos.net.resource;

/**
 * Representation of allocated lambda resource.
 */
public class LambdaResourceAllocation extends LambdaResourceRequest
        implements ResourceAllocation {
    private final Lambda lambda;

    @Override
    public ResourceType type() {
        return ResourceType.LAMBDA;
    }

    /**
     * Creates a new {@link LambdaResourceAllocation} with {@link Lambda}
     * object.
     *
     * @param lambda allocated lambda
     */
    public LambdaResourceAllocation(Lambda lambda) {
        this.lambda = lambda;
    }

    /**
     * Returns the lambda resource.
     *
     * @return the lambda resource
     */
    public Lambda lambda() {
        return lambda;
    }
}
