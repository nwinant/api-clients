/*
 * Copyright 2017 Cycorp, Inc..
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
package com.cyc.query.client.templates;

import com.cyc.query.client.templates.OeTemplateJob.TemplateJobId;

/*
 * #%L
 * File: OeTemplateListener.java
 * Project: Query Client
 * %%
 * Copyright (C) 2013 - 2018 Cycorp, Inc.
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

/**
 *
 * @author nwinant
 */
public interface OeTemplateListener {
  
  void onEvent(TemplateJobId jobId, ToeTemplateEventType eventType);
  
  void onResults(TemplateJobId jobId, OeTemplateResults results);
  
  void onError(TemplateJobId jobId, Throwable error);
  
  //====|    ToeTemplateEventType    |============================================================//
  
  public enum ToeTemplateEventType {
    PROCESSING_BEGUN,
    QUERY_COMPLETE,
    PROCESSING_COMPLETE
  }
  
}
