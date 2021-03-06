/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz;

import org.junit.Assert;
import org.junit.Test;
import org.superbiz.applet.Calculator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

public class JNDILookupTest {

    @Test
    public void test() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http://127.0.0.1:8080/tomee/ejb");
        try {
            Context ctx = new InitialContext(props);
            System.out.println("Found context " + ctx);
            final Object ref = ctx.lookup("CalculatorImplRemote");
            Calculator calc = (Calculator) PortableRemoteObject.narrow(ref, Calculator.class);
            double result = calc.add(10, 30);
            Assert.assertEquals(40, result, 0.5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
