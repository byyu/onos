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
package org.onlab.onos.provider.host.impl;

import static org.slf4j.LoggerFactory.getLogger;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.onlab.onos.net.ConnectPoint;
import org.onlab.onos.net.Host;
import org.onlab.onos.net.HostId;
import org.onlab.onos.net.HostLocation;
import org.onlab.onos.net.host.DefaultHostDescription;
import org.onlab.onos.net.host.HostDescription;
import org.onlab.onos.net.host.HostProvider;
import org.onlab.onos.net.host.HostProviderRegistry;
import org.onlab.onos.net.host.HostProviderService;
import org.onlab.onos.net.packet.PacketContext;
import org.onlab.onos.net.packet.PacketProcessor;
import org.onlab.onos.net.packet.PacketService;
import org.onlab.onos.net.provider.AbstractProvider;
import org.onlab.onos.net.provider.ProviderId;
import org.onlab.onos.net.topology.Topology;
import org.onlab.onos.net.topology.TopologyService;
import org.onlab.packet.ARP;
import org.onlab.packet.Ethernet;
import org.onlab.packet.IpAddress;
import org.onlab.packet.VlanId;
import org.slf4j.Logger;

/**
 * Provider which uses an OpenFlow controller to detect network
 * end-station hosts.
 */
@Component(immediate = true)
public class HostLocationProvider extends AbstractProvider implements HostProvider {

    private final Logger log = getLogger(getClass());

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected HostProviderRegistry providerRegistry;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected PacketService pktService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected TopologyService topologyService;

    private HostProviderService providerService;

    private final InternalHostProvider processor = new InternalHostProvider();


    /**
     * Creates an OpenFlow host provider.
     */
    public HostLocationProvider() {
        super(new ProviderId("of", "org.onlab.onos.provider.host"));
    }

    @Activate
    public void activate() {
        providerService = providerRegistry.register(this);
        pktService.addProcessor(processor, 1);
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        providerRegistry.unregister(this);
        pktService.removeProcessor(processor);
        providerService = null;
        log.info("Stopped");
    }

    @Override
    public void triggerProbe(Host host) {
        log.info("Triggering probe on device {}", host);
    }

    private class InternalHostProvider implements PacketProcessor {

        @Override
        public void process(PacketContext context) {
            if (context == null) {
                return;
            }
            Ethernet eth = context.inPacket().parsed();

            VlanId vlan = VlanId.vlanId(eth.getVlanID());
            ConnectPoint heardOn = context.inPacket().receivedFrom();

            // If this is not an edge port, bail out.
            Topology topology = topologyService.currentTopology();
            if (topologyService.isInfrastructure(topology, heardOn)) {
                return;
            }

            HostLocation hloc = new HostLocation(heardOn, System.currentTimeMillis());

            HostId hid = HostId.hostId(eth.getSourceMAC(), vlan);

            // Potentially a new or moved host
            if (eth.getEtherType() == Ethernet.TYPE_ARP) {
                ARP arp = (ARP) eth.getPayload();
                IpAddress ip =
                    IpAddress.valueOf(arp.getSenderProtocolAddress());
                HostDescription hdescr =
                        new DefaultHostDescription(eth.getSourceMAC(), vlan, hloc, ip);
                providerService.hostDetected(hid, hdescr);

            } else if (eth.getEtherType() == Ethernet.TYPE_IPV4) {
                //Do not learn new ip from ip packet.
                HostDescription hdescr =
                        new DefaultHostDescription(eth.getSourceMAC(), vlan, hloc);
                providerService.hostDetected(hid, hdescr);

            }
        }
    }
}
