package com.cyc.base;

/*
 * #%L
 * File: CycAccessManager.java
 * Project: Base Client
 * %%
 * Copyright (C) 2013 - 2017 Cycorp, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.cyc.baseclient.CycClientManager;
import com.cyc.baseclient.LegacyCycClientManager;
import com.cyc.session.CycSession;
import com.cyc.session.CycSessionManager;
import com.cyc.session.exception.SessionCommunicationException;
import com.cyc.session.exception.SessionConfigurationException;
import com.cyc.session.exception.SessionInitializationException;
import com.cyc.session.spi.SessionManager;
import java.io.IOException;
import java.util.ServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 * 
 * @author nwinant
 * @param <T>
 */
abstract public class CycAccessManager<T extends CycAccessSession> implements SessionManager<T> {
  
  static final private Logger LOGGER = LoggerFactory.getLogger(CycAccessManager.class);
  static private CycAccessManager manager;
  final private LegacyCycClientManager legacyMgr;
  
  protected CycAccessManager() {
    this.legacyMgr = new LegacyCycClientManager();
  }
  
  /**
   * Returns an implementation of CycAccessManager. It will return the first
   * implementation it finds which has been registered per {@link java.util.ServiceLoader}.
   * If one has not been specified, it will fall back on the default implementation.
   * 
   * @return an object which implements CycAccessManager.
   */
  public static synchronized <T extends CycAccessSession> CycAccessManager<T> getAccessManager() {
    // Note: The relevant service provider file in META-INF/services
    //       is generated by the serviceloader-maven-plugin, specified
    //       in the pom.xml file. You're welcome, Dave! - nwinant, 2014-04-30
    if (manager == null) {
       ServiceLoader<CycAccessManager> loader = 
            ServiceLoader.load(CycAccessManager.class);
       for (CycAccessManager mgr : loader) {
         // CycClientManager is the default provider,  
         //   but any other implementation will override it. - nwinant, 2014-04-28
         if ((manager == null) || CycClientManager.class.isInstance(mgr)) {
           manager = mgr;
         }
       }
    }
    return manager;
  }
  
  /**
   * Returns a LegacyCycClientManager, which behaves much like the old CycAccessManager.
   * This method will be changed dramatically or removed before final 1.0.0 release.
   * @return
   * @deprecated
   */
  @Deprecated
  public static synchronized LegacyCycClientManager get() {
    return CycAccessManager.getAccessManager().legacyMgr;
  }
  
  
  // Public
  /*
  static public CycAccess getAccess() throws SessionConfigurationException, SessionCommunicationException, SessionInitializationException {
    //LOGGER.debug("Calling #getAccess()");
    return CycAccessManager.getAccessManager().getSession().getAccess();
  }
  */
  
  static public CycAccess getCurrentAccess() throws SessionConfigurationException, SessionCommunicationException, SessionInitializationException {
    //LOGGER.debug("Calling #getCurrentAccess()");
    return CycAccessManager.getAccessManager().getCurrentSession().getAccess();
  }
  
  public CycAccess fromSession(CycSession session) {
    return ((CycAccessSession) session).getAccess();
  }
  
  @Override
  public void close() throws IOException {
    this.getSessionMgr().close();
  }
  
  @Override
  public boolean isClosed() {
    return this.getSessionMgr().isClosed();
  }
  
  
  // Protected
  
  protected SessionManager<T> getSessionMgr() {
    return CycSessionManager.getInstance();
  }
}