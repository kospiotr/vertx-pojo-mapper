/*
 * #%L
 * vertx-pojo-mapper-common
 * %%
 * Copyright (C) 2015 Braintags GmbH
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */

package de.braintags.io.vertx.pojomapper.mongo;

import de.braintags.io.vertx.pojomapper.IDataStoreMetaData;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * 
 * 
 * @author Michael Remme
 * 
 */

/**
 * 
 * 
 * @author Michael Remme
 * 
 */
public class MongoMetaData implements IDataStoreMetaData {
  private MongoClient client;
  private JsonObject buildInfo;

  /**
   * 
   */
  public MongoMetaData(MongoClient client) {
    this.client = client;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.braintags.io.vertx.pojomapper.IDataStoreMetaData#getVersion(io.vertx.core.Handler)
   */
  @Override
  public void getVersion(Handler<AsyncResult<String>> handler) {
    if (buildInfo != null) {
      handler.handle(Future.succeededFuture(buildInfo.getString("version")));
      return;
    }
    JsonObject command = new JsonObject().put("buildInfo", 1);
    client.runCommand("buildInfo", command, result -> {
      if (result.failed()) {
        handler.handle(Future.failedFuture(result.cause()));
      } else {
        buildInfo = result.result();
        handler.handle(Future.succeededFuture(buildInfo.getString("version")));
      }
    });
  }

}
