/*
 * Copyright 2015 Cycorp, Inc.
 *
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
 */
package com.cyc.kb.client.services;

/*
 * #%L
 * File: SymbolServiceImplTest.java
 * Project: KB Client
 * %%
 * Copyright (C) 2013 - 2018 Cycorp, Inc
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

import com.cyc.kb.Symbol;
import com.cyc.kb.client.SymbolImpl;
import com.cyc.kb.client.TestConstants;
import com.cyc.kb.exception.KbTypeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author nwinant
 */
public class SymbolServiceImplTest {
  
  public SymbolServiceImplTest() {
  }
  
  @BeforeClass
  public static void setUpClass() throws Exception {
    TestConstants.ensureInitialized();
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    instance = new SymbolServiceImpl();
  }
  
  @After
  public void tearDown() {
  }
  
  
  // Fields
  
  private SymbolServiceImpl instance;

  
  // Tests

  /**
   * Test of get method, of class SymbolServiceImpl.
   */
  @Test
  public void testGet() throws KbTypeException {
    System.out.println("get");
    SymbolImpl expResult = new SymbolImpl(":CYCLIST");
    Symbol result = instance.get(":CYCLIST");
    assertEquals(expResult, result);
    System.out.println(result);
  }
  
}
