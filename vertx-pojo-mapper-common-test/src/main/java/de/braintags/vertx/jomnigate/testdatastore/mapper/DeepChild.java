/*
 * #%L
 * vertx-pojo-mapper-common-test
 * %%
 * Copyright (C) 2017 Braintags GmbH
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */
package de.braintags.vertx.jomnigate.testdatastore.mapper;

public class DeepChild {
  public String name;
  public DeeperChild deeperChild;

  public DeepChild() {

  }

  public DeepChild(String name) {
    this.name = name;
    this.deeperChild = new DeeperChild("deeper " + name);
  }

}
