/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.server.dhcp.options.dhcp;

import org.apache.directory.server.dhcp.DhcpException;
import org.apache.directory.server.dhcp.options.DhcpOption;
import org.apache.directory.server.dhcp.options.DhcpOptionsRegistry;

/**
 * This option is used by a DHCP client to request values for specified
 * configuration parameters.  The list of requested parameters is
 * specified as n octets, where each octet is a valid DHCP option code
 * as defined in this document.
 * 
 * The client MAY list the options in order of preference.  The DHCP
 * server is not required to return the options in the requested order,
 * but MUST try to insert the requested options in the order requested
 * by the client.
 * 
 * The code for this option is 55.  Its minimum length is 1.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class ParameterRequestList extends DhcpOption {
    /*
     * @see org.apache.directory.server.dhcp.options.DhcpOption#getTag()
     */

    @Override
    public byte getTag() {
        return 55;
    }

    @Override
    protected String toStringData() throws DhcpException {
        DhcpOptionsRegistry registry = DhcpOptionsRegistry.getInstance();
        StringBuilder buf = new StringBuilder("[ ");
        for (byte tag : getData()) {
            Class<? extends DhcpOption> type = registry.getOptionType(tag);
            if (type == null)
                type = UnrecognizedOption.class;
            buf.append(type.getSimpleName());
            buf.append("(").append(tag & 0xFF).append(") ");
        }
        buf.append("]");
        return buf.toString();
    }

}
