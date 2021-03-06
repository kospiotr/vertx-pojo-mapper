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
package de.braintags.vertx.jomnigate.testdatastore.mapper.typehandler;

import java.util.ArrayList;
import java.util.Collection;

import de.braintags.vertx.jomnigate.annotation.Entity;

/**
 * 
 * 
 * @author Michael Remme
 * 
 */
@Entity
public class CollectionRecord extends BaseRecord {
  public Collection<String> collection = new ArrayList<>();
  private Collection<String> collection2 = null;

  public CollectionRecord() {
    collection.add("Eins");
    collection.add("Zwei");
    collection.add("Drei");

  }

  /**
   * @return the collection2
   */
  public Collection<String> getCollection2() {
    if (collection2 == null) {
      collection2 = new ArrayList();
      collection2.add("Eins");
      collection2.add("Zwei");
      collection2.add("Drei");
    }
    return collection2;
  }

  /**
   * @param collection2
   *          the collection2 to set
   */
  public void setCollection2(Collection<String> collection2) {
    this.collection2 = collection2;
  }

}
