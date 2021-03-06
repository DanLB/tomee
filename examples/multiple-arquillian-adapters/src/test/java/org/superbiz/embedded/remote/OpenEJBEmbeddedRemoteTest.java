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
package org.superbiz.embedded.remote;

import org.apache.ziplock.IO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.superbiz.SomeRest;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@Category(EmbeddedRemote.class)
@RunWith(Arquillian.class)
public class OpenEJBEmbeddedRemoteTest {

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class, "my-webapp.jar").addClass(SomeRest.class);
    }

    @Test
    public void check() throws IOException {
        final String content = IO.slurp(new URL("http://localhost:4204/my-webapp/rest/ok"));
        assertEquals("rest", content);
    }
}
