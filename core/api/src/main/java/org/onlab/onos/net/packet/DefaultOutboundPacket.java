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
package org.onlab.onos.net.packet;

import java.nio.ByteBuffer;

import org.onlab.onos.net.DeviceId;
import org.onlab.onos.net.flow.TrafficTreatment;

import com.google.common.base.MoreObjects;

/**
 * Default implementation of an immutable outbound packet.
 */
public class DefaultOutboundPacket implements OutboundPacket {
    private final DeviceId sendThrough;
    private final TrafficTreatment treatment;
    private final ByteBuffer data;

    /**
     * Creates an immutable outbound packet.
     *
     * @param sendThrough identifier through which to send the packet
     * @param treatment   list of packet treatments
     * @param data        raw packet data
     */
    public DefaultOutboundPacket(DeviceId sendThrough,
            TrafficTreatment treatment, ByteBuffer data) {
        this.sendThrough = sendThrough;
        this.treatment = treatment;
        this.data = data;
    }

    @Override
    public DeviceId sendThrough() {
        return sendThrough;
    }

    @Override
    public TrafficTreatment treatment() {
        return treatment;
    }

    @Override
    public ByteBuffer data() {
        // FIXME: figure out immutability here
        return data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sendThrough", sendThrough)
                .add("treatment", treatment)
                .toString();
    }
}
