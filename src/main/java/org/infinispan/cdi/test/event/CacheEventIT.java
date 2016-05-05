/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.cdi.test.event;

import org.infinispan.AdvancedCache;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;

import static org.jgroups.util.Util.assertEquals;

@javax.ejb.Singleton
@Startup
public class CacheEventIT {

   @Inject
   @Cache1
   private AdvancedCache<String, String> cache1;

   @Inject
   @Cache2
   private AdvancedCache<String, String> cache2;

   @Inject
   private Cache1Observers observers1;

   @Inject
   private Cache2Observers observers2;

   @PostConstruct
   public void testSmallCache() {
      System.out.println("#### SINGLETON START ####");

      Cache1Observers observer = new Cache1Observers();
      cache1.addListener(observer);

      cache1.put("pete", "Edinburgh");
      assertEquals(cache1.get("pete"), "Edinburgh");
      assertEquals(1, observer.getCacheStartedEventCount());
   }
}
