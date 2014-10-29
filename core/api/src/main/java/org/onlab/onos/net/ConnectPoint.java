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
package org.onlab.onos.net;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Abstraction of a network connection point expressed as a pair of the
 * network element identifier and port number.
 */
public class ConnectPoint {

    private final ElementId elementId;
    private final PortNumber portNumber;

    /**
     * Creates a new connection point.
     *
     * @param elementId  network element identifier
     * @param portNumber port number
     */
    public ConnectPoint(ElementId elementId, PortNumber portNumber) {
        this.elementId = elementId;
        this.portNumber = portNumber;
    }

    /**
     * Returns the network element identifier.
     *
     * @return element identifier
     */
    public ElementId elementId() {
        return elementId;
    }

    /**
     * Returns the identifier of the infrastructure device if the connection
     * point belongs to a network element which is indeed an infrastructure
     * device.
     *
     * @return network element identifier as a device identifier
     * @throws java.lang.IllegalStateException if connection point is not
     *                                         associated with a device
     */
    public DeviceId deviceId() {
        if (elementId instanceof DeviceId) {
            return (DeviceId) elementId;
        }
        throw new IllegalStateException("Connection point not associated " +
                                                "with an infrastructure device");
    }

    /**
     * Returns the identifier of the infrastructure device if the connection
     * point belongs to a network element which is indeed an end-station host.
     *
     * @return network element identifier as a host identifier
     * @throws java.lang.IllegalStateException if connection point is not
     *                                         associated with a host
     */
    public HostId hostId() {
        if (elementId instanceof HostId) {
            return (HostId) elementId;
        }
        throw new IllegalStateException("Connection point not associated " +
                                                "with an end-station host");
    }

    /**
     * Returns the connection port number.
     *
     * @return port number
     */
    public PortNumber port() {
        return portNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, portNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ConnectPoint) {
            final ConnectPoint other = (ConnectPoint) obj;
            return Objects.equals(this.elementId, other.elementId) &&
                    Objects.equals(this.portNumber, other.portNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("elementId", elementId)
                .add("portNumber", portNumber)
                .toString();
    }

}
