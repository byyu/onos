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
package org.onlab.onos.mastership;

import org.onlab.onos.cluster.NodeId;
import org.onlab.onos.cluster.RoleInfo;
import org.onlab.onos.net.DeviceId;
import org.onlab.onos.net.MastershipRole;

import java.util.Set;

/**
 * Test adapter for mastership service.
 */
public class MastershipServiceAdapter implements MastershipService {
    @Override
    public MastershipRole getLocalRole(DeviceId deviceId) {
        return null;
    }

    @Override
    public MastershipRole requestRoleFor(DeviceId deviceId) {
        return null;
    }

    @Override
    public void relinquishMastership(DeviceId deviceId) {
    }

    @Override
    public NodeId getMasterFor(DeviceId deviceId) {
        return null;
    }

    @Override
    public Set<DeviceId> getDevicesOf(NodeId nodeId) {
        return null;
    }

    @Override
    public void addListener(MastershipListener listener) {
    }

    @Override
    public void removeListener(MastershipListener listener) {
    }

    @Override
    public MastershipTermService requestTermService() {
        return null;
    }

    @Override
    public RoleInfo getNodesFor(DeviceId deviceId) {
        return null;
    }
}
